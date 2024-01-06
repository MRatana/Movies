package kh.edu.rupp.ite.movies.model

class Movie2 {

    private var id: String? = null
    private var title: String? = null
    private var category: String? = null
    private var description: String? = null
    private var img: String? = null
    private var rating: String? = null
    private var video: String? = null

    fun getVideo(): String? {
        return video
    }

    fun setVideo(video: String?) {
        this.video = video
    }

    fun getId(): String? {
        return id
    }


    fun setId(id: String?) {
        this.id = id
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun getCategory(): String? {
        return category
    }

    fun setCategory(category: String?) {
        this.category = category
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?) {
        this.description = description
    }

    fun getImg(): String? {
        return img
    }

    fun setImg(img: String?) {
        this.img = img
    }

    fun getRating(): String? {
        return rating
    }

    fun setRating(rating: String?) {
        this.rating = rating
    }

}