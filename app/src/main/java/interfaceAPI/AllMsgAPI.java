package interfaceAPI;


import java.util.List;
import java.util.Map;

import model.MessageDetail;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.QueryMap;

/**
 * Created by Dhanu on 16-08-2015.
 */
public interface AllMsgAPI {
    @GET("/get_all_products.php")
    public  void getAllMsg(@QueryMap Map<String, String> link, Callback<List<MessageDetail>> response);

    @GET("/get_video.php")
    public  void getAllVideo(@QueryMap Map<String, String> link, Callback<List<MessageDetail>> response);

    @GET("/offer.php")
    public  void getAllOffer(@QueryMap Map<String, String> link, Callback<List<MessageDetail>> response);

    @GET("/place.php")
    public  void getAllplace(@QueryMap Map<String, String> link, Callback<List<MessageDetail>> response);

    @GET("/birthday.php")
    public  void getAllbirthday(@QueryMap Map<String, String> link, Callback<List<MessageDetail>> response);

    @GET("/death.php")
    public  void getAlldeath(@QueryMap Map<String, String> link, Callback<List<MessageDetail>> response);

    @GET("/science.php")
    public  void getAllscience(@QueryMap Map<String, String> link, Callback<List<MessageDetail>> response);

}
