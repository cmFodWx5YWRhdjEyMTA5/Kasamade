package AllFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.kasamade.news.AppPrefs;
import com.kasamade.news.R;

import java.util.ArrayList;

import Adapter.CustomViewPager;
import Adapter.How_To_Adapter1;
import Bean.Bean_Master;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Precautions_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Precautions_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Precautions_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String ENDPOINT = "http://kasamadenews.androideducator.com";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private GridView gridView;
    static final String[] CHECK = new String[]{"कळवण", "सटाणा", "मालेगाव", "देवळा"};
    private OnFragmentInteractionListener mListener;
    CustomViewPager viewpager;
    View view;
    ArrayList<Bean_Master> array_bean = new ArrayList<Bean_Master>();
    AppPrefs appPrefs;
    ImageView im_no_internet_connection;
    public Precautions_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Precautions_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Precautions_Fragment newInstance(String param1, String param2) {
        Precautions_Fragment fragment = new Precautions_Fragment();
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


        view = inflater.inflate(R.layout.fragment_actors_, container, false);

        appPrefs = new AppPrefs(getActivity());
     /*   viewpager = (CustomViewPager) view.findViewById(R.id.viewpager);
        im_no_internet_connection = (ImageView) view.findViewById(R.id.im_no_internet_connection);
        viewpager.setPageTransformer();*/
        callgrid();
        return view;
    }
    private void callgrid()
    {
        gridView = (GridView)view.findViewById(R.id.gridView1);
        gridView.setAdapter(new How_To_Adapter1(getActivity(), CHECK));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                //Toast.makeText(getApplicationContext(), ((TextView) v.findViewById(R.id.grid_item_label)).getText(), //Toast.LENGTH_SHORT).show();
                TextView getValue = (TextView) v.findViewById(R.id.grid_item_label);
                String check_battry="http://deviseelectronics.com/mahindra/files/assets/data/checkings/How_to_check_Battery_Voltage.pdf";
                String check_relay="http://deviseelectronics.com/mahindra/files/assets/data/checkings/RELAYS.pdf";
                String check_fuses="http://deviseelectronics.com/mahindra/files/assets/data/checkings/How_to_check_fuse.pdf";
                String multimiter="http://deviseelectronics.com/mahindra/files/assets/data/checkings/How_To_Use_a_Multimeter.pdf";
                String garuda="http://deviseelectronics.com/mahindra/files/assets/data/checkings/How_To_Use_GARUDA_Diagnostic_Tool.pdf";
                String check_ecu="http://deviseelectronics.com/mahindra/files/assets/data/checkings/How_To_Connect_ECU.pdf";
                String getClickValue = getValue.getText().toString().trim();

                if (getClickValue.equals("कळवण")) {
                    android.support.v4.app.Fragment detail = new kalwanprecaution();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_container, detail).commit();
                 /*  Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse( "http://docs.google.com/viewer?url=" + check_fuses), "text/html");
                    startActivity(intent);*/
                   /* Intent intent = new Intent(getActivity(), kalwanweather.class);

                    startActivity(intent);*/
                    /*Intent intent = new Intent(getActivity(), kalwanweather.class);

                    getActivity().startActivity(intent);*/

                }
                if (getClickValue.equals("सटाणा")) {
                    android.support.v4.app.Fragment detail = new satanaprecaution();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_container, detail).commit();
                   /* Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse( "http://docs.google.com/viewer?url=" + check_relay), "text/html");
                    startActivity(intent);*/
                 /*   Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("URL","http://docs.google.com/viewer?url=" + check_relay);
                    startActivity(intent);*/


                }
                if (getClickValue.equals("मालेगाव")) {
                    android.support.v4.app.Fragment detail = new malegoanprecaution();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_container, detail).commit();
                   /* Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse( "http://docs.google.com/viewer?url=" + multimiter), "text/html");
                    startActivity(intent);*/
                   /* Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("URL","http://docs.google.com/viewer?url=" + multimiter);
                    startActivity(intent);*/



                }
                if (getClickValue.equals("देवळा")) {
                    android.support.v4.app.Fragment detail = new deolaprecaution();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_container, detail).commit();
                   /* Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse( "http://docs.google.com/viewer?url=" + check_battry), "text/html");
                    startActivity(intent);*/
                /*    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("URL","http://docs.google.com/viewer?url=" + check_battry);
                    startActivity(intent);*/

                }
                if (getClickValue.equals("How to Check Wiring Harness to ECU")) {
                   /* Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse( "http://docs.google.com/viewer?url=" + check_ecu), "text/html");
                    startActivity(intent);*/
                   /* Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("URL","http://docs.google.com/viewer?url=" + check_ecu);
                    startActivity(intent);*/


                }
                if (getClickValue.equals("How to use Garuda")) {
                    /*Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse( "http://docs.google.com/viewer?url=" + garuda), "text/html");
                    startActivity(intent);*/
                 /*   Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("URL","http://docs.google.com/viewer?url=" + garuda);
                    startActivity(intent);*/


                }
            }
        });
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
