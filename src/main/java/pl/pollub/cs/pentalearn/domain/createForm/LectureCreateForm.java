package pl.pollub.cs.pentalearn.domain.createForm;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by pglg on 19-05-2016.
 */
public class LectureCreateForm {

    @NotEmpty
    @Size(min=10,max=500)
    private String content;

    public LectureCreateForm(String content) {
        this.content = content;
    }

    private LectureCreateForm(){}

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
