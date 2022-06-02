package com.example.springshop.persist;

import javax.persistence.*;

@Entity
@Table(name = "shopping_items")
//Yes,I use in this project Getter and Setter, although I could use Lombok, I know it :)
public class ShoppingItem {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String Name;

    public ShoppingItem(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


}
