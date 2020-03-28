package Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kasamade.news.R;


/**
 * Created by Dhanu on 12/9/2015.
 */
public class WeatherRecyclerViewHolder extends RecyclerView.ViewHolder{

    TextView cityField;
    TextView updatedField;
    TextView detailsField;
    TextView currentTemperatureField;
    TextView weatherIcon;
    TextView humidityField;
    TextView pressureField;
 ImageView refButt;
     CardView card_ref;

    public WeatherRecyclerViewHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        cityField = (TextView) itemView.findViewById(R.id.city_field);
        updatedField = (TextView) itemView.findViewById(R.id.updated_field);
        detailsField = (TextView) itemView.findViewById(R.id.details_field);
        currentTemperatureField = (TextView) itemView.findViewById(R.id.current_temperature_field);
        humidityField = (TextView) itemView.findViewById(R.id.tvHumidity);
        pressureField = (TextView) itemView.findViewById(R.id.tvPressure);
        weatherIcon = (TextView) itemView.findViewById(R.id.weather_icon);
        refButt = (ImageView) itemView.findViewById(R.id.imageButton);
        card_ref = (CardView) itemView.findViewById(R.id.card_view_ref);
    }


}