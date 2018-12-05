package com.gary.weatherdemo.firebase.indexing;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.google.firebase.appindexing.FirebaseAppIndex;

public class AppIndexingUpdateReceiver extends BroadcastReceiver{
	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent != null && FirebaseAppIndex.ACTION_UPDATE_INDEX.equals(intent.getAction())) {
			// Schedule the job to be run in the background.
			AppIndexingService.enqueueWork(context);
		}
	}
}
