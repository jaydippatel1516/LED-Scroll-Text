package ledbanner.ledboard.ledscrollingtext.Activity;

import static ledbanner.ledboard.ledscrollingtext.Activity.Led_MakeVideoActrivity.arrayB;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.ads.nativetemplates.TemplateView;

import ledbanner.ledboard.ledscrollingtext.R;
import ledbanner.ledboard.ledscrollingtext.admodule.AllAdCommonClass;

import java.io.File;

public class Led_PreviewVideoActivity extends AppCompatActivity {

    File file;
    MediaController mediaControls;
    String path;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led_share_preview_video);
        ShimmerFrameLayout shimmer_view_container = findViewById(R.id.shimmer_view_container);
        AllAdCommonClass.NativeLoad(this, (TemplateView) findViewById(R.id.my_template), findViewById(R.id.fram_fbnative), shimmer_view_container);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Led_PreviewVideoActivity.this, Led_EditActivity.class));
                finish();
            }
        });
        videoView = findViewById(R.id.videoView);
        if (arrayB == 0) {
            path = getIntent().getStringExtra("vedio");
        } else {
            path = getIntent().getStringExtra("vedio");
        }
        file = new File(path);
        if (mediaControls == null) {
            mediaControls = new MediaController(this);
            mediaControls.setAnchorView(this.videoView);
        }
        mediaControls = new MediaController(this);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaControls.setAnchorView(videoView);
                videoView.start();
            }
        });
        videoView.setMediaController(this.mediaControls);
        if (arrayB == 0) {
            videoView.setVideoPath(this.path);

        } else {
            videoView.setVideoPath(this.path);

        }
        videoView.start();
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                return false;
            }
        });

    }


}