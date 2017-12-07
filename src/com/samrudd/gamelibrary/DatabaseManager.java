package com.samrudd.gamelibrary;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

// DatabaseManager class
// Provides methods for adding/editing/removing data from the SQLite database
public class DatabaseManager
{
	private SQLiteDatabase m_db;
	private DatabaseHelper m_dbhelper;
	private boolean m_bIsOpen = false;
	
	public DatabaseManager(Context con)
	{
		this.m_dbhelper = new DatabaseHelper(con);
		this.m_db = m_dbhelper.getWritableDatabase();
		this.m_bIsOpen = true;
		
	}
	
	// Adds a game
	public void addGame(int system_id, String title)
	{
		this.m_db.execSQL("INSERT INTO games (title, system_id) VALUES (\"" + title + "\", " + system_id + ");");
	}
	
	// Deletes a game by id
	public void deleteGame(int id)
	{
		this.m_db.delete("games","id=" + id, null);	
	}
	
	// Find a game by its id
	public Game findGame(int id)
	{
		// Query the database
		Cursor cur = this.m_db.rawQuery("SELECT title, system_id FROM games WHERE id=\"" + id + "\"", null);
		
		// If nothing in the results, return empty game
		if (cur.getCount() != 1)
			return new Game();
		
		// Otherwise, move to the result, get the info, and close the cursor
		cur.moveToFirst();
		
		String title = cur.getString(0);
		int system_id = cur.getInt(1);
		
		cur.close();
		
		// Return the info as a new game
		Game game = new Game(id, title, system_id);
		return game;
	}
	
	// Returns a list of games for a given system
	public Game [] getList(int system_id)
	{
		// Query the database
		Cursor cur = this.m_db.rawQuery("SELECT id, title FROM games WHERE system_id=" + system_id + " ORDER BY title ASC", null);
		
		// Create an array of games using the results in the cursor
		Game games[] = new Game[cur.getCount()];
		
    	cur.moveToFirst();
        
    	int i = 0;
    	while (!cur.isAfterLast())
    	{
    		games[i] = new Game(cur.getInt(0), cur.getString(1), system_id);
    		cur.moveToNext();
    		i++;
    	}
    	
    	cur.close();

    	return games;
		
	}
	
	// Do a search on the database
	public Cursor getSearch(String search)
	{
		Cursor cur = this.m_db.rawQuery("SELECT * FROM games WHERE title LIKE \"%" + search + "%\"", null);
		return cur;
	}
	
	// Close everything out
	public void Close(){
		if (m_bIsOpen)
		{
			this.m_db.close();
			this.m_dbhelper.close();
		}
	}
}