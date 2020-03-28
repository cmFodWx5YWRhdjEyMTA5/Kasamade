package Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.TypedValue;

public class Util {
	private static final String PREFERENCES_FILE = "materialsample_settings";
	private static final String PREFERENCES_FILE1 = "materialsample_settings";
	public static int dpToPx(Resources res, int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, res.getDisplayMetrics());
	}
	public static Drawable tintMyDrawable(Drawable drawable, int color) {
		drawable = DrawableCompat.wrap(drawable);
		DrawableCompat.setTint(drawable, color);
		DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);
		return drawable;
	}
	public static String readSharedSetting(Context ctx, String settingName, String defaultValue) {
		SharedPreferences sharedPref = ctx.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
		return sharedPref.getString(settingName, defaultValue);
	}

	public static void saveSharedSetting(Context ctx, String settingName, String settingValue) {
		SharedPreferences sharedPref = ctx.getSharedPreferences(PREFERENCES_FILE1, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putString(settingName, settingValue);
		editor.apply();
	}

}
