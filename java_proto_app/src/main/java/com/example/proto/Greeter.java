package com.example.proto;

import io.grpc.examples.Greeter.HelloRequest;

public class Greeter {

    public static void main(String[] args) {
        HelloRequest helloRequest = HelloRequest.newBuilder()
                .setName("Hello World")
                .build();

        System.out.println(helloRequest);

    }
}
