package io.garbage.com;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity
{
	private WebView mWebView = null;
	final String WEB_URL = "WEB_URL";
	private String defaultUrl = "file:///android_asset/grabage/map.html";
	private String webUrl = defaultUrl;
	private ProgressBar progressBar;

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);

		Bundle extras = getIntent().getExtras();
		if (extras != null)
		{
			webUrl = extras.getString(WEB_URL, defaultUrl);
		}

		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		mWebView = (WebView) findViewById(R.id.webview);

		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setDomStorageEnabled(true);
		mWebView.getSettings().setAllowFileAccess(true);
		mWebView.getSettings().setAllowContentAccess(true);
		mWebView.getSettings().setAllowFileAccessFromFileURLs(true);
		mWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
		mWebView.setWebViewClient(mWebViewClient);
		mWebView.loadUrl(webUrl);

		displayProgressBar();
	}

	WebViewClient mWebViewClient = new WebViewClient()
	{
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url)
		{
			Log.e("url", "" + url);
			view.loadUrl(url);
			return true;
		}
	};

	private void displayProgressBar()
	{

		final Handler mHandler;

		mHandler = new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{

				switch (msg.what)
				{
					case 0:
						if (progressBar != null)
						{
							progressBar.setVisibility(View.INVISIBLE);
						}
						break;
				}
			}
		};

		new Thread(new Runnable()
		{

			public void run()
			{
				try
				{
					Thread.sleep(3000);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				Message msg = new Message();
				msg.what = 0;
				mHandler.sendMessage(msg);
			}
		}).start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_fb)
		{
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(
					"https://www.facebook.com/%E5%8F%B0%E5%8C%97%E5%B8%82%E5%9E%83%E5%9C%BE%E8%BB%8A-268253086877987/"));

			startActivity(browserIntent);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
