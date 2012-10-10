package com.example.test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.Entity;
import org.andengine.entity.modifier.MoveModifier;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class Level extends Entity
{
	private LinkedList<GenericEnemy> enemies;
	public static Level instance;
	//public int enemyCount;
	public int level;
	protected final int MAX_ENEMIES = 10;
	public int enemyOnScreen = 0;
	protected final int MAX_ENEMIES_ON_SCREEN = 5;
	private boolean end;
	
	public static Level getSharedInstance()
	{
		return instance;
	}
	
	public static int size()
	{
		return instance.enemies.size();
	}
	
	public static boolean isEmpty()
	{
		if (instance.enemies.size() == 0)
			return true;
		
		return false;
	}
	
	public static Iterator<GenericEnemy> getIterator()
	{
		return instance.enemies.iterator();
	}
	
	public Level(int level)
	{
		
		instance = this;
		setLevel(level);
		end = false;
	}
	
	public void setLevel(int _level)
	{
		
		this.level = _level;
		Log.v("Level change", level + "");
		end = false;
		enemies = new LinkedList<GenericEnemy>();
		//enemyCount = (int) (MAX_ENEMIES * this.level);
		enemyCreation();
	}
	
	public void enemyCreation()
	{
		enemies.clear();
		clearEntityModifiers();
		clearUpdateHandlers();
		
		for (int i = 0; i < MAX_ENEMIES_ON_SCREEN; i++)
		{
			createSprites();
		}
	}
	
	public void spawnEnemy()
	{
		//Log.v("Test", "Just checking sum stuff!! - " + GreenEnemyPool.sharedEnemyPool().getAvailableItemCount());
		if (GreenEnemyPool.sharedEnemyPool().getAvailableItemCount() > 0)
		{
			createSprites();
		}
	}
	
	synchronized private void createSprites()
	{
		
		if (!end)
		{
			//switch (level)
			//{
				//case 1: 
			GenericEnemy e = GreenEnemyPool.sharedEnemyPool().obtainPoolItem();
			
			//break;
				//case 2:
					//e = (YellowEnemy)EnemyPool.sharedEnemyPool().obtainPoolItem();
				//	break;
				//case 3:
					//e = (RedEnemy)EnemyPool.sharedEnemyPool().obtainPoolItem();
					//break;
				//}
			Log.v("TEST", level + "TIMER: " + e.timer);
			e.init();
			attachChild(e.sprite);
			
			boolean restart = true;
			while(restart)
			{
				if (!enemies.isEmpty())
				{
					for (GenericEnemy ge : enemies)
					{
	            	
						if (ge.sprite.collidesWith(e.sprite))
						{
							e.init();
							restart = true;
							break;
						}
						else
							restart = false;
					}
				}
				else
					break;
			}
            
			enemies.add(e);
		}
		else
		{
			GreenEnemyPool.sharedEnemyPool().doneRecycling();
			Log.v("Check", GreenEnemyPool.sharedEnemyPool().getAvailableItemCount() + "");
			if (GreenEnemyPool.sharedEnemyPool().getAvailableItemCount() == MAX_ENEMIES_ON_SCREEN)
			{
				Log.v("FSDF", "SDFSD");
				GreenEnemyPool.sharedEnemyPool().clearPool();
				
				for (GenericEnemy e : enemies)
					e.clean(true);
				enemies.clear();
				
			}

		
		}
	}
	
	public void removeEnemy(GenericEnemy e)
	{
		enemies.remove(e);
	}
	
	public void endGame()
	{
		end = true;
	}
}
