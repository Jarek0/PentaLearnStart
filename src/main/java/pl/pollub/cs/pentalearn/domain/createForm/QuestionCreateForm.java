package pl.pollub.cs.pentalearn.domain.createForm;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pglg on 25-04-2016.
 */
public class QuestionCreateForm {

    @NotEmpty
    @Size(max = 64)
    private String questionText;

    @NotNull
    private List<AnswerCreateForm> answers =new ArrayList<>();

    private QuestionCreateForm(){}

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<AnswerCreateForm> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerCreateForm> answers) {
        this.answers = answers;
    }

    public QuestionCreateForm(String questionText, List<AnswerCreateForm> answers) {
        this.questionText = questionText;
        this.answers = answers;
    }

}
