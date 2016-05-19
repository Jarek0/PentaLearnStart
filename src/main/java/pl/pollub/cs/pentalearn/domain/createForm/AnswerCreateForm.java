package pl.pollub.cs.pentalearn.domain.createForm;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by pglg on 29-04-2016.
 */
public class AnswerCreateForm {

    @NotNull
    private Boolean correct;

    @NotEmpty
    @Size(max=200)
    private String answerText;

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

    private AnswerCreateForm(){}

    public AnswerCreateForm(Boolean correct, String answerText) {
        this.correct = correct;
        this.answerText = answerText;
    }
}
