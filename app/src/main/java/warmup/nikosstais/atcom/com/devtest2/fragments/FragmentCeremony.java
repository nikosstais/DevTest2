package warmup.nikosstais.atcom.com.devtest2.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import warmup.nikosstais.atcom.com.devtest2.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentCeremony#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCeremony extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_ITEM_NUMBER = "5";

    private OnFragmentInteractionListener mListener;

    public FragmentCeremony() {
        // Required empty public constructor
    }

    public static FragmentCeremony newInstance(int position) {
        FragmentCeremony fragment = new FragmentCeremony();
        Bundle args = new Bundle();
        args.putInt(ARG_ITEM_NUMBER, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_ITEM_NUMBER);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_ceremony, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(int position) {
        if (mListener != null) {
            mListener.onFragmentInteraction(position);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
