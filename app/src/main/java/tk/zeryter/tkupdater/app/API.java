package tk.zeryter.tkupdater.app;

import android.util.Log;
import android.webkit.*;

/*
* Owen Holloway, Zeryt
* */

public class API {

    private static WebView webViewer;

    private static String html = "";

    private static String ip = "";

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

    public static void login(final String user, final String pass) {
        //The main website url


        webViewer.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                Log.d("Load","1");
                webViewer.addJavascriptInterface(new MyJavascriptInterface(), "INTERFACE");
                webViewer.loadUrl("javascript:INTERFACE.setDocument(document.body.innerText);");
                Log.d("CurrentIp",API.html);
                ip = API.html;
                webViewer.setWebViewClient(new WebViewClient() {

                    public void onPageFinished(WebView view, String url) {
                        Log.d("Load","2");
                        webViewer.addJavascriptInterface(new MyJavascriptInterface(), "INTERFACE");
                        webViewer.setWebViewClient(new WebViewClient() {
                        });
                        webViewer.setWebViewClient(new WebViewClient(){

                            public void onPageFinished(WebView view, String url) {
                                Log.d("Load","3");
                                webViewer.setWebViewClient(new WebViewClient() {

                                    public void onPageFinished(WebView view, String url) {
                                        Log.d("Load","4");
                                        webViewer.addJavascriptInterface(new MyJavascriptInterface(), "INTERFACE");
                                        webViewer.loadUrl("javascript:$('[name=\"records[0][value]\"]').val('"+ip+"');$(\"form:first\").submit();");
                                        //Log.d("WebViewerTitle",webViewer.getTitle());
                                    }
                                });
                                webViewer.loadUrl("https://my.freenom.com/clientarea.php?managedns=zeryter.tk&domainid=79828912");
                            }
                        });
                        webViewer.loadUrl("javascript:function set() {document.getElementById('username').value=\""+user+"\";document.getElementById('password').value=\""+pass+"\";} set();$('.form-stacked').submit();INTERFACE.setDocument('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
                    }
                });
                webViewer.loadUrl("https://partners.freenom.com/clientarea.php");
            }
        });
        webViewer.loadUrl("http://icanhazip.com");
    }

    public static void getCurrentIp() {
    }

    public static void setIp(final String ip) {

    }

    public static void  updateDNS(String url, String newAddress) {
        //webViewer.loadUrl();
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
