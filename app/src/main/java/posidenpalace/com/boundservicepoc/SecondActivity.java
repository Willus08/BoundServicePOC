package posidenpalace.com.boundservicepoc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    RecyclerView recy;
    RecyclerAdapter adapt;
    DefaultItemAnimator animate;
    RecyclerView.LayoutManager layoutm;
    List<String> randomStrings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        recy = (RecyclerView) findViewById(R.id.rvSARecyclerView);

    }

    private void setupRecyclerView() {

        adapt = new RecyclerAdapter(randomStrings);
        animate = new DefaultItemAnimator();
        layoutm = new LinearLayoutManager(this);

        recy.setAdapter(adapt);
        recy.setLayoutManager(layoutm);
        recy.setItemAnimator(animate);
    }

    MyService mService;
    boolean mBound = false;

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onStop() {
        super.onStop();
        // Unbind from the service
        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }
    }



    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            MyService.LocalBinder binder = (MyService.LocalBinder) service;
            mService = binder.getService();
            int number = getIntent().getIntExtra("number",1);
            randomStrings = mService.getStrings(number);
            mBound = true;
            setupRecyclerView();

        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}
