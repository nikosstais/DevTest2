package warmup.nikosstais.atcom.com.devtest2.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import warmup.nikosstais.atcom.com.devtest2.R;
import warmup.nikosstais.atcom.com.devtest2.fragments.FragmentHappenings;
import warmup.nikosstais.atcom.com.devtest2.fragments.FragmentSpeaker.OnListFragmentInteractionListener;
import warmup.nikosstais.atcom.com.devtest2.remote.data.models.Speaker;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Speaker} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class AdapterSpeakerRecyclerView extends RecyclerView.Adapter<AdapterSpeakerRecyclerView.ViewHolder> {

    private final List<Speaker> mValues;
    private FragmentHappenings.OnFragmentInteractionListener mListener;

    public AdapterSpeakerRecyclerView(List<Speaker> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_speaker_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getName());
        holder.mContentView.setText(mValues.get(position).getCompanyName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onFragmentInteraction(holder.mItem.getRowID());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView mIdView;
        final TextView mContentView;
        public Speaker mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.speakerGridNameSurname);
            mContentView = view.findViewById(R.id.speakerDetailCompany);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
