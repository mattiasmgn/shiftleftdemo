package se.freefarm.fruit.prg;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

public class Main7 {
    final static int NAME=0, PRICE=1, WHIGHT=2;
    public static void main( String[] args ) {
        Main7 m = new Main7();
        int numberOfFruit=2;
        String fruit="apple";
        double cost =m.getPrice(numberOfFruit, fruit);
        System.out.println("The cost of "+ numberOfFruit + " "+ fruit + " is "+cost );

        double rate= m.currencyConversion("SEK","NOK");
        double priceNOK = cost/rate;
        System.out.println("The cost of "+ numberOfFruit + " "+ fruit + " is "+  String.format("%.2f", priceNOK)  + " in NOK" );


        numberOfFruit=3;
        fruit="banana";
        cost =m.getPrice(numberOfFruit, fruit);
        System.out.println("The cost of "+ numberOfFruit + " "+ fruit + " is "+cost );


    }
    double getPrice(int numberOfFruit, String fruit) {
        double kgPrice=getFruitPrice(fruit);
        double unitWhight=getFruitWeight(fruit);
        double unitPrice=kgPrice*unitWhight;
        double unitPriceVAT = addVAT(unitPrice);
        double price = numberOfFruit*unitPriceVAT;
        return price;
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
        return null;
    }

    double addVAT(double price) {
        return price*1.25;
    }

    double currencyConversion(String from, String to) {
        HttpResponse<JsonNode> response = Unirest.get("https://api.exchangeratesapi.io/latest")
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
