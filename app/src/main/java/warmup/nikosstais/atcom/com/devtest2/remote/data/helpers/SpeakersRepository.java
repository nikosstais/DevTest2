package warmup.nikosstais.atcom.com.devtest2.remote.data.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import warmup.nikosstais.atcom.com.devtest2.remote.apis.ErmisAwardApiInterface;
import warmup.nikosstais.atcom.com.devtest2.remote.data.models.SpeakersResponse;

public class SpeakersRepository {
    private static final String TAG = SpeakersRepository.class.toString();
    private static final SpeakersRepository INSTANCE = new SpeakersRepository();
    private static final ErmisAwardApiInterface apiInterface = getService();

    private SpeakersRepository(){}

    public static SpeakersRepository getInstance(){

        return INSTANCE;
    }


    public Single<SpeakersResponse> getSpeakersRespone(){
        return apiInterface.getSpeakersResponse();
    }


    private static ErmisAwardApiInterface getService(){
        return  new Retrofit.Builder()
                .baseUrl(ErmisAwardApiInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(getGsonBuilder()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(ErmisAwardApiInterface.class);
    }

    private static Gson getGsonBuilder() {
        return new GsonBuilder()
                .create();
    }

}
