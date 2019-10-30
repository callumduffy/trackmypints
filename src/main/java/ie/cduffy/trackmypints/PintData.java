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

    public int getPintsDrank(){
        return this.count;
    }

    public void multiIncrement(Double price, int count){
        this.priceTotal += (price * count);
        this.count += count;
    }

    public Double getAveragePrice(){
        return priceTotal/count;
    }

    public String getName(){
        return this.name;
    }
}
