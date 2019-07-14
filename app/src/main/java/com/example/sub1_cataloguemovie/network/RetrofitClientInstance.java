package com.example.sub1_cataloguemovie.network;

import com.example.sub1_cataloguemovie.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by auzan on 7/12/2019.
 * Github: @auzanassdq
 */
public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static OkHttpClient client;

    private static void interception() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }
    public static Retrofit getRetrofitInstance(){
        interception();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.MOVIE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return  retrofit;
    }
}
