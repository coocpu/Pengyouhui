package com.enrich.pengyouhui.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class SharedPreferencesUtils {

	private static SharedPreferences sharedPreferences;
	private static SharedPreferences.Editor editor;
	private static SharedPreferencesUtils instance;
	private SharedPreferencesUtils(){}

	public static SharedPreferencesUtils getInstance(){
		return instance;
	}

	public static void init(Context context, String sharedPreferenceName){
		sharedPreferences = context.getSharedPreferences(sharedPreferenceName, context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
		instance = new SharedPreferencesUtils();
	}
	public String getValue(String key){
		return sharedPreferences.getString(key, null);
	}

	public Set<String> getValueSet(String key){
		return sharedPreferences.getStringSet(key, null);
	}

	public void removeValue(String key){

		try {
			editor.putString(key,null);
		}catch (Exception e){
			editor.commit();
		}
		editor.commit();
	}

	public void remove(String key) {
			editor.remove(key);
			editor.commit();
	}

	public void setValue(String key, Object value){
		if (value instanceof Boolean){
			editor.putBoolean(key,(boolean) value);
		}else if (value instanceof String){
			editor.putString(key,(String) value);
		}else if (value instanceof Integer){
			editor.putInt(key, (int) value);
		}else if (value instanceof Long){
			editor.putLong(key, (long) value);
		}else if (value instanceof Float){
			editor.putFloat(key,(float) value);
		}
		editor.commit();
	}

	public void setValue(String key, Set<String> data){
		editor.putStringSet(key, data);
		editor.commit();
	}
}