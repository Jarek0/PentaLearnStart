package pl.pollub.cs.pentalearn.domain.createForm;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by pglg on 19-05-2016.
 */
public class ExerciseCreateForm {

    @NotEmpty
    @Size(max = 64)
    private String title;

    private ExerciseCreateForm(){}

    public ExerciseCreateForm(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
