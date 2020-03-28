package interfaceAPI;


import model.Climate;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Dhanu on 16-08-2015.
 */
public interface WeatherAPI {
    @GET("/data/2.5/weather?lat=19.0760&lon=72.8777&appid=a5b0588422164548cbc25fdbec538b73")
    public void getWeather(Callback<Climate> response);
    @GET("/data/2.5/weather?lat=18.5204&lon=73.8567&appid=a5b0588422164548cbc25fdbec538b73")
    public void getWeather1(Callback<Climate> response);
    @GET("/data/2.5/weather?lat=21.1458&lon=79.0882&appid=a5b0588422164548cbc25fdbec538b73")
    public void getWeather2(Callback<Climate> response);
    @GET("/data/2.5/weather?lat=19.9975&lon=73.7898 &appid=a5b0588422164548cbc25fdbec538b73")
    public void getWeather3(Callback<Climate> response);
}
