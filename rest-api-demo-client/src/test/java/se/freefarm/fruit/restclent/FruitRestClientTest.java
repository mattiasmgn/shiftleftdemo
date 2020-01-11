package se.freefarm.fruit.restclent;


import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;

import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;


public class FruitRestClientTest {
    @Rule
    public PactProviderRuleMk2 provider = new PactProviderRuleMk2("FruitRestService", "localhost", 8118, this);

    @Pact(consumer = "FruitRestClient")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap();
        headers.put("Content-Type", "application/json");


        DslPart etaResults = new PactDslJsonBody()
                .decimalType("price",1.234)
                .asBody();

        return builder
                .given("There is a fruit named apple in the store")
                .uponReceiving("A request for 2 apples")
                .path("/priceinfoNOK/2/apple")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body(etaResults).toPact();

    }

    @Test
    @PactVerification()
    public void doTest() {
        System.setProperty("pact.rootDir","../pacts");  // Change output dir for generated pact-files
        PriceInformationDTO p = new FruitRestClient(provider.getPort()).getPriceInformation(2, "apple");
        System.out.println("According to test 2 apples price ="+p.getPrice());
        assertTrue(p.getPrice() >= 0);
    }

}
