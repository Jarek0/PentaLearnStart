package pl.pollub.cs.pentalearn.domain;

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
public class AnswersCreateForm {

    @Min(1)
    @Max(10)
    private Integer answersNumber;

    public Integer getAnswersNumber() {
        return answersNumber;
    }

    public void setAnswersNumber(Integer answersNumber) {
        this.answersNumber = answersNumber;
    }

}
