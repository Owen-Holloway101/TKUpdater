package tk.zeryter.tkupdater.app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import tk.zeryter.tkupdater.app.fragments.SettingsFragment;

/**
 * Created by owen on 16/08/14.
 */
public class SettingsActivity extends Activity {

    public SettingsActivity() {
        Log.d("SettingsActivity", "Run");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

    }
}
