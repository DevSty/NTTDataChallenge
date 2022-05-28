package com.nttdata.movies.data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    private String releaseYear;
    private String descriptionGenres;
    @ManyToMany
    private List<Actor> actors;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getDescriptionGenres() {
        return descriptionGenres;
    }

    public void setDescriptionGenres(String descriptionGenres) {
        this.descriptionGenres = descriptionGenres;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public boolean hasActor(Actor actor) {
        for (Actor containedActor : getActors()) {
            if (containedActor.getId() == actor.getId()) {
                return true;
            }
        }
        return false;
    }

}
