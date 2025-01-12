
# Saga Pattern Applications with Quarkus and Apache Camel

This repository contains two applications that implement the Saga Pattern using Quarkus and Apache Camel. Both applications handle distributed transactions but differ in their architectural approach.

## Project 1: Monolithic Saga Pattern

### Description
This application implements the Saga Pattern with all services integrated within a single monolithic application. It leverages Quarkus for high-performance Java execution and Apache Camel for routing and message handling.

### Technologies Used

* Quarkus: A Kubernetes-native Java framework for building lightweight applications.
* Apache Camel: An open-source integration framework for routing and mediation rules.
* Saga Pattern: Manages transactions by coordinating a series of local transactions.

### Key Features

* Centralized transaction management.
* Simplified service communication.
* Error handling through compensating actions.

### Running the Application

```
mvn quarkus:dev
```

### cURL
```
curl localhost:8080/purchase-camel/saga
```