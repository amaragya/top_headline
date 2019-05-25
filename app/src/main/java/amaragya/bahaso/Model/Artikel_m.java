package amaragya.bahaso.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Artikel_m {

    @SerializedName("totalResults")
    private int totalResults;

    @SerializedName("articles")
    private List<ArticlesItem> articles;

    @SerializedName("status")
    private String status;

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<ArticlesItem> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesItem> articles) {
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return
                "Artikel_m{" +
                        "totalResults = '" + totalResults + '\'' +
                        ",articles = '" + articles + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}