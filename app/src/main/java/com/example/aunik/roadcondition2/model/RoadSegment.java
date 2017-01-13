package com.example.aunik.roadcondition2.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by aunik on 1/5/17.
 */
public class RoadSegment {
    @SerializedName("placeId")
    private String placeId;

    @SerializedName("condition")
    private int condition;

    @SerializedName("images")
    private List<String> images;

    @SerializedName("comments")
    private List<String> comments;

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public RoadSegment(String placeId, int condition, List<String> images, List<String> comments) {

        this.placeId = placeId;
        this.condition = condition;
        this.images = images;
        this.comments = comments;
    }

    public String getPlaceId() {

        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
