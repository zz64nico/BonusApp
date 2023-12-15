package com.example.bonusapp

import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.bonusapp.base.BaseActivity
import com.example.bonusapp.databinding.ActivityWenviewBinding

class WebActivity:BaseActivity<ActivityWenviewBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_wenview

    override fun initData() {
        val url = "https://luddy.indiana.edu/";
        mBinding?.webview?.loadUrl(url)
        val webClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return false
            }
        }
        mBinding?.webview?.webViewClient=webClient;//或者不要上面内容，直接web.webViewClient=WebViewClient()
    }
}