package com.example.Sanskrit;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorFragment extends Fragment {

    public ColorFragment() {
        // Required empty public constructor
    }
    private MediaPlayer mmediaplayer;
    private AudioManager maudioManager;

    AudioManager.OnAudioFocusChangeListener afchangelistener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mmediaplayer.pause();
                mmediaplayer.seekTo(0);
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mmediaplayer.start();
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }
        }
    };

    private MediaPlayer.OnCompletionListener mcompleteListner = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_word, container, false);
        maudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> colorword = new ArrayList<>();
        colorword.add(new Word("Red","लोहितः",R.drawable.color_red, R.raw.c_red));
        colorword.add(new Word("Green","हरितः",R.drawable.color_green, R.raw.c_green));
        colorword.add(new Word("Brown","कपिशः",R.drawable.color_brown, R.raw.c_brown));
        colorword.add(new Word("Grey","धूसरः",R.drawable.color_gray, R.raw.c_grey));
        colorword.add(new Word("Black","कालः",R.drawable.color_black, R.raw.c_black));
        colorword.add(new Word("White","श्वेतः",R.drawable.color_white, R.raw.c_white));
        colorword.add(new Word("Dusty Yellow","हरिद्राभः",R.drawable.color_dusty_yellow, R.raw.c_myellow));
        colorword.add(new Word("Mustard Yellow","पीतः",R.drawable.color_mustard_yellow, R.raw.c_myellow));

        WordAdapter colour_word_adapter = new WordAdapter(getActivity(),colorword, R.color.category_colors);
        ListView clv = rootView.findViewById(R.id.list);
        clv.setAdapter(colour_word_adapter);

        clv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = colorword.get(position);
                releaseMediaPlayer();
                int result = maudioManager.requestAudioFocus(afchangelistener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mmediaplayer = MediaPlayer.create(getActivity(), word.getAudioId());
                    mmediaplayer.start();
                    mmediaplayer.setOnCompletionListener(mcompleteListner);
                }
            }
        });
        return rootView;
    }

    private  void releaseMediaPlayer(){
        if(mmediaplayer != null){
            mmediaplayer.release();
            mmediaplayer = null;
            maudioManager.abandonAudioFocus(afchangelistener);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}