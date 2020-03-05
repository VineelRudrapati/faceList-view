package com.example.facelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.microsoft.projectoxford.face.contract.Face;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.*;
public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        String data=getIntent().getStringExtra("list_faces");
        Gson gson=new Gson();
        Face[] faces=gson.fromJson(data,Face[].class);
        ListView Mylistview=(ListView)findViewById(R.id.listview);
        Bitmap originalBitmap= BitmapFactory.decodeResource(getResources(),R.drawable.group);
        CustomAdapter customAdapter=new CustomAdapter(faces,this,originalBitmap);
        Mylistview.setAdapter(customAdapter);
        /*try {
        Thread.sleep(2000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Intent youtubeads=new Intent(getApplicationContext(),YoutubeAds.class);
        startActivity(youtubeads);*/
    }
}
