package com.example.testproject.Services;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.testproject.DB.DBHelper;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PdfDownloadTask extends AsyncTask<String, Void, Boolean> {

    private Context context;
    private String Id;


    public PdfDownloadTask(Context context, String pdfId) {
        this.context = context;
        this.Id = pdfId;  // Store the provided pdfId
    }

    @Override
    protected Boolean doInBackground(String... params) {
        String pdfUrl = params[0];

        try {
            URL url = new URL(pdfUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                byte[] pdfData = inputStreamToByteArray(inputStream);
                inputStream.close();

                savePdfToDatabase(pdfData);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private byte[] inputStreamToByteArray(InputStream inputStream) throws Exception {
        byte[] buffer = new byte[4096];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
        return output.toByteArray();
    }

    private void savePdfToDatabase(byte[] pdfData) {
        DBHelper dbHelper = new DBHelper(context);
       boolean status = dbHelper.insetruserdata(pdfData,Id);
        Log.e("SAVED",String.valueOf(status));

    }

    @Override
    protected void onPostExecute(Boolean success) {
        // Handle the result after the download and storage is complete
        if (success) {
            // The PDF has been successfully downloaded and stored in the database
            Log.e("SAVED","1");
        } else {
            // There was an error downloading the PDF or storing it in the database
        }
    }
}
