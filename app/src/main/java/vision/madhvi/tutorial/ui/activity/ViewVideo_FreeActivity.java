package vision.madhvi.tutorial.ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.VideoView;


import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSchemeDataSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSourceInputStream;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

import vision.madhvi.tutorial.R;

public class ViewVideo_FreeActivity extends Activity {
    VideoView videoView;
    String videourl, freevideoUrl;
    ProgressDialog progressDialog;
   // PlayerView playerView;
    ProgressBar progressBar;
    ImageView btFullscreen;
    //SimpleExoPlayer simpleExoPlayer;
    boolean flag = false;

    private   SimpleExoPlayer videoPlayer = null;
    private String sampleUrl = "https://www.learningcontainer.com/wp-content/uploads/2020/05/sample-mp4-file.mp4";
    private PlayerView video_Player_View;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_video__free);
        Intent intent = getIntent();

        freevideoUrl = intent.getStringExtra("freevideopath");
        progressBar = findViewById(R.id.progress_bar);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        btFullscreen = findViewById(R.id.bt_fullscreen);

        initializePlayer();

   /*
        playerView = findViewById(R.id.player_view);
        btFullscreen = findViewById(R.id.bt_fullscreen);

        // progressDialog = new ProgressDialog(View_Video_Activity.this);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //videoView = findViewById(R.id.Video_View);
        Intent intent = getIntent();
       // videourl = intent.getStringExtra("videourl");
        freevideoUrl = intent.getStringExtra("freevideopath");

        // String videoPath="android.resource://"+getPackageName()+"/"+R.raw.video;
        Uri freevurl = Uri.parse(freevideoUrl);
      //  Uri videoUrl = Uri.parse(videourl);
//        videoView.setVideoPath(String.valueOf(uri));
//        MediaController mediaController = new MediaController(this);
//        videoView.setMediaController(mediaController);
//        mediaController.setAnchorView(videoView);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //video url

        // Uri videoUrl = Uri.parse("https://www.madhvivision.com/Tutorials/10thScience_FOODANDHUMANNUTRITION_NUTRITION.MP4");

        //inistilize load

        LoadControl loadControl = new DefaultLoadControl();

        //initialize band

        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

        TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));

        // initialize simple exo player

        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(ViewVideo_FreeActivity.this, trackSelector, loadControl);

        // data source

        final DefaultHttpDataSourceFactory factory = new DefaultHttpDataSourceFactory("exoplayer_video");

        // initalize extrafactory

        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        // initilize mediasource
        //free video
        MediaSource mediaSourcefree = new ExtractorMediaSource(freevurl, factory, extractorsFactory, null, null);

        // free video end

        //setplayer
        playerView.setPlayer(simpleExoPlayer);

        //keep screen on
        playerView.setKeepScreenOn(true);
        //prepar media
        //free video
        simpleExoPlayer.prepare(mediaSourcefree);

        // free video end
        // play video when ready
        simpleExoPlayer.setPlayWhenReady(true); */

//        simpleExoPlayer.addListener(new Player.EventListener() {
//            @Override
//            public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {
//
//            }
//
//            @Override
//            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
//
//            }
//
//            @Override
//            public void onLoadingChanged(boolean isLoading) {
//
//            }
//
//            @Override
//            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
//                // check condition
//
//                if (playbackState == Player.STATE_BUFFERING) {
//                    // when buffering
//                    progressBar.setVisibility(View.VISIBLE);
//                } else if (playbackState == Player.STATE_READY) {
//                    progressBar.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onRepeatModeChanged(int repeatMode) {
//
//            }
//
//            @Override
//            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {
//
//            }
//
//            @Override
//            public void onPlayerError(ExoPlaybackException error) {
//
//            }
//
//            @Override
//            public void onPositionDiscontinuity(int reason) {
//
//            }
//
//            @Override
//            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
//
//            }
//
//            @Override
//            public void onSeekProcessed() {
//
//            }
//        });

      btFullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                ///check condition
                if (flag){
                    //is flase true
                    btFullscreen.setImageDrawable(getResources().getDrawable(R.drawable.ic_fullscreen));

                    //set paratite orienttation
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    //set flag value is false
                    flag=false;
                }else {
                    //flag is fals
                    //exit fullscreen set image

                    btFullscreen.setImageDrawable(getResources().getDrawable(R.drawable.ic_fullscreen_exit));
                    //set orientation
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    //set flage value
                    flag=true;
                }
    }
        });

        videoPlayer.addListener(new Player.Listener() {

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                // check condition

                if (playbackState == Player.STATE_BUFFERING) {
                    // when buffering
                    progressBar.setVisibility(View.VISIBLE);
                } else if (playbackState == Player.STATE_READY) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
       /*
        //stop video when ready
        simpleExoPlayer.setPlayWhenReady(false);
        //get plabacke state
        simpleExoPlayer.getPlaybackState();

        */

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // video when ready

        /*
        simpleExoPlayer.setPlayWhenReady(true);
        //get plabacke state
        simpleExoPlayer.getPlaybackState();

         */
    }


    private MediaSource buildMediaSource() {
        DataSource.Factory dataSourceFactory =new  DefaultDataSourceFactory(this, "sample");
        return new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(sampleUrl));
    }


    private void initializePlayer() {


          videoPlayer = new SimpleExoPlayer.Builder(this).build();
         video_Player_View =findViewById(R.id.video_player_view);

         video_Player_View.setPlayer(videoPlayer);
         videoPlayer.prepare(buildMediaSource());



    }

    @Override
    protected void onResume() {
        super.onResume();
        videoPlayer.setPlayWhenReady(true);
        videoPlayer.getPlaybackState();
    }

    @Override
    protected void onStop() {
        super.onStop();

        videoPlayer.setPlayWhenReady(false);
        videoPlayer.getPlaybackState();

//        if (isFinishing) {
//            releasePlayer();
//        }
    }

    private void releasePlayer() {
        videoPlayer.release();
    }
}