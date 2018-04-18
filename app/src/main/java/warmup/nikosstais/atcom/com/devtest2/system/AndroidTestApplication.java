package warmup.nikosstais.atcom.com.devtest2.system;

import android.support.multidex.MultiDexApplication;

import warmup.nikosstais.atcom.com.devtest2.database.AppDatabase;
import warmup.nikosstais.atcom.com.devtest2.system.network.ConnectivityReceiver;

public class AndroidTestApplication extends MultiDexApplication {
    private static AndroidTestApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        AppDatabase.getDatabase();
    }

    public static synchronized AndroidTestApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        AppDatabase.destroyInstance();
    }
}
