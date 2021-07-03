package com.example.xpertstudio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class GalleryActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    VideoAdapter videoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        viewPager2 =(ViewPager2) findViewById(R.id.vpaper2);
        FirebaseRecyclerOptions<VideoModel> options =
                new FirebaseRecyclerOptions.Builder<VideoModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("VideoPortfolio"), VideoModel.class)
                        .build();

        videoAdapter=new VideoAdapter(options);
        viewPager2.setAdapter(videoAdapter);


    }
    @Override
    protected void onStart() {
        super.onStart();
        videoAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        videoAdapter.stopListening();
    }
}