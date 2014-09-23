package tk.zeryter.tkupdater.app.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.webkit.WebView;
import tk.zeryter.tkupdater.app.API;
import tk.zeryter.tkupdater.app.R;

/**
 * Created by owen on 29/08/14.
 */
public class UpdateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
    }

    WebView webView;

    @Override
    protected void onStart() {
        super.onStart();

        webView = (WebView) findViewById(R.id.WebViewer);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        API.setWebView(webView);
        API.updateDNS(sharedPref.getString("PREF_EMAIL", ""), sharedPref.getString("PREF_PASSWORD", ""),sharedPref.getString("PREF_URL",""));
    }
}
