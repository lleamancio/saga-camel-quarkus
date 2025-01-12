package com.leticiaamancio;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.Header;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class CreditService {

    private int totalCredit;
    private Map<Long, Integer> orderAmount = new HashMap<>();

    public CreditService() {
        this.totalCredit = 100;
    }

    public void newOrderAmount(@Header("id") final Long orderId, @Header("amount") final int amount){
        if(amount > totalCredit){
            throw new IllegalStateException("Amount must be less than total credit");
        }

        totalCredit -= amount;
        orderAmount.put(orderId, amount);
    }

    public void cancelOrderAmount(@Header("id") final Long orderId){
        System.out.println("Failed order. Initiating order cancellation.");
    }

    public int getTotalCredit() {
        return totalCredit;
    }
}