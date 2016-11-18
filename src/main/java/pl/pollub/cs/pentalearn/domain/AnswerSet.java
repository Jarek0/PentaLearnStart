package pl.pollub.cs.pentalearn.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import pl.pollub.cs.pentalearn.serializer.Views;
import pl.pollub.cs.pentalearn.service.exception.InvalidAnswerSetException;

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

    @ElementCollection
    @CollectionTable(name="answer_set_answers")
    @Column(name="answers")
    @JsonView(Views.Private.class)
    List<Boolean> answers = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name="answer_set_texts")
    @Column(name="texts")
    List<String> texts=new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    UserExercise userExercise;

    @NotNull
    private Boolean multiSelectAllowed;

    @JsonCreator
    public AnswerSet(@JsonProperty("texts") List<String> texts,@JsonProperty("answers") List<Boolean> answers, @JsonProperty("multiSelectAllowed") boolean multiSelectAllowed) throws InvalidAnswerSetException {
        boolean isObjectValidToMake=false;
        if(texts.size()==answers.size() && multiSelectAllowed){
            isObjectValidToMake=true;
        }
        else if(texts.size()==answers.size()){
            int tNumber=0;
            for(boolean b:answers){
                if(b) tNumber++;
                if(tNumber>1) break;
            }
            if(tNumber<=1) isObjectValidToMake=true;
        }

        if(isObjectValidToMake){
            this.texts=texts;
            this.answers=answers;
            this.multiSelectAllowed = multiSelectAllowed;
        }
        else throw new InvalidAnswerSetException();
    }

    public AnswerSet( List<String> texts, List<Boolean> answers, boolean multiSelectAllowed, Question question) throws InvalidAnswerSetException {
        this(texts,answers, multiSelectAllowed);
        this.question = question;
    }

    public AnswerSet(List<String> texts, List<Boolean> answers, boolean multiSelectAllowed, Question question, UserExercise userExercise) throws InvalidAnswerSetException {
        this(texts,answers, multiSelectAllowed,question);
        this.userExercise = userExercise;
    }

    private AnswerSet(){}

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @JsonView(Views.Private.class)
   public List<Boolean> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Boolean> answers) {
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

    public static double matchLevel(AnswerSet answerSet,AnswerSet answerSet2){
        if(!isAnswerSetsCompatible(answerSet,answerSet2)) return 0;
        else{
            int result=0;
            for(int i=0;i<answerSet.getAnswers().size();i++){
                if(answerSet.getAnswers().get(i)==answerSet2.getAnswers().get(i)) result++;
            }
            return (result/(double)answerSet.getAnswers().size());
        }
    }

    public static boolean isAnswerSetsCompatible(AnswerSet answerSet, AnswerSet answerSet2){
        if(answerSet.getAnswers().size()==answerSet2.getAnswers().size() &&
                answerSet.getTexts().size()==answerSet2.getTexts().size() &&
                answerSet.multiSelectAllowed == answerSet2.multiSelectAllowed){
            boolean result=true;
            for(int i=0;i<answerSet.getTexts().size();i++){
                if (!answerSet.getTexts().get(i).equals(answerSet2.getTexts().get(i))){
                    result=false;
                    break;
                }
            }
            return result;
        }
        else return false;
    }

    public Boolean getMultiSelectAllowed() {
        return multiSelectAllowed;
    }

    public void setMultiSelectAllowed(Boolean multiSelectAllowed) {
        this.multiSelectAllowed = multiSelectAllowed;
    }
}