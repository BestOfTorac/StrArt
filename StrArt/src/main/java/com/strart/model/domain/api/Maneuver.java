
package com.strart.model.domain.api;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Maneuver {

    @SerializedName("bearing_after")
    private Long mBearingAfter;
    @SerializedName("bearing_before")
    private Long mBearingBefore;
    @SerializedName("location")
    private List<Double> mLocation;
    @SerializedName("type")
    private String mType;

    public Long getBearingAfter() {
        return mBearingAfter;
    }

    public Long getBearingBefore() {
        return mBearingBefore;
    }

    public List<Double> getLocation() {
        return mLocation;
    }

    public String getType() {
        return mType;
    }

    public static class Builder {

        private Long mBearingAfter;
        private Long mBearingBefore;
        private List<Double> mLocation;
        private String mType;

        public Maneuver.Builder withBearingAfter(Long bearingAfter) {
            mBearingAfter = bearingAfter;
            return this;
        }

        public Maneuver.Builder withBearingBefore(Long bearingBefore) {
            mBearingBefore = bearingBefore;
            return this;
        }

        public Maneuver.Builder withLocation(List<Double> location) {
            mLocation = location;
            return this;
        }

        public Maneuver.Builder withType(String type) {
            mType = type;
            return this;
        }

        public Maneuver build() {
            Maneuver maneuver = new Maneuver();
            maneuver.mBearingAfter = mBearingAfter;
            maneuver.mBearingBefore = mBearingBefore;
            maneuver.mLocation = mLocation;
            maneuver.mType = mType;
            return maneuver;
        }

    }

}
