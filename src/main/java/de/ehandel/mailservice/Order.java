// Java Program to Illustrate EmailDetails Class

package de.ehandel.mailservice;

// Importing required classes
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor

// Class
public class Order {

    // Class data members
    private String id;
    private String name;
    private double price;
    private double quantity;
    private double salePrice;
    private String slug;
    private String thumbnail;
}
