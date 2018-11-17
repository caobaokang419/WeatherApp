package com.gary.weatherdemo.firebase.crashreport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.crashlytics.android.Crashlytics;
import com.gary.weatherdemo.R;


/**
 * Created by GaryCao on 2018/11/16.
 * <p>
 * Firebase崩溃上报
 */
public class CrashReportActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb_crash_report);

        // Log the onCreate event, this will also be printed in logcat
        Crashlytics.log(Log.VERBOSE, TAG, "onCreate");

        // Add some custom values and identifiers to be included in crash reports
        Crashlytics.setInt("MeaningOfLife", 42);
        Crashlytics.setString("LastUIAction", "Test value");
        Crashlytics.setUserIdentifier("123456789");

        // Report a non-fatal exception, for demonstration purposes
        Crashlytics.logException(new Exception("Non-fatal exception: something went wrong!"));

        // Checkbox to indicate when to catch the thrown exception.
        final CheckBox catchCrashCheckBox = findViewById(R.id.catchCrashCheckBox);

        // Button that causes NullPointerException to be thrown.
        Button crashButton = findViewById(R.id.crashButton);
        crashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Log that crash button was clicked.
                Crashlytics.log(Log.INFO, TAG, "Crash button clicked.");
                if (catchCrashCheckBox.isChecked()) {
                    try {
                        throw new NullPointerException();
                    } catch (NullPointerException ex) {
                        Crashlytics.log(Log.ERROR, TAG, "NPE caught!");
                        Crashlytics.logException(ex);
                    }
                } else {
                    //TODO: 主动throw Exception，测试Firebase后台是否能收到上报？？？
                    throw new NullPointerException();
                }
            }
        });

        // Log that the Activity was created.
        Crashlytics.log("Activity created");
    }
}

