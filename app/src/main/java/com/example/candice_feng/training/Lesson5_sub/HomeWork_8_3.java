package com.example.candice_feng.training.Lesson5_sub;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.candice_feng.training.BaseActivity;
import com.example.candice_feng.training.R;

/*
https://google-developer-training.gitbooks.io/android-developer-fundamentals-course-practicals/content/en/Homework/7_8_homework.html#JobScheduler

It delivers a notification in place of performing an actual download.
The "download" is performed once a day, when the phone is idle but connected to power and to WiFi, or when the button is pressed.
When the user taps the Download Now button, it triggers a "downloading" notification.
 */

public class HomeWork_8_3 extends BaseActivity {


    private ComponentName mServiceComponent;
    private int mJobId = 532;
    static int ONGOING_NOTIFICATION_ID = 123;
    static String NOTIFICATION_CHANNEL_ID = "My_download_job";

    static NotificationManager notificationManager;
    static Context mContext;


    static class MyJobScheduler extends JobService {
        private final String SERVICE_TAG = MyJobScheduler.class.getSimpleName();

        public MyJobScheduler() {
            super();
        }

        @Override
        public boolean onStartJob(JobParameters params) {
            Log.i(SERVICE_TAG, "onStartJob");
            Log.i(SERVICE_TAG, "ID: " + params.getJobId());

            makeNotification();
            return false;
        }

        @Override
        public boolean onStopJob(JobParameters params) {
            Log.i(SERVICE_TAG, "onStopJob");
            return false;
        }

        @Override
        public void onCreate() {
            super.onCreate();
            Log.i(SERVICE_TAG, "Service created");
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.i(SERVICE_TAG, "Service destroyed");
        }

        /**
         * When the app's MainActivity is created, it starts this service. This is so that the
         * activity and this service can communicate back and forth. See "setUiCallback()"
         */
        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            Log.i(SERVICE_TAG, "onStartCommand");
            return START_NOT_STICKY;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_work_8_3);
        if (notificationManager == null) {
            notificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        mContext = this.getApplicationContext();

        new MyJobScheduler();
        mServiceComponent = new ComponentName(this, MyJobScheduler.class);
        downloadJob();
        Button downloadBtn = findViewById(R.id.download_btn);
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //forceExecute();
                makeNotification();
            }
        });
    }

    public void forceExecute() {
        Log.i(TAG, "forceExecute");
        JobInfo jobInfo;

        jobInfo = new JobInfo.Builder(mJobId, mServiceComponent)
                .setMinimumLatency(0)
                .build();

        JobScheduler tm = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);

        tm.schedule(jobInfo);

    }

    public void downloadJob() {
        //setup builder
        JobInfo.Builder builder = new JobInfo.Builder(mJobId, mServiceComponent);
        //Condition: use wifi
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED);
        //Condition: only charging
        builder.setRequiresCharging(true);
        //Condition: Device Idle
        builder.setRequiresDeviceIdle(true);
        //Condition: Period is one day : 24 * 60 * 60 * 1000
        builder.setPeriodic(86400000);

        // Schedule job
        JobScheduler tm = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        tm.schedule(builder.build());

    }

    @Override
    protected void onStop() {
        // A service can be "started" and/or "bound". In this case, it's "started" by this Activity
        // and "bound" to the JobScheduler (also called "Scheduled" by the JobScheduler). This call
        // to stopService() won't prevent scheduled jobs to be processed. However, failing
        // to call stopService() would keep it alive indefinitely.
        stopService(new Intent(this, MyJobScheduler.class));
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Start service and provide it a way to communicate with this class.
        Intent startServiceIntent = new Intent(this, MyJobScheduler.class);
        startService(startServiceIntent);
    }

    public static void makeNotification() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.i(TAG,"SDK Version over O");
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);
            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            if (notificationChannel == null) {
                Log.i(TAG, "set up notification channel.");
                // Configure the notification channel.
                notificationChannel.setDescription("Channel description");
                notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
                notificationChannel.enableVibration(true);
                notificationChannel.setSound(sound, Notification.AUDIO_ATTRIBUTES_DEFAULT);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        Log.i(TAG, "Notification builder");
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(mContext, NOTIFICATION_CHANNEL_ID)
                        .setSmallIcon(android.R.drawable.ic_popup_reminder)
                        .setContentTitle("Performing")
                        .setContentText("Download in progress.");

        Log.i(TAG,"start Foreground");
        notificationManager.notify(ONGOING_NOTIFICATION_ID,mBuilder.build());
    }


}
