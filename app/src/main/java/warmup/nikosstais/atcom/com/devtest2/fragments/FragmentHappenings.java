package warmup.nikosstais.atcom.com.devtest2.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import warmup.nikosstais.atcom.com.devtest2.R;
import warmup.nikosstais.atcom.com.devtest2.activity.main.MainActivity;
import warmup.nikosstais.atcom.com.devtest2.adapters.SpeakersGridViewAdapter;
import warmup.nikosstais.atcom.com.devtest2.fragments.views.FragmentHappeningsView;
import warmup.nikosstais.atcom.com.devtest2.presenters.FragmentHappeningsPresenter;
import warmup.nikosstais.atcom.com.devtest2.remote.data.models.Speaker;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentHappenings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHappenings extends Fragment implements FragmentHappeningsView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SECTION_NUMBER = "1";

    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private FragmentHappeningsPresenter presenter;

    public FragmentHappenings() {
        // Required empty public constructor
    }

    public static FragmentHappenings newInstance(int position) {
        FragmentHappenings fragment = new FragmentHappenings();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, position);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new FragmentHappeningsPresenter(this, AndroidSchedulers.mainThread());

        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_ITEM_NUMBER);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        //getActivity().findViewById(R.id.recyclerview)
        recyclerView  = container.findViewById(R.id.recyclerview);
        presenter.loadSpeakers();


        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

        return inflater.inflate(R.layout.fragment_fragment_happenings, container, false);
    }

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
        presenter.unsubscribe();
    }

    @Override
    public void displaySpeakers(List<Speaker> speakerList) {
        recyclerView.setAdapter(
                new SpeakersGridViewAdapter(speakerList,(MainActivity) getActivity()));
    }
}
