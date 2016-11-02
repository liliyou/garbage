package io.garbage.com;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by lili on 2016/4/13.
 */
public class MoreActivity extends AppCompatActivity
{
	public static final String TAG = "MoreActivity";

	@Override
	protected void onCreate(
			@Nullable
			Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setParentContentView();
		initBaseView();
	}

	public void setParentContentView()
	{
		setContentView(R.layout.activity_more);
	}

	private void initBaseView()
	{
//		ImageButton imbClose = (ImageButton) findViewById(R.id.imbClose);
//		imbClose.setOnClickListener(new View.OnClickListener()
//		{
//			@Override
//			public void onClick(View v)
//			{
//
//				finish();
//				playAnimation();
//			}
//		});
	}

	public void playAnimation()
	{

		overridePendingTransition(0, R.anim.ac_slide_down);
		return;
	}
}

