package Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.kasamade.news.AppPrefs;
import com.kasamade.news.R;

import java.util.ArrayList;

import Bean.Bean_id_title;

/**
 * Created by Prince on 8/10/2016.
 */
public class Custom_Autocomplete_movie_names extends BaseAdapter implements Filterable{

    ArrayList<Bean_id_title> array_bean;
    Activity activity;
    LayoutInflater layoutinflater;
    AppPrefs appPrefs;
    private ArrayList<Bean_id_title> suggestions = new ArrayList<>();
    private Filter filter = new CustomFilter();
    public Custom_Autocomplete_movie_names(Activity activity,ArrayList<Bean_id_title> array_bean,LayoutInflater layoutinflater)
    {

        this.activity = activity;
        this.array_bean = array_bean;
        this.layoutinflater = layoutinflater;
        appPrefs = new AppPrefs(activity);
    }


    @Override
    public int getCount() {
        return suggestions.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = layoutinflater.inflate(R.layout.textview_movies_name, parent, false);

        final TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_title.setTag(suggestions.get(position).getId());
        tv_title.setText(suggestions.get(position).getTitle());

        tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appPrefs.setSelected_Movie_ID(tv_title.getTag().toString());
            }
        });

        return view;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            suggestions.clear();

            if (array_bean != null && constraint != null) { // Check if the Original List and Constraint aren't null.
                for (int i = 0; i < array_bean.size(); i++) {
                    if (array_bean.get(i).getTitle().toLowerCase().contains(constraint)) { // Compare item in original list if it contains constraints.
                        suggestions.add(array_bean.get(i)); // If TRUE add item in Suggestions.
                    }
                }
            }
            FilterResults results = new FilterResults(); // Create new Filter Results and return this to publishResults;
            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }
}
