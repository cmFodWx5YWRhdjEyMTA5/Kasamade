package com.kasamade.news;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class AppPrefs {
	
	private static final String USER_PREFS = "USER_PREFS";
	private SharedPreferences appSharedPrefs;
	private SharedPreferences.Editor prefsEditor;




	private String positon_id="positon_id";

	private String user_id = "user_id";

	private String language_id = "language_id";

	private String channel_id = "channel_id";

	private String device_id = "device_id";

	private String is_login = "is_login";

	private String selected_movie_id = "selected_movie_id";

	private String transformer = "transformer";

	private String hindi_id="hindi_id";



	private String English_id="English_id";



	private String Bhojpuri_id="Bhojpuri_id";


	private String Gujurati_id="Gujurati_id";



	private String Punjabi_id="Punjabi_id";



	private String Bengali_id="Bengali_id";



	private String Telegu_id="Telegu_id";



	private String Malyalam_id="Malyalam_id";
	private String Kanada_id="Kanada_id";



	private String Marathi_id="Marathi_id";



	private String Tamil_id=" Tamil_id";


	private String news_Article_last_id= "news_Article_last_id";


	public AppPrefs(Context context) {
		// TODO Auto-generated constructor stub
		this.appSharedPrefs = context.getSharedPreferences(USER_PREFS,Activity.MODE_PRIVATE);
		this.prefsEditor = appSharedPrefs.edit();
	}

	public void remove_all_prefs()
	{
		prefsEditor.remove(language_id);
		prefsEditor.commit();
	}


	public void remove_HindiID_prefs()
	{
		prefsEditor.remove(hindi_id);
		prefsEditor.commit();
	}
	public void remove_EnglishID_prefs()
	{
		prefsEditor.remove(English_id);
		prefsEditor.commit();
	}


	public void remove_Bhojpuri_id_prefs()
	{
		prefsEditor.remove(Bhojpuri_id);
		prefsEditor.commit();
	}


	public void remove_Gujurati_id_prefs()
	{
		prefsEditor.remove(Gujurati_id);
		prefsEditor.commit();
	}



	public void remove_Punjabi_id_prefs()
	{
		prefsEditor.remove(Punjabi_id);
		prefsEditor.commit();
	}


	public void remove_Bengali_id_prefs()
	{
		prefsEditor.remove(Bengali_id);
		prefsEditor.commit();
	}


	public void remove_Telegu_id_prefs()
	{
		prefsEditor.remove(Telegu_id);
		prefsEditor.commit();
	}

	public void remove_Kanada_id_prefs()
	{
		prefsEditor.remove(Kanada_id);
		prefsEditor.commit();
	}

	public void remove_Malyalam_id_prefs()
	{
		prefsEditor.remove(Malyalam_id);
		prefsEditor.commit();
	}



	public void remove_Marathi_id_prefs()
	{
		prefsEditor.remove(Marathi_id);
		prefsEditor.commit();
	}

	public void remove_Tamil_id_prefs()
	{
		prefsEditor.remove(Tamil_id);
		prefsEditor.commit();
	}

	public void remove_Positon_id_prefs()
	{
		prefsEditor.remove(positon_id);
		prefsEditor.commit();
	}



	public void setLanguage_ID(String id) {

		// TODO Auto-generated method stub
		prefsEditor.putString(language_id, id).commit();

	}


	public String getLanguage_ID() {
		// TODO Auto-generated method stub
		return appSharedPrefs.getString(language_id, "");
	}


	public String getPositon_id() {
		return appSharedPrefs.getString(positon_id, "");
	}

	public void setPositon_id(String id) {
		prefsEditor.putString(positon_id, id).commit();
	}

	public String getHindi_id() {
		return appSharedPrefs.getString(hindi_id, "");
	}

	public void setHindi_id(String id) {
		prefsEditor.putString(hindi_id, id).commit();
	}


	public String getEnglish_id() {
		return appSharedPrefs.getString(English_id, "");
	}

	public void setEnglish_id(String id) {
		prefsEditor.putString(English_id, id).commit();
	}


	public String getBhojpuri_id() {
		return appSharedPrefs.getString(Bhojpuri_id, "");
	}

	public void setBhojpuri_id(String id) {
		prefsEditor.putString(Bhojpuri_id, id).commit();
	}



	public String getGujurati_id() {
		return appSharedPrefs.getString(Gujurati_id, "");
	}

	public void setGujurati_id(String id) {
		prefsEditor.putString(Gujurati_id, id).commit();
	}


	public String getPunjabi_id() {
		return appSharedPrefs.getString(Punjabi_id, "");
	}

	public void setPunjabi_id(String id) {
		prefsEditor.putString(Punjabi_id, id).commit();
	}



	public String getBengali_id() {
		return appSharedPrefs.getString(Bengali_id, "");
	}

	public void setBengali_id(String id) {
		prefsEditor.putString(Bengali_id, id).commit();
	}


	public String getTelegu_id() {
		return appSharedPrefs.getString(Telegu_id, "");
	}

	public void setTelegu_id(String id) {
		prefsEditor.putString(Telegu_id, id).commit();
	}

	public String getKanada_id() {
		return appSharedPrefs.getString(Kanada_id, "");
	}

	public void setKanada_id(String id) {
		prefsEditor.putString(Kanada_id, id).commit();
	}


	public String getMalyalam_id() {
		return appSharedPrefs.getString(Malyalam_id, "");
	}

	public void setMalyalam_id(String id) {
		prefsEditor.putString(Malyalam_id, id).commit();
	}

	public String getMarathi_id() {
		return appSharedPrefs.getString(Marathi_id, "");
	}

	public void setMarathi_id(String id) {
		prefsEditor.putString(Marathi_id, id).commit();
	}



	public String getTamil_id() {
		return appSharedPrefs.getString(Tamil_id, "");
	}

	public void setTamil_id(String id) {
		prefsEditor.putString(Tamil_id, id).commit();
	}



	public void setChannel_ID(String id) {

		// TODO Auto-generated method stub
		prefsEditor.putString(channel_id, id).commit();

	}


	public String getChannel_ID() {
		// TODO Auto-generated method stub
		return appSharedPrefs.getString(channel_id, "");
	}

	public void setDevice_ID(String id) {

		// TODO Auto-generated method stub
		prefsEditor.putString(device_id, id).commit();

	}


	public String getDevice_ID() {
		// TODO Auto-generated method stub
		return appSharedPrefs.getString(device_id, "");
	}



	public void setIs_Login(String id) {

		// TODO Auto-generated method stub
		prefsEditor.putString(is_login, id).commit();

	}


	public String getIs_Login() {
		// TODO Auto-generated method stub
		return appSharedPrefs.getString(is_login, "");
	}

	public void setSelected_Movie_ID(String id) {

		// TODO Auto-generated method stub
		prefsEditor.putString(selected_movie_id, id).commit();

	}


	public String getSelected_Movie_ID() {
		// TODO Auto-generated method stub
		return appSharedPrefs.getString(selected_movie_id, "");
	}

	public void setUser_ID(String id) {

		// TODO Auto-generated method stub
		prefsEditor.putString(user_id, id).commit();

	}


	public String getUser_ID() {
		// TODO Auto-generated method stub
		return appSharedPrefs.getString(user_id, "");
	}

	public void setTransformer_ID(int id) {

		// TODO Auto-generated method stub
		prefsEditor.putInt(transformer, id).commit();

	}


	public int getTransformer_ID() {
		// TODO Auto-generated method stub
		return appSharedPrefs.getInt(transformer, 0);
	}













	public int getNews_Article_last_id() {
		return appSharedPrefs.getInt(news_Article_last_id, 0);
	}

	public void setNews_Article_last_id(int id) {
		prefsEditor.putInt(news_Article_last_id, id).commit();
	}


}
