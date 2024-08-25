
package com.strart.model.domain.api;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Waypoint {

    @SerializedName("distance")
    private Double mDistance;
    @SerializedName("hint")
    private String mHint;
    @SerializedName("location")
    private List<Double> mLocation;
    @SerializedName("name")
    private String mName;

    public Double getDistance() {
        return mDistance;
    }

    public String getHint() {
        return mHint;
    }

    public List<Double> getLocation() {
        return mLocation;
    }

    public String getName() {
        return mName;
    }

    public static class Builder {

        private Double mDistance;
        private String mHint;
        private List<Double> mLocation;
        private String mName;

        public Waypoint.Builder withDistance(Double distance) {
            mDistance = distance;
            return this;
        }

        public Waypoint.Builder withHint(String hint) {
            mHint = hint;
            return this;
        }

        public Waypoint.Builder withLocation(List<Double> location) {
            mLocation = location;
            return this;
        }

        public Waypoint.Builder withName(String name) {
            mName = name;
            return this;
        }

        public Waypoint build() {
            Waypoint waypoint = new Waypoint();
            waypoint.mDistance = mDistance;
            waypoint.mHint = mHint;
            waypoint.mLocation = mLocation;
            waypoint.mName = mName;
            return waypoint;
        }

    }

}
