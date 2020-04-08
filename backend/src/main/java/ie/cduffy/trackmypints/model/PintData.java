package ie.cduffy.trackmypints.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class PintData {

    private Double priceTotal;
    private int count;

    public PintData(Double priceTotal){
        this.priceTotal = priceTotal;
        this.count = 1;
    }

    public Double getPriceTotal(){
        return this.priceTotal;
    }

    public int getCount(){
        return this.count;
    }

    public void increment(Double price){
        this.priceTotal += price;
        this.count += 1;
    }
}
