package com.example.springframework.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class MyModel implements Serializable, Comparable<MyModel> {

    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String title;
    @Column(length = 1000000)
    @Lob
    private String content;
    @Column
    private long creationTimestamp;

    public MyModel() {
        this.creationTimestamp = System.currentTimeMillis();
    }

    @Override
    public int compareTo(MyModel that) {
        return Long.compare(this.creationTimestamp, that.creationTimestamp);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}