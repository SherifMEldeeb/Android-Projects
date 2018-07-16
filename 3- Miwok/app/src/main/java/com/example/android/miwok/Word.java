package com.example.android.miwok;

/**
 * Created by Sherif M. Eldeeb on 3/30/2018.
 */

public class Word {
    /** the original & Translated  words  */
    private String mDefaultTranslation;
    private String mMiwokTranslation;

    /** variable to state whether the view has an image or not **/
    private static final int NO_IMAGE = -1;
    /**     * the image & audio  ids;     */
    private int mImageId = NO_IMAGE;
    private int mAudioId;

    /**        the instructions to the Class...     */
    public Word(String OriginalWord, String Miwokword,int ImageId,int Audio ) {
        mDefaultTranslation = OriginalWord;
        mMiwokTranslation = Miwokword;
        mImageId = ImageId;
        mAudioId = Audio;
    }

    /**     the instrctions for non image Activities    */
    public Word(String OriginalWord, String MiwokWord,int Audio){
        mDefaultTranslation = OriginalWord;
        mMiwokTranslation = MiwokWord;
        mAudioId = Audio;

    }

    /**     *     */
    public boolean hasImage(){
        return mImageId != NO_IMAGE;
    }

    /**        Get the Default Translation method.    */
    public String getoriginal(){
        return mDefaultTranslation;
    }

    /**     * Get the Miwok Translation Method.     */
    public String getMiwok(){
        return mMiwokTranslation;
    }

    /**     * get the image Id...     */
    public int getResourceId(){
        return mImageId;
    }
    /** get the audio */
    public int getAudioId(){    return mAudioId;    };
}