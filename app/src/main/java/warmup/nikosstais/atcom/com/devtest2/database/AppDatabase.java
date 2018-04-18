package warmup.nikosstais.atcom.com.devtest2.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import warmup.nikosstais.atcom.com.devtest2.database.speakers.SpeakersCache;
import warmup.nikosstais.atcom.com.devtest2.database.speakers.SpeakersCacheDao;
import warmup.nikosstais.atcom.com.devtest2.system.AndroidTestApplication;

@Database(entities = {SpeakersCache.class},
        version = 1,
        exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase() {
        if (INSTANCE == null) {
            Context context = AndroidTestApplication.getInstance().getApplicationContext();
            INSTANCE =
                    Room.databaseBuilder(context, AppDatabase.class, "responsesDatabase")
                            .fallbackToDestructiveMigration()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract SpeakersCacheDao speakersCacheDao();
}
