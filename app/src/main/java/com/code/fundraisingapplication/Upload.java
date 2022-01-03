package com.code.fundraisingapplication;

public class Upload {


    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmTargetAmount() {
        return mTargetAmount;
    }

    public void setmTargetAmount(String mTargetAmount) {
        this.mTargetAmount = mTargetAmount;
    }

    private String mName;
    private String mImageUrl;
    private String mDescription;
    private String mTargetAmount;

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    private String mCategory;
    private String mStatus;

    public Upload(){
        //empty
    }
    public Upload(String imageUrl,String name,String description,String TargetAmount,String Category,String ActiveStatus){
        if(name.trim().equals("")){
            name="No Name";
        }
        mName=name;
        mImageUrl=imageUrl;
        mDescription=description;
        mTargetAmount=TargetAmount;
        mCategory=Category;
        mStatus=ActiveStatus;
    }



}
