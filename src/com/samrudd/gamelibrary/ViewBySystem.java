package com.samrudd.gamelibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ViewBySystem extends Activity implements OnItemClickListener {
	
	// List of game ids 
	private int [] m_gameIDs;
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_system);
        
        // Get the system id from the previous activity
        // so we know which games to display
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
        	int value = extras.getInt("system_id");
        	populateList(value);
        }
        
        ListView lv = (ListView) findViewById(R.id.list);
        lv.setOnItemClickListener(this);
        
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    // Create the menu
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
    
    // Populate the list of games for the given system
    public void populateList(int system_id)
    {
    	ArrayAdapter<String> arr = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
    	ListView lv = (ListView) findViewById(R.id.list);
    	DatabaseManager dbman = new DatabaseManager(this);
    	
    	// Get the list
    	Game games[] = dbman.getList(system_id);
    	
    	if (games == null)
    		return;
    	
    	// Populate array of game ids (for deleting)
    	this.m_gameIDs = new int[games.length];
    	
    	for (int i = 0; i < games.length;i++)
    	{
    		arr.add(games[i].getTitle());
    		this.m_gameIDs[i] = games[i].getId();
    	}
    	   
    	lv.setAdapter(arr);
    	
    }

    // Open the screen for an individual game
	public void onItemClick(AdapterView<?> arg0, View v, int pos, long id) {
		   	
    	Intent i = new Intent(this, ViewGame.class);
    	i.putExtra("id", this.m_gameIDs[pos]);
    	startActivity(i);
	}
}
