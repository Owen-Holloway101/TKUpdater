package tk.zeryter.tkupdater.app;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.*;
import android.webkit.WebView;
import android.widget.Button;
import tk.zeryter.tkupdater.app.activities.SettingsActivity;
import tk.zeryter.tkupdater.app.activities.UpdateActivity;


public class TKUpdaterMain extends Activity {

    public TKUpdaterMain() {
        Log.d("TKUpdater", "Run");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tkupdater_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new BaseFragment())
                    .commit();
        }

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        Log.d("PREF_EMAIL",sharedPref.getString("PREF_EMAIL",""));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tkupdater_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            Intent settings = new Intent(this, SettingsActivity.class);
            startActivity(settings);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class BaseFragment extends Fragment {

        public BaseFragment() {
            Log.d("BaseFragment", "Run");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tkupdate_main, container, false);

            return rootView;
        }

        Button update;

        @Override
        public void onStart() {
            super.onStart();

            update = (Button) getView().findViewById(R.id.buttonUpdate);
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent updateActivity = new Intent(getActivity(), UpdateActivity.class);
                    startActivity(updateActivity);
                }
            });

            Log.d("BaseFragment","onStart");

        }
    }
}
