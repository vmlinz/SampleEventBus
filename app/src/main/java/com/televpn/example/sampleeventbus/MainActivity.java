package com.televpn.example.sampleeventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.televpn.example.sampleeventbus.Events.CreateViewEvent;
import com.televpn.example.sampleeventbus.Events.HelloEvent;
import com.televpn.example.sampleeventbus.Events.MenuClickedEvent;
import com.televpn.example.sampleeventbus.Events.OkButtonClickedEvent;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            EventBus.getDefault().post(new MenuClickedEvent());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("unused")
    public void onEvent(MenuClickedEvent event) {
        Log.d(TAG, "Menu clicked");
    }

    @SuppressWarnings("unused")
    public void onEvent(HelloEvent event) {
        Log.d(TAG, "Message received: " + event.message);
    }

    @SuppressWarnings("unused")
    public void onEvent(CreateViewEvent event) {
        Log.d(TAG, "Fragment view created");
    }

    @SuppressWarnings("unused")
    public void onEvent(OkButtonClickedEvent event) {
        Log.d(TAG, "Ok Button clicked");

        Toast.makeText(this, "Ok Button clicked", Toast.LENGTH_LONG).show();
    }
}
