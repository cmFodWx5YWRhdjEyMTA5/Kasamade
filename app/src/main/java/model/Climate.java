package model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Dhanu on 20-Jan-16.
 */
public class Climate implements Serializable {

    Coord coord;
    List<Weather> weather;
    Main main;
    Sys sys;
    String name;
    String cod;
    Long dt;

    public Climate() {
    }

    public Climate(Coord coord, List<Weather> weather, Main main, Sys sys, String name, String cod, Long dt) {
        this.coord = coord;
        this.weather = weather;
        this.main = main;
        this.sys = sys;
        this.name = name;
        this.cod = cod;
        this.dt=dt;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }
}
