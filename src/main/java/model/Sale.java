package model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Artyom Kulagin
 */
@Data
@AllArgsConstructor
public class Sale {
    private int id;
    private int count;
    private int goodId;


    public Sale(int id) {
        this.id = id;
    }

    public Sale(int count, int goodId) {
        this.count = count;
        this.goodId = goodId;
    }
}
