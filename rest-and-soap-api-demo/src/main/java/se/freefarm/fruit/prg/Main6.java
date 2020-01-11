package se.freefarm.fruit.prg;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

public class Main6 {
    final static int NAME=0, PRICE=1, WHIGHT=2;
    public static void main( String[] args ) {
        Main6 m = new Main6();
        int numberOfFruit=2;
        String fruit="apple";
        double kgPrice=0;
        String priceDB [][] = {
                // name, price, unit weight
                {"apple","40","0.100"},
                {"banana","70","0.150"}
        };
        for (int i=0;i<priceDB.length;i++){
            if (fruit.equals(priceDB[i][NAME])) {
                kgPrice = new Double(priceDB[i][PRICE]).doubleValue();
            }
        }
        
        double unitWhight=0;
        for (int i=0;i<priceDB.length;i++){
            if (fruit.equals(priceDB[i][NAME])) {
                unitWhight = new Double(priceDB[i][WHIGHT]).doubleValue();
            }
        }

        double unitPrice=kgPrice*unitWhight;
        double unitPriceVAT = unitPrice*1.1;
        double price = numberOfFruit*unitPriceVAT;

        System.out.println("The cost of "+ numberOfFruit + " "+ fruit + " is "+price );

        HttpResponse<JsonNode> response = Unirest.get("https://api.exchangeratesapi.io/latest")
                .queryString("symbols", "SEK")
                .queryString("symbols", "NOK")
                .queryString("base", "SEK")
                .asJson();
        JSONObject rates = response.getBody().getObject().getJSONObject("rates");
        String nok = rates.get("NOK").toString();
        double rate = new Double(nok);

        double priceNOK = price/rate;
        System.out.println("The cost of "+ numberOfFruit + " "+ fruit + " is "+  String.format("%.2f", priceNOK)  + " in NOK" );


    }
 }
