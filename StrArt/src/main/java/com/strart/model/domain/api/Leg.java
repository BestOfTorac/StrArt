
package com.strart.model.domain.api;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Leg {

    @SerializedName("distance")
    private Double mDistance;
    @SerializedName("duration")
    private Double mDuration;
    @SerializedName("steps")
    private List<Step> mSteps;
    @SerializedName("summary")
    private String mSummary;
    @SerializedName("weight")
    private Double mWeight;

    public Double getDistance() {
        return mDistance;
    }

    public Double getDuration() {
        return mDuration;
    }

    public List<Step> getSteps() {
        return mSteps;
    }

    public String getSummary() {
        return mSummary;
    }

    public Double getWeight() {
        return mWeight;
    }

    public static class Builder {

        private Double mDistance;
        private Double mDuration;
        private List<Step> mSteps;
        private String mSummary;
        private Double mWeight;

        public Leg.Builder withDistance(Double distance) {
            mDistance = distance;
            return this;
        }

        public Leg.Builder withDuration(Double duration) {
            mDuration = duration;
            return this;
        }

        public Leg.Builder withSteps(List<Step> steps) {
            mSteps = steps;
            return this;
        }

        public Leg.Builder withSummary(String summary) {
            mSummary = summary;
            return this;
        }

        public Leg.Builder withWeight(Double weight) {
            mWeight = weight;
            return this;
        }

        public Leg build() {
            Leg leg = new Leg();
            leg.mDistance = mDistance;
            leg.mDuration = mDuration;
            leg.mSteps = mSteps;
            leg.mSummary = mSummary;
            leg.mWeight = mWeight;
            return leg;
        }

    }

}
