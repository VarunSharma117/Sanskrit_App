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
public class NumbersFragment extends Fragment {
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

    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_word, container, false);

        maudioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> numword = new ArrayList<>();
        numword.add(new Word("One","एकः",R.drawable.r1, R.raw.a1));
        numword.add(new Word("Two","द्वौ",R.drawable.r2, R.raw.a2));
        numword.add(new Word("Three","त्रयः",R.drawable.r3, R.raw.a3));
        numword.add(new Word("Four","चत्वारः",R.drawable.r4, R.raw.a4));
        numword.add(new Word("Five","पञ्च",R.drawable.r5, R.raw.a5));
        numword.add(new Word("Six","षट्",R.drawable.r6, R.raw.a6));
        numword.add(new Word("Seven","सप्त",R.drawable.r7, R.raw.a7));
        numword.add(new Word("Eight","अष्ट",R.drawable.r8, R.raw.a8));
        numword.add(new Word("Nine","नव",R.drawable.r9, R.raw.a9));
        numword.add(new Word("Ten","दश",R.drawable.r10, R.raw.a10));
        numword.add(new Word("Eleven","एकादशन्",R.drawable.r11, R.raw.a11));
        numword.add(new Word("Twelve","द्वादशन्",R.drawable.r12, R.raw.a12));
        numword.add(new Word("Thirteen","त्रयोदशन्",R.drawable.r13, R.raw.a13));
        numword.add(new Word("Fourteen","चतुर्दशन्",R.drawable.r14, R.raw.a14));
        numword.add(new Word("Fifteen","पञ्चदशन्",R.drawable.r15, R.raw.a15));
        numword.add(new Word("Sixteen","षोडशन्",R.drawable.r16, R.raw.a16));
        numword.add(new Word("Seventeen","सप्तदशन्",R.drawable.r17, R.raw.a17));
        numword.add(new Word("Eighteen","अष्टादशन्",R.drawable.r18, R.raw.a18));
        numword.add(new Word("Nineteen","नवदशन्",R.drawable.r19, R.raw.a19));
        numword.add(new Word("Twenty","विंशति",R.drawable.r20, R.raw.a20));
        numword.add(new Word("Twenty one","एकाविंशति",R.drawable.r21, R.raw.a21));
        numword.add(new Word("Twenty Two","द्वाविंशति",R.drawable.r22, R.raw.a22));
        numword.add(new Word("Twenty Three","त्रयोविंशति",R.drawable.r23, R.raw.a23));
        numword.add(new Word("Twenty Four","चतुर्विंशति",R.drawable.r24, R.raw.a24));
        numword.add(new Word("Twenty Five","पञ्चविंशति",R.drawable.r25, R.raw.a25));
        numword.add(new Word("Twenty Six","षड्विंशति",R.drawable.r26, R.raw.a26));
        numword.add(new Word("Twenty Seven","सप्तविंशति",R.drawable.r27, R.raw.a27));
        numword.add(new Word("Twenty Eight","अष्टाविंशति",R.drawable.r28, R.raw.a28));
        numword.add(new Word("Twenty Nine","नवविंशति",R.drawable.r29, R.raw.a29));
        numword.add(new Word("Thirty","त्रिंशत्",R.drawable.r30, R.raw.a30));
        numword.add(new Word("Thirty One","एकत्रिंशत्",R.drawable.r31, R.raw.a31));
        numword.add(new Word("Thirty Two","द्वात्रिंशत्",R.drawable.r32, R.raw.a32));
        numword.add(new Word("Thirty Three","त्रयत्रिंशत्",R.drawable.r33, R.raw.a33));
        numword.add(new Word("Thirty Four","चतुस्त्रिंशत्",R.drawable.r34, R.raw.a34));
        numword.add(new Word("Thirty Five","पञ्चत्रिंशत्",R.drawable.r35, R.raw.a35));
        numword.add(new Word("Thirty Six","षट्त्रिंशत्",R.drawable.r36, R.raw.a36));
        numword.add(new Word("Thirty Seven","सप्तत्रिंशत्",R.drawable.r37, R.raw.a37));
        numword.add(new Word("Thirty Eight","अष्टात्रिंशत्",R.drawable.r38, R.raw.a38));
        numword.add(new Word("Thirty Nine","एकोनचत्वारिंशत्",R.drawable.r39, R.raw.a39));
        numword.add(new Word("Forty","चत्वारिंशत्",R.drawable.r40, R.raw.a40));
        numword.add(new Word("Forty One","एकचत्वारिंशत्",R.drawable.r41, R.raw.a41));
        numword.add(new Word("Forty Two","द्विचत्वारिंशत्",R.drawable.r42, R.raw.a42));
        numword.add(new Word("Forty Three","त्रिचत्वारिंशत्",R.drawable.r43, R.raw.a43));
        numword.add(new Word("Forty Four","चतुश्चत्वारिंशत्",R.drawable.r44, R.raw.a44));
        numword.add(new Word("Forty Five","पञ्चचत्वारिंशत्",R.drawable.r45, R.raw.a45));
        numword.add(new Word("Forty Six","षट्चत्वारिंशत्",R.drawable.r46, R.raw.a46));
        numword.add(new Word("Forty Seven","सप्तचत्वारिंशत्",R.drawable.r47, R.raw.a47));
        numword.add(new Word("Forty Eight","अष्टचत्वारिंशत्",R.drawable.r48, R.raw.a48));
        numword.add(new Word("Forty Nine","एकोनपञ्चाशत्",R.drawable.r49, R.raw.a49));
        numword.add(new Word("Fifty ","पञ्चाशत्",R.drawable.r50, R.raw.a50));
        numword.add(new Word("Fifty One","एकपञ्चाशत्",R.drawable.r51, R.raw.a51));
        numword.add(new Word("Fifty Two","द्विपञ्चाशत्",R.drawable.r52, R.raw.a52));
        numword.add(new Word("Fifty Three","त्रिपञ्चाशत्",R.drawable.r53, R.raw.a53));
        numword.add(new Word("Fifty Four","चतुःपञ्चाशत्",R.drawable.r54, R.raw.a54));
        numword.add(new Word("Fifty Five","पञ्चपञ्चाशत्",R.drawable.r55, R.raw.a55));
        numword.add(new Word("Fifty Six","षट्पञ्चाशत्",R.drawable.r56, R.raw.a56));
        numword.add(new Word("Fifty Seven","सप्तपञ्चाशत्",R.drawable.r57, R.raw.a57));
        numword.add(new Word("Fifty Eight","अष्टपञ्चाशत्",R.drawable.r58, R.raw.a58));
        numword.add(new Word("Fifty Nine","एकोनषष्ठिः",R.drawable.r59, R.raw.a59));
        numword.add(new Word("Sixty ","षष्ठिः",R.drawable.r60, R.raw.a60));
        numword.add(new Word("Sixty One","एकषष्ठिः",R.drawable.r61, R.raw.a61));
        numword.add(new Word("Sixty Two","द्विषष्ठिः",R.drawable.r62, R.raw.a62));
        numword.add(new Word("Sixty Three","त्रिषष्ठिः",R.drawable.r63, R.raw.a63));
        numword.add(new Word("Sixty Four","चतुःषष्ठिः",R.drawable.r64, R.raw.a64));
        numword.add(new Word("Sixty Five","पञ्चषष्ठिः",R.drawable.r65, R.raw.a65));
        numword.add(new Word("Sixty Six","षट्षष्ठिः",R.drawable.r66, R.raw.a66));
        numword.add(new Word("Sixty Seven","सप्तषष्ठिः",R.drawable.r67, R.raw.a67));
        numword.add(new Word("Sixty Eight","अष्टषष्ठिः",R.drawable.r68, R.raw.a68));
        numword.add(new Word("Sixty Nine","एकोनसप्ततिः",R.drawable.r69, R.raw.a69));
        numword.add(new Word("Seventy","सप्ततिः",R.drawable.r70, R.raw.a70));
        numword.add(new Word("Seventy One","एकसप्ततिः",R.drawable.r71, R.raw.a71));
        numword.add(new Word("Seventy Two","द्विसप्ततिः",R.drawable.r72, R.raw.a72));
        numword.add(new Word("Seventy Three","त्रिसप्ततिः",R.drawable.r73, R.raw.a73));
        numword.add(new Word("Seventy Four","चतुःसप्ततिः",R.drawable.r74, R.raw.a74));
        numword.add(new Word("Seventy Five","पञ्चसप्ततिः",R.drawable.r75, R.raw.a75));
        numword.add(new Word("Seventy Six","षट्सप्ततिः",R.drawable.r76, R.raw.a76));
        numword.add(new Word("Seventy Seven","सप्तसप्ततिः",R.drawable.r77, R.raw.a77));
        numword.add(new Word("Seventy Eight","अष्टसप्ततिः",R.drawable.r78, R.raw.a78));
        numword.add(new Word("Seventy Nine","एकोनाशीतिः",R.drawable.r79, R.raw.a79));
        numword.add(new Word("Eighty","अशीतिः",R.drawable.r80, R.raw.a80));
        numword.add(new Word("Eighty One","एकाशीतिः",R.drawable.r81, R.raw.a81));
        numword.add(new Word("Eighty Two","द्वशीतिः",R.drawable.r82, R.raw.a82));
        numword.add(new Word("Eighty Three","त्र्यशीतिः",R.drawable.r83, R.raw.a83));
        numword.add(new Word("Eighty Four","चतुरशीतिः",R.drawable.r84, R.raw.a84));
        numword.add(new Word("Eighty Five","पञ्चाशीतिः",R.drawable.r85, R.raw.a85));
        numword.add(new Word("Eighty Six","षडशीतिः",R.drawable.r86, R.raw.a86));
        numword.add(new Word("Eighty Seven","सप्ताशीतिः",R.drawable.r87, R.raw.a87));
        numword.add(new Word("Eighty Eight","अष्टाशीतिः",R.drawable.r88, R.raw.a88));
        numword.add(new Word("Eighty Nine","एकोननवतिः",R.drawable.r89, R.raw.a89));
        numword.add(new Word("Ninety","नवतिः",R.drawable.r90, R.raw.a90));
        numword.add(new Word("Ninety One","एकनवतिः",R.drawable.r91, R.raw.a91));
        numword.add(new Word("Ninety Two","द्विनवतिः",R.drawable.r92, R.raw.a92));
        numword.add(new Word("Ninety Three","त्रिनवतिः",R.drawable.r93, R.raw.a93));
        numword.add(new Word("Ninety Four","चतुर्नवतिः",R.drawable.r94, R.raw.a94));
        numword.add(new Word("Ninety Five","पञ्चनवतिः",R.drawable.r95, R.raw.a95));
        numword.add(new Word("Ninety Six","षण्णवतिः",R.drawable.r96, R.raw.a96));
        numword.add(new Word("Ninety Seven","सप्तनवतिः",R.drawable.r97, R.raw.a97));
        numword.add(new Word("Ninety Eight","अष्टनवतिः",R.drawable.r98, R.raw.a98));
        numword.add(new Word("Ninety Nine","एकोनशतम्",R.drawable.r99, R.raw.a99));
        numword.add(new Word("Hundred","शतम्",R.drawable.r100, R.raw.a100));



        WordAdapter itemsAdapter = new WordAdapter(getActivity(), numword, R.color.category_numbers);

        ListView listView = rootView.findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer();
                Word word = numword.get(position);
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
