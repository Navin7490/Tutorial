package vision.madhvi.tutorial;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import vision.madhvi.tutorial.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

public class View_Video_Activity extends Activity {
    VideoView videoView;
    String videourl;
    ProgressDialog progressDialog;
    PlayerView playerView;
    ProgressBar progressBar;
    ImageView btFullscreen;
    SimpleExoPlayer simpleExoPlayer;
    boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__video_);

        playerView = findViewById(R.id.player_view);
        progressBar = findViewById(R.id.progress_bar);
        btFullscreen = findViewById(R.id.bt_fullscreen);

       // progressDialog = new ProgressDialog(View_Video_Activity.this);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //videoView = findViewById(R.id.Video_View);
        Intent intent = getIntent();
        videourl = intent.getStringExtra("videourl");

        // String videoPath="android.resource://"+getPackageName()+"/"+R.raw.video;

        Uri videoUrl = Uri.parse(videourl);
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

        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(View_Video_Activity.this, trackSelector, loadControl);

        // data source

        final DefaultHttpDataSourceFactory factory = new DefaultHttpDataSourceFactory("exoplayer_video");

        // initalize extrafactory

        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        // initilize mediasource

        MediaSource mediaSource = new ExtractorMediaSource(videoUrl, factory, extractorsFactory, null, null);

        //setplayer
        playerView.setPlayer(simpleExoPlayer);

        //keep screen on
        playerView.setKeepScreenOn(true);
        //prepar media
        simpleExoPlayer.prepare(mediaSource);
        // play video when ready
        simpleExoPlayer.setPlayWhenReady(true);

        simpleExoPlayer.addListener(new Player.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        // check condition

                if (playbackState==Player.STATE_BUFFERING){
                    // when buffering
                    progressBar.setVisibility(View.VISIBLE);
                }else if (playbackState==Player.STATE_READY){
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {

            }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity(int reason) {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }

            @Override
            public void onSeekProcessed() {

            }
        });

        btFullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  ///check condition
//                if (flag){
//                    //is flase true
//                    btFullscreen.setImageDrawable(getResources().getDrawable(R.drawable.ic_fullscreen));
//
//                    //set paratite orienttation
//                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                    //set flag value is false
//                    flag=false;
//                }else {
//                    //flag is fals
//                    //exit fullscreen set image
//
//                    btFullscreen.setImageDrawable(getResources().getDrawable(R.drawable.ic_fullscreen_exit));
//                    //set orientation
//                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                    //set flage value
//                    flag=true;
//                }
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        //stop video when ready
        simpleExoPlayer.setPlayWhenReady(false);
        //get plabacke state
        simpleExoPlayer.getPlaybackState();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // video when ready
        simpleExoPlayer.setPlayWhenReady(true);
        //get plabacke state
        simpleExoPlayer.getPlaybackState();
    }

    }
