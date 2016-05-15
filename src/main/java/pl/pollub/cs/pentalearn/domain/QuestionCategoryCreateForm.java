package pl.pollub.cs.pentalearn.domain;

import jdk.nashorn.internal.runtime.regexp.RegExp;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by pglg on 24-04-2016.
 */
public class QuestionCategoryCreateForm {

    @NotEmpty
    @Size(max=64)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "QuestionCategoryCreateFrom{" +
                "name='" + name + '\'' +
                '}';
    }
}
