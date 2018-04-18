package warmup.nikosstais.atcom.com.devtest2.remote.data.models;

import java.io.Serializable;
import java.util.List;

public class SpeakersResponse implements Serializable {
    private boolean success;
    private Speakers speakers;
    private String error;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Speakers getSpeakers() {
        return speakers;
    }

    public void setSpeakers(Speakers speakers) {
        this.speakers = speakers;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

class Speakers implements Serializable{
    private List<Speaker> speakerList;

    public List<Speaker> getSpeakerList() {
        return speakerList;
    }

    public void setSpeakerList(List<Speaker> speakerList) {
        this.speakerList = speakerList;
    }
}