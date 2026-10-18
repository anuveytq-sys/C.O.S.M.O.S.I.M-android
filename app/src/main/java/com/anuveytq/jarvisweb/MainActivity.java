package com.anuveytq.jarvisweb;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.ActivityNotFoundException;
import android.webkit.JavascriptInterface;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.webkit.WebSettingsCompat;
import androidx.webkit.WebViewFeature;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST = 501;
    private static final int FILE_REQUEST = 502;
    private static final String JARVIS_URL = "https://anuveytq-sys.github.io/JARVIS-WEB/?api=https%3A%2F%2Fjarvis-web-ai-eov6.onrender.com";

    private WebView webView;
    private ValueCallback<Uri[]> fileCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(android.graphics.Color.rgb(0, 4, 10));
        getWindow().setNavigationBarColor(android.graphics.Color.rgb(0, 4, 10));

        webView = new WebView(this);
        webView.setBackgroundColor(android.graphics.Color.rgb(0, 4, 10));
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        setContentView(webView);

        // Keep the web page (including the command bar with VOICE / SEND) clear of the
        // system status bar, the Android navigation bar and the on-screen keyboard.
        getWindow().getDecorView().setBackgroundColor(android.graphics.Color.rgb(0, 4, 10));
        final int extraBottomPx = (int) (10 * getResources().getDisplayMetrics().density);
        ViewCompat.setOnApplyWindowInsetsListener(webView, (v, windowInsets) -> {
            Insets bars = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout());
            Insets ime = windowInsets.getInsets(WindowInsetsCompat.Type.ime());
            int bottom = Math.max(bars.bottom, ime.bottom);
            v.setPadding(bars.left, bars.top, bars.right, bottom + extraBottomPx);
            return WindowInsetsCompat.CONSUMED;
        });

        configureWebView();
        requestRuntimePermissions();
        webView.loadUrl(JARVIS_URL);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override public void handleOnBackPressed() {
                if (webView.canGoBack()) webView.goBack(); else finish();
            }
        });
    }

    /**
     * Opens common Android apps directly when installed. If the native app is
     * unavailable, the original HTTPS URL is opened in JARVIS WebView.
     */
    private boolean openNativeOrWeb(Uri uri) {
        String scheme = uri.getScheme();
        if (scheme == null) return false;

        // Normal Android/web schemes that should be handled by an installed app.
        if (scheme.equalsIgnoreCase("http") || scheme.equalsIgnoreCase("https")) {
            String host = uri.getHost() == null ? "" : uri.getHost().toLowerCase();
            String pkg = packageForHost(host);
            if (pkg != null) {
                try {
                    Intent appIntent = new Intent(Intent.ACTION_VIEW, uri);
                    appIntent.setPackage(pkg);
                    startActivity(appIntent);
                    return true;
                } catch (ActivityNotFoundException ignored) {
                    // Fall through to the web version in this WebView.
                }
            }
            webView.loadUrl(uri.toString());
            return true;
        }

        // Android deep links such as whatsapp://, tg://, spotify://, etc.
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            return true;
        } catch (Exception ignored) {
            return true;
        }
    }

    private String packageForHost(String host) {
        if (host.equals("youtube.com") || host.endsWith(".youtube.com") || host.equals("youtu.be")) return "com.google.android.youtube";
        if (host.equals("open.spotify.com") || host.equals("spotify.com") || host.endsWith(".spotify.com")) return "com.spotify.music";
        if (host.equals("maps.google.com")) return "com.google.android.apps.maps";
        if (host.equals("google.com") || host.endsWith(".google.com")) return null;
        if (host.equals("mail.google.com")) return "com.google.android.gm";
        if (host.equals("drive.google.com")) return "com.google.android.apps.docs";
        if (host.equals("photos.google.com")) return "com.google.android.apps.photos";
        if (host.equals("docs.google.com")) return "com.google.android.apps.docs.editors.docs";
        if (host.equals("sheets.google.com")) return "com.google.android.apps.docs.editors.sheets";
        if (host.equals("slides.google.com")) return "com.google.android.apps.docs.editors.slides";
        if (host.equals("calendar.google.com")) return "com.google.android.calendar";
        if (host.equals("meet.google.com")) return "com.google.android.apps.meetings";
        if (host.equals("translate.google.com")) return "com.google.android.apps.translate";
        if (host.equals("discord.com") || host.endsWith(".discord.com")) return "com.discord";
        if (host.equals("slack.com") || host.endsWith(".slack.com")) return "com.Slack";
        if (host.equals("teams.microsoft.com") || host.endsWith(".teams.microsoft.com")) return "com.microsoft.teams";
        if (host.equals("outlook.live.com") || host.equals("outlook.office.com")) return "com.microsoft.office.outlook";
        if (host.equals("onedrive.live.com") || host.endsWith(".onedrive.live.com")) return "com.microsoft.skydrive";
        if (host.equals("notion.so") || host.endsWith(".notion.so")) return "notion.id";
        if (host.equals("zoom.us") || host.endsWith(".zoom.us")) return "us.zoom.videomeetings";
        if (host.equals("wa.me") || host.equals("whatsapp.com") || host.endsWith(".whatsapp.com")) return "com.whatsapp";
        if (host.equals("instagram.com") || host.endsWith(".instagram.com")) return "com.instagram.android";
        if (host.equals("facebook.com") || host.endsWith(".facebook.com")) return "com.facebook.katana";
        if (host.equals("m.me")) return "com.facebook.orca";
        if (host.equals("t.me") || host.equals("telegram.me")) return "org.telegram.messenger";
        if (host.equals("x.com") || host.equals("twitter.com") || host.endsWith(".twitter.com")) return "com.twitter.android";
        if (host.equals("reddit.com") || host.endsWith(".reddit.com")) return "com.reddit.frontpage";
        if (host.equals("netflix.com") || host.endsWith(".netflix.com")) return "com.netflix.mediaclient";
        if (host.equals("amazon.in") || host.equals("amazon.com")) return "in.amazon.mShop.android.shopping";
        return null;
    }

    /** JavaScript bridge for future native app launches without changing the web repo. */
    public class AndroidLauncher {
        @JavascriptInterface
        public void open(String url) {
            runOnUiThread(() -> {
                try { openNativeOrWeb(Uri.parse(url)); } catch (Exception ignored) {}
            });
        }
    }

    private void configureWebView() {
        WebSettings s = webView.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setDatabaseEnabled(true);
        s.setAllowFileAccess(true);
        s.setAllowContentAccess(true);
        s.setMediaPlaybackRequiresUserGesture(false);
        s.setBuiltInZoomControls(false);
        s.setDisplayZoomControls(false);
        s.setSupportZoom(false);
        s.setLoadWithOverviewMode(false);
        s.setUseWideViewPort(false);
        s.setJavaScriptCanOpenWindowsAutomatically(true);
        s.setSupportMultipleWindows(false);
        s.setGeolocationEnabled(true);
        s.setCacheMode(WebSettings.LOAD_DEFAULT);
        s.setTextZoom(100);
        s.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
        webView.addJavascriptInterface(new AndroidLauncher(), "AndroidJARVIS");

        if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
            WebSettingsCompat.setForceDark(s, WebSettingsCompat.FORCE_DARK_OFF);
        }

        webView.setWebViewClient(new WebViewClient() {
            @Override public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                Uri u = request.getUrl();
                String path = u == null ? "" : u.toString();
                if (path.endsWith("/JARVIS-WEB/jarvis-ui-patch.js")) {
                    try {
                        java.io.InputStream in = getAssets().open("jarvis-ui-patch-gemini25.js");
                        return new WebResourceResponse("application/javascript", "UTF-8", in);
                    } catch (Exception ignored) {}
                }
                return super.shouldInterceptRequest(view, request);
            }

            @Override public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return openNativeOrWeb(request.getUrl());
            }

            @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return openNativeOrWeb(Uri.parse(url));
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override public void onPermissionRequest(final PermissionRequest request) {
                runOnUiThread(() -> {
                    java.util.ArrayList<String> granted = new java.util.ArrayList<>();
                    for (String resource : request.getResources()) {
                        if (PermissionRequest.RESOURCE_VIDEO_CAPTURE.equals(resource) &&
                                ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                            granted.add(resource);
                        } else if (PermissionRequest.RESOURCE_AUDIO_CAPTURE.equals(resource) &&
                                ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                            granted.add(resource);
                        }
                    }
                    if (!granted.isEmpty() && granted.size() == request.getResources().length) {
                        request.grant(granted.toArray(new String[0]));
                    } else {
                        request.deny();
                    }
                });
            }

            @Override public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }

            @Override public boolean onShowFileChooser(WebView view, ValueCallback<Uri[]> callback, FileChooserParams params) {
                if (fileCallback != null) fileCallback.onReceiveValue(null);
                fileCallback = callback;
                Intent intent = params.createIntent();
                try { startActivityForResult(intent, FILE_REQUEST); }
                catch (Exception e) { fileCallback = null; return false; }
                return true;
            }
        });
    }

    private void requestRuntimePermissions() {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            String[] p = { Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION };
            ActivityCompat.requestPermissions(this, p, PERMISSION_REQUEST);
        }
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_REQUEST && fileCallback != null) {
            Uri[] results = null;
            if (resultCode == Activity.RESULT_OK && data != null) {
                if (data.getClipData() != null) {
                    int n = data.getClipData().getItemCount();
                    results = new Uri[n];
                    for (int i = 0; i < n; i++) results[i] = data.getClipData().getItemAt(i).getUri();
                } else if (data.getData() != null) {
                    results = new Uri[] { data.getData() };
                }
            }
            fileCallback.onReceiveValue(results);
            fileCallback = null;
        }
    }

    @Override protected void onDestroy() {
        if (webView != null) {
            webView.stopLoading();
            webView.destroy();
        }
        super.onDestroy();
    }
}
