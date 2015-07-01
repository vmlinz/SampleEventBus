package com.televpn.example.sampleeventbus;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.televpn.example.sampleeventbus.Events.HelloEvent;
import com.televpn.example.sampleeventbus.Events.MenuClickedEvent;
import com.televpn.example.sampleeventbus.Events.OkButtonClickedEvent;

import de.greenrobot.event.EventBus;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private static final EventBus bus = EventBus.getDefault();
    private static final String TAG = MainActivityFragment.class.getSimpleName();

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        View button = rootView.findViewById(R.id.ok_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bus.post(new OkButtonClickedEvent());
            }
        });

        bus.registerSticky(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        bus.unregister(this);
    }

    @SuppressWarnings("unused")
    public void onEvent(HelloEvent event) {
        Log.d(TAG, event.message);
    }

    @SuppressWarnings("unused")
    public void onEvent(MenuClickedEvent event) {
        Log.d(TAG, "Menu clicked");
    }
}
