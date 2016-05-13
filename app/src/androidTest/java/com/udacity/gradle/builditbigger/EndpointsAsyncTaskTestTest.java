package com.udacity.gradle.builditbigger;

import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.test.AndroidTestCase;
import android.test.ServiceTestCase;
import android.util.Pair;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by 3j1ka9cjk119fj2nda on 5/12/2016.
 */
public class EndpointsAsyncTaskTestTest extends AndroidTestCase {
    private String mJoke = "";
    private CountDownLatch mCountDownLatch;
    private EndpointsAsyncTask mEndpointsAsyncTask;

    /**
     * Sets up the CountDownLatch and EndpointAsyncTask for testing
     * @throws Exception
     */
    @Before
    public void testSetUp() throws Exception {
        mEndpointsAsyncTask = new EndpointsAsyncTask(getContext());
        mCountDownLatch =  new CountDownLatch(1);
    }

    /**
     * Test retrieving a joke from the google app server using an EndpointsAsyncTask
     * @throws Exception
     */
    @Test
    public void testTestEndpointsAsyncTask() throws Exception {
        testSetUp();
        mEndpointsAsyncTask.execute(new Pair<Context, String>(mEndpointsAsyncTask.getContext(), "Joke"));
        mEndpointsAsyncTask.setListener(new EndpointsAsyncTask.EndpointsAsyncTaskListener() {
            @Override
            public void onCompleted(String joke) {
                mJoke = joke;
                mCountDownLatch.countDown();
            }
        });

        //Causes current thread to wait until the latch has counted to 0, unless interrupted
        mCountDownLatch.await(20, TimeUnit.SECONDS);
        //String should equal joke(String) passed in as parameter in execute call of EndpointsAsyncTask
        assertEquals("Joke", mJoke);
    }

}