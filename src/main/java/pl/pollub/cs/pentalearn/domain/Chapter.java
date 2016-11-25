package pl.pollub.cs.pentalearn.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Chapter class, contains one lecture and one exercise
 * Created by Wojciech on 2016-05-02.
 */

@Entity
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Course course;

    @NotNull
    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lecture> lectures = new ArrayList<>();

    @NotNull
    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Exercise> exercises = new ArrayList<>();

    @NotNull
    @Size(max = 32)
    private String name;

    @NotNull
    @Size(max = 64)
    private String description;


    public Chapter(String name, String description, Course course) {
        this.name = name;
        this.description = description;
        this.course = course;
    }

    private Chapter() {
    }

    @JsonCreator
    public Chapter(@JsonProperty("name")
                   @NotEmpty
                   @Size(max = 64)
                           String name,
                   @JsonProperty("description")
                   @NotEmpty
                           String description) {
        this.name = name;
        this.description = description;
    }

    public void addLecture(Lecture lecture) {
        this.lectures.add(lecture);
    }

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @JsonIgnore
    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    @JsonIgnore
    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
