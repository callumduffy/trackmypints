package ie.cduffy.trackmypints;

import org.springframework.data.annotation.Id;

public class PintData {

    @Id
    private String name;

    private Double priceTotal;
    private int count;

    public PintData(Double price){
        this.priceTotal = price;
        count = 1;
    }

    //TODO sketch out all fucntionality for this model
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
