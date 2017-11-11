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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.andres.rioloro.persistence.DatabaseHelper;
import com.example.andres.rioloro.view.ListActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class Main extends AppCompatActivity {

    private TextView mTextMessage;
    private TextView mColaboradores;
    private ImageView mImageView;
    private ImageView mLogoMuni;
    private ImageView mLogoTEC;

    DatabaseHelper databaseHelper;

    private Random random= new Random();

    private final int[] drawables = {
            R.drawable.bebedor,
            R.drawable.contador,
            R.drawable.lluvia,
            R.drawable.pensativo,
            R.drawable.reparador,
            R.drawable.sembrar
    };

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.text_intro);
                    mColaboradores.setText("Con la colaboración de:");
                    mLogoMuni.setImageResource(R.drawable.logomuni);
                    mLogoTEC.setImageResource(R.drawable.teclogo);

                    mImageView.setImageResource(0);
                    return true;
                case R.id.navigation_dashboard:
                    int cantidad = databaseHelper.getAmount();
                    mTextMessage.setText("Tu puntuación actual es de "+String.valueOf(cantidad)+" puntos.");
                    mColaboradores.setText("");
                    mLogoMuni.setImageResource(0);
                    mLogoTEC.setImageResource(0);


                    int image = random.nextInt(drawables.length);
                    mImageView.setImageResource(drawables[image]);

                    return true;
                case R.id.navigation_notifications:
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
        mImageView = (ImageView) findViewById(R.id.imgPuntuacion);
        mColaboradores = (TextView) findViewById(R.id.colaboradores);
        mLogoMuni = (ImageView) findViewById(R.id.logoMuni);
        mLogoTEC = (ImageView) findViewById(R.id.logoTEC);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        databaseHelper = new DatabaseHelper(this);

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