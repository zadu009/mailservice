package de.ehandel.mailservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PizzaExtraItem {
    private String name;
    private boolean selected;
    private int price;
}
