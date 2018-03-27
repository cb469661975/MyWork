package com.example.cheng.myapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.cheng.myapplication.factorymodel.View1;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.example.cheng.myapplication", appContext.getPackageName());
        //我们不知道这个框架中有什么东西都的  不知懂啊调用曾
//        对象的产生过程，叫做最少知识原则

    }

    /**
     * 最少知识原则，
     *
     * @param args
     */

    public static void main(String[]args){

    }
}
