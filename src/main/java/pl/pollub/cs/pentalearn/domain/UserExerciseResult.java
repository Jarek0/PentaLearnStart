package pl.pollub.cs.pentalearn.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Created by pglg on 27-07-2016.
 */
@Entity
public class UserExerciseResult{

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Double exerciseMadePercentage;

    @NotNull
    private Double correctAnswersInMadeExercisePercentage;

    @NotNull
    private Double finalExerciseResult;

    public UserExerciseResult(Double exerciseMadePercentage, Double correctAnswersInMadeExercisePercentage, Double finalExerciseResult) {
        this.exerciseMadePercentage = exerciseMadePercentage;
        this.correctAnswersInMadeExercisePercentage = correctAnswersInMadeExercisePercentage;
        this.finalExerciseResult = finalExerciseResult;
    }

    public Double getExerciseMadePercentage() {
        return exerciseMadePercentage;
    }

    public void setExerciseMadePercentage(Double exerciseMadePercentage) {
        this.exerciseMadePercentage = exerciseMadePercentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCorrectAnswersInMadeExercisePercentage() {
        return correctAnswersInMadeExercisePercentage;
    }

    public void setCorrectAnswersInMadeExercisePercentage(Double correctAnswersInMadeExercisePercentage) {
        this.correctAnswersInMadeExercisePercentage = correctAnswersInMadeExercisePercentage;
    }

    public Double getFinalExerciseResult() {
        return finalExerciseResult;
    }

    public void setFinalExerciseResult(Double finalExerciseResult) {
        this.finalExerciseResult = finalExerciseResult;
    }

}
