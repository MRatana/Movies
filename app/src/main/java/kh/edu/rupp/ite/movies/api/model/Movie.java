package kh.edu.rupp.ite.movies.api.model;

public class Movie {
    private int id;
    private String title;
    private String category;
    private String description;
    private String img;
    private String rating;


    public void setRating(String rating) {
        this.rating = rating;
    }
    public String getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



//    private String id;
//
//    public Movie(String id, String title, String description, String star) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
//        this.star = star;
//    }
//
//    private String title;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getStar() {
//        return star;
//    }
//
//    public void setStar(String star) {
//        this.star = star;
//    }
//
//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }
//
//    private String description;
//
//    private String star;
//
//    private String image;



}
