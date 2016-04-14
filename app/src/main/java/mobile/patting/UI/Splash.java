package mobile.patting.UI;

import android.mobile.patting.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.ImageView;

public class Splash extends Activity {
	private final int SPLASH_DISPLAY_LENGHT = 10000;
	ImageView logoImg;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splash);
		logoImg = (ImageView) findViewById(R.id.logoImg);

		// the phone has a sim card
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// Create an Intent that will start the Menu-Activity.
				Intent mainIntent = new Intent(Splash.this,
						TourActivity.class);
				Splash.this.startActivity(mainIntent);
				Splash.this.finish();
			}
		}, SPLASH_DISPLAY_LENGHT);
	}
}
