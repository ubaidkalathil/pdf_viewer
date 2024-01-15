package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.testproject.DB.DBHelper;
import com.example.testproject.controller.HomeController;
import com.example.testproject.model.PdfListModel;
import com.example.testproject.view.adapter.PdfAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    PdfAdapter pdfAdapter;
    RecyclerView rvPdf;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPdf = findViewById(R.id.rv_items);
        progressBar = findViewById(R.id.progress_bar);

        DBHelper db = new DBHelper(this);
        if (db.getData().moveToFirst()) {
            Log.e("SAVED","1");
        } else {
            Log.e("SAVED","0");
        }

        HomeController controller = new HomeController(this);
        controller.getData();

    }

    public void setAssigneeRecycler(List<PdfListModel> pdf){
        rvPdf.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvPdf.setItemAnimator(new DefaultItemAnimator());
        pdfAdapter = new PdfAdapter(this,pdf);
        rvPdf.setAdapter(pdfAdapter);
    }

}