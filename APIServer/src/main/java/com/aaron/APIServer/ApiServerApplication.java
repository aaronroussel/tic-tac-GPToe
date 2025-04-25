package com.aaron.APIServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiServerApplication {

    public static void main(String[] args) {
        Data data = new Data(44);
        System.out.println(data.getData());
        SpringApplication.run(ApiServerApplication.class, args);
    }
}

class Data {

    private int data;

    Data() {
        this.data = 0;
    }

    Data(int value) {
        this.data = value;
    }

    public int getData() {
        return this.data;
    }

    public void doNothing() {}
}
