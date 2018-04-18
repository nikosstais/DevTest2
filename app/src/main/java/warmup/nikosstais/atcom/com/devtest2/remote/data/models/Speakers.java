package warmup.nikosstais.atcom.com.devtest2.remote.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Speakers implements Serializable {
    @SerializedName("speakers")
    private List<Speaker> speakerList;

    public List<Speaker> getSpeakerList() {
        return speakerList;
    }

    public void setSpeakerList(List<Speaker> speakerList) {
        this.speakerList = speakerList;
    }
}