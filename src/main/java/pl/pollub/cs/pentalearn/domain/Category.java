package pl.pollub.cs.pentalearn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pglg on 24-04-2016.
 */
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 64)
    private String name;

    @NotNull
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Course> courses=new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    private Category() {
    }

    public void addCourse(Course course){
       courses.add(course);
    }

    @JsonIgnore
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "QuestionCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
