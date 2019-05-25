package amaragya.bahaso.Network;

import amaragya.bahaso.Model.Artikel_m;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("https://newsapi.org/v2/top-headlines?country=id&apiKey=cd671c9a9ba34a6e824145e737903f02&pageSize=6")
    Observable<Artikel_m> fetchData(
            @Query("page") int page
    );

    @GET("https://newsapi.org/v2/top-headlines?country=id&apiKey=cd671c9a9ba34a6e824145e737903f02&pageSize=6")
    Observable<Artikel_m> searchData(
            @Query("q") String keyword,
            @Query("page") int page
    );

}
