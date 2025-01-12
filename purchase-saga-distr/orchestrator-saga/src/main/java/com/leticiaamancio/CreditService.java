package com.leticiaamancio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.apache.camel.Header;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@ApplicationScoped
@RegisterRestClient(baseUri = "http://localhost:8081/credit")
public interface CreditService {

    @GET
    @Path("order-amount")
    @Produces(MediaType.TEXT_PLAIN)
    void newOrderAmount(@QueryParam("orderId") @Header("orderId") Long orderId, @QueryParam("value")  @Header("value") int value);

    @DELETE
    @Path("order-amount")
    @Produces(MediaType.TEXT_PLAIN)
    void cancelOrderAmount(@QueryParam("id")  @Header("id") Long id);

    @GET
    @Path("total-credit")
    @Produces(MediaType.TEXT_PLAIN)
    int getTotalCredit();
}