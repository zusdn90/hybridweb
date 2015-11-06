package com.hybrid.hybridweb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class HybridWebActivity extends Activity implements OnClickListener {

	Button btnWeb80;
	Button btnWeb8080;
	WebView myweb;

	@SuppressLint("JavascriptInterface")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hybrid_web);

		btnWeb80 = (Button) findViewById(R.id.btnWeb80);
		btnWeb8080 = (Button) findViewById(R.id.btnWeb8080);

		btnWeb80.setOnClickListener(this);
		btnWeb8080.setOnClickListener(this);

		myweb = (WebView) findViewById(R.id.myweb);

		WebSettings settings = myweb.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setBuiltInZoomControls(true);

		myweb.addJavascriptInterface(new MyJavascriptInterface(), "android");
		myweb.setWebViewClient(new MyWebViewClient()); // a href
		myweb.setWebChromeClient(new WebChromeClient()); // alert

		// myweb.loadUrl("http://192.168.10.29");
		myweb.loadUrl("http://www.soen.kr");
		// web.loadUrl("http://192.168.10.29:8080/web/index.jsp");

	}

	class MyJavascriptInterface {
		public void showToast(String msg) {
			Log.i("###", msg);
			Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
		}
	}

	class MyWebViewClient extends WebViewClient {
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			Log.i("###", "url = " + url);
			view.loadUrl(url);
			return true;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hybrid_web, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnWeb80:
			myweb.loadUrl("http://192.168.10.29");
			break;
		case R.id.btnWeb8080:
			myweb.loadUrl("http://192.168.10.29:8080/web/");
			break;
		default:
			break;
		}
	}
}
