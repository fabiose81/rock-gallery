    package com.fabiose.photovideo.fragments;

    import android.graphics.Color;
    import android.graphics.PorterDuff;
    import android.media.MediaPlayer;
    import android.net.Uri;
    import android.os.Bundle;
    import android.os.Handler;
    import android.support.v4.app.Fragment;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.MotionEvent;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;
    import android.widget.ImageButton;
    import android.widget.SeekBar;
    import android.widget.TextView;
    import android.widget.VideoView;

    import com.fabiose.photovideo.R;
    import com.fabiose.photovideo.utils.AnimationUtil;

    /**
     * Created by fabioestrela on 18-04-10.
     */

    public class VideoFragment extends Fragment {

        private VideoView videoRoots;
        private boolean visible = true;
        private int playing;
        private Handler handler = new Handler();
        private Runnable runnable;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_video, container, false);

            videoRoots = (VideoView)rootView.findViewById(R.id.videoViewRoots);
            final ImageButton imageButtonPlayPause = (ImageButton)rootView.findViewById(R.id.imageButtonPlayPause);
            final SeekBar seekBarVideo = (SeekBar)rootView.findViewById(R.id.seekBarVideo);
// android:progressDrawable="@drawable/progress_fill"
            final String uriPath = "android.resource://com.fabiose.photovideo/"+R.raw.roots;
            final Uri uri = Uri.parse(uriPath);

            videoRoots.setVideoURI(uri);
            videoRoots.requestFocus();

            videoRoots.setOnTouchListener(new View.OnTouchListener() {
                  @Override
                  public boolean onTouch(View view, MotionEvent motionEvent) {
                      if(!visible) {
                          seekBarVideo.setAnimation(AnimationUtil.animationFadeOut(300, 300));
                          seekBarVideo.setVisibility(View.VISIBLE);

                          imageButtonPlayPause.setAnimation(AnimationUtil.animationFadeOut(300, 300));
                          imageButtonPlayPause.setVisibility(View.VISIBLE);
                      }else {
                          seekBarVideo.setAnimation(AnimationUtil.animationFadeIn(300, 300));
                          seekBarVideo.setVisibility(View.INVISIBLE);

                          imageButtonPlayPause.setAnimation(AnimationUtil.animationFadeIn(300, 300));
                          imageButtonPlayPause.setVisibility(View.INVISIBLE);
                      }

                      visible = !visible;

                      return false;
                  }
            });

            imageButtonPlayPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(playing == 0){
                        playing = 1;
                        videoRoots.start();
                        seekBarVideo.setMax(videoRoots.getDuration());
                        refreshSeekBar(0);
                        imageButtonPlayPause.setImageResource(R.mipmap.iconpause);
                    }else if(playing == 1){
                        videoRoots.pause();
                        imageButtonPlayPause.setImageResource(R.mipmap.iconplay);
                        playing = 2;
                    }else if(playing == 2){
                        videoRoots.start();
                        imageButtonPlayPause.setImageResource(R.mipmap.iconpause);
                        playing = 1;
                    }
                }
            });

            seekBarVideo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    if(b)
                      videoRoots.seekTo(i);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {}

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {}
            });

            runnable = new Runnable() {
                @Override
                public void run() {
                    seekBarVideo.setProgress(videoRoots.getCurrentPosition());
                    refreshSeekBar(1000);
                }
            };

            return rootView;
        }

        public void refreshSeekBar(long time){
            handler.postDelayed(runnable, time);
        }

        public void pauseVideo(){
            videoRoots.pause();
        }
    }
