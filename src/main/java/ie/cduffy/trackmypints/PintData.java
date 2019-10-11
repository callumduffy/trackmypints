package ie.cduffy.trackmypints;

public class PintData {
    Double priceTotal;
    int count;

    public PintData(Double price){
        this.priceTotal = price;
        count = 1;
    }

    public void increment(Double price){
        this.priceTotal += price;
        this.count +=1;
    }

    public void multiIncrement(Double price, int count){
        this.priceTotal += (price * count);
        this.count += count;
    }

    public Double getAveragePrice(){
        return priceTotal/count;
    }
}
