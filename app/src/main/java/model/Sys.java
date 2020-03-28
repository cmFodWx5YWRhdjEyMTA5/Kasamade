package model;

import java.io.Serializable;

/**
 * Created by Dhanu on 20-Jan-16.
 */
public class Sys implements Serializable {
String country;
    Long sunrise;
    Long sunset;

    public Sys() {
    }

    public Sys(String country, Long sunrise, Long sunset) {
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getSunrise() {
        return sunrise;
    }

    public void setSunrise(Long sunrise) {
        this.sunrise = sunrise;
    }

    public Long getSunset() {
        return sunset;
    }

    public void setSunset(Long sunset) {
        this.sunset = sunset;
    }
}
