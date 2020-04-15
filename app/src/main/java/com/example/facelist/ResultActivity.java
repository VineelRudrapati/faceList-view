package com.example.facelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import com.google.gson.Gson;
import com.microsoft.projectoxford.face.contract.Face;

import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {
    Button ads;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        String data=getIntent().getStringExtra("list_faces");
        char[] imp=data.toCharArray();
        Gson gson=new Gson();
        Face[] faces=gson.fromJson(data,Face[].class);
        ListView Mylistview=(ListView)findViewById(R.id.listview);
        Bitmap originalBitmap= BitmapFactory.decodeResource(getResources(),R.drawable.group);
        CustomAdapter customAdapter=new CustomAdapter(faces,this,originalBitmap);
        Mylistview.setAdapter(customAdapter);
        transfering(data);
}

    private void transfering(String data) {
        Gson gson2=new Gson();
        Face[] values=gson2.fromJson(data,Face[].class);
        int len=values.length;
        Log.d("this",String.valueOf(len));
        long t= System.currentTimeMillis();
        long end = t+15000;
        while(System.currentTimeMillis() < end) {
            // do something
            // pause to avoid churning
            Thread.sleep( end);
        }
        Intent youtube=new Intent(this,YoutubeAds.class);
        startActivity(youtube);
    }
}
