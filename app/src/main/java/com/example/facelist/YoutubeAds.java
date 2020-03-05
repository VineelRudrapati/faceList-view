package com.example.facelist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class YoutubeAds extends YouTubeBaseActivity {
    YouTubePlayerView mYoutubePlayerView;
    Button bplay;
    List<String> wads= Arrays.asList("GNsBFwK1J_g","6JRMOLO2j1w","k1D9uPa0uKc","yQZhSCHHDSk","huKezSK7Adg","FbTWBTWCJbE","W6tMQP6zda0","iJnEx8proYg","IfXlD8_hm1U","8lt1aSw8UMs","FZMcyvrNcxQ");
    List<String> mads=Arrays.asList("FZMcyvrNcxQ","2hv42havE4w","zZik36qvK_g","Xg--lBFNOqM","ce9Ug2ZWnI4","nCpVcMFYl0w","wRNiG1uPlDw","Imf5kQ7c_Nc","lEmw9pWvZaA");
    int adlen=wads.size();
    YouTubePlayer.OnInitializedListener mOnInitialisedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_ads);
        bplay=(Button)findViewById(R.id.button);
        mYoutubePlayerView=findViewById(R.id.view);
        mOnInitialisedListener=new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Random rand = new Random();
                int number=rand.nextInt((adlen-0)+1)+0;
                youTubePlayer.loadVideo(wads.get(number));
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
