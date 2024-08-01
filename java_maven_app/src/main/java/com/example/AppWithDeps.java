package com.example;

import com.google.common.collect.ImmutableList;

public class AppWithDeps {
    public static void main(String[] args) {
        var fromGuava = ImmutableList.of("Hello", "World!");
        System.out.println(fromGuava);
    }
}
