package pl.pollub.cs.pentalearn.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which is responsible for courses.
 * There are two parts of class:
 * lecture is responsible for learning,
 * if user ends learn, exercise will appear.
 * Created by Wojciech on 2016-04-30.
 */
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(hidden = true)
    private Long id;

    @NotNull
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Chapter> chapters = new ArrayList<>();

    @NotNull
    @Size(max = 32)
    private String category;

    @NotNull
    @Size(max = 64, min = 4)
    private String name;

    @NotNull
    @Size(max = 64)
    private String description;

    public Course(String name, String description, String category) {
        this.name = name;
        this.description = description;
        this.category = category;
    }

    private Course() {
    }

    @JsonIgnore
    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(ArrayList<Chapter> chapters) {
        this.chapters = chapters;
    }

    public void addChapter(Chapter chapter) {
        this.chapters.add(chapter);
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
