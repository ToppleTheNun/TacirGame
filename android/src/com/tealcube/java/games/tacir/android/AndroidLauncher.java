package com.tealcube.java.games.tacir.android;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.tealcube.java.games.tacir.TacirGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        // setting these to false reduces our battery usage
		config.useAccelerometer = false;
        config.useCompass = false;
		initialize(new TacirGame(), config);
	}
}
