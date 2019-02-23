package com.example.apistocksearch;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etSearch;
    TextView tvDisplay, tvTitle;
    Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSearch = findViewById(R.id.etSearch);
        tvDisplay = findViewById(R.id.tvDisplay);
        tvTitle = findViewById(R.id.tvTitle);

        btnClick = findViewById(R.id.btnClick);

        btnClick.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnClick:
                LocationClicked();


        }
    }

    private void LocationClicked() {

        String key = etSearch.getText().toString();
        String url = "https://api.iextrading.com/1.0/stock/" + key + "/quote";
        try {
            new APIWorker().execute(new URL(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    class APIWorker extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... urls) {
            StringBuilder output = new StringBuilder();
            try {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection)
                        urls[0].openConnection();
                InputStream inputStream = new
                        BufferedInputStream(httpsURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    output.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return output.toString();
        }

        @Override
        protected void onPostExecute(String results) {
            super.onPostExecute(results);

            Gson gson = new Gson();
            StockNameProfile StockNameProfile = gson.fromJson(results, StockNameProfile.class);

            String symbol = StockNameProfile.getSymbol();
            String companyName = StockNameProfile.getCompanyName();
            String sector = StockNameProfile.getSector();
            String calculationPrice = StockNameProfile.getCalculationPrice();
            String result = "Company Name: " + companyName + "\n" +
                    "Company Symbol: " + symbol + "\n" +
                    "Sector: " + sector + "\n" +
                    "CalculationPrice: " + calculationPrice;

            tvDisplay.setText(result);
        }
    }
}