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
public class FamilyFragment extends Fragment {

    public FamilyFragment() {
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
        final ArrayList<Word> familyword = new ArrayList<>();
        familyword.add(new Word("Father","पिता",R.drawable.family_father, R.raw.f_father));
        familyword.add(new Word("Mother","माता",R.drawable.family_mother, R.raw.f_mother));
        familyword.add(new Word("Son","पुत्रः",R.drawable.family_son, R.raw.f_son));
        familyword.add(new Word("Daughter","पुत्री",R.drawable.family_daughter, R.raw.f_daughter));
        familyword.add(new Word("Older Brother","ज्येष्ठभ्राता",R.drawable.family_older_brother, R.raw.f_elderbrother));
        familyword.add(new Word("Younger Brother","कनिष्ठभ्राता",R.drawable.family_younger_brother, R.raw.f_youngerbrother));
        familyword.add(new Word("Older Sister ","ज्येष्ठभगिनी",R.drawable.family_older_sister, R.raw.f_eldersister));
        familyword.add(new Word("Younger Sister","कनिष्ठभगिनी",R.drawable.family_younger_sister, R.raw.f_youngersister));
        familyword.add(new Word("GrandMother","पितामही",R.drawable.family_grandmother, R.raw.f_grandmother));
        familyword.add(new Word("GrandFather","पितामहः",R.drawable.family_grandfather, R.raw.f_grandfather));
        WordAdapter family_word_adapter = new WordAdapter(getActivity(),familyword, R.color.category_family);
        ListView flv = rootView.findViewById(R.id.list);
        flv.setAdapter(family_word_adapter);

        flv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = familyword.get(position);
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

