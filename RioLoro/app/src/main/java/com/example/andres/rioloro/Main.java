package com.example.andres.rioloro;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.example.andres.rioloro.view.ListActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main extends AppCompatActivity {

    private TextView mTextMessage;
    private Button mButton;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.text_intro);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    // mTextMessage.setText(R.string.title_notifications);
                    startActivity(new Intent(Main.this.getApplicationContext(),ListActivity.class));
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mButton = (Button) findViewById(R.id.btnPrueba);
        mButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //new JSONTask().execute("http://172.20.10.3:5050/especies/29.json");
                new JSONTask().execute("http://192.168.100.33:8081/JSON/especie.json");
            }
        });

    }

    //Convierte el string del link y lo convierte en un JSON
    public class JSONTask extends AsyncTask<String, String, String>{
        //Conexion al server
        HttpURLConnection connection=null;
        BufferedReader reader = null;
        @Override
        protected String doInBackground(String... params) {
            try{
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new BufferedReader(new InputStreamReader(stream)));

                StringBuffer buffer = new StringBuffer();

                String line = "";
                while ((line = reader.readLine()) != null){
                    buffer.append(line);
                }
                Toast.makeText(Main.this, "CONNECTED", Toast.LENGTH_LONG).show();
                Toast.makeText(Main.this, buffer.toString(), Toast.LENGTH_LONG).show();
                return buffer.toString();

            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                if(connection != null){
                    connection.disconnect();
                }
                try{
                    if (reader != null){
                        reader.close();
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            mTextMessage.setText(result);
            CharSequence cs = (CharSequence) result;
            Toast.makeText(Main.this, cs, Toast.LENGTH_LONG).show();
        }
    }

}