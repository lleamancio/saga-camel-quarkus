package com.leticiaamancio;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("order")
public class OrderResource {
    @Inject
    OrderService orderService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public void newOrder(@QueryParam("id") Long id) {
        orderService.newOrder(id);
        System.out.println("New order id: " + id);
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public void cancelOrder(@QueryParam("id") Long id) {
        orderService.cancelOrder(id);
        System.out.println("Cancel order id: " + id);
    }
}
