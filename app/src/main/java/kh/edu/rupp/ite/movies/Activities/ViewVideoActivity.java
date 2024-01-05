package kh.edu.rupp.ite.movies.Activities;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.media.MediaPlayer;
import android.widget.MediaController;
import androidx.appcompat.app.AppCompatActivity;


import kh.edu.rupp.ite.movies.databinding.ActivityViewVideoBinding;

public class ViewVideoActivity extends AppCompatActivity {

    private String videoUrl;
    private ActivityViewVideoBinding binding;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        if (intent != null) {
            this.videoUrl = intent.getStringExtra("video");
        }

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                releasePlayer();
                finish();
            }
        });

        Uri videoUri = Uri.parse(videoUrl);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(binding.videoView);
        binding.videoView.setMediaController(mediaController);
        binding.videoView.setVideoURI(videoUri);
        binding.videoView.requestFocus();
        binding.videoView.start();
    }



    private void releasePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }
}
