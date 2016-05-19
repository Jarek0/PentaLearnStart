package pl.pollub.cs.pentalearn.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by pglg on 18-05-2016.
 */
public class ChapterCreateForm {

    @NotEmpty
    @Size(max = 32)
    private String name;

    @NotEmpty
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ChapterCreateForm(String name, String description) {
        this.name = name;
        this.description = description;
    }

    private ChapterCreateForm(){}
}
