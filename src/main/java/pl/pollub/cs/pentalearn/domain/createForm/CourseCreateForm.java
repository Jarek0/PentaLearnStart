package pl.pollub.cs.pentalearn.domain.createForm;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by pglg on 19-05-2016.
 */
public class CourseCreateForm {

    @NotEmpty
    @Size(max = 64)
    private String name;

    @NotEmpty
    @Size(max = 64)
    private String description;

    @NotEmpty
    @Size(max=400)
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public CourseCreateForm(String name, String description, String category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

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

    private CourseCreateForm(){}
}
