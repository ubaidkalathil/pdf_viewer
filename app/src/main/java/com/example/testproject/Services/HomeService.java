package com.example.testproject.Services;

import android.util.Log;

import com.example.testproject.HomeInterface;
import com.example.testproject.API.ApiClient;
import com.example.testproject.model.PdfListModel;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class HomeService {

    public void getPdfListData(HomeInterface homeInterface) {
        new ApiClient().getCallInterface().getTheList().enqueue(new Callback<List<PdfListModel>>() {
            @Override
            public void onResponse(Call<List<PdfListModel>> call, retrofit2.Response<List<PdfListModel>> response) {
                List<PdfListModel> pdfData =   response.body();

                Log.e("PDF_DATA", new Gson().toJson(pdfData));
                Log.e("PDF",new Gson().toJson(pdfData));

                if (response.isSuccessful() && response.body() != null) {
                    homeInterface.OnSuccess(pdfData);
                } else {
                    homeInterface.OnFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<PdfListModel>> call, Throwable t) {
                Log.e("CONFIGRATION", t.getMessage());
                homeInterface.OnFailure("failed");
                Log.e("PDF","Failed");
//                Toasty.warning(SplashActivity.this, "Server error", Toasty.LENGTH_LONG).show();
//                sche.setVisibility(View.VISIBLE);
            }
        });
    }
}
