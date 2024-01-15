package com.example.testproject.controller;

import com.example.testproject.HomeInterface;
import com.example.testproject.MainActivity;
import com.example.testproject.Services.HomeService;
import com.example.testproject.model.PdfListModel;

import java.util.List;

public class HomeController {
    private MainActivity activity;
    private PdfListModel model;
    private HomeService service;

    public HomeController(MainActivity activity) {
        this.activity = activity;
        model = new PdfListModel();
        service = new HomeService();

        // Service for API calls
    }

    public void getData() {

        // Call API using service
        service.getPdfListData(new HomeInterface(){

            @Override
            public void OnFailure(String message) {

            }

            @Override
            public void OnSuccess(List<PdfListModel> pdf) {
               activity.setAssigneeRecycler(pdf);
            }
        });
    }
}
