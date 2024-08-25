
package com.strart.model.domain.api;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Intersection {

    @SerializedName("bearings")
    private List<Long> mBearings;
    @SerializedName("location")
    private List<Double> mLocation;
    @SerializedName("out")
    private Long mOut;

    public List<Long> getBearings() {
        return mBearings;
    }

    public List<Double> getLocation() {
        return mLocation;
    }

    public Long getOut() {
        return mOut;
    }

    public static class Builder {

        private List<Long> mBearings;
        private List<Double> mLocation;
        private Long mOut;

        public Intersection.Builder withBearings(List<Long> bearings) {
            mBearings = bearings;
            return this;
        }

        public Intersection.Builder withLocation(List<Double> location) {
            mLocation = location;
            return this;
        }

        public Intersection.Builder withOut(Long out) {
            mOut = out;
            return this;
        }

        public Intersection build() {
            Intersection intersection = new Intersection();
            intersection.mBearings = mBearings;
            intersection.mLocation = mLocation;
            intersection.mOut = mOut;
            return intersection;
        }

    }

}
