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
public class PhrasesFragment extends Fragment {

    public PhrasesFragment() {
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
        final ArrayList<Word> phrasesword= new ArrayList<>();
        phrasesword.add(new Word("Good Morning","सुप्रभातम्", R.raw.p_goodmorning));
        phrasesword.add(new Word("Good Night","शुभ रात्रि:", R.raw.p_goodnight));
        phrasesword.add(new Word("Good Evening","शुभ सायकाल", R.raw.p_goodevening));
        phrasesword.add(new Word("How are you?","कथमस्ति भवान्?", R.raw.p_howareyou));
        phrasesword.add(new Word("I am fine.","अहं कुशली", R.raw.p_iamfine));
        phrasesword.add(new Word("What's your name?","तव नाम किम्?", R.raw.p_whatisyourname));
        phrasesword.add(new Word("My name is Ram","मम नाम राम?", R.raw.p_mynameisraj)); //wrong name!
        phrasesword.add(new Word("Have a nice day."," सुदिनमस्तु  ", R.raw.p_howcanidothis)); //wrong entry!
        phrasesword.add(new Word("Have a good journey.","शुभयात्रा", R.raw.p_haveagoodjourney));
        phrasesword.add(new Word("Where are you from?","भवन कुत्रत्यः", R.raw.p_whereareyoufrom));
        WordAdapter phrases_word_adapter = new WordAdapter(getActivity(),phrasesword, R.color.category_phrases);
        ListView plv = rootView.findViewById(R.id.list);
        plv.setAdapter(phrases_word_adapter);

        plv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = phrasesword.get(position);
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

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private  void releaseMediaPlayer(){
        if(mmediaplayer != null){
            mmediaplayer.release();
            mmediaplayer = null;
            maudioManager.abandonAudioFocus(afchangelistener);
        }
    }
}
