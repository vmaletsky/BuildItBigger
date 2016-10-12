package com.udacity.gradle.builditbigger;

import android.util.Log;

import org.junit.Test;
import org.junit.Assert;

/**
 * Created by v.maletskiy on 11.10.2016.
 */
public class EndpointsAsyncTaskTest {
    EndpointsAsyncTask mTask;
    @Test
    public void testAsyncTask() throws Exception {
        mTask = new EndpointsAsyncTask(
                new OnAsyncTaskCompleted() {
                    @Override
                    public void onCompleted(String joke) {
                        Assert.assertNotEquals(null, joke);
                    }
                }
        );
        mTask.execute();
    }

}