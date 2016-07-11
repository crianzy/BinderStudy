package com.czy.binderstudy;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by chenzhiyong on 16/7/11.
 */
public class Process2Service extends Service {
    private static final String TAG = "Process2Service";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.w(TAG, "onCreate() called with: " + "");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.w(TAG, "onStartCommand() called with: " + "intent = [" + intent + "], flags = [" + flags + "], startId = [" + startId + "]");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            Log.w(TAG, "basicTypes() called with: " + "anInt = [" + anInt + "], aLong = [" + aLong + "], aBoolean = [" + aBoolean + "], aFloat = [" + aFloat + "], aDouble = [" + aDouble + "], aString = [" + aString + "]");
        }

        @Override
        public int getPid() throws RemoteException {
            return Process.myPid();
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            Log.w(TAG, "addBook() called with: " + "book = [" + book + "]");
        }

        @Override
        public Book getBook() throws RemoteException {
            return new Book(100, "book");
        }
    };


    /**
     * Command to the service to display a message
     */
    static final int MSG_SAY_HELLO = 1;

    /**
     * Handler of incoming messages from clients.
     */
    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    Toast.makeText(getApplicationContext(), "hello!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    /**
     * Target we publish for clients to send messages to IncomingHandler.
     */
    final Messenger mMessenger = new Messenger(new IncomingHandler());

}
