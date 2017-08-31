package com.example.android.miwok;

/**
 * Created by chong chen on 2017/8/21.
 */

public class Word {

    private String mMiwokText;

    private String mDefaultText;

    private int mImageID = NO_IMAGE_RESOURCE;

    private int mAudioID = NO_AUDIO_RESOURCE;

    private static final int NO_IMAGE_RESOURCE = -1;

    private static final int NO_AUDIO_RESOURCE = -1;

    public Word(String defaultText, String miwokText) {
        mMiwokText = miwokText;
        mDefaultText = defaultText;
    }

    public Word(String defaultText, String miwokText, int audioID) {
        mMiwokText = miwokText;
        mDefaultText = defaultText;
        mAudioID = audioID;
    }

    public Word(String defaultText, String miwokText, int imageID, int audioID) {
        mMiwokText = miwokText;
        mDefaultText = defaultText;
        mImageID = imageID;
        mAudioID = audioID;
    }

    /**
     * get miwok text info
     */
    public String getMiwokText() {
        return mMiwokText;
    }

    /**
    * get the default text info, e.g. english
    */
    public String getDefaultText() {
        return mDefaultText;
    }

    /**
     * get the image resource id
     * */
    public int getImageID() { return mImageID; }

    /**
     * Return whether or not there is an image for this word
     * @return ture if have image resource, false for no image resource
     */
    public boolean hasImage() {
        return mImageID != NO_IMAGE_RESOURCE;
    }

    /**
     *
     * @return audio resource id
     */
    public int getAudioID() {
        return mAudioID;
    }

    /**
     *
     * @return ture if have audio resource, false for no audio resource
     */
    public boolean hasAudio() {
        return mAudioID != NO_AUDIO_RESOURCE;
    }
}
