package com.pentalearn.pentalearnstart.model;

/**
 * Created by pglg on 04-04-2016.
 */
public class Person {

    private Integer id;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {

        return id;
    }

    private  String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

}
