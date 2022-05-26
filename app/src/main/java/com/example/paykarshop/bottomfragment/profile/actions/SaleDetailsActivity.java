package com.example.paykarshop.bottomfragment.profile.actions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.paykarshop.R;

public class SaleDetailsActivity extends AppCompatActivity {

    ImageView imageView;
    TextView name, period,detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_details);

        Intent intent = getIntent();
        String Name = intent.getStringExtra("Name");
        String Period = intent.getStringExtra("Period");
        String Detail = intent.getStringExtra("Detail");
        String DetailPicture = intent.getStringExtra("DetailPicture");

        Log.e("DetailPicture",DetailPicture);



        imageView = findViewById(R.id.image_DetailPicture);
        name = findViewById(R.id.id_Name);
        period = findViewById(R.id.id_Period);
        detail = findViewById(R.id.id_Detail);

        String url = "https://paykar.tj" + DetailPicture;
        new image.DownloadImageTask(imageView).execute(url);


        name.setText(Name);
        period.setText(Period);
        detail.setText(Detail);
    }
}