package tk.zeryter.tkupdater.app;

import android.util.Log;
import android.webkit.*;
import org.apache.http.util.EncodingUtils;

/*
* Owen Holloway, Zeryt
* */

public class API {

    private static WebView webViewer;

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
    }

    public static void login(final String user, final String pass) {
        String postdata = "username="+user+"&password="+pass;

        webViewer.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                return false;
            }
        });

        webViewer.postUrl("https://my.freenom.com/dologin.php", EncodingUtils.getBytes(postdata,"BASE64"));
    }

    public static void  updateDNS(String url, String id, String ip) {
        String postdata = "dnsaction=modify"+"&="+url+"&domainid="+id+"&="+ip;

        webViewer.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                return false;
            }
        });

        webViewer.postUrl("https://my.freenom.com/clientarea.php", EncodingUtils.getBytes(postdata,"BASE64"));

    }
}