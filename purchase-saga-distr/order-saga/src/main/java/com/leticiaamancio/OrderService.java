package com.leticiaamancio;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class OrderService {

    private Set<Long> orders = new HashSet<>();

    public void newOrder(final Long id) {
        orders.add(id);
    }

    public void cancelOrder(final Long id) {
        orders.remove(id);
    }

}
