package warmup.nikosstais.atcom.com.devtest2.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import warmup.nikosstais.atcom.com.devtest2.R;
import warmup.nikosstais.atcom.com.devtest2.activity.main.MainActivity;
import warmup.nikosstais.atcom.com.devtest2.activity.speaker.SpeakerDetailActivity;
import warmup.nikosstais.atcom.com.devtest2.fragments.FragmentSpeakerDetail;
import warmup.nikosstais.atcom.com.devtest2.remote.data.models.Speaker;

public class SpeakersGridViewAdapter extends RecyclerView.Adapter<SpeakersGridViewAdapter.ViewHolder> {


    private final List<Speaker> speakerList;
    private final MainActivity parentActivity;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            final Speaker speaker = (Speaker) view.getTag();
            Context context = view.getContext();
            Intent intent = new Intent(context, SpeakerDetailActivity.class);
            intent.putExtra(FragmentSpeakerDetail.ARG_ITEM_ID, (Parcelable) speaker);
        }
    };

    public SpeakersGridViewAdapter(List<Speaker> speakerList, MainActivity parentActivity) {
        this.speakerList = speakerList;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.speaker_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Speaker speaker = speakerList.get(position);

        try {
            Picasso.with(holder.itemView.getContext())
                    .load(speaker.getPhoto())
                    .into(holder.speakerImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder sbName = new StringBuilder();
        sbName.append(speaker.getName());
        sbName.append(speaker.getSurname());

        StringBuilder sbCompany = new StringBuilder("// ");
        sbCompany.append(speaker.getCompanyName());

        holder.speakerCompanyName.setText(sbCompany.toString());
        holder.speakerNameSurname.setText(sbName.toString());
    }

    @Override
    public int getItemCount() {
        return speakerList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView speakerNameSurname;
        final TextView speakerCompanyName;
        final ImageView speakerImage;



        ViewHolder(View view) {
            super(view);
            speakerNameSurname = view.findViewById(R.id.speakerGridNameSurname);
            speakerCompanyName = view.findViewById(R.id.speakerGridCompanyName);
            speakerImage = view.findViewById(R.id.speakerGridImage);

        }
    }
}
