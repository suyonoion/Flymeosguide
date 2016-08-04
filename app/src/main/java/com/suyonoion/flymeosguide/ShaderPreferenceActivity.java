package com.suyonoion.flymeosguide;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class ShaderPreferenceActivity extends PreferenceActivity
{
	public static final String SHARED_PREFERENCES_NAME = "ShaderEditorSettings";
	public static final String SHADER = "shader";
	public static final String COMPILE_ON_CHANGE = "compile_on_change";
	public static final String SHOW_FPS_GAUGE = "show_fps_gauge";
	public static final String SENSOR_DELAY = "sensor_delay";
	public static final String UPDATE_DELAY = "update_delay";
	public static final String TEXT_SIZE = "text_size";

	@Override
	public void onCreate( Bundle state )
	{
		super.onCreate( state );

		getPreferenceManager().setSharedPreferencesName(
			SHARED_PREFERENCES_NAME );

		addPreferencesFromResource( R.xml.preferences );
	}
}
