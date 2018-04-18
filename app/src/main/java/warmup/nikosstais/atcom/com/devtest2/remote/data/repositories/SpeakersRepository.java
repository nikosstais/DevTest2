package warmup.nikosstais.atcom.com.devtest2.remote.data.repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import warmup.nikosstais.atcom.com.devtest2.database.AppDatabase;
import warmup.nikosstais.atcom.com.devtest2.database.speakers.SpeakersCache;
import warmup.nikosstais.atcom.com.devtest2.remote.apis.ErmisAwardApiInterface;
import warmup.nikosstais.atcom.com.devtest2.remote.data.models.Speaker;
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
        return new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        }).create();
    }

    private List<Speaker> getSpeakersFromDB() {
        List<SpeakersCache> speakersCacheList = AppDatabase.getDatabase()
                .speakersCacheDao()
                .getLatestSpeakersFromCache();

        if (speakersCacheList.isEmpty()){
            return new ArrayList<>();
        }

        final SpeakersCache speakersCache = speakersCacheList.get(0);

        SpeakersResponse savedResponse = getGsonBuilder()
                .fromJson(speakersCache.responseText, SpeakersResponse.class);

        return savedResponse.getSpeakers().getSpeakerList();
    }

    private void addSpeakerResponseToDB(SpeakersResponse response){
        SpeakersCache resToSave = new SpeakersCache();
        resToSave.dateInserted = Calendar.getInstance().getTime().toString();//checking public properties don't like them
        resToSave.responseText = getGsonBuilder().toJson(response, SpeakersResponse.class);//checking public properties don't like them

        AppDatabase.getDatabase().speakersCacheDao().cleanSpeakersCache();
        AppDatabase.getDatabase().speakersCacheDao().addArticlesToCache(resToSave);
    }

}
