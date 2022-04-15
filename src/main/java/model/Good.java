package model;

import lombok.Data;

import javax.persistence.*;


/**
 * @author Artyom Kulagin
 */
@Data
@Entity
@Table(name = "goods")
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int price;

    public Good() {
    }

    public Good(int id) {
        this.id = id;
    }

    public Good(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Good(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
