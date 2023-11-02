package kh.edu.rupp.ite.movies.api.model;

public class Movie {
    private String id;

    public Movie(String id, String title, String description, String star) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.star = star;
    }

    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String description;

    private String star;

    private String image;



}
