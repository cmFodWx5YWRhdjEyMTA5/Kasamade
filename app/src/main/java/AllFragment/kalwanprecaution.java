package AllFragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kasamade.news.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link kalwanprecaution.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link kalwanprecaution#newInstance} factory method to
 * create an instance of this fragment.
 */
public class kalwanprecaution extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    ImageView ivGramContOne,ivGramContTwo,ivWaterOne,ivHealthOne,ivElecOne;
    public kalwanprecaution() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment kalwanprecaution.
     */
    // TODO: Rename and change types and number of parameters
    public static kalwanprecaution newInstance(String param1, String param2) {
        kalwanprecaution fragment = new kalwanprecaution();
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

        View rootView = inflater.inflate(R.layout.fragment_kalwanprecaution, container,false);

        ivGramContOne=(ImageView)rootView.findViewById(R.id.ivGramContOne);
        ivGramContOne.setOnClickListener(this);
        ivGramContTwo=(ImageView)rootView.findViewById(R.id.ivGramContTwo);
        ivGramContTwo.setOnClickListener(this);
        ivWaterOne=(ImageView)rootView.findViewById(R.id.ivWaterOne);
        ivWaterOne.setOnClickListener(this);
        ivHealthOne=(ImageView)rootView.findViewById(R.id.ivHealthOne);
        ivHealthOne.setOnClickListener(this);
        ivElecOne=(ImageView)rootView.findViewById(R.id.ivElecOne);
        ivElecOne.setOnClickListener(this);


        return rootView;
        //return inflater.inflate(R.layout.fragment_kalwanprecaution, container, false);
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
           // throw new RuntimeException(context.toString(+ " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    @Override
    public void onClick(View v) {
        int id=v.getId();
       /* AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
        AlertDialog alert = builder.create();*/
        switch (id) {
            case R.id.ivGramContOne:
                callNumber("0259221033");
                break;
            case R.id.ivGramContTwo:
                callNumber("100");
                break;
            case R.id.ivWaterOne:
                callNumber("02592221039");
                break;
            case R.id.ivHealthOne:
                callNumber("101");
                break;
            case R.id.ivElecOne:
                callNumber("102");
                break;





        }

    }
    public void callNumber(String number)
    {
        String phone="";
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        phone="tel:"+number;

        callIntent.setData(Uri.parse(phone));
        startActivity(callIntent);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

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
