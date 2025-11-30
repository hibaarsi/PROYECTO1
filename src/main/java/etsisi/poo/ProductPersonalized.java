package etsisi.poo;

import java.util.ArrayList;
import java.util.List;

public class ProductPersonalized extends RegularProduct implements Personalized {

    private final int maxPersonal;
    private List<String> personalizationList = new ArrayList<>();

    public ProductPersonalized(int id, String name, Category category, double price, int maxPersonal) {
        super(id, name, category, price);
        if (maxPersonal < 1) {
            System.out.println("maxPersonal must be >= 1");
        }
        this.maxPersonal = maxPersonal;
    }

    public void setPersonalizations(List<String> list) {
        personalizationList = new ArrayList<>(list);
    }

    public List<String> getPersonalizations() {
        return personalizationList;
    }

    @Override
    public double getPrice() {
        return getPriceWithTexts(getBasePrice(), 1, personalizationList);
    }
    public double getBasePrice(){
        return super.getPrice();

    }

    @Override
    public int getMaxPersonal() {
        return maxPersonal;
    }

    @Override
    public String toString() {
        double price= getPrice();
       if(personalizationList.isEmpty()) {
            return String.format(java.util.Locale.US, "{class:ProductPersonalized, id:%d, name:'%s', category:%s, price:%.1f, maxPersonal:%d}",
                    id, name.replace("\"", ""), getCategory(), price, maxPersonal);
        }else return String.format("{class:ProductPersonalized, id:%d, name:'%s', category:%s, price:%.1f, maxPersonal:%d, personalizationList:%s}",
                id, name, getCategory(), price, maxPersonal, getPersonalizations());


    }
}
