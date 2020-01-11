package se.freefarm.fruit.rest;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class PriceInfoController2 {

    @Value("${rate_service_url}")
    private String rate_service_url;

    final static int NAME = 0, PRICE = 1, WHIGHT = 2;

    @RequestMapping("/priceinfoNOK/{numberOfFruit}/{fruit}")
    PriceInformationDTO getPrice(@PathVariable Integer numberOfFruit, @PathVariable String fruit) {
        System.out.println("Version-2 REST request for "+ numberOfFruit+ " "+fruit);
        double kgPrice=getFruitPrice(fruit);
        double unitWhight=getFruitWeight(fruit);
        double unitPrice=kgPrice*unitWhight;
        double unitPriceVAT = addVAT(unitPrice);
        double price = numberOfFruit*unitPriceVAT;
        double rate= currencyConversion("SEK","NOK");
        double priceNOK = price / rate;
        priceNOK = priceNOK - (priceNOK * getDiscount(numberOfFruit));
        PriceInformationDTO p = new PriceInformationDTO(fruit,numberOfFruit,priceNOK,"NOK");
        p.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        return p;
    }

    double getDiscount(Integer numberOfFruit) {
        if (numberOfFruit>=3) {
            return 0.4;
        }
        return 0;
    }

    double getFruitPrice(String furit) {
        return getFruitData(furit, PRICE);
    }

    double getFruitWeight(String furit) {
        return getFruitData(furit, WHIGHT);
    }

    Double getFruitData(String furit, int column) {
        String priceDB [][] = {
                // name, price, unit weight
                {"apple","40","0.100"},
                {"banana","70","0.150"}
        };
        for (int i=0;i<priceDB.length;i++){
            if (furit.equals(priceDB[i][NAME]))
                return new Double(priceDB[i][column]).doubleValue();
        }
        System.out.println("could not find "+furit);
        return null;
    }

    double addVAT(double price) {
        return price*1.25;
    }

    double currencyConversion(String from, String to) {
        HttpResponse<JsonNode> response = Unirest.get(rate_service_url+"/latest")
                .queryString("symbols", from)
                .queryString("symbols", to)
                .queryString("base", from)
                .asJson();
        JSONObject rates = response.getBody().getObject().getJSONObject("rates");
        String nok = rates.get("NOK").toString();
        System.out.println("rate="+nok);
        Double rate = new Double(nok);
        return rate;

    }


}

// http://localhost:8118/priceinfo/2/apple