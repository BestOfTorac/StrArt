
package com.strart.model.domain.api;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Route {

    @SerializedName("distance")
    private Double mDistance;
    @SerializedName("duration")
    private Double mDuration;
    @SerializedName("legs")
    private List<Leg> mLegs;
    @SerializedName("weight")
    private Double mWeight;
    @SerializedName("weight_name")
    private String mWeightName;

    public Double getDistance() {
        return mDistance;
    }

    public Double getDuration() {
        return mDuration;
    }

    public List<Leg> getLegs() {
        return mLegs;
    }

    public Double getWeight() {
        return mWeight;
    }

    public String getWeightName() {
        return mWeightName;
    }

    public static class Builder {

        private Double mDistance;
        private Double mDuration;
        private List<Leg> mLegs;
        private Double mWeight;
        private String mWeightName;

        public Route.Builder withDistance(Double distance) {
            mDistance = distance;
            return this;
        }

        public Route.Builder withDuration(Double duration) {
            mDuration = duration;
            return this;
        }

        public Route.Builder withLegs(List<Leg> legs) {
            mLegs = legs;
            return this;
        }

        public Route.Builder withWeight(Double weight) {
            mWeight = weight;
            return this;
        }

        public Route.Builder withWeightName(String weightName) {
            mWeightName = weightName;
            return this;
        }

        public Route build() {
            Route route = new Route();
            route.mDistance = mDistance;
            route.mDuration = mDuration;
            route.mLegs = mLegs;
            route.mWeight = mWeight;
            route.mWeightName = mWeightName;
            return route;
        }

    }

}
