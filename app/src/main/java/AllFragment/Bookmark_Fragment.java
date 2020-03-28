package AllFragment;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kasamade.news.AppPrefs;
import com.kasamade.news.R;

import java.util.ArrayList;

import Adapter.CustomViewPager;
import Adapter.Custom_View_Pager1;
import Bean.Bean_Master;
import ConnetionHandler.SQLController;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Bookmark_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Bookmark_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Bookmark_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    CustomViewPager viewpager;
    public static SQLController dbconMsg;
    View view;
    ArrayList<Bean_Master> array_bean = new ArrayList<Bean_Master>();
    AppPrefs appPrefs;
    ImageView im_no_internet_connection;
    private OnFragmentInteractionListener mListener;

    public Bookmark_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Bookmark_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Bookmark_Fragment newInstance(String param1, String param2) {
        Bookmark_Fragment fragment = new Bookmark_Fragment();
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
        view = inflater.inflate(R.layout.fragment_bookmark, container, false);



        appPrefs = new AppPrefs(getActivity());
        viewpager = (CustomViewPager) view.findViewById(R.id.viewpager);
        im_no_internet_connection = (ImageView) view.findViewById(R.id.im_no_internet_connection);
        viewpager.setPageTransformer();



        try {
            dbconMsg = new SQLController(getActivity());
            dbconMsg.open();
            Cursor c = dbconMsg.readData();

            if (c.moveToFirst()) {
                do {
                    System.out.println("Id Stored- " + c.getString(0));
                    System.out.println(" BookMarkidId Stored- " + c.getString(1));
                    System.out.println("Image Url Stored- " + c.getString(3));
                    System.out.println("title Id Stored- " + c.getString(2));
                    System.out.println("description Id Stored- " + c.getString(4));
                    System.out.println("sourcename  Stored- " + c.getString(5));


                    Bean_Master bean = new Bean_Master();

                    bean.setId(c.getString(1));

                    bean.setTitle(c.getString(2));
                    bean.setImage_url(c.getString(3));
                    bean.setDesc(c.getString(4));
                    bean.setSource_name(c.getString(5));
                    bean.setNews_type("");
                    array_bean.add(bean);



                   // dbconMsg.insertData(Position,array_bean.get(position).getTitle(), array_bean.get(position).getImage_url(),array_bean.get(position).getDesc(),array_bean.get(position).getSource_name(),0);
                    /*bean.setRead_more_link(jobject_news.getString("source_url"));
                    bean.setImage_url(jobject_news.getString("banner"));
                    bean.setVideo_url(jobject_news.getString("video_url"));
                    bean.setNews_type(jobject_news.getString("news_type"));
                    bean.setAuthor(jobject_author.getString("full_name"));
*/
                    //      System.out.println("Positon- " + Position);
                  //  System.out.println(c.getString(1).equals(Position));
             /*  if (c.getString(1).equals(Position)) {
                        //  isPressed = true;
                        System.out.println("Done");
                        btn_bookmark.setBackgroundResource(R.drawable.bookmark_black);
                    }*/
                } while (c.moveToNext());
            }
            dbconMsg.close();
        }catch(Exception e)
        {

        }

        Custom_View_Pager1 adapter = new Custom_View_Pager1(getActivity(),getActivity(),array_bean);
        adapter.notifyDataSetChanged();
        viewpager.setAdapter(adapter);
        viewpager.setVisibility(View.VISIBLE);

        return view;




    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

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
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
