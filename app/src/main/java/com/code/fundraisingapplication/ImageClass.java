package com.code.fundraisingapplication;

import android.graphics.drawable.Drawable;

public class ImageClass {


    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getGoal_title() {
        return Goal_title;
    }

    public void setGoal_title(String goal_title) {
        Goal_title = goal_title;
    }

    public String getGoal_short_description() {
        return Goal_short_description;
    }

    public void setGoal_short_description(String goal_short_description) {
        Goal_short_description = goal_short_description;
    }

    public String getGoal_targetamount() {
        return Goal_targetamount;
    }

    public void setGoal_targetamount(String goal_targetamount) {
        Goal_targetamount = goal_targetamount;
    }

    public String getGoal_category() {
        return Goal_category;
    }

    public void setGoal_category(String goal_category) {
        Goal_category = goal_category;
    }

    public ImageClass(Drawable image, String goal_title, String goal_short_description, String goal_targetamount, String goal_category) {
        this.image = image;
        Goal_title = goal_title;
        Goal_short_description = goal_short_description;
        Goal_targetamount = goal_targetamount;
        Goal_category = goal_category;
    }

    Drawable image;
    String Goal_title;
    String Goal_short_description;
    String Goal_targetamount;
    String Goal_category;

}