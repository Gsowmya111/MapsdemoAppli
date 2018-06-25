package com.example.edisonoffice.mapsdemoappli;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;

public class CallLogHelper extends Activity {



	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


	}

	public static Cursor getAllCallLogs(ContentResolver cr) {
		// reading all data in descending order according to DATE
		String strOrder = android.provider.CallLog.Calls.DURATION + " DESC";
		Uri callUri = Uri.parse("content://call_log/calls");
		Cursor curCallLogs = cr.query(callUri, null, null, null, strOrder);

		return curCallLogs;
	}

	public static void insertPlaceholderCall(ContentResolver contentResolver,
											 String name, String number) {
		ContentValues values = new ContentValues();
		values.put(CallLog.Calls.NUMBER, number);
		values.put(CallLog.Calls.DATE, System.currentTimeMillis());
		values.put(CallLog.Calls.DURATION, 0);
		values.put(CallLog.Calls.TYPE, CallLog.Calls.OUTGOING_TYPE);
		values.put(CallLog.Calls.NEW, 1);
		values.put(CallLog.Calls.CACHED_NAME, name);
		values.put(CallLog.Calls.CACHED_NUMBER_TYPE, 0);
		values.put(CallLog.Calls.CACHED_NUMBER_LABEL, "");
		Log.d("Call Log", "Inserting call log placeholder for " + number);
		contentResolver.insert(CallLog.Calls.CONTENT_URI, values);
	}

}
