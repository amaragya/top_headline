package mobileapps.talbia.Model;


import com.google.gson.annotations.SerializedName;

public class Informasi_m {

    @SerializedName("image")
    private String image;

    @SerializedName("category_id")
    private int categoryId;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("author")
    private String author;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("kategori")
    private String kategori;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("excerpt")
    private String excerpt;

    @SerializedName("body")
    private String body;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
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

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}