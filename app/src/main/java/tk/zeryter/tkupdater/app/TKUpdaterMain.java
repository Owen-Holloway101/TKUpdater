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
import tk.zeryter.tkupdater.app.activities.SettingsActivity;


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

        Log.d("sharedPref",sharedPref.getString("PREF_EMAIL",""));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tkupdater_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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

        WebView webView;

        public BaseFragment() {
            Log.d("BaseFragment", "Run");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tkupdate_main, container, false);

            return rootView;
        }

        @Override
        public void onStart() {
            super.onStart();

            Log.d("BaseFragment","onStart");

            webView = (WebView) getView().findViewById(R.id.WebViewer);

            API.setWebView(webView);
            //API.testLogin("exaple@example.com","asdf");

        }
    }
}
