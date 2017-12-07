package com.samrudd.gamelibrary;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

// The MainActivity is a list of game systems that can be clicked
// to be taken to a list of games owned for that system
public class MainActivity extends Activity implements OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Create the listview of systems to choose from
        ListView lv = (ListView) findViewById(R.id.SystemList);
        lv.setOnItemClickListener(this);
        
        populateList();
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
    
    // Populates the list of systems for us to choose from
    // @TODO: This needs to be extended so that the user can
    // add/remove systems like they can with games instead of
    // using a hard-coded list of systems (in Constants.java)
    public void populateList()
    {
    	ArrayAdapter<String> arr = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line);
    	ListView lv = (ListView) findViewById(R.id.SystemList);
    	
    	lv.setAdapter(arr);
    	
    	for (int i = 1;i <= Constants.NUMBER_OF_SYSTEMS; i++)
    	{
    		arr.add(Constants.SYSTEMS[i]);
    	}
    	
    }

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int pos, long id) {
		
    	TextView tv = (TextView) v;
    	String lol = (String) tv.getText();
    	System.out.println(lol);
    	
    	Intent i = new Intent(this, ViewBySystem.class);
    	i.putExtra("system_id", pos+1);
    	startActivity(i);
	}
}
    
