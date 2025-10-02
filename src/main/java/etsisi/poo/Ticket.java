package etsisi.poo;

import java.util.ArrayList;
import java.util.Iterator;

public class Ticket {
    private ArrayList<Product> products;
    private Catalogo catalogo;
    private static final int MAX_PRODUCTOS=100;

    public Ticket(Catalogo catalogo) {
        this.products = new ArrayList<>();
        this.catalogo = catalogo;
    }
    public void newTicket(){
        products.clear();
    }
   public void addProduct(int productId,int cant){
        if(catalogo.existProduct(productId)){
            throw new IllegalArgumentException("Product with id " + productId + " does not exist");
        }
        if(products.size() + cant >=MAX_PRODUCTOS)
            throw new IllegalArgumentException("You can't add more products");
        Product product = catalogo.getProduct(productId);
        for(int i=0;i<cant; i++) {
            products.add(product);
        }
   }
   public void removeProduct(int productId){
        if (!catalogo.existProduct(productId)){
            System.out.println("The product doesn't exist in this ticket");
        }{
           Iterator<Product> iterator =  products.iterator();
           while (iterator.hasNext()){
               Product p = iterator.next();
               if (p.getId() == productId){
                   iterator.remove();
               }
           }
       }
   }
   //importe provisional, imprimir tickrt, updateticket,

    public void printTicket(){
        if(products.isEmpty()){
            System.out.println("Esta vacio");
        }
        products.sort((p1, p2)->p1.getNombre().compareToIgnoreCase(p2.getNombre()));

        for(int i = 0; i< products.size(); i++){
            Product p= products.get(i);
            int cont=0;

            for(int j = 0; j< products.size(); j++){
                if (products.get(j).getCategory() == p.getCategory()) {
                    cont++;
                }
            }
            System.out.println("El producto "+p.getNombre()+" pertecene a la categoria "+p.getCategory()+" y hay en total: "+cont);
        }


    }

}
