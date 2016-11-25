package pl.pollub.cs.pentalearn.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by pglg on 27-07-2016.
 */
@Entity
public class UserExerciseResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;

    @NotNull
    @ManyToOne
    @JsonIgnore
    private UserExercise userExercise;

    @NotNull
    private Double exerciseMadePercentage;

    @NotNull
    private Double correctAnswersInMadeExercisePercentage;

    @NotNull
    private Double finalExerciseResult;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date resultTime;

    private UserExerciseResult() {
    }

    public UserExerciseResult(Double exerciseMadePercentage, Double correctAnswersInMadeExercisePercentage,
                              Double finalExerciseResult, UserExercise userExercise, Date timestamp) {
        this.exerciseMadePercentage = exerciseMadePercentage;
        this.correctAnswersInMadeExercisePercentage = correctAnswersInMadeExercisePercentage;
        this.finalExerciseResult = finalExerciseResult;
        this.userExercise = userExercise;
        this.resultTime = timestamp;
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

    public UserExercise getUserExercise() {
        return userExercise;
    }

    public void setUserExercise(UserExercise userExercise) {
        this.userExercise = userExercise;
    }

    public Date getResultTime() {
        return resultTime;
    }

    public void setResultTime(Date resultTime) {
        this.resultTime = resultTime;
    }
}
