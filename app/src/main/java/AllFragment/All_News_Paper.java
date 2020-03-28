package AllFragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.kasamade.news.R;
import com.kasamade.news.WebViewActivity;

import Adapter.How_To_Adapter1;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link All_News_Paper.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link All_News_Paper#newInstance} factory method to
 * create an instance of this fragment.
 */
public class All_News_Paper extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View view;
    private GridView gridView;
    static final String[] CHECK = new String[]{"लोकमत", "सकाळ", "लोकसत्ता", "दिव्य मराठी", "महाराष्ट्र टाइम्स", "सामना", "पुढारी",  "देशदूत", "पुण्यनगरी", "गांवकरी", "प्रहार", "तरुण भारत"};

    private OnFragmentInteractionListener mListener;

    public All_News_Paper() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment All_News_Paper.
     */
    // TODO: Rename and change types and number of parameters
    public static All_News_Paper newInstance(String param1, String param2) {
        All_News_Paper fragment = new All_News_Paper();
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

        view = inflater.inflate(R.layout.fragment_all__news__paper, container, false);



        callgrid();

        return view;


    }



    private void callgrid()
    {
        gridView = (GridView)view.findViewById(R.id.gridView3);
        gridView.setAdapter(new How_To_Adapter1(getActivity(), CHECK));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                //Toast.makeText(getApplicationContext(), ((TextView) v.findViewById(R.id.grid_item_label)).getText(), //Toast.LENGTH_SHORT).show();
                TextView getValue = (TextView) v.findViewById(R.id.grid_item_label2);
               String getClickValue = getValue.getText().toString().trim();

                if (getClickValue.equals("लोकमत")) {


                    String url="http://www.lokmat.com";
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("URL",url );
                    getActivity().startActivity(intent);


                }
                if (getClickValue.equals("सकाळ")) {
                    String url="http://www.esakal.com";
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("URL",url );
                    getActivity().startActivity(intent);
                   /* Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse( "http://docs.google.com/viewer?url=" + check_relay), "text/html");
                    startActivity(intent);*/
                 /*   Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("URL","http://docs.google.com/viewer?url=" + check_relay);
                    startActivity(intent);*/


                }
                if (getClickValue.equals("लोकसत्ता")) {
                    String url="https://www.loksatta.com/";
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("URL",url );
                    getActivity().startActivity(intent);
                   /* Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse( "http://docs.google.com/viewer?url=" + multimiter), "text/html");
                    startActivity(intent);*/
                   /* Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("URL","http://docs.google.com/viewer?url=" + multimiter);
                    startActivity(intent);*/



                }
                if (getClickValue.equals("दिव्य मराठी")) {
                    String url="https://divyamarathi.bhaskar.com";
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("URL",url );
                    getActivity().startActivity(intent);
                   /* Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse( "http://docs.google.com/viewer?url=" + check_battry), "text/html");
                    startActivity(intent);*/
                /*    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("URL","http://docs.google.com/viewer?url=" + check_battry);
                    startActivity(intent);*/

                }
                if (getClickValue.equals("महाराष्ट्र टाइम्स")) {



                    String url="https://maharashtratimes.indiatimes.com/";
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("URL",url );
                    getActivity().startActivity(intent);
                   /* Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse( "http://docs.google.com/viewer?url=" + check_ecu), "text/html");
                    startActivity(intent);*/
                   /* Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("URL","http://docs.google.com/viewer?url=" + check_ecu);
                    startActivity(intent);*/


                }
                if (getClickValue.equals("सामना")) {

                    String url="http://www.saamana.com";
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("URL",url );
                    getActivity().startActivity(intent);
                    /*Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse( "http://docs.google.com/viewer?url=" + garuda), "text/html");
                    startActivity(intent);*/
                 /*   Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("URL","http://docs.google.com/viewer?url=" + garuda);
                    startActivity(intent);*/


                }



                if (getClickValue.equals("पुढारी")) {

                    String url="http://newspaper.pudhari.co.in";
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("URL",url );
                    getActivity().startActivity(intent);
                    /*Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse( "http://docs.google.com/viewer?url=" + garuda), "text/html");
                    startActivity(intent);*/
                 /*   Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("URL","http://docs.google.com/viewer?url=" + garuda);
                    startActivity(intent);*/


                }

                if (getClickValue.equals("देशदूत")) {

                    String url="http://www.deshdoot.com/";
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("URL",url );
                    getActivity().startActivity(intent);
                    /*Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse( "http://docs.google.com/viewer?url=" + garuda), "text/html");
                    startActivity(intent);*/
                 /*   Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("URL","http://docs.google.com/viewer?url=" + garuda);
                    startActivity(intent);*/


                }
                if (getClickValue.equals("पुण्यनगरी")) {

                    String url="http://www.epunyanagari.com/";
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("URL",url );
                    getActivity().startActivity(intent);
                    /*Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse( "http://docs.google.com/viewer?url=" + garuda), "text/html");
                    startActivity(intent);*/
                 /*   Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("URL","http://docs.google.com/viewer?url=" + garuda);
                    startActivity(intent);*/


                }

                if (getClickValue.equals("गांवकरी")) {

                    String url="http://www.adarshgavkari.in/";
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("URL",url );
                    getActivity().startActivity(intent);
                    /*Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse( "http://docs.google.com/viewer?url=" + garuda), "text/html");
                    startActivity(intent);*/
                 /*   Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("URL","http://docs.google.com/viewer?url=" + garuda);
                    startActivity(intent);*/


                }





                if (getClickValue.equals("प्रहार")) {

                    String url="http://prahaar.in/";
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("URL",url );
                    getActivity().startActivity(intent);
                    /*Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse( "http://docs.google.com/viewer?url=" + garuda), "text/html");
                    startActivity(intent);*/
                 /*   Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("URL","http://docs.google.com/viewer?url=" + garuda);
                    startActivity(intent);*/


                }


                if (getClickValue.equals("तरुण भारत")) {

                    String url="http://epaper.tarunbharat.com/";
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("URL",url );
                    getActivity().startActivity(intent);
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {

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
