package com.example.Sanskrit;

public class Word {
    private String mDefaultTranslation;
    private String mSanskritTranslation;
    private int mImageResourceId = NoImage;
    private static final int NoImage = -1;
    private int maudio;

    public Word(String DefaultTranslation, String SanskritTranslation, int audio){
        mDefaultTranslation = DefaultTranslation;
        mSanskritTranslation = SanskritTranslation;
        maudio = audio;
    }
    public Word(String DefaultTranslation, String SanskritTranslation, int ImageResourceId, int audio){
        mDefaultTranslation = DefaultTranslation;
        mSanskritTranslation = SanskritTranslation;
        mImageResourceId = ImageResourceId;
        maudio = audio;
    }
    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }
    public String getSanskritTranslation(){
        return mSanskritTranslation;
    }
    public int getmImageResourceId(){
        return mImageResourceId;
    }
    public int getAudioId(){
        return maudio;
    }
    public boolean hasImage(){
        return mImageResourceId != NoImage;
    }
}
