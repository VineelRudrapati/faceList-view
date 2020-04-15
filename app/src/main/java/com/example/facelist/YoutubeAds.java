package com.example.facelist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.List;

public class YoutubeAds extends YouTubeBaseActivity {
    YouTubePlayerView mYoutubePlayerView;
    Button bplay;
    List<String> wads= Arrays.asList("GNsBFwK1J_g","6JRMOLO2j1w","k1D9uPa0uKc","yQZhSCHHDSk","huKezSK7Adg","FbTWBTWCJbE","W6tMQP6zda0","iJnEx8proYg","IfXlD8_hm1U","8lt1aSw8UMs","FZMcyvrNcxQ");
    List<String> mads=Arrays.asList("FZMcyvrNcxQ","2hv42havE4w","zZik36qvK_g","Xg--lBFNOqM","ce9Ug2ZWnI4","nCpVcMFYl0w","wRNiG1uPlDw","Imf5kQ7c_Nc","lEmw9pWvZaA");
    List<String> chil=Arrays.asList("3smnq7TzNog","5D7JoSzBW-g","t6f_ZNiQe0k","QO3ANBIDlIU","LdcnyqjMy_E","c_9mb4Q2zuQ","SdaFrEdoVMw","ramfLqO0MC8","0qMm0res75Y","z5S-Vy2LrZ4");
    int wlen=wads.size();
    int mlen=mads.size();
    int clen=chil.size();
    YouTubePlayer.OnInitializedListener mOnInitialisedListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_ads);
        bplay=(Button)findViewById(R.id.button);
        mYoutubePlayerView=findViewById(R.id.view);
        int male=getIntent().getIntExtra("males",0);
        int female=getIntent().getIntExtra("female",0);
        Log.d("number of males",String.valueOf(male));
        Log.d("number of females",String.valueOf(female));
        mOnInitialisedListener=new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Random rand = new Random();
                int number = rand.nextInt((wlen - 0) + 1) + 0;
                youTubePlayer.loadVideo(wads.get(number));
                if (female > male) {
                    youTubePlayer.loadVideo(wads.get(number));
                } else {
                    int f=rand.nextInt((mlen - 0) + 1) + 0;
                    youTubePlayer.loadVideo(mads.get(number));
                }
            }
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            }
        };
        bplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mYoutubePlayerView.initialize(config.getApiKey(),mOnInitialisedListener);
            }
        });
    }
    }
