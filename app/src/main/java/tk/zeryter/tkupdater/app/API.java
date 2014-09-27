package tk.zeryter.tkupdater.app;

import android.util.Log;
import android.webkit.*;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import tk.zeryter.tkupdater.app.activities.UpdateActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
* Owen Holloway, Zeryt
* */

public class API {

    private static WebView webViewer;

    private static String html = "";

    private static String ip = "123.123.123.123";

    public static void setWebView(WebView viewToSet) {
        webViewer = viewToSet;

        //Remove all previous cookies
        CookieSyncManager.createInstance(webViewer.getContext());
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();
        Log.i("cookies", "removed all cookies");

        WebSettings webSettings = webViewer.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //setup the webViewer
        webViewer.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                //webViewer.loadUrl("javascript:window.INTERFACE.setDocument(document.documentElement.innerHTML);");
            }
        });

        webViewer.addJavascriptInterface(new MyJavascriptInterface(), "INTERFACE");
    }

    public static void getIp() {
        webViewer.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                Log.d("Load","1");
                webViewer.addJavascriptInterface(new MyJavascriptInterface(), "INTERFACE");
                webViewer.loadUrl("javascript:INTERFACE.setDocument(document.body.innerText);");
                Log.d("CurrentIp",API.html);
                ip = API.html;
            }
        });
        webViewer.loadUrl("http://icanhazip.com");
    }

    public static void updateDNS(final String user, final String pass, final String url) {

        Log.d("running","updateDNS");

        new Thread(new Runnable() {

            @Override
            public void run() {

                //The post url is an xml ... interesting
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost post = new HttpPost("https://api.freenom.com/v2/domain/modify.xml");

                //These are the requests we need
                List<NameValuePair> postRequests = new ArrayList<NameValuePair>(4);
                postRequests.add(new BasicNameValuePair("domainname",url));
                postRequests.add(new BasicNameValuePair("forward_url",ip));
                postRequests.add(new BasicNameValuePair("email",user));
                postRequests.add(new BasicNameValuePair("password",pass));

                HttpResponse response = null;

                try {
                    response = httpclient.execute(post);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.d("response", response.toString());

                HttpEntity entity = response.getEntity();

                try {
                    Log.d("response", EntityUtils.toString(entity));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    public static class MyJavascriptInterface {

        @JavascriptInterface
        @SuppressWarnings("unused")
        public void setDocument(String html) {
            API.html = html;
            //Log.i("html", html);
        }

    }
}
