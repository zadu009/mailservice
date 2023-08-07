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
    private String name;
    private double price;
    private double salePrice;
    private String thumbnail;
    private double quantity;
}
