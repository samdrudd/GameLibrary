package com.samrudd.gamelibrary;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

// Search activity
// Allows the user to search by game title
public class Search extends Activity implements OnItemClickListener
{
	// The list of returned game ids, so that we can give the user the option of
	// opening a separate screen for an individual game and do things (delete it etc)
	private int [] m_gameIDs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        
        // Set up the text field and search button
        Button button = (Button) findViewById(R.id.SearchButton);
        button.setOnClickListener(new Button.OnClickListener() {
        	public void onClick(View v) {
        		TextView tv = (TextView) findViewById(R.id.SearchField);
        		String text = tv.getText().toString();
        		doSearch(text);
        		
        	}
        });
        ListView lv = (ListView) findViewById(R.id.SearchResults);
        lv.setOnItemClickListener(this);
    }
    
    @Override
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
    
    // Do the search and display the results
    public void doSearch(String search)
    {
    	ArrayAdapter<String> arr = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
    	ListView lv = (ListView) findViewById(R.id.SearchResults);
    	DatabaseManager dbman = new DatabaseManager(this);
    	
    	// Do the search
    	Cursor cur = dbman.getSearch(search);
    	
    	// Add all the games to the arrayadapter to populate the listview
    	cur.moveToFirst();
    	this.m_gameIDs = new int[cur.getCount()];
   
    	int i = 0;
    	while (!cur.isAfterLast())
    	{
    		// Add the system name in [] before the name of each game
    		arr.add("[" + Constants.SYSTEMS[cur.getInt(2)] + "] " + cur.getString(1));
    		this.m_gameIDs[i] = cur.getInt(0);
    		cur.moveToNext();
    		i++;
    	}
    	
    	cur.close();
    	
    	lv.setAdapter(arr);
    	
    }
    
    // Open options for an individual game (delete)
	public void onItemClick(AdapterView<?> arg0, View v, int pos, long id) {
	   	
    	Intent i = new Intent(this, ViewGame.class);
    	i.putExtra("id", this.m_gameIDs[pos]);
    	startActivity(i);
	}
}