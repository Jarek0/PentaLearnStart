package pl.pollub.cs.pentalearn.domain;

import org.aspectj.weaver.ast.Test;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wojciech on 2016-06-04.
 */
@Entity
public class UserExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne  //JESZCZE EXERCISE, DECYZJE POZOSTAWIAM TOBIE
    private Exercise exercise;

    @NotNull
    @OneToMany(mappedBy = "userExercise",cascade = CascadeType.ALL,fetch=FetchType.LAZY)
    private List<AnswerSet> answerSets = new ArrayList<>();

    public UserExercise(Exercise exercise){
        this.exercise = exercise;
    }

    private UserExercise() {
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise test) {
        this.exercise = test;
    }

    public List<AnswerSet> getAnswerSets() {
        return answerSets;
    }

    public void setAnswerSets(List<AnswerSet> answerSets) {
        this.answerSets = answerSets;
    }

    public void addToAnswerSets(AnswerSet answerSet){
        answerSets.add(answerSet);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
