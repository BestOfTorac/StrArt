
package com.strart.model.domain.api;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Routes {

    @SerializedName("code")
    private String mCode;
    @SerializedName("routes")
    private List<Route> mRoutes;
    @SerializedName("waypoints")
    private List<Waypoint> mWaypoints;

    public String getCode() {
        return mCode;
    }

    public List<Route> getRoutes() {
        return mRoutes;
    }

    public List<Waypoint> getWaypoints() {
        return mWaypoints;
    }

    public static class Builder {

        private String mCode;
        private List<Route> mRoutes;
        private List<Waypoint> mWaypoints;

        public Routes.Builder withCode(String code) {
            mCode = code;
            return this;
        }

        public Routes.Builder withRoutes(List<Route> routes) {
            mRoutes = routes;
            return this;
        }

        public Routes.Builder withWaypoints(List<Waypoint> waypoints) {
            mWaypoints = waypoints;
            return this;
        }

        public Routes build() {
            Routes routes = new Routes();
            routes.mCode = mCode;
            routes.mRoutes = mRoutes;
            routes.mWaypoints = mWaypoints;
            return routes;
        }

    }

}
