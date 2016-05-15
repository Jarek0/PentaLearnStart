package pl.pollub.cs.pentalearn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wojciech on 2016-04-24.
 * Main Exercise class responsible for multiply choice test
 * Exercise contains Question, and Task contains Answer
 */
@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    private Chapter chapter;

    @NotNull
    @Size(min = 4,max = 64)
    private String title;

    @NotNull
    @OneToMany(mappedBy = "exercise",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Question>questions=new ArrayList<>();

    private Exercise(){}

    public Exercise(Chapter chapter, String title) {
        this.chapter = chapter;
        this.title = title;
    }

    public void addQuestion(Question question){
        this.questions.add(question);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
