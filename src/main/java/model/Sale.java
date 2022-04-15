package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author Artyom Kulagin
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sales")
public class Sale {
    @Id
    private int id;
    private int count;
    @Column(name = "good_id")
    private int goodId;


    public Sale(int id) {
        this.id = id;
    }

    public Sale(int count, int goodId) {
        this.count = count;
        this.goodId = goodId;
    }
}
