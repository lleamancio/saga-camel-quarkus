package com.leticiaamancio;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("credit")
public class CreditResource {
    @Inject
    CreditService creditService;

    @GET
    @Path("order-amount")
    @Produces(MediaType.TEXT_PLAIN)
    public Response newOrderAmount(@QueryParam("order-id") Long orderId, @QueryParam("amount") int amount) {
        try {
            creditService.newOrderAmount(orderId, amount);

            return Response.ok().entity("debited amount").build();
        }  catch(IllegalStateException e){
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity("Something went wrong").build();
        }
    }


    @DELETE
    @Path("order-amount")
    @Produces(MediaType.TEXT_PLAIN)
    public void cancelOrderAmount(@QueryParam("id") Long id){
        creditService.cancelOrderAmount(id);
    }

    @GET
    @Path("total-credit")
    @Produces(MediaType.TEXT_PLAIN)
    public int getTotalCredit(){
        return creditService.getTotalCredit();
    }

}
