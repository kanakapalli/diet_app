package com.anurag.losequarantinefat.ui.webview

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import com.anurag.losequarantinefat.R
import com.google.android.gms.ads.*
import kotlinx.android.synthetic.main.activity_web_view.*

class WebView : AppCompatActivity() {
    private lateinit var mInterstitialAd: InterstitialAd
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
//        progressBar.visibility = VISIBLE
        supportActionBar?.title = intent.getStringExtra("title")
        MyWebViewClient()
        intent.getStringExtra("Link")?.let { webView.loadUrl(it) }


    }


}
class MyWebViewClient : WebViewClient(){

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun shouldOverrideUrlLoading(view: android.webkit.WebView?,request: WebResourceRequest? ): Boolean {
        view?.loadUrl(request?.url.toString())
        return true
    }

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if (url != null) {
            view?.loadUrl(url)
        }
        return true
    }

}