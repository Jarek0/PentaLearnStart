package pl.pollub.cs.pentalearn.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * This class is responsible for learn before exercise
 * Created by Wojciech on 2016-05-02.
 */
@Entity
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    private Chapter chapter;

    @NotNull
    @Size(min=10,max=500)
    private String content;

    private Lecture(){}

    public Lecture(Chapter chapter, String content) {
        this.chapter = chapter;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
