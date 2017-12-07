package com.samrudd.gamelibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// View a game to perform operations (delete) on it
public class ViewGame extends Activity {
	
	// The game being viewed
	private Game m_game;
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_game);
        
        int id = 0;
        this.m_game = new Game();
        
        // Get the game id from the previous activity
        Bundle extras = getIntent().getExtras();
        
        if (extras != null)
        {
        		// If there is one, get the game info from the db
        		if (extras.containsKey("id"))
        		{
        			id = extras.getInt("id");
        			DatabaseManager dbman = new DatabaseManager(this);
        			this.m_game = dbman.findGame(id); 			
        		}
        			
        }
        
        // Populate the textview with the game title
        TextView tv = (TextView) findViewById(R.id.GameTitle);
        tv.setText(this.m_game.getTitle());
        
        // Populate the textview with the game system
        tv = (TextView) findViewById(R.id.GameSystem);
        tv.setText(Constants.SYSTEMS[this.m_game.getSystem()] + " - ");
        
        // Create the button for deleting the game
        Button button = (Button) findViewById(R.id.GameDeleteButton);
        button.setOnClickListener(new Button.OnClickListener() {
        	public void onClick(View v) {
        		deleteGame();
        	}
        });
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
    
    // Delete the game from the database
    public void deleteGame()
    {
    	DatabaseManager dbman = new DatabaseManager(this);
    	dbman.deleteGame(this.m_game.getId());
    	
    	Intent i = new Intent(this, ViewBySystem.class);
    	i.putExtra("system_id", this.m_game.getSystem());
    	startActivity(i);
    }
   
}
