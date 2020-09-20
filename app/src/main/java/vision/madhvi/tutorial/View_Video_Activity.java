package vision.madhvi.tutorial;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.tutorial.R;

public class View_Video_Activity extends Activity {
     VideoView videoView;
     String videourl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__video_);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        videoView=findViewById(R.id.Video_View);

        Intent intent=getIntent();
        videourl=intent.getStringExtra("videourl");

       // String videoPath="android.resource://"+getPackageName()+"/"+R.raw.video;
        Uri uri=Uri.parse(videourl);
        videoView.setVideoPath(String.valueOf(uri));

        MediaController mediaController=new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }
}