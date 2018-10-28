package com.example.auzan.cataloguemovieuiux;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.evernote.android.job.DailyJob;
import com.evernote.android.job.Job;
import com.evernote.android.job.JobCreator;
import com.evernote.android.job.JobRequest;

import java.util.concurrent.TimeUnit;

/**
 * Created by auzan on 10/28/2018.
 * Github: @auzanassdq
 */
public class ReminderJobCreator implements JobCreator {
    @Nullable
    @Override
    public Job create(@NonNull String tag) {
        return null;
    }

    public final class MyDailyJob extends DailyJob {

        public static final String TAG = "MyDailyJob";

        public void schedule() {
            // schedule between 1 and 6 AM
            DailyJob.schedule(new JobRequest.Builder(TAG), TimeUnit.HOURS.toMillis(1), TimeUnit.HOURS.toMillis(6));
        }

        @NonNull
        @Override
        protected DailyJobResult onRunDailyJob(Params params) {
            return DailyJobResult.SUCCESS;
        }
    }
}
