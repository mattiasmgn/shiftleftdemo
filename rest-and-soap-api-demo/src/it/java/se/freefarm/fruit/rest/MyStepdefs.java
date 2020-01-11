package se.freefarm.fruit.rest;

import cucumber.api.java8.En;
import gherkin.deps.com.google.gson.Gson;
import org.junit.Assert;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import kong.unirest.Unirest;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.RegularExpressionValueMatcher;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

public class MyStepdefs implements En {
    String reqResult;
    public MyStepdefs() {
        When("^GET request for \"([^\"]*)\" \"([^\"]*)\" to the price method is executed$", (String units, String name) -> {
                reqResult = Unirest.get("http://localhost:8118/priceinfoNOK/{units}/{name}")
                        .routeParam("units", units)
                        .routeParam("name", name)
                        .asString().getBody();
                System.out.println("\nreqResult="+reqResult);
        });
        Then("^response from fruit price method should be correct$", (String expected) -> {

            JSONAssert.assertEquals(expected, reqResult, false); // Match exact response
/*
            CustomComparator customComparator =
                    new CustomComparator(
                            JSONCompareMode.STRICT,
                            new Customization(
                                    "price",
                                    new RegularExpressionValueMatcher<>("^\\d*\\.\\d+|\\d+\\.\\d*$") // Match decimal number
                            )
                    );
            JSONAssert.assertEquals(expected, reqResult, customComparator);

 */
            System.out.println("JSONAssert OK");

        });
    }
  }

