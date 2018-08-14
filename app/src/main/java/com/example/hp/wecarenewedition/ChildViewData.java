package com.example.hp.wecarenewedition;

public class ChildViewData  {
    public int mImageIdl;
    public int mCheck;
    public int mCheckup;
    public int mLastCheckup;
    public int mTimeTillLastCheckup;
    public int mServiceType;
    public int mRemarks;
    public int mRemarksType;
    public int mPredicted;
    public int mPredictedValue;
    public int randomSugar;
    public int randomSugarValue;


    ChildViewData (int mImageIdl,int mCheck,int mCheckup,int mLastCheckup,int mTimeTillLastCheckup
                 ,int mServiceType,int mRemarks,int mRemarksType,int mPredicted,int mPredictedValue,
                   int randomSugar,int randomSugarValue){

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
