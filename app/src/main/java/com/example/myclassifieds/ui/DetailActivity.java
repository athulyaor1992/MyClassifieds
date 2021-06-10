package com.example.myclassifieds.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myclassifieds.R;
import com.example.myclassifieds.model.Classify;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {

    ImageView detailImage;
    Button home;
    TextView classifyName, classifyDate, classifyPrice;
    Classify classify;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

         classify = getIntent().getParcelableExtra("classify");

        detailImage = (ImageView)findViewById(R.id.detailImage);
        classifyName = (TextView)findViewById(R.id.classifyName);
        classifyDate = (TextView)findViewById(R.id.classifyDate);
        classifyPrice = (TextView)findViewById(R.id.classifyPrice);
        home = (Button)findViewById(R.id.home);

        if(classify!=null) {

            classifyName.setText(classify.getName());
            classifyPrice.setText(classify.getPrice());

            String date = classify.getCreated_at();
            SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date newDate = null;
            try {
                newDate = spf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            spf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            date = spf.format(newDate);

            classifyDate.setText(date);

            Glide.with(this)
                    .load(classify.getImage_urls_thumbnails().get(0))
                    .into(detailImage);

        }

        home.setOnClickListener(v -> {

            finish();
        });

    }
}
