package warmup.nikosstais.atcom.com.devtest2.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import warmup.nikosstais.atcom.com.devtest2.R;
import warmup.nikosstais.atcom.com.devtest2.remote.data.models.Speaker;


public class FragmentSpeakerDetail extends Fragment {
    public static final String ARG_ITEM_ID = "item_id";

    private OnFragmentInteractionListener mListener;
    private Speaker speaker;

    public FragmentSpeakerDetail() {
        // Required empty public constructor
    }


    public static FragmentSpeakerDetail newInstance(Speaker speaker) {
        FragmentSpeakerDetail fragment = new FragmentSpeakerDetail();
        Bundle args = new Bundle();
        args.putParcelable(ARG_ITEM_ID, speaker);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(ARG_ITEM_ID)) {
            speaker = (Speaker) getArguments().getSerializable(ARG_ITEM_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragmet_speaker_detail, container, false);

        if(speaker!=null){
            try {
                Picasso.with(rootView.getContext())
                        .load(speaker.getPhoto())
                        .into((ImageView) rootView.findViewById(R.id.speakerDetailImage));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rootView;
    }


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
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
