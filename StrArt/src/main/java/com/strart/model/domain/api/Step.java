
package com.strart.model.domain.api;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Step {

    @SerializedName("distance")
    private Double mDistance;
    @SerializedName("driving_side")
    private String mDrivingSide;
    @SerializedName("duration")
    private Double mDuration;
    @SerializedName("geometry")
    private String mGeometry;
    @SerializedName("intersections")
    private List<Intersection> mIntersections;
    @SerializedName("maneuver")
    private Maneuver mManeuver;
    @SerializedName("mode")
    private String mMode;
    @SerializedName("name")
    private String mName;
    @SerializedName("weight")
    private Double mWeight;

    public Double getDistance() {
        return mDistance;
    }

    public String getDrivingSide() {
        return mDrivingSide;
    }

    public Double getDuration() {
        return mDuration;
    }

    public String getGeometry() {
        return mGeometry;
    }

    public List<Intersection> getIntersections() {
        return mIntersections;
    }

    public Maneuver getManeuver() {
        return mManeuver;
    }

    public String getMode() {
        return mMode;
    }

    public String getName() {
        return mName;
    }

    public Double getWeight() {
        return mWeight;
    }

    public static class Builder {

        private Double mDistance;
        private String mDrivingSide;
        private Double mDuration;
        private String mGeometry;
        private List<Intersection> mIntersections;
        private Maneuver mManeuver;
        private String mMode;
        private String mName;
        private Double mWeight;

        public Step.Builder withDistance(Double distance) {
            mDistance = distance;
            return this;
        }

        public Step.Builder withDrivingSide(String drivingSide) {
            mDrivingSide = drivingSide;
            return this;
        }

        public Step.Builder withDuration(Double duration) {
            mDuration = duration;
            return this;
        }

        public Step.Builder withGeometry(String geometry) {
            mGeometry = geometry;
            return this;
        }

        public Step.Builder withIntersections(List<Intersection> intersections) {
            mIntersections = intersections;
            return this;
        }

        public Step.Builder withManeuver(Maneuver maneuver) {
            mManeuver = maneuver;
            return this;
        }

        public Step.Builder withMode(String mode) {
            mMode = mode;
            return this;
        }

        public Step.Builder withName(String name) {
            mName = name;
            return this;
        }

        public Step.Builder withWeight(Double weight) {
            mWeight = weight;
            return this;
        }

        public Step build() {
            Step step = new Step();
            step.mDistance = mDistance;
            step.mDrivingSide = mDrivingSide;
            step.mDuration = mDuration;
            step.mGeometry = mGeometry;
            step.mIntersections = mIntersections;
            step.mManeuver = mManeuver;
            step.mMode = mMode;
            step.mName = mName;
            step.mWeight = mWeight;
            return step;
        }

    }

}
