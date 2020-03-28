package AllFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.kasamade.news.AppPrefs;
import com.kasamade.news.Config;
import com.kasamade.news.R;
import com.kasamade.news.Splash_Screen_Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.CustomViewPager;
import Adapter.Custom_View_Pager;
import Adapter.GifImageView;
import Bean.Bean_Master;
import interfaceAPI.AllMsgAPI;
import model.MessageDetail;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Science_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Science_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Science_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    CustomViewPager viewpager;
    ImageView im_no_internet_connection;
    View view;
    ArrayList<Bean_Master> array_bean = new ArrayList<Bean_Master>();
    AppPrefs appPrefs;

    String Notification="off";
    int Notificationid;
    InterstitialAd mInterstitialAd;
    private GifImageView gifImageView;
    TextView nonetwork;
    public static final String ENDPOINT = "http://kasamadenews.androideducator.com";
    public Science_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Science_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Science_Fragment newInstance(String param1, String param2) {
        Science_Fragment fragment = new Science_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_films_, container, false);

        appPrefs = new AppPrefs(getActivity());
        viewpager = (CustomViewPager) view.findViewById(R.id.viewpager);
        im_no_internet_connection = (ImageView) view.findViewById(R.id.im_no_internet_connection);
        viewpager.setPageTransformer();

        gifImageView = (GifImageView) view.findViewById(R.id.image1);



        nonetwork=(TextView) view.findViewById(R.id.nonetwork);
        gifImageView.setGifImageResource(R.drawable.bird);
        gifImageView.setVisibility(View.VISIBLE);
        nonetwork.setVisibility(View.VISIBLE);
        nonetwork.setText("Please Wait...!");
        viewpager.setVisibility(View.GONE);


        mInterstitialAd=  new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));


        try {
            Notification = getArguments().getString("Notification");
        }catch (Exception e)
        {
            Notification="off";
        }


        if(Notification.equals("On")) {
            Notificationid = Integer.valueOf(getArguments().getString("noitifyid"));
        }



        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });
        //new get_news_list().execute();
        if(Config.isConnectingToInternet(getActivity().getApplicationContext())==true)
        {
            final RestAdapter adapter2 = new RestAdapter.Builder().setClient(new OkClient(Splash_Screen_Activity.getOkHttpClient())).setEndpoint(ENDPOINT).build();
            AllMsgAPI api = adapter2.create(AllMsgAPI.class);
            String query = "WHERE ";
            Map<String,String > params=new HashMap<String, String>();
            params.put("link",query);



            api.getAllscience(params, new Callback<List<MessageDetail>>() {
                @Override
                public void success(List<MessageDetail> imgDetails, retrofit.client.Response response) {


                    array_bean.clear();
                    if (imgDetails != null) {

                        gifImageView.setVisibility(View.INVISIBLE);
                        nonetwork.setVisibility(View.INVISIBLE);
                        viewpager.setVisibility(View.VISIBLE);

                        if(Notification.equals("off")) {
                        for (int j = 0; j < imgDetails.size(); j++) {
                            Bean_Master bean = new Bean_Master();
                            System.out.println(imgDetails.get(j).getPid() + " " + imgDetails.get(j).getName() + " " + imgDetails.get(j).getImage() + " " + imgDetails.get(j).getDescription() + " " + imgDetails.get(j).getMsg_from() + " " + imgDetails.get(j).getIsread());
                            bean.setId(String.valueOf(imgDetails.get(j).getPid()));
                            bean.setTitle(imgDetails.get(j).getName());
                            bean.setDesc(imgDetails.get(j).getDescription());
                            String value = "1";
                            bean.setNews_type(value);
                            //   getMsgImages(imgDetails.get(j).getPid(), imgDetails.get(j).getImage());
                            System.out.println(imgDetails.get(j).getDescription());
                            bean.setSource_name(imgDetails.get(j).getLink());
                            bean.setRead_more_link(imgDetails.get(j).getMsg_from());
                            bean.setImage_url(imgDetails.get(j).getImage());
                            System.out.println(imgDetails.get(j).getImage());
                            bean.setVideo_url(imgDetails.get(j).getMsg_from());
                            //  bean.setNews_type(imgDetails.get(j).getMsg_from());
                            bean.setAuthor(imgDetails.get(j).getMsg_from());


                            //  getMsgImages(imgDetails.get(j).getPid(), imgDetails.get(j).getImage());


                            array_bean.add(bean);

                        }
                        }

                        else if(Notification.equals("on")) {


                            Notificationid = Integer.valueOf(getArguments().getString("noitifyid"));

                            for (int j = 0; j < imgDetails.size(); j++) {


                                if (Notificationid == imgDetails.get(j).getPid()) {
                                    Bean_Master bean = new Bean_Master();
                                    System.out.println(imgDetails.get(j).getPid() + " " + imgDetails.get(j).getName() + " " + imgDetails.get(j).getImage() + " " + imgDetails.get(j).getDescription() + " " + imgDetails.get(j).getMsg_from() + " " + imgDetails.get(j).getIsread());
                                    bean.setId(String.valueOf(imgDetails.get(j).getPid()));
                                    bean.setTitle(imgDetails.get(j).getName());
                                    bean.setDesc(imgDetails.get(j).getDescription());
                                    String value = "1";
                                    bean.setNews_type(value);
                                    //   getMsgImages(imgDetails.get(j).getPid(), imgDetails.get(j).getImage());
                                    System.out.println(imgDetails.get(j).getDescription());
                                    bean.setSource_name(imgDetails.get(j).getLink());
                                    bean.setRead_more_link(imgDetails.get(j).getMsg_from());
                                    bean.setImage_url(imgDetails.get(j).getImage());
                                    System.out.println(imgDetails.get(j).getImage());
                                    bean.setVideo_url(imgDetails.get(j).getMsg_from());
                                    //  bean.setNews_type(imgDetails.get(j).getMsg_from());
                                    bean.setAuthor(imgDetails.get(j).getMsg_from());


                                    //  getMsgImages(imgDetails.get(j).getPid(), imgDetails.get(j).getImage());


                                    array_bean.add(bean);


                                }


                            }


                            for (int j = 0; j < imgDetails.size(); j++) {


                                if (Notificationid != imgDetails.get(j).getPid()) {
                                    Bean_Master bean = new Bean_Master();
                                    System.out.println(imgDetails.get(j).getPid() + " " + imgDetails.get(j).getName() + " " + imgDetails.get(j).getImage() + " " + imgDetails.get(j).getDescription() + " " + imgDetails.get(j).getMsg_from() + " " + imgDetails.get(j).getIsread());
                                    bean.setId(String.valueOf(imgDetails.get(j).getPid()));
                                    bean.setTitle(imgDetails.get(j).getName());
                                    bean.setDesc(imgDetails.get(j).getDescription());
                                    String value = "1";
                                    bean.setNews_type(value);
                                    //   getMsgImages(imgDetails.get(j).getPid(), imgDetails.get(j).getImage());
                                    System.out.println(imgDetails.get(j).getDescription());
                                    bean.setSource_name(imgDetails.get(j).getLink());
                                    bean.setRead_more_link(imgDetails.get(j).getMsg_from());
                                    bean.setImage_url(imgDetails.get(j).getImage());
                                    System.out.println(imgDetails.get(j).getImage());
                                    bean.setVideo_url(imgDetails.get(j).getMsg_from());
                                    //  bean.setNews_type(imgDetails.get(j).getMsg_from());
                                    bean.setAuthor(imgDetails.get(j).getMsg_from());


                                    //  getMsgImages(imgDetails.get(j).getPid(), imgDetails.get(j).getImage());


                                    array_bean.add(bean);


                                }


                            }
                        }








                        Custom_View_Pager adapter = new Custom_View_Pager(getActivity(),getActivity(),array_bean);
                        adapter.notifyDataSetChanged();
                        viewpager.setAdapter(adapter);
                        viewpager.setVisibility(View.VISIBLE);
                    }
                }    @Override
                public void failure(RetrofitError retrofitError) {

                    System.out.println("Sachin");
                    //Toast.makeText(MainActivity1.this, "Error in Retro: " + retrofitError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            gifImageView.setVisibility(View.VISIBLE);

            System.out.println("Sachin Khairnar");

            nonetwork.setText("Sorry..No Internet Connection...!");
            nonetwork.setVisibility(View.VISIBLE);

            viewpager.setVisibility(View.GONE);
        }
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }
    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
