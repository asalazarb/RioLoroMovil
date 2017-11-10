package com.example.andres.rioloro.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.andres.rioloro.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class DetailActivity extends AppCompatActivity{
    private static final String EXTRA_DATE_AND_TIME = "EXTRA_DATE_AND_TIME";
    private static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private static final String EXTRA_DRAWABLE = "EXTRA_DRAWABLE";
    private static final String EXTRA_IMAGE = "EXTRA_IMAGE";

    private TextView dateAndTime;
    private TextView message;
    private View coloredBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);

        Intent i = getIntent();
        String dateAndTimeExtra = i.getStringExtra(EXTRA_DATE_AND_TIME);
        String messageExtra = i.getStringExtra(EXTRA_MESSAGE);
        int drawableResourceExtra = i.getIntExtra(EXTRA_DRAWABLE, 0);
        String image = i.getStringExtra(EXTRA_IMAGE);

        dateAndTime = (TextView) findViewById(R.id.lbl_date_and_time_header);
        dateAndTime.setText(dateAndTimeExtra);

        message = (TextView) findViewById(R.id.lbl_message_body);
        message.setText(messageExtra);

        coloredBackground = findViewById(R.id.imv_colored_background);
        coloredBackground.setBackgroundResource(
                drawableResourceExtra
        );
        ImageLoader imageLoader= ImageLoader.getInstance();
        imageLoader.displayImage(image, (ImageView) coloredBackground);
    }
}
