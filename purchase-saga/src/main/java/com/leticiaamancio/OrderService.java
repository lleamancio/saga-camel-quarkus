package com.leticiaamancio;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.Header;

import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class OrderService {

    private Set<Long> orders = new HashSet<>();

    public void newOrder(@Header("id") final Long id) {
        orders.add(id);
    }

    public void cancelOrder(@Header("id") final Long id) {
        orders.remove(id);
    }

}
