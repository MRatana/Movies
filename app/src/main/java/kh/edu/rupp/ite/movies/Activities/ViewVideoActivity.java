package kh.edu.rupp.ite.movies.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import kh.edu.rupp.ite.movies.R;
import kh.edu.rupp.ite.movies.databinding.ActivityViewVideoBinding;

public class ViewVideoActivity extends AppCompatActivity {

    private String vdoUrl;
    private ActivityViewVideoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        if (intent != null) {
            vdoUrl = intent.getStringExtra("video");
        }
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        playVideo(vdoUrl);

    }


    private void playVideo(String url){
        Toast.makeText(this,url,Toast.LENGTH_SHORT).show();
        ExoPlayer player = new ExoPlayer.Builder(this).build();
        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(url));
        player.setMediaItem(mediaItem);
        binding.videoView.setPlayer(player);
        player.prepare();
        player.play();
    }


}