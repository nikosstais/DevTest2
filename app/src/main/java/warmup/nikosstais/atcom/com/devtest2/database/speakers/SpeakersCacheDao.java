package warmup.nikosstais.atcom.com.devtest2.database.speakers;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface SpeakersCacheDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addArticlesToCache(SpeakersCache speakersCache);

    @Query("select * from SpeakersCache order by id desc")
    List<SpeakersCache> getLatestSpeakersFromCache();

    @Query("delete from SpeakersCache")
    void cleanSpeakersCache();
}
