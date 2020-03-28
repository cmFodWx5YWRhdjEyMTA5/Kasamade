package AllFragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kasamade.news.Config;
import com.kasamade.news.MainActivity;
import com.kasamade.news.R;
import com.kasamade.news.SimpleDividerItemDecoration;
import com.kasamade.news.Splash_Screen_Activity;

import java.util.ArrayList;
import java.util.List;

import Adapter.WeatherRecyclerViewAdapter2;
import interfaceAPI.WeatherAPI;
import model.Climate;
import model.Main;
import model.Sys;
import model.Weather;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;

public class malegoanFragment extends Fragment {

    Typeface weatherFont;
    public static final String ENDPOINT = "http://api.openweathermap.org";

    NestedScrollView nsWeather;
    Handler handler;
    public static MenuItem item;
    private ImageView iv;
    private MenuItem refreshItem;
    private Animation rotation;
    private static RecyclerView recyclerView;
    public static List<Climate> climate;
    private static SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager layoutManager;
    private static WeatherRecyclerViewAdapter2 adapter;
    private Sys sys;
    private Main main;
    private Weather weather;
    private TextView tvEmptyView;
    private ImageView refButt;
    private CardView card_ref;
    private boolean afterCreate;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_malegoan, container, false);
        Splash_Screen_Activity.initialization = MainActivity.context.getSharedPreferences(MY_PREFS_NAME, MainActivity.context.MODE_PRIVATE);
        Splash_Screen_Activity.editor = MainActivity.context.getSharedPreferences(MY_PREFS_NAME, MainActivity.context.MODE_PRIVATE).edit();
        //weatherFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/weather.ttf");
        //weatherIcon.setTypeface(weatherFont);
        handler = new Handler();
        LayoutInflater inflater2 = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        iv = (ImageView) inflater2.inflate(R.layout.refresh_action_view, null);
        rotation = AnimationUtils.loadAnimation(MainActivity.context, R.anim.rotate);
        setHasOptionsMenu(true);
        layoutManager = new LinearLayoutManager(getActivity());
        afterCreate = false;
        final DisplayMetrics metrics = getResources().getDisplayMetrics();
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        climate = new ArrayList<>();
        tvEmptyView = (TextView) rootView.findViewById(R.id.empty_view);
        refButt = (ImageView) rootView.findViewById(R.id.imageButton);
        card_ref = (CardView) rootView.findViewById(R.id.card_view_ref);
        card_ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Config.isConnectingToInternet(getActivity().getApplicationContext())==true) {
                    refButt.startAnimation(rotation);
                    updateWeatherData("junnar", "metric", getActivity().getString(R.string.open_weather_maps_app_id), true);
                } else {
                    /*Snackbar.make(MainActivity.layMain, "इंटरनेट कनेक्शन नाही..", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();*/
                    Toast toast = Toast.makeText(MainActivity.context, "इंटरनेट कनेक्शन नाही..", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                }
            }
        });
        adapter = new WeatherRecyclerViewAdapter2(getActivity().getApplicationContext(), climate, recyclerView);
        recyclerView.setAdapter(adapter);
       /* int paddingTop = Utils.getToolbarHeight(MainActivity.context) + Utils.getTabsHeight(MainActivity.context);
        recyclerView.setPadding(recyclerView.getPaddingLeft(), paddingTop, recyclerView.getPaddingRight(), recyclerView.getPaddingBottom());
*/
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                MainActivity.context
        ));
        /*recyclerView.setOnScrollListener(new HidingScrollListener(MainActivity.context) {
            @Override
            public void onMoved(int distance) {
                MainActivity.mToolbarContainer.setTranslationY(-distance);
            }

            @Override
            public void onShow() {
                MainActivity.mToolbarContainer.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
            }

            @Override
            public void onHide() {
                MainActivity.mToolbarContainer.animate().translationY(-MainActivity.mToolbarHeight).setInterpolator(new AccelerateInterpolator(2)).start();
            }

        });*/
        if (climate.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            tvEmptyView.setVisibility(View.VISIBLE);
            refButt.setVisibility(View.VISIBLE);

        } else {
            recyclerView.setVisibility(View.VISIBLE);
            tvEmptyView.setVisibility(View.GONE);
            refButt.setVisibility(View.GONE);
        }
        /*nsWeather.setOnScrollChangeListener(new HidingNestedScrollListener(MainActivity.context) {
            @Override
            public void onMoved(int distance) {
                MainActivity.mToolbarContainer.setTranslationY(-distance);
            }
            @Override
            public void onShow() {
                MainActivity.mToolbarContainer.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
            }

            @Override
            public void onHide() {
                MainActivity.mToolbarContainer.animate().translationY(-MainActivity.mToolbarHeight).setInterpolator(new AccelerateInterpolator(2)).start();
            }
        });*/
        updateWeatherData("junnar", "metric", getActivity().getString(R.string.open_weather_maps_app_id), false);
        return rootView;
    }

    private void updateWeatherData(final String q, final String units, final String APPID, final boolean afterCreate) {
        final RestAdapter adapter2 = new RestAdapter.Builder().setClient(new OkClient(Splash_Screen_Activity.getOkHttpClient())).setEndpoint(ENDPOINT).build();
        WeatherAPI api = adapter2.create(WeatherAPI.class);

        api.getWeather2(new Callback<Climate>() {
            @Override
            public void success(Climate climate2, retrofit.client.Response response) {
                refButt.clearAnimation();
                //iv.setVisibility(View.GONE);

                if (afterCreate) {
                    //item.getActionView().clearAnimation();
                    // item.getActionView().setVisibility(View.GONE);
                   // item.setVisible(false);
                }

                renderWeather(climate2);

                setWeatherPrefs(climate2);


            }

            @Override
            public void failure(RetrofitError retrofitError) {
                refButt.clearAnimation();
                //iv.setVisibility(View.GONE);

                if (afterCreate) {
                    //item.getActionView().clearAnimation();
                    // item.getActionView().setVisibility(View.GONE);
                    item.setVisible(false);
                }
                getWeatherFromPrefs();
                //Toast.makeText(MainActivity.context, "नेटवर्क एरर all members" + retrofitError.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getWeatherFromPrefs() {
        Climate climate = new Climate();
        Sys sys = new Sys();
        Main main = new Main();
        Weather weather = new Weather();
        weather.setId(Splash_Screen_Activity.initialization.getInt("weather_id3", 800));
        sys.setSunrise(Splash_Screen_Activity.initialization.getLong("sunrise3", 0));
        sys.setSunset(Splash_Screen_Activity.initialization.getLong("sunset3", 0));
        main.setHumidity(Splash_Screen_Activity.initialization.getString("humidity3", null));
        main.setPressure(Splash_Screen_Activity.initialization.getString("pressure3", null));
        main.setTemp((double) Splash_Screen_Activity.initialization.getFloat("temparature3", 0));
        climate.setDt(Splash_Screen_Activity.initialization.getLong("updatedOn3", 0));
        climate.setMain(main);
        climate.setSys(sys);
        List<Weather> weather2 = new ArrayList<>();
        weather2.add(weather);
        climate.setWeather(weather2);
        renderWeather(climate);
    }

    private void setWeatherPrefs(Climate climate) {
        Splash_Screen_Activity.editor.putInt("weather_id3", climate.getWeather().get(0).getId());
        Splash_Screen_Activity.editor.putString("humidity3", climate.getMain().getHumidity());
        Splash_Screen_Activity.editor.putString("pressure3", climate.getMain().getPressure());
        Splash_Screen_Activity.editor.putFloat("temparature3", climate.getMain().getTemp().floatValue());

        //DateFormat df = DateFormat.getDateTimeInstance();
        // String updatedOn = df.format(new Date(climate.getDt() * 1000));
        Splash_Screen_Activity.editor.putLong("updatedOn3", climate.getDt());
        Splash_Screen_Activity.editor.putLong("sunrise3", climate.getSys().getSunrise() * 1000);
        Splash_Screen_Activity.editor.putLong("sunset3", climate.getSys().getSunset() * 1000);

        Splash_Screen_Activity.editor.apply();
    }


    private void renderWeather(Climate climate2) {
        try {

            climate.clear();
            climate.add(climate2);
            if (climate.isEmpty()) {
                recyclerView.setVisibility(View.GONE);
                tvEmptyView.setVisibility(View.VISIBLE);
                refButt.setVisibility(View.VISIBLE);


                            /*Snackbar.make(MainActivity.layMain, "मेम्बर्स नाही सापडले..", Snackbar.LENGTH_SHORT)
                                    .setAction("Action", null).show();*/
                Toast toast = Toast.makeText(MainActivity.context, "हवामान नाही सापडले..", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();


            } else {
                recyclerView.setVisibility(View.VISIBLE);
                tvEmptyView.setVisibility(View.GONE);
                adapter.notifyDataSetChanged();
                refButt.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            Log.e("SimpleWeather", "One or more fields not found in the JSON data");
        }
    }


    public void changeCity(String city) {
        //updateWeatherData(city);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        // Set up 1 action button
      /*  inflater.inflate(R.menu.menu_weather, menu);
        item = menu.findItem(R.id.action_refresh);*/
      /*  ProgressBar v = (ProgressBar) MenuItemCompat.getActionView(item);
        //item.setActionView(v);
        item.setVisible(false);*/

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
         /*   case R.id.action_refresh:
                //refreshItem = item;
                refresh();
                break;*/
            // action with ID action_settings was select
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void refresh() {
        if (getActivity() != null) {


            refreshWeatherData("junnar", "metric", getActivity().getString(R.string.open_weather_maps_app_id));
        }
    }

    public void refreshWeatherData(final String q, final String units, final String APPID) {
        final RestAdapter adapter2 = new RestAdapter.Builder().setClient(new OkClient(Splash_Screen_Activity.getOkHttpClient())).setEndpoint(ENDPOINT).build();
        WeatherAPI api = adapter2.create(WeatherAPI.class);
        refreshItem.getActionView().startAnimation(rotation);

        api.getWeather2(new Callback<Climate>() {
            @Override
            public void success(Climate climate, retrofit.client.Response response) {
                //refreshItem.getActionView().clearAnimation();
                getActivity().invalidateOptionsMenu();
                renderWeather(climate);
                setWeatherPrefs(climate);

            }

            @Override
            public void failure(RetrofitError retrofitError) {
                // refreshItem.getActionView().clearAnimation();
                getWeatherFromPrefs();
                getActivity().invalidateOptionsMenu();
                //Toast.makeText(MainActivity.context, "नेटवर्क एरर all members" + retrofitError.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}
