package com.czy.binderstudy;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    Button basicTypesBtn;
    Button getPidBtn;
    Button getBookBtn;
    Button addBookBtn;
    Button binderBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binderBtn = (Button) findViewById(R.id.binder);
        basicTypesBtn = (Button) findViewById(R.id.basicTypes);
        getPidBtn = (Button) findViewById(R.id.getPid);
        getBookBtn = (Button) findViewById(R.id.getBook);
        addBookBtn = (Button) findViewById(R.id.addBook);


        binderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService();
            }
        });

        basicTypesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIMyAidlInterface != null) {
                    try {
                        mIMyAidlInterface.basicTypes(1, System.currentTimeMillis(), true, 3.14f, Math.PI, "hello");
                    } catch (RemoteException e) {
                        e.printStackTrace();

                    }
                }
            }
        });
        getPidBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIMyAidlInterface != null) {
                    try {
                        int otherProcessPid = mIMyAidlInterface.getPid();
                        Log.e(TAG, "getPid: otherProcessPid = " + otherProcessPid);
                    } catch (RemoteException e) {
                        e.printStackTrace();

                    }
                }
            }
        });
        getBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIMyAidlInterface != null) {
                    try {
                        Book book = mIMyAidlInterface.getBook();
                        Log.e(TAG, "getBook: book = " + book.toString());
                    } catch (RemoteException e) {
                        e.printStackTrace();

                    }
                }
            }
        });
        addBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIMyAidlInterface != null) {
                    try {
                        mIMyAidlInterface.addBook(new Book(1, "add Book"));
                    } catch (RemoteException e) {
                        e.printStackTrace();

                    }
                }
            }
        });

    }

    IMyAidlInterface mIMyAidlInterface;

    private void bindService() {
        Intent intent = new Intent(this, Process2Service.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d(TAG, "onServiceConnected() called with: " + "componentName = [" + componentName + "], iBinder = [" + iBinder + "]");
                mIMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
                Toast.makeText(getContext(), "binder Connected", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                mIMyAidlInterface = null;
                Toast.makeText(getContext(), "binder Disconnected", Toast.LENGTH_SHORT).show();

            }
        }, Context.BIND_AUTO_CREATE);
        //Context.BIND_AUTO_CREATE  表示当 service 没有启动时则 自动启动 onCreate
    }

    private Context getContext() {
        return this;
    }
}
