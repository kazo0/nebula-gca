package com.example.test;

import org.andengine.engine.handler.IUpdateHandler;

public class GameLoopUpdateHandler implements IUpdateHandler
{
	

	@Override
	public void onUpdate(float pSecondsElapsed)
	{
		if (BaseActivity.getSharedInstance().mCurrentScene instanceof GameScene)
		{
			((GameScene)BaseActivity.getSharedInstance().mCurrentScene).moveShip();
			((GameScene)BaseActivity.getSharedInstance().mCurrentScene).moveEnemies();
			((GameScene)BaseActivity.getSharedInstance().mCurrentScene).cleaner();
			Level.getSharedInstance().spawnEnemy();
		}
	}

	@Override
	public void reset()
	{
		// TODO Auto-generated method stub	
	}
}