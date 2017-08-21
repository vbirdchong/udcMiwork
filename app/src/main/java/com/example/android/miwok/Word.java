package com.example.android.miwok;

/**
 * Created by chong chen on 2017/8/21.
 */

public class Word {

    private String mMiwokText;

    private String mDefaultText;

    public Word(String defaultText, String miwokText) {
        mMiwokText = miwokText;
        mDefaultText = defaultText;
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

}
