package warmup.nikosstais.atcom.com.devtest2.activity.splash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;

import warmup.nikosstais.atcom.com.devtest2.R;
import warmup.nikosstais.atcom.com.devtest2.activity.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            // Using handler with postDelayed called runnable run method

            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, 3*1000);
    }
}
