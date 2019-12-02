package com.aplication.CurrencyCalculator;

import com.aplication.CurrencyCalculator.model.Rate;
import com.aplication.CurrencyCalculator.model.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@Service
public class CalculatorService {
    @Autowired
    private RequestRepository repository;


    public void saveRequest(String url){
        Request request = new Request(url);
        repository.save(request);

    }
    public  Collection<Rate> getRates()
    {
        final String url = "http://api.nbp.pl/api/exchangerates/tables/A";
        RestTemplate restTemplate = new RestTemplate();
        Table[] tables;
        try {
            tables = restTemplate.getForObject(url, Table[].class);
            saveRequest(url);
        }catch(HttpClientErrorException e){
            System.out.println(e);
            return null;
        }

        return tables[0].getRates();
    }


    public  Rate getRate(String currency)
    {
        final String url = "http://api.nbp.pl/api/exchangerates/rates/A/"+currency;
        RestTemplate restTemplate = new RestTemplate();
        Table table;
        try {
            table = restTemplate.getForObject(url, Table.class);
            saveRequest(url);
        } catch(HttpClientErrorException e){
            System.out.println(e);
            return null;
        }

        return table.getRates().get(0);
    }



}
