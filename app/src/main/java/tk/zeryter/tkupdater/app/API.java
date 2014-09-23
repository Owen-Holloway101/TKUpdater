package tk.zeryter.tkupdater.app;

import android.util.Log;
import android.webkit.*;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

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

        //Remove all previous cookies
        CookieSyncManager.createInstance(webViewer.getContext());
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        CookieSyncManager.getInstance().sync();
        Log.i("cookies", "removed all cookies");

        //The post url is an xml ... interesting
        HttpPost post = new HttpPost("https://api.freenom.com/v2/domain/modify.xml");

        //These are the requests we need
        List<NameValuePair> postRequests = new ArrayList<NameValuePair>(4);
        postRequests.add(new BasicNameValuePair("domainname",url));
        postRequests.add(new BasicNameValuePair("forward_url",ip));
        postRequests.add(new BasicNameValuePair("email",user));
        postRequests.add(new BasicNameValuePair("password",pass));

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
