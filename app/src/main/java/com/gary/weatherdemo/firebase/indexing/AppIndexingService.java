package com.gary.weatherdemo.firebase.indexing;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.widget.Toast;

import com.gary.weatherdemo.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.appindexing.Action;
import com.google.firebase.appindexing.FirebaseAppIndex;
import com.google.firebase.appindexing.FirebaseUserActions;
import com.google.firebase.appindexing.Indexable;

import java.util.ArrayList;


public class AppIndexingService extends JobIntentService {
	private static final String URL = "cbkpersonal://";
	private static final String NAME = "Github caobaokang";

	// Job-ID must be unique across your whole app.
	public static final int UNIQUE_JOB_ID = 123;

	public static void enqueueWork(Context context) {
		enqueueWork(context, AppIndexingService.class, UNIQUE_JOB_ID, new Intent());
	}

	@Override
	protected void onHandleWork(@NonNull Intent intent) {
		ArrayList<Indexable> indexablePersons = new ArrayList<>();

		Indexable personIndex = new Indexable.Builder()
				.setDescription("Welcome to caobaokang github")
				.setImage("https://developers.google.com/experts/img/user/106148559283737827113.png")
				.setKeywords("cbkpersonal", "cbkgithub")
				.setName(NAME)
				.setUrl(URL)
				.build();

		indexablePersons.add(personIndex);

		if (indexablePersons.size() > 0) {
			Indexable[] personsArr = new Indexable[indexablePersons.size()];
			personsArr = indexablePersons.toArray(personsArr);

			// batch insert indexable persons into index
			Task<Void> task = FirebaseAppIndex.getInstance().update(personsArr);
			task.addOnSuccessListener(new OnSuccessListener<Void>() {
				@Override
				public void onSuccess(Void aVoid) {
					Toast.makeText(getApplicationContext(), "Personal data is added!", Toast.LENGTH_SHORT).show();
				}
			});
			task.addOnFailureListener(new OnFailureListener() {
				@Override
				public void onFailure(@NonNull Exception e) {
					Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
				}
			});
		}
		FirebaseUserActions.getInstance().start(getCustomAction());
	}

	public static void removePersonalContent() {
		FirebaseUserActions.getInstance().end(getCustomAction());
		FirebaseAppIndex.getInstance().remove(URL);
	}

	public static void clearPersonalContent() {
		FirebaseUserActions.getInstance().end(getCustomAction());
		FirebaseAppIndex.getInstance().removeAll();
	}

	public static Action getCustomAction() {
		return new Action.Builder(Action.Builder.VIEW_ACTION)
				.setObject(NAME, URL)
				.setMetadata(new Action.Metadata.Builder().setUpload(false))
				.build();
	}
}