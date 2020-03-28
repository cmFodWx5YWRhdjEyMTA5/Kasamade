package interfaceAPI;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Sachin Khairnar on 27-06-2017.
 */

public interface login {

    @FormUrlEncoded
    @POST("/insert_member.php")
    public  void insertMember(
            @Field("link") String link,
            Callback<String> response);
}
