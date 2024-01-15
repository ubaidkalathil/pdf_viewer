package com.example.testproject;

import com.example.testproject.model.PdfListModel;

import java.util.List;

public interface  HomeInterface {
    void OnFailure(String message);
    void OnSuccess(List<PdfListModel> pdf);
}
