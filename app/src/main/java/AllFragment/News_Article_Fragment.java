package AllFragment;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.kasamade.news.AppPrefs;
import com.kasamade.news.Config;
import com.kasamade.news.MainActivity;
import com.kasamade.news.R;
import com.kasamade.news.Splash_Screen_Activity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Adapter.CustomViewPager;
import Adapter.Custom_View_Pager;
import Adapter.GifImageView;
import Bean.Bean_Master;
import interfaceAPI.AllMsgAPI;
import model.ImageAndId;
import model.MessageDetail;
import model.MySingleton;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;

public class News_Article_Fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static ImageLoader mImageLoader;


    CustomViewPager viewpager;
    View view;

    TextView nonetwork;

    ArrayList<Bean_Master> array_bean = new ArrayList<Bean_Master>();
    public static List<MessageDetail> imgMsgDetailList;
    public static final String ENDPOINT = "http://kasamadenews.androideducator.com";
    AppPrefs appPrefs ;
    String Notification="off";
    int Notificationid;
    ImageView im_no_internet_connection;
    private GifImageView gifImageView;
    InterstitialAd mInterstitialAd;
    public News_Article_Fragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_news__article_, container, false);
        im_no_internet_connection = (ImageView) view.findViewById(R.id.im_no_internet_connection);
        appPrefs = new AppPrefs(getActivity());
       // getActivity().getSupportActionBar().hide();
      //  getActivity().getActionBar().show();
        viewpager = (CustomViewPager) view.findViewById(R.id.viewpager);
        gifImageView = (GifImageView) view.findViewById(R.id.image1);



        nonetwork=(TextView) view.findViewById(R.id.nonetwork);
        gifImageView.setGifImageResource(R.drawable.bird);
        gifImageView.setVisibility(View.VISIBLE);
        nonetwork.setVisibility(View.VISIBLE);
        nonetwork.setText("Please Wait...!");
        viewpager.setVisibility(View.GONE);


        mInterstitialAd=  new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen1));

        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });

try {
    Notification = getArguments().getString("Notification");
}catch (Exception e)
{
    Notification="off";
}


        if(Notification.equals("On")) {
            Notificationid = Integer.valueOf(getArguments().getString("noitifyid"));
        }
        /*viewpager.setPageTransformer();
        viewpager.setOffscreenPageLimit(1);
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Please Wait...");
        pDialog.setCancelable(false);
        pDialog.show();*/


        if(Config.isConnectingToInternet(getActivity().getApplicationContext())==true)
        {
            //new get_news_list().execute();

            final RestAdapter adapter2 = new RestAdapter.Builder().setClient(new OkClient(Splash_Screen_Activity.getOkHttpClient())).setEndpoint(ENDPOINT).build();
            AllMsgAPI api = adapter2.create(AllMsgAPI.class);
            String query = "WHERE ";
            Map<String,String > params=new HashMap<String, String>();
            params.put("link",query);



            api.getAllMsg(params, new Callback<List<MessageDetail>>() {
                @Override
                public void success(List<MessageDetail> imgDetails, retrofit.client.Response response) {



                    array_bean.clear();
                    if (imgDetails != null) {

                     //   pDialog.dismiss();
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


                                    getMsgImages(imgDetails.get(j).getPid(), imgDetails.get(j).getImage());


                                    array_bean.add(bean);


                                }
                            }else if(Notification.equals("on"))
                            {


                                Notificationid = Integer.valueOf(getArguments().getString("noitifyid"));

                                for (int j = 0; j < imgDetails.size(); j++) {



                                    if(Notificationid==imgDetails.get(j).getPid() ) {

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


                                        getMsgImages(imgDetails.get(j).getPid(), imgDetails.get(j).getImage());


                                        array_bean.add(bean);
                                    }






                                }











                                for (int j = 0; j < imgDetails.size(); j++) {



                                    if(Notificationid!=imgDetails.get(j).getPid() ) {

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


                                        getMsgImages(imgDetails.get(j).getPid(), imgDetails.get(j).getImage());


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
                    gifImageView.setVisibility(View.INVISIBLE);

                    nonetwork.setVisibility(View.INVISIBLE);
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
    public static void getMsgImages(int pid, final String image) {
        String imgUrl = MainActivity.PHOTO_BASE_URL + image;
        mImageLoader = MySingleton.getInstance(MainActivity.context).getImageLoader();

        final int finalpId = pid;
        mImageLoader.get(imgUrl, new ImageLoader.ImageListener() {

            @Override
            public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                Bitmap bitmap = imageContainer.getBitmap();
                if (bitmap != null) {
                    //  img.setImageBitmap(bitmap);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 15, stream);
                    byte[] byteArray = stream.toByteArray();
                    ImageAndId imd = new ImageAndId();
                    imd.setPid(finalpId);
                    imd.setImage(byteArray);

                    File file;
                    FileOutputStream outputStream;
                    try {
                        // file = File.createTempFile("MyCache", null, getCacheDir());
                        file = new File(MainActivity.context.getCacheDir(), image);
                        outputStream = new FileOutputStream(file);
                        ObjectOutputStream oos = new ObjectOutputStream(outputStream);

                        oos.writeObject(imd);
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else {

                }

            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //Toast.makeText(context, "Vlr" + volleyError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        //new get_news_list().execute();
    }
    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
    // TODO: Rename method, update argument and hook method into UI event


    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();

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
