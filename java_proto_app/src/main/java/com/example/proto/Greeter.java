package com.example.proto;

import io.grpc.examples.Greeter.HelloRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class Greeter {



    public static void main(String[] args) throws IOException {
        HelloRequest helloRequest = HelloRequest.newBuilder()
                .setName("Hello World")
                .build();

        List<HelloRequest> summaries = List.of(
                helloRequest
        );
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ParquetHelper.writeParquet(summaries.iterator(), HelloRequest.class, baos);

        System.out.println(helloRequest);

    }
}
