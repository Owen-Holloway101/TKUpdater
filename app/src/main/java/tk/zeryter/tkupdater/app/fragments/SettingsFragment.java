package tk.zeryter.tkupdater.app.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.util.Log;
import tk.zeryter.tkupdater.app.R;

/**
 * Created by owen on 16/08/14.
 */
public class SettingsFragment extends PreferenceFragment {

    public SettingsFragment() {
        Log.d("SettingsFragment", "Run");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
