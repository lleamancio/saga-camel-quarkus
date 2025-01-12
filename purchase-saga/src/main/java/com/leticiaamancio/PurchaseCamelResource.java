package com.leticiaamancio;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.camel.CamelContext;

@Path("purchase-camel")
public class PurchaseCamelResource {
    @Inject
    CamelContext camelContext;

    @GET
    @Path("saga")
    @Produces(MediaType.TEXT_PLAIN)
    public Response saga() {
        try {
            Long id = 0L;
            purchase(++id, 20);
            purchase(++id, 30);
            purchase(++id, 30);
            purchase(++id, 25);

            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(500).build();
        }
    }

    private void purchase(Long id, int amount) {
        System.out.println("Order: " + id + " amount: " + amount);
        try {
            camelContext.createFluentProducerTemplate()
                    .to("direct:saga")
                    .withHeader("id", id)
                    .withHeader("amount", amount)
                    .request();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
