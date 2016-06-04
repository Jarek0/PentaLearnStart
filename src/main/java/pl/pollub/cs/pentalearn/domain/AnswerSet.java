package pl.pollub.cs.pentalearn.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wojciech on 2016-06-04.
 */
@Entity
public class AnswerSet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    private Question question;

    //przyjalem ze to odp wybrane przez usera, boolean się nie nadaje
    @NotNull
    @ElementCollection(targetClass=String.class)
    List<String> answers = new ArrayList<>();

    @NotNull
    @ManyToOne
    UserTest userTest;

    //when user think answer is true
    public String getUserAnswers(){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<answers.size(); i++){
                sb.append(answers.get(i)); sb.append(" ,");
        }
    }

    public AnswerSet() {
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Boolean> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Boolean> answers) {
        this.answers = answers;
    }

    public UserTest getUserTest() {
        return userTest;
    }

    public void setUserTest(UserTest userTest) {
        this.userTest = userTest;
    }
}