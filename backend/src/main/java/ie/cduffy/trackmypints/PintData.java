package ie.cduffy.trackmypints;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pintdata")
public class PintData {

    @Id
    private String id;

    private String name;
    private Double priceTotal;
    private int count;

    public PintData(String name, Double priceTotal){
        this.name = name;
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

    public String getName(){
        return this.name;
    }
}
