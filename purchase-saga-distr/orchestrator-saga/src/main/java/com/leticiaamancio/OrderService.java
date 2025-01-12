package com.leticiaamancio;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.apache.camel.Header;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@ApplicationScoped
@RegisterRestClient(baseUri = "http://localhost:8082/order")
public interface OrderService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    void newOrder(@QueryParam("id") @Header("id") Long id);

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    void cancelOrder(@QueryParam("id") @Header("id") Long id);

}
