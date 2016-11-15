package pl.pollub.cs.pentalearn.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;

    @ManyToOne
    @ApiModelProperty(hidden = true)
    private Chapter chapter;

    @NotNull
    @Size(min = 4,max = 64)
    private String title;

    @NotNull
    @ApiModelProperty(hidden = true)
    @OneToMany(mappedBy = "exercise",cascade = CascadeType.ALL,fetch=FetchType.LAZY,targetEntity = Question.class)
    private List<Question>questions=new ArrayList<>();

    @NotNull
    @ApiModelProperty(hidden = true)
    @OneToMany(mappedBy = "exercise",cascade = CascadeType.ALL,fetch = FetchType.LAZY,targetEntity = UserExercise.class)
    private List<UserExercise> userExercises = new ArrayList<>();


    public Exercise(Chapter chapter, String title) {
        this.chapter = chapter;
        this.title = title;
    }

    private Exercise(){}

    @JsonCreator
    public Exercise(@JsonProperty("title") @NotEmpty @Size(max = 64)String title){
        this.title=title;
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

    @JsonIgnore
    public List<UserExercise> getUserExercises() {
        return userExercises;
    }

    public void setUserExercises(List<UserExercise> userExercises) {
        this.userExercises = userExercises;
    }
}
