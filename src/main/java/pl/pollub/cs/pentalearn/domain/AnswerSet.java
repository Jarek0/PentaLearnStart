package pl.pollub.cs.pentalearn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wojciech on 2016-06-04.
 */
@Entity
public class AnswerSet implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @OneToOne
    private Question question;

    //przyjalem ze to odp wybrane przez usera, boolean się nie nadaje

    @ElementCollection
    @CollectionTable(name="answer_set_answers")
    @Column(name="answers")
    List<String> answers = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name="answer_set_texts")
    @Column(name="texts")
    List<String> texts=new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    UserExercise userExercise;

    //when user think answer is true
    public String getUserAnswers(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<answers.size(); i++){
                sb.append(answers.get(i)); sb.append(" ,");
        }
        return sb.toString();
    }

    private AnswerSet() {
    }

    public AnswerSet(List<String> texts, List<String> answers, Question question) {
        this.texts = texts;
        this.answers = answers;
        this.question = question;
    }

    public AnswerSet(UserExercise userExercise, List<String> texts, List<String> answers) {
        this.userExercise = userExercise;
        this.texts = texts;
        this.answers = answers;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public UserExercise getUserExercise() {
        return userExercise;
    }

    public void setUserExercise(UserExercise userExercise) {
        this.userExercise = userExercise;
    }

    public List<String> getTexts() {
        return texts;
    }

    public void setTexts(List<String> texts) {
        this.texts = texts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //very naive function . return percent of correct answers
    public double check(){
        List<String> correctAnswers=question.getCorrectAnswerSet().getAnswers();
        List<String> userAnswer=answers;
        if(userAnswer.size()==correctAnswers.size()){
            int result=0;
            for(int i=0;i<userAnswer.size();i++){
                if(userAnswer.get(i).equals(correctAnswers.get(i))) result++;
            }
            return (result/(double)correctAnswers.size())*100;
        }
        else return 0;
    }

}