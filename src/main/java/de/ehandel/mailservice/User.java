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
public class User {

    // Class data members
    private String firstname;
    private String name;
    private String street;
    private String streetnumber;
    private String zipcode;
    private String country;
    private String city;
}
