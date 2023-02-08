package com.example.finedu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;

public class ViewPdf extends AppCompatActivity {
    PDFView pdfview;
    ProgressDialog pd;
    String activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        pd = new ProgressDialog(this);
        pd.setTitle("open file");
        pd.setMessage("opening the file ...!!!");
        pd.show();
        pdfview = findViewById(R.id.pdfview);
        Intent intent = getIntent();
        String module_name = intent.getStringExtra("pdf");
        activity=intent.getStringExtra("intent");

        new RetrivePdfStream().execute(module_name);

    }

    class RetrivePdfStream extends AsyncTask<String, Void, InputStream> {

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {

                // adding url
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                // if url connection response code is 200 means ok the execute
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }
            }
            // if error return null
            catch (IOException e) {
                return null;
            }
            return inputStream;
        }

        @Override
        // Here load the pdf and dismiss the dialog box
        protected void onPostExecute(InputStream inputStream) {
            pdfview.fromStream(inputStream).load();
            pd.dismiss();
        }
    }

//    @Override
//    public void onBackPressed() {
//        if(activity.equals("books")) {
//            Intent intent = new Intent(ViewPdf.this, books.class);
//            startActivity(intent);
//        }
//        super.onBackPressed();
//
//    }
}