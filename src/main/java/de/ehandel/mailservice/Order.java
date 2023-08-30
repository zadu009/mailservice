// Java Program to Illustrate EmailDetails Class

package de.ehandel.mailservice;

// Importing required classes
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor

// Class
public class Order {

    // Class data members
    private String id;
    private String name;
    private String thumbnail;
    private int price;
    private int salePrice;
    private int quantity;
    private List<String> extras;
    private List<String> sossen;
    private String speziell;
    private List<PizzaExtra> pizzaextras;
}
