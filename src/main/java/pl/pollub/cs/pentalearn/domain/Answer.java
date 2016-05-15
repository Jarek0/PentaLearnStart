package pl.pollub.cs.pentalearn.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by pglg on 28-04-2016.
 */
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean correct;
    @NotNull
    @Size(max=64)
    private String answerText;
    @NotNull
    @ManyToOne
    private Question question;


    public Answer(Question question, String answerText, Boolean correct) {
        this.question = question;
        this.answerText = answerText;
        this.correct = correct;
    }

    private Answer(){}

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", correct=" + correct +
                ", answerText='" + answerText + '\'' +
                '}';
    }
}
