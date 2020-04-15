package com.example.facelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.time.LocalDate;
import java.util.List;

public class something extends AppCompatActivity {

    public void calcuation(List<Integer> integerList, List<String> stringList) {
       // something obj = new something();
        int m = 0;
        int f = 0;
        int sum = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sum = integerList.stream().mapToInt(Integer::intValue).sum();
        }
        Log.d("sum of ages", String.valueOf(sum));
        for (int i = 0; i < stringList.size(); i++) {
            String value = stringList.get(i);
            if (value.equals("male")) {
                m++;
            } else {
                f++;
            }
        }
        Intent youtube=new Intent(this,YoutubeAds.class);
        youtube.putExtra("males",m);
        youtube.putExtra("females",f);
        startActivity(youtube);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

