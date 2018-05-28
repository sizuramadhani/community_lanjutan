package com.udakita.komunitas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by leonardo.siagian on 5/8/2017.
 */

public class ResponseRoute {
    /*
        mengikuti struktur result json
        dari contoh : https://maps.googleapis.com/maps/api/directions/json?origin=%22+%20-6.2312924,106.8167499+%22&destination=%22-6.249801,106.8704773+%22&sensor=true
    */
    @SerializedName("routes")
    @Expose
    ArrayList<Kedua> route = new ArrayList();

    public ArrayList<Kedua> getRoute() {
        return route;
    }

    public void setRoute(ArrayList<Kedua> route) {
        this.route = route;
    }

    public class Kedua {
        @SerializedName("legs")
        @Expose
        private ArrayList<Jarak> legs = new ArrayList<>();

        public ArrayList<Jarak> getLegs() {
            return legs;
        }

        public void setLegs(ArrayList<Jarak> legs) {
            this.legs = legs;
        }

        public class Jarak {
            @SerializedName("distance")
            @Expose
            private Distances distance;

            public class Distances {
                @SerializedName("text")
                @Expose
                String text;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                @SerializedName("value")
                @Expose
                Double value;

                public Double getValue() {
                    return value;
                }

                public void setValue(Double value) {
                    this.value = value;
                }
            }

            public Distances getDistance() {
                return distance;
            }

            public void setDistance(Distances distance) {
                this.distance = distance;
            }

            @SerializedName("duration")
            @Expose
            private Durations duration;

            public class Durations {
                @SerializedName("text")
                @Expose
                String text;

                public String getText() {
                    return text;
                }

                public void setText(String text) {
                    this.text = text;
                }

                @SerializedName("value")
                @Expose
                Double value;

                public Double getValue() {
                    return value;
                }

                public void setValue(Double value) {
                    this.value = value;
                }
            }

            public Durations getDuration() {
                return duration;
            }

            public void setDuration(Durations duration) {
                this.duration = duration;
            }
        }


        @SerializedName("overview_polyline")
        @Expose
        Poly poly;

        public Poly getPoly() {
            return poly;
        }

        public void setPoly(Poly poly) {
            this.poly = poly;
        }

        public class Poly {
            @SerializedName("points")
            @Expose
            String point;

            public String getPoint() {
                return point;
            }

            public void setPoint(String point) {
                this.point = point;
            }
        }
    }
}
