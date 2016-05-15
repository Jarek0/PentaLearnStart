package pl.pollub.cs.pentalearn.domain;

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
    private Long id;

    @NotNull
    @ManyToOne
    private Exercise exercise;

    @NotNull
    @Size(max = 64)
    private String questionText;

    @NotNull
    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    private List<Answer> answers=new ArrayList<>();

    private Question(){}

    public Question(String questionText,Exercise exercise) {
        this.questionText = questionText;
        this.exercise=exercise;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

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

    public List<Answer> getAnswers() {
        return answers;
    }
}
