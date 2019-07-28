package com.example.sub1_cataloguemovie.network;

import com.example.sub1_cataloguemovie.model.MovieList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by auzan on 7/12/2019.
 * Github: @auzanassdq
 */
public interface GetDataService {
    @GET("discover/movie")
    Observable<MovieList> getAllMovie(@Query("api_key") String apiKey,
                                      @Query("language") String lang);

    @GET("discover/tv")
    Observable<MovieList> getAllTv(@Query("api_key") String apiKey,
                                   @Query("language") String lang);
}
