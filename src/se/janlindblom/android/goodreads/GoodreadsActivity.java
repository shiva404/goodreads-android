package se.janlindblom.android.goodreads;

/**
 * $Id$
 * 
 * Copyright (c) 2009, Jan Lindblom
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * * Neither the name of the project nor the names of its contributors may be
 *   used to endorse or promote products derived from this software without
 *   specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 */

import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import se.janlindblom.android.goodreads.handler.Requester;
import se.janlindblom.android.goodreads.handler.ResponseBucket;
import se.janlindblom.android.goodreads.meta.Response;
import se.janlindblom.android.goodreads.meta.User;
import se.janlindblom.android.goodreads.meta.operation.UserOperation;
import se.janlindblom.android.goodreads.meta.response.UserResponse;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * 
 * @author Jan Lindblom (lindblom.jan@gmail.com)
 * @version $Rev$
 *
 */
public class GoodreadsActivity extends Activity implements Observer {
	public static final String GOODREADS_PREFS = "GoodreadsPrefs";
	public int CURRENT_USER = 0;
	public User me;
	
	private Requester requester;
	private ResponseBucket responses;
	
	private Handler handler;
	private Response response;
	
	public void onStop() {
		super.onStop();
		if (requester != null)
			requester.stop();
	}
	
	public void onStart() {
		super.onStart();
		if (requester != null)
			if (!requester.isRunning())
				requester.start();
	}
	
	public void onResume() {
		super.onResume();
		if (requester != null)
			if (!requester.isRunning())
				requester.start();
		Uri uri = this.getIntent().getData();
	}
	
	public void onPause() {
		super.onPause();
		if (requester != null)
			if (requester.isRunning())
				requester.stop();
	}
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        handler = new Handler();
        
        SharedPreferences settings = getSharedPreferences(GOODREADS_PREFS, 0);
        settings.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
			
			@Override
			public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
					String key) {
				if (sharedPreferences.equals(getSharedPreferences(GOODREADS_PREFS,0))) {
					if (key.equals("user")) {
						fetchCurrentUser(sharedPreferences.getInt(key, 0));
						updateMainScreenForUser();
						updatePreferencesScreenForUser();
						CURRENT_USER = sharedPreferences.getInt(key, 0);
					}
				}
			}
		});
        
        requester = new Requester();
        responses = new ResponseBucket();
        requester.addObserver(responses);
        responses.addObserver(this);
        requester.start();
        
        int userid = settings.getInt("user", 0);
        
        if (userid != 0) {
        	fetchCurrentUser(userid);	
        	CURRENT_USER = userid;
        } else {
        	this.findViewById(R.id.userinfo_layout).setVisibility(View.GONE);
    		this.findViewById(R.id.updates_listview).setVisibility(View.GONE);
        }
    }
    
    private void fetchCurrentUser(int userid) {
    	requester.request(new UserOperation(getString(R.string.key), userid));
    }
    
    private void updateMainScreenForUser() {
		findViewById(R.id.userinfo_layout).setVisibility(View.VISIBLE);
		findViewById(R.id.updates_listview).setVisibility(View.VISIBLE);
    	TextView tv = (TextView) findViewById(R.id.welcome_label);
		tv.setText(Html.fromHtml(getString(R.string.hi_user) + " <a href=\""+ me.getLink() + "\">" + me.getName() + "</a>"));
		TextView fl = (TextView) findViewById(R.id.friends_label);
		fl.setText(me.getFriends() + " " + getString(R.string.friends_label));
		TextView rl = (TextView) findViewById(R.id.reviews_label);
		rl.setText(me.getReviews() + " " + getString(R.string.reviews_label));
		TextView ul = (TextView) findViewById(R.id.username_label);
		ul.setText(getString(R.string.username_label) + " " + me.getUserName());
		TextView bl = (TextView) findViewById(R.id.bookshelves_label);
		bl.setText(me.getBookShelves().size() + " " + getString(R.string.bookshelves_label));
		ListView lv = (ListView) findViewById(R.id.updates_listview);

		List<Update> updates = me.getUpdates().subList(0, me.getUpdates().size());
		Iterator<Update> i = updates.iterator();
		Vector<Spanned> newEntries = new Vector<Spanned>();
		while (i.hasNext()) {
			newEntries.add(i.next().toSpanned());
		}
		lv.setAdapter(new ArrayAdapter<Spanned>(this, R.layout.updateitem, newEntries.subList(0, newEntries.size())));
    }
    
    private void updatePreferencesScreenForUser() {
    	TextView rl = (TextView) findViewById(R.id.enterid_feedback_label);
    	if (rl != null) {
    		rl.setText(getString(R.string.stored_id_user) + " " + me.getName() + " (" + me.getUserName() + ")");
    		rl.setVisibility(View.VISIBLE);
    	}
    }
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.startmenu, menu);
        MenuItem i = menu.findItem(R.id.identifyuser);
        i.setIntent(new Intent(GoodreadsActivity.this, SettingsActivity.class));
        i = menu.findItem(R.id.aboutapp);
        i.setIntent(new Intent(GoodreadsActivity.this, AboutActivity.class));
        return true;
    }

	@Override
	public void update(Observable observable, Object data) {
		if (observable.getClass().equals(ResponseBucket.class)) {
			response = responses.get();
			if ((response != null) && (response.getType() == Response.RESPONSE_USER)) {
				/* Response in queue is a User response. */
				SharedPreferences settings = getSharedPreferences(GOODREADS_PREFS, 0);
				int userid = settings.getInt("user", 0);
				UserResponse ur = (UserResponse) response;
				/* Update main screen if user == me */
				if (userid == ur.getUser().getId()) {
					handler.post(new Runnable() {
						public void run() {
							UserResponse ur = (UserResponse) response;
							me = ur.getUser();
							updateMainScreenForUser();
							updatePreferencesScreenForUser();
						}
					});
				}
			}
		}
	}
}