package com.leticiaamancio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.SagaPropagation;
import org.apache.camel.saga.CamelSagaService;
import org.apache.camel.saga.InMemorySagaService;

@ApplicationScoped
public class SagaRoute extends RouteBuilder {
    @Inject
    OrderService orderService;

    @Inject
    CreditService creditService;

    @Override
    public void configure() throws Exception{
        CamelSagaService sagaService = new InMemorySagaService();
        getContext().addService(sagaService);

        from("direct:saga")
                .saga()
                .propagation(SagaPropagation.REQUIRES_NEW)
                .log("Starting new order")
                .to("direct:newOrder").log("Create new order with id ${header.id}. Saga ${body}.")
                .to("direct:newOrderAmount").log("Reserving credit ${header.valor} Order ${header.id}")
                .to("direct:finish").log("Finished order");

        // Order Service
        from("direct:newOrder")
                .saga()
                .propagation(SagaPropagation.MANDATORY)
                .compensation("direct:cancelOrder")
                .transform().header(Exchange.SAGA_LONG_RUNNING_ACTION)
                .bean(orderService, "newOrder")
                .log("Order created ${header.id}");

        from("direct:cancelOrder")
                .transform().header(Exchange.SAGA_LONG_RUNNING_ACTION)
                .bean(orderService, "cancelOrder")
                .log("Order ${body} cancelled");

        // Credit Service
        from("direct:newOrderAmount")
                .saga()
                .propagation(SagaPropagation.MANDATORY)
                .compensation("direct:cancelOrderAmount")
                .transform().header(Exchange.SAGA_LONG_RUNNING_ACTION)
                .bean(creditService, "newOrderAmount")
                .log("Debited ${body} credit");

        from("direct:cancelOrderAmount")
                .transform().header(Exchange.SAGA_LONG_RUNNING_ACTION)
                .bean(creditService, "cancelOrderAmount")
                .log("Debited ${body} credit");

        // Finish
        from("direct:finish")
        .saga().propagation(SagaPropagation.MANDATORY)
                .choice()
                .end();

    }
}
