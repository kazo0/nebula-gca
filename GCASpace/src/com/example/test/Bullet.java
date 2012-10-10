package com.example.test;

import org.andengine.entity.sprite.Sprite;



public class Bullet
{
    public Sprite sprite;
    
    public Bullet()
    {
    	sprite = new Sprite(0,0, BaseActivity.shipBullet, BaseActivity.getSharedInstance()
				.getVertexBufferObjectManager());
		
		
		sprite.setScale(0.3f);
    }
}

