package com.samrudd.gamelibrary;

public class Game
{
	private int m_id;
	private String m_title;
	private int m_system;
	
	public Game()
	{
		this.m_id = 0;
		this.m_title = "";
		this.m_system = 0;
	}
	
	public Game(int id, String title, int system)
	{
		this.m_id = id;
		this.m_title = title;
		this.m_system = system;
	}
	
	public int getId()
	{
		return this.m_id;
	}
	
	public String getTitle()
	{
		return this.m_title;
	}
	
	public int getSystem()
	{
		return this.m_system;
	}
	
	public void setId(int id)
	{
		this.m_id = id;
	}
	
	public void setTitle(String title)
	{
		this.m_title = title;
	}
	
	public void setSystem(int id)
	{
		this.m_system = id;
	}
}