package pl.pollub.cs.pentalearn.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by pglg on 24-04-2016.
 */
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Exercise exercise;

    @NotNull
    @Size(max = 64)
    private String questionText;

    @OneToMany(mappedBy = "question")
    @JsonIgnore
    @ApiModelProperty(hidden = true, example = "Use only this field to construct question")
    private List<AnswerSet> answerSets;

    public Question(String questionText, Exercise exercise) {
        this.questionText = questionText;
        this.exercise = exercise;
    }

    private Question() {
    }

    @JsonCreator
    public Question(@JsonProperty("questionText") String questionText) {
        this.questionText = questionText;
    }

    @JsonIgnore
    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Long getId() {
        return id;
    }

    public String getQuestionText() {
        return questionText;
    }


    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnswerSet getCorrectAnswerSet() {
        for (AnswerSet set : answerSets) {
            if (!set.getUserAnswerSet()) return set;
        }
        return null;
    }


    public List<AnswerSet> getAnswerSets() {
        return answerSets;
    }

    public void setAnswerSets(List<AnswerSet> answerSets) {
        this.answerSets = answerSets;
    }

}
