package com.aplication.CurrencyCalculator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String method;

    public Request(String name) {
        this.name = name;
        this.method = "GET";
    }
    public Request(String name, String method) {
        this.name = name;
        this.method = method;
    }

}
