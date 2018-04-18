package warmup.nikosstais.atcom.com.devtest2.remote.apis;

import retrofit2.Call;
import retrofit2.http.GET;
import warmup.nikosstais.atcom.com.devtest2.remote.data.models.SpeakersResponse;

public interface ErmisAwardApiInterface {
    String BASE_URL = "https://www.ermisawards.gr/";

    @GET("ermismobile/Awards/GetSpeakers/?categoryId=1")
    Call<SpeakersResponse> getSpeakersResponse();

}
