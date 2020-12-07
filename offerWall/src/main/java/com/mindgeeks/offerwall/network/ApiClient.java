package com.mindgeeks.offerwall.network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    //  private static String url;

    /* public ApiClient(String url) {
         ApiClient.url = url;
     }*/
    // http://fcc8f1b8a382.ngrok.io/api/v1/
    // static String url="http://492e2beb84bf.ngrok.io/api/v1/";
    //static String url=" https://cashmantra.app/api/v1/";
    static String url = "https://starcash.app/api/v1/";

    private static Retrofit getRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        Retrofit retrofit = new Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;

    }

    public static ApiInterface getApiInterface() {
        ApiInterface apiInterface = getRetrofit().create(ApiInterface.class);
        return apiInterface;
    }
}
