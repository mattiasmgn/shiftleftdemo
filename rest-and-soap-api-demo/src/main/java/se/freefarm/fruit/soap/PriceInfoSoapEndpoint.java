package se.freefarm.fruit.soap;


import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import se.freefarm.fruit.PriceDetailsRequest;
import se.freefarm.fruit.PriceDetailsResponse;
import se.freefarm.fruit.PriceInformationDTO;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Endpoint
public class PriceInfoSoapEndpoint {

    @Value("${rate_service_url}")
    private String rate_service_url;
    final static int NAME = 0, PRICE = 1, WHIGHT = 2;

        @PayloadRoot(namespace = "http://www.freefarm.se/fruit/", localPart = "PriceDetailsRequest")
        @ResponsePayload
        public PriceDetailsResponse getPrice(@RequestPayload PriceDetailsRequest request) {
            String fruit = request.getFruit();
            Integer numberOfFruit = request.getUnits();

            System.out.println("soap request for "+ fruit + ", "+ numberOfFruit);

            double kgPrice=getFruitPrice(fruit);
            double unitWhight=getFruitWeight(fruit);
            double unitPrice=kgPrice*unitWhight;
            double unitPriceVAT = addVAT(unitPrice);
            double price = numberOfFruit*unitPriceVAT;
            double rate= currencyConversion("SEK","NOK");
            double priceNOK = price/rate;
            PriceDetailsResponse response = new PriceDetailsResponse();
            PriceInformationDTO dto = new PriceInformationDTO();
            dto.setCurrency("NOK");
            dto.setFruit(fruit);
            dto.setPrice(priceNOK);
            dto.setUnits(numberOfFruit);
            dto.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
            response.setPriceInformation(dto);
            return response;
        }

    double getFruitPrice(String fruit) {
        return getFruitData(fruit, PRICE);
    }

    double getFruitWeight(String fruit) {
        return getFruitData(fruit, WHIGHT);
    }

    Double getFruitData(String fruit, int column) {
        String priceDB [][] = {
                // name, price, unit weight
                {"apple","40","0.100"},
                {"banana","70","0.150"}
        };
        for (int i=0;i<priceDB.length;i++){
            if (fruit.equals(priceDB[i][NAME]))
                return new Double(priceDB[i][column]).doubleValue();
        }
        System.out.println("could not find "+fruit);
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
        Double rate = new Double(nok);
        return rate;

    }


}
