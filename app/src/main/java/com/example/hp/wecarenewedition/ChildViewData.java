package com.example.hp.wecarenewedition;

public class ChildViewData  {
    public int mImageIdl;
    public String mCheck; //checkup last date text
    public String mCheckup; // last date
    public String mLastCheckup; //text time till last
    public String mTimeTillLastCheckup; // time
    public String mServiceType; //header
    public String mRemarks; //remarks text
    public String mRemarksType; // remarks
    public String mPredicted; // text predicted
    public String mPredictedValue; // value
    public String randomSugar; // text head
    public String randomSugarValue; // value


    ChildViewData (int mImageIdl, String mCheck, String mCheckup, String mLastCheckup, String mTimeTillLastCheckup
                 , String mServiceType, String mRemarks, String mRemarksType, String mPredicted, String mPredictedValue,
                   String randomSugar, String randomSugarValue){

        this.mImageIdl = mImageIdl;
        this.mCheck = mCheck;
        this.mCheckup = mCheckup;
        this.mLastCheckup = mLastCheckup;
        this.mTimeTillLastCheckup = mTimeTillLastCheckup;
        this.mServiceType = mServiceType;
        this.mRemarks = mRemarks;
        this.mRemarksType = mRemarksType;
        this.mPredicted = mPredicted;
        this.mPredictedValue = mPredictedValue;
        this.randomSugar = randomSugar;
        this.randomSugarValue =randomSugarValue;

    }

}
