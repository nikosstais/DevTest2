package warmup.nikosstais.atcom.com.devtest2.database.speakers;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "SpeakersCache")
public class SpeakersCache {
    @PrimaryKey(autoGenerate = true)
    public Long id;
    public String dateInserted;//checking public properties don't like them
    public String responseText;//checking public properties don't like them
}
