package Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.kasamade.news.R;
import com.kasamade.news.Splash_Screen_Activity;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import interfaceAPI.WeatherAPI;
import model.Climate;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;

import static AllFragment.KalavanFragment.ENDPOINT;

/**
 * Created by Dhanu on 12/9/2015.
 */
public class WeatherRecyclerViewAdapter extends RecyclerView.Adapter<WeatherRecyclerViewHolder> {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private List<Climate> climate;
    private Context context;
    RecyclerView rv;
    private WeatherRecyclerViewHolder recyclerViewHolder;
    private Typeface weatherFont;
    //private ImageView iv;
    private Animation rotation;

    public WeatherRecyclerViewAdapter(final Context context, List<Climate> itemList, RecyclerView recyclerView) {
        this.climate = itemList;
        this.context = context;
        this.rv=recyclerView;
       
    }

    @Override
    public int getItemViewType (int position) {
        return TYPE_ITEM;
    }


    @Override
    public WeatherRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("RecyclingTest", "onCreateViewHolder method is called");


        WeatherRecyclerViewHolder vh;

            View v = LayoutInflater.from (parent.getContext ()).inflate(R.layout.list_item_weather, parent, false);
            vh=  new WeatherRecyclerViewHolder(v);
        return vh;

    }


    @Override
    public void onBindViewHolder(final WeatherRecyclerViewHolder recyclerViewHolder, final int position) {
        this.recyclerViewHolder=recyclerViewHolder;
        weatherFont = Typeface.createFromAsset(context.getAssets(), "fonts/weather.ttf");
        recyclerViewHolder.weatherIcon.setTypeface(weatherFont);
            Log.d("RecyclingTest", "onBindViewHolder method is called");
        recyclerViewHolder.cityField.setText("मुंबई हवामान");
            /*detailsField.setText(
	    			climate.getWeather().get(0).getDescription().toUpperCase(Locale.US) +
	    			"\n" + "Humidity: " + climate.getMain().getHumidity() + "%" +
	    			"\n" + "Pressure: " + climate.getMain().getPressure() + " hPa");*/
        recyclerViewHolder.humidityField.setText("आर्द्रता : " + replaceEnglishDigit(getItem(position).getMain().getHumidity()) + "%");
        recyclerViewHolder.pressureField.setText("हवेचा दाब : " + replaceEnglishDigit(getItem(position).getMain().getPressure()) + " hPa");
        recyclerViewHolder.currentTemperatureField.setText(replaceEnglishDigit(
                String.format("%.2f", getItem(position).getMain().getTemp()/10)) + " ℃");
        DateFormat df = DateFormat.getDateTimeInstance();
        String updatedOn = df.format(new Date(getItem(position).getDt() * 1000));
        recyclerViewHolder.updatedField.setText("Last update : " + replaceEnglishDigit(updatedOn));
        setWeatherIcon(getItem(position).getWeather().get(0).getId(),
                getItem(position).getSys().getSunrise() * 1000,
                getItem(position).getSys().getSunset() * 1000);

        LayoutInflater inflater2 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //iv = (ImageView) inflater2.inflate(R.layout.refresh_action_view, null);
        rotation = AnimationUtils.loadAnimation(context, R.anim.rotate);
        recyclerViewHolder.card_ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerViewHolder.refButt.startAnimation(rotation);
                refreshWeatherData("junnar", "metric", context.getString(R.string.open_weather_maps_app_id));
             /*   if (Config.isConnectingToInternet(getActivity().getApplicationContext())==true) {
                    WeatherFragment.item.setVisible(true);
                    recyclerViewHolder.refButt.startAnimation(rotation);
                    refreshWeatherData("junnar", "metric", context.getString(R.string.open_weather_maps_app_id));
                } else {
                    *//*Snackbar.make(MainActivity.layMain, "इंटरनेट कनेक्शन नाही..", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();*//*
                    Toast toast = Toast.makeText(context, "इंटरनेट कनेक्शन नाही..", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                }*/
            }
        });

    }

    private void refreshWeatherData(final String q, final String units, final String APPID) {
        final RestAdapter adapter2 = new RestAdapter.Builder().setClient(new OkClient(Splash_Screen_Activity.getOkHttpClient())).setEndpoint(ENDPOINT).build();
        WeatherAPI api = adapter2.create(WeatherAPI.class);

        api.getWeather(new Callback<Climate>() {
            @Override
            public void success(Climate climate2, retrofit.client.Response response) {

                //renderWeather(climate);
                //setWeatherPrefs(climate);
                climate.clear();
                climate.add(climate2);
                notifyDataSetChanged();
               // WeatherFragment.item.setVisible(false);
                recyclerViewHolder.refButt.clearAnimation();

            }

            @Override
            public void failure(RetrofitError retrofitError) {
                recyclerViewHolder.refButt.clearAnimation();
                //WeatherFragment.item.setVisible(false);
                //getWeatherFromPrefs();

                //Toast.makeText(MainActivity.context, "नेटवर्क एरर all members" + retrofitError.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void setWeatherIcon(int actualId, long sunrise, long sunset) {
        int id = actualId / 100;
        String icon = "";
        if (actualId == 800) {
            long currentTime = new Date().getTime();
            if (currentTime >= sunrise && currentTime < sunset) {
                icon = (String) context.getResources().getText(R.string.weather_sunny);
                recyclerViewHolder.detailsField.setText("निरभ्र आकाश");
            } else {
                icon = (String) context.getResources().getText(R.string.weather_clear_night);
                recyclerViewHolder.detailsField.setText("निरभ्र आकाश");
            }
        } else {
            switch (id) {
                case 2:
                    icon = (String) context.getResources().getText(R.string.weather_thunder);
                    switch (actualId) {
                        case 200:
                            recyclerViewHolder.detailsField.setText("विजेच्या कडकडाटासह हलक्या सरी");
                            break;
                        case 201:
                            recyclerViewHolder.detailsField.setText("विजेच्या कडकडाटासह सरी");
                            break;
                        case 202:
                            recyclerViewHolder.detailsField.setText("विजेच्या कडकडाटासह मुसळधार सरी");
                            break;
                        case 210:
                            recyclerViewHolder.detailsField.setText("हलका विजेचा कडकडाट");
                            break;
                        case 211:
                            recyclerViewHolder.detailsField.setText("विजेचा कडकडाट");
                            break;
                        case 212:
                            recyclerViewHolder.detailsField.setText("मोठा विजेचा कडकडाट");
                            break;
                        case 221:
                            recyclerViewHolder.detailsField.setText("मोठा विजेचा कडकडाट");
                            break;
                        case 230:
                            recyclerViewHolder.detailsField.setText("विजेच्या कडकडाटासह हलका रिमझिम");
                            break;
                        case 231:
                            recyclerViewHolder.detailsField.setText("विजेच्या कडकडाटासह रिमझिम");
                            break;
                        case 232:
                            recyclerViewHolder.detailsField.setText("विजेच्या कडकडाटासह दाट रिमझिम");
                            break;
                    }
                    break;
                case 3:
                    icon = (String)context.getResources().getText(R.string.weather_drizzle);
                    switch (actualId) {
                        case 300:
                            recyclerViewHolder.detailsField.setText("हलका रिमझिम");
                            break;
                        case 301:
                            recyclerViewHolder.detailsField.setText("रिमझिम ");
                            break;
                        case 302:
                            recyclerViewHolder.detailsField.setText("दाट रिमझिम");
                            break;
                        case 310:
                            recyclerViewHolder.detailsField.setText("हलका रिमझिम पाऊस");
                            break;
                        case 311:
                            recyclerViewHolder.detailsField.setText("रिमझिम पाऊस");
                            break;
                        case 312:
                            recyclerViewHolder.detailsField.setText("दाट रिमझिम पाऊस");
                            break;
                        case 313:
                            recyclerViewHolder.detailsField.setText("तुषारांचा वर्षाव, रिमझिम ");
                            break;
                        case 314:
                            recyclerViewHolder.detailsField.setText("दाट तुषारांचा वर्षाव, रिमझिम");
                            break;
                    }
                    break;
                case 7:
                    icon =(String) context.getResources().getText(R.string.weather_foggy);
                    switch (actualId) {
                        case 701:
                            recyclerViewHolder.detailsField.setText("ढगाळ वातावरण");
                            break;
                        case 711:
                            recyclerViewHolder.detailsField.setText("धुके");
                            break;
                        case 721:
                            recyclerViewHolder.detailsField.setText("विरळ धुके");
                            break;
                        case 731:
                            recyclerViewHolder.detailsField.setText("वाळू, धुळीची वावटळ");
                            break;
                        case 741:
                            recyclerViewHolder.detailsField.setText("दाट धुके");
                            break;
                        case 751:
                            recyclerViewHolder.detailsField.setText("धूलीकणयुक्त");
                            break;
                        case 761:
                            recyclerViewHolder.detailsField.setText("धूलीकणयुक्त");
                            break;
                        case 762:
                            recyclerViewHolder.detailsField.setText("ज्वालामुखीची हवेतील राख");
                            break;
                        case 771:
                            recyclerViewHolder.detailsField.setText("हानिकारक वावटळ");
                            break;
                        case 781:
                            recyclerViewHolder.detailsField.setText("तुफानी वावटळ ");
                            break;
                    }
                    break;
                case 8:
                    icon = (String) context.getResources().getText(R.string.weather_cloudy);
                    switch (actualId) {
                        case 801:
                            recyclerViewHolder.detailsField.setText("तुरळक ढग");
                            break;
                        case 802:
                            recyclerViewHolder.detailsField.setText("विखुरलेले ढग");
                            break;
                        case 803:
                            recyclerViewHolder.detailsField.setText("फाटलेले ढग");
                            break;
                        case 804:
                            recyclerViewHolder.detailsField.setText("झाकाळलेले ढग");
                            break;

                    }
                    break;
                case 6:
                    icon = (String) context.getResources().getText(R.string.weather_snowy);
                    switch (actualId) {
                        case 600:
                            recyclerViewHolder.detailsField.setText("हिमवर्षाव");
                            break;
                        case 601:
                            recyclerViewHolder.detailsField.setText("हिमवर्षाव");
                            break;
                        case 602:
                            recyclerViewHolder.detailsField.setText("दाट हिमवर्षाव");
                            break;
                        case 611:
                            recyclerViewHolder.detailsField.setText("गारांचा पाऊस");
                            break;
                        case 612:
                            recyclerViewHolder.detailsField.setText("गारांचा पाऊस, तुषरांचा वर्षाव");
                            break;
                        case 615:
                            recyclerViewHolder.detailsField.setText("हलका पाऊस, हिमवर्षाव");
                            break;
                        case 616:
                            recyclerViewHolder.detailsField.setText("पाऊस, हिमवर्षाव");
                            break;
                        case 620:
                            recyclerViewHolder.detailsField.setText("हलका पाऊस, हिमवर्षाव");
                            break;
                        case 621:
                            recyclerViewHolder.detailsField.setText("हिमवर्षाव");
                            break;
                        case 622:
                            recyclerViewHolder.detailsField.setText("दाट हिमवर्षाव");
                            break;
                    }
                    break;
                case 5:
                    icon = (String) context.getResources().getText(R.string.weather_rainy);
                    switch (actualId) {
                        case 500:
                            recyclerViewHolder.detailsField.setText("हलका पाऊस");
                            break;
                        case 501:
                            recyclerViewHolder.detailsField.setText("मध्यम पाऊस");
                            break;
                        case 502:
                            recyclerViewHolder.detailsField.setText("मुसळधार पाऊस");
                            break;
                        case 503:
                            recyclerViewHolder.detailsField.setText("मुसळधार पाऊस");
                            break;
                        case 504:
                            recyclerViewHolder.detailsField.setText("मुसळधार पाऊस");
                            break;
                        case 511:
                            recyclerViewHolder.detailsField.setText("थंड पाऊस");
                            break;
                        case 520:
                            recyclerViewHolder.detailsField.setText("हलका तुषारांचा वर्षाव");
                            break;
                        case 521:
                            recyclerViewHolder.detailsField.setText("तुषारांचा वर्षाव");
                            break;
                        case 522:
                            recyclerViewHolder.detailsField.setText("दाट तुषारांचा वर्षाव");
                            break;
                        case 531:
                            recyclerViewHolder.detailsField.setText("दाट तुषारांचा वर्षाव");
                            break;
                    }
                    break;
                case 9:
                    icon =(String) context.getResources().getText(R.string.wi_hurricane);
                    switch (actualId) {
                        case 900:
                            recyclerViewHolder.detailsField.setText("तुफानी वावटळ");
                            break;
                        case 901:
                            recyclerViewHolder.detailsField.setText("उष्णकटीबंधीय वादळ");
                            break;
                        case 902:
                            recyclerViewHolder.detailsField.setText("चक्रीवादळ");
                            break;
                        case 903:
                            recyclerViewHolder.detailsField.setText("थंड");
                            break;
                        case 904:
                            recyclerViewHolder.detailsField.setText("उष्ण");
                            break;
                        case 905:
                            recyclerViewHolder.detailsField.setText("वारा");
                            break;
                        case 906:
                            recyclerViewHolder.detailsField.setText("गारा");
                            break;
                        case 951:
                            recyclerViewHolder.detailsField.setText("शांत हवामान");
                            break;
                        case 952:
                            recyclerViewHolder.detailsField.setText("हलकी वा-याची झुळूक");
                            break;
                        case 953:
                            recyclerViewHolder.detailsField.setText("सौम्य वा-याची झुळूक");
                            break;
                        case 954:
                            recyclerViewHolder.detailsField.setText("मध्यम वा-याची झुळूक");
                            break;
                        case 955:
                            recyclerViewHolder.detailsField.setText("ताजी वा-याची झुळूक");
                            break;
                        case 956:
                            recyclerViewHolder.detailsField.setText("मजबूत वा-याची झुळूक");
                            break;
                        case 957:
                            recyclerViewHolder.detailsField.setText("वादळ");
                            break;
                        case 958:
                            recyclerViewHolder.detailsField.setText("वादळ");
                            break;
                        case 959:
                            recyclerViewHolder.detailsField.setText("वादळ");
                            break;
                        case 960:
                            recyclerViewHolder.detailsField.setText("वादळ");
                            break;
                        case 961:
                            recyclerViewHolder.detailsField.setText("चक्रीवादळ");
                            break;
                    }
                    break;
            }
        }
        recyclerViewHolder.weatherIcon.setText(icon);
    }

    private String replaceEnglishDigit(String s) {
        String replace = s;
        if(replace!=null) {
            if (replace.contains("1"))
                replace = replace.replace("1", "१");
            if (replace.contains("2"))
                replace = replace.replace("2", "२");
            if (replace.contains("3"))
                replace = replace.replace("3", "३");
            if (replace.contains("4"))
                replace = replace.replace("4", "४");
            if (replace.contains("5"))
                replace = replace.replace("5", "५");
            if (replace.contains("6"))
                replace = replace.replace("6", "६");
            if (replace.contains("7"))
                replace = replace.replace("7", "७");
            if (replace.contains("8"))
                replace = replace.replace("8", "८");
            if (replace.contains("9"))
                replace = replace.replace("9", "९");
            if (replace.contains("0"))
                replace = replace.replace("0", "०");
        }
        return replace;
    }


    public Climate getItem (int position) {
        return climate.get(position);
    }



    @Override
    public int getItemCount () {
        return climate.size();
    }





}