package io.garbage.com;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebActivity extends AppCompatActivity
{
	WebView mWebView = null;
	final String WEB_URL = "WEB_URL";

	String webUrl = "";

	private ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);

		Bundle extras = getIntent().getExtras();
		if (extras != null)
		{
			webUrl = extras.getString(WEB_URL, "");
		}
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		mWebView = (WebView) findViewById(R.id.webview);
		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setDomStorageEnabled(true);
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
					Thread.sleep(2000);
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
}
