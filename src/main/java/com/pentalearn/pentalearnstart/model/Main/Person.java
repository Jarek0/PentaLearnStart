package com.pentalearn.pentalearnstart.model.Main;

/**
 * Created by pglg on 04-04-2016.
 */
public class Person {
    private Integer id;
    private String name;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void setId(Integer id) {this.id = id;}
    public Integer getId() {return id; }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {return name;}
}
