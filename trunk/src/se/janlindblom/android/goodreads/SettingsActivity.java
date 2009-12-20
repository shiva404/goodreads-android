package se.janlindblom.android.goodreads;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprefs);
        
        SharedPreferences settings = getSharedPreferences(GoodreadsActivity.GOODREADS_PREFS, 0);
        int userid = settings.getInt("user", 0);
        if (userid != 0) {
        	EditText ui = (EditText) findViewById(R.id.enter_id_input);
        	ui.setText("" + userid);
        }
        
        Button b = (Button) findViewById(R.id.storevalidate_gr_id);
        b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText userid = (EditText) findViewById(R.id.enter_id_input);
				SharedPreferences settings = getSharedPreferences(GoodreadsActivity.GOODREADS_PREFS, 0);
				Editor e = settings.edit();
				e.putInt("user", Integer.parseInt(userid.getText().toString()));
				e.commit();
			}
		});
	}
}
