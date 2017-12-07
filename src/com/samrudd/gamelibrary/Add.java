package com.samrudd.gamelibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

// Activity to add game to the database
public class Add extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        
        // Set up the spinner for selecting the system
        Spinner spin = (Spinner) findViewById(R.id.AddForm_SystemChooser);
        ArrayAdapter<CharSequence> arr = ArrayAdapter.createFromResource(this, R.array.systems_array, android.R.layout.simple_spinner_item);
        arr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(arr);
        
        // Set up Add Game button listener
        Button button = (Button) findViewById(R.id.AddForm_Submit);
        button.setOnClickListener(new Button.OnClickListener() {
        	public void onClick(View v) {
        		// Get the selected system id from the spinner
        		Spinner spin = (Spinner) findViewById(R.id.AddForm_SystemChooser);
        		int system_index = (int) spin.getSelectedItemId() + 1;
        		
        		System.out.println("system id: " + system_index);
        		
        		// Get the game title from the text field
        		TextView tv = (TextView) findViewById(R.id.AddForm_Title);
        		String title = tv.getText().toString();

        		// Add it
        		addGame(system_index, title);
        		
        	}
        });
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    // Set up the menu
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch (item.getItemId())
    	{
    	case R.id.action_add:
    		Intent i = new Intent(this, Add.class);
    		startActivity(i);
    		break;
    	case R.id.action_viewlist:
    		Intent i2 = new Intent(this, MainActivity.class);
    		startActivity(i2);
    		break;
    	case R.id.action_search:
    		Intent i3 = new Intent(this, Search.class);
    		startActivity(i3);
    		break;
    	default:
    		return false;
    		
    	}
    	return false;
    }
    
    // Adds a game to the database
    private void addGame(int system_id, String title)
    {
    	DatabaseManager dbman = new DatabaseManager(this);
    	
    	dbman.addGame(system_id, title);
    	
    	Intent i = new Intent(this, ViewBySystem.class);
    	i.putExtra("system_id", system_id);
		startActivity(i);
    }
    
}