package com.aplication.CurrencyCalculator;


import com.aplication.CurrencyCalculator.model.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class CalculatorController {
    @Autowired
    private CalculatorService calculatorService;


    @RequestMapping(value = "/api/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<String>> list(HttpServletRequest request) {
        String url = String.valueOf((request.getRequestURL()));
        calculatorService.saveRequest(url);

        Collection<Rate> rates = calculatorService.getRates();
        if (rates == null) return new ResponseEntity<Collection<String>>(HttpStatus.BAD_REQUEST);

        Collection<String> w = rates.stream().map(x -> x.getCode()).collect(Collectors.toList());

        return new ResponseEntity<Collection<String>>(w, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/prices", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Map<String,Float>> prices(HttpServletRequest request) {
        String url = String.valueOf((request.getRequestURL()));
        calculatorService.saveRequest(url);

        Collection<Rate> rates = calculatorService.getRates();
        if (rates == null) return new ResponseEntity<Map<String,Float>>(HttpStatus.BAD_REQUEST);

        Map<String,Float> w = rates.stream().collect(Collectors.toMap(Rate::getCode,Rate::getMid ));

        return new ResponseEntity<Map<String,Float>>(w, HttpStatus.OK);
    }


    @RequestMapping( value="/api/calculate",  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BigDecimal> calculate(HttpServletRequest request,@RequestParam String from,
                                                @RequestParam String to, @RequestParam BigDecimal value ) {
        String url = String.valueOf((request.getRequestURL()));
        calculatorService.saveRequest(url+"?from="+from+"&to="+to+"&value="+value);

        Rate rateFrom=calculatorService.getRate(from);
        Rate rateTo=calculatorService.getRate(to);
        if(rateFrom == null || rateTo == null){
            return new ResponseEntity<BigDecimal>(HttpStatus.BAD_REQUEST);
        }
        value=value.multiply(BigDecimal.valueOf((rateFrom.getMid()/rateTo.getMid())));
        BigDecimal displayValue = value.setScale(2, RoundingMode.HALF_DOWN);
        return new ResponseEntity<BigDecimal>(displayValue, HttpStatus.OK);
    }
}
