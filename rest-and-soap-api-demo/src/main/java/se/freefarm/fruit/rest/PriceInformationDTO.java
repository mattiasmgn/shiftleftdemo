package se.freefarm.fruit.rest;

public class PriceInformationDTO {
    String fruit;
    Integer units;
    Double price;
    String currency;

/*
    String date;
    public String getDate() {return date;}
    public void setDate(String date) {this.date = date;}
*/

    public PriceInformationDTO() {
    }

    public PriceInformationDTO(String fruit, Integer units, Double price, String currency) {
        this.fruit = fruit;
        this.units = units;
        this.price = price;
        this.currency = currency;
    }




    public String getFruit() {
        return fruit;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

