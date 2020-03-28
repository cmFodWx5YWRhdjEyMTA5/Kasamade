package model;

import java.io.Serializable;

/**
 * Created by Dhanu on 20-Jan-16.
 */
public class Coord implements Serializable {
    Double lon;
    Double lat;

    public Coord() {
    }

    public Coord(Double lon, Double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLst(Double lat) {
        this.lat = lat;
    }
}
