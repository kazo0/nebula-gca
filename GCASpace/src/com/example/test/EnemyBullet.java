package com.example.test;

import org.andengine.entity.sprite.Sprite;


public class EnemyBullet {
	
	
	public Sprite sprite;
	
	public EnemyBullet()
    {
		sprite = new Sprite(0,0, BaseActivity.enemyBullet, BaseActivity.getSharedInstance()
				.getVertexBufferObjectManager());
		
		
		sprite.setScale(0.3f);
    }

}
