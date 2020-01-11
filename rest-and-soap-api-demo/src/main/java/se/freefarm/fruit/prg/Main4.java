package se.freefarm.fruit.prg;

public class Main4 {
    final static int NAME=0, PRICE=1, WHIGHT=2;
    public static void main( String[] args ) {
        Main4 m = new Main4();
        int numberOfFruit=2;
        String fruit="apple";
        double cost =m.getPrice(numberOfFruit, fruit);
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
        return null;
    }

    double addVAT(double price) {
        return price*1.25;
    }




 }
