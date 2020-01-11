package se.freefarm.fruit.restclent;

import kong.unirest.Unirest;

public class FruitRestClient {

    public int port = 8118;

    public FruitRestClient(int port) {
        this.port = port;
        System.out.println("Using port "+port);
    }
    public FruitRestClient() {
    }
    PriceInformationDTO getPriceInformation(int units, String name){
        System.out.println("Sending request for "+units+" "+name);
        String url = "http://localhost:"+port+"/priceinfoNOK/"+units+"/"+name;
        System.out.println("Using url "+url);
        String rslt = Unirest.get(url).asString().getBody();
        PriceInformationDTO p= Unirest.get(url)
                .asObject(PriceInformationDTO.class)
                .getBody();
        System.out.println("Resulting PriceInformationDTO:");
        p.dump();
        return p;

    }
    public static void main(String args[]){
        PriceInformationDTO p = new FruitRestClient().getPriceInformation(2, "apple");
        System.out.println(p.getPrice());

    }
}
