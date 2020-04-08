package ie.cduffy.trackmypints.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pintdata")
public class PintData {

    @Id
    private String id;

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
