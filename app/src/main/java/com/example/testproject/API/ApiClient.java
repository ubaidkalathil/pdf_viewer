package com.example.testproject.API;
import com.example.testproject.model.PdfListModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


public class ApiClient {

    public static final String BASE_URL="https://6593a93a1493b0116068da73.mockapi.io/api/";

    private CallInterface callInterface;
    Retrofit retrofit;

    public CallInterface getCallInterface() {
        return callInterface;
    }

    public ApiClient() {

        String currentURL= BASE_URL ;

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit=new Retrofit.Builder().baseUrl(currentURL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson)).build() ;
        callInterface=retrofit.create(CallInterface.class);
    }

    public interface CallInterface {


        @GET("v1/pdf/list/pdf")
        Call<List<PdfListModel>> getTheList();

    }

}
