package com.example.test;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.util.color.Color;

import android.util.Log;

public class HealthBar
{
	public Rectangle sprite;
    public static HealthBar instance;
    Camera mCamera;
    private int shipHP;
    private int maxHP;
    private int curWidth;
    public boolean dec;
    
    HealthBar(int hp)
    {
    	shipHP = maxHP = hp;
    	curWidth = BaseActivity.CAMERA_WIDTH;
        mCamera = BaseActivity.getSharedInstance().mCamera;
        sprite = new Rectangle(0, 0, BaseActivity.CAMERA_WIDTH / 3, 10, BaseActivity.getSharedInstance().getVertexBufferObjectManager());
        sprite.setPosition(BaseActivity.CAMERA_WIDTH - sprite.getWidth(), 0);
        sprite.setColor(Color.RED);
        dec = false;
    }
    
    public void decreaseHealth(int newHP)
    {
    	sprite.setWidth((BaseActivity.CAMERA_WIDTH / 3) * newHP / maxHP);
    	sprite.setPosition(BaseActivity.CAMERA_WIDTH - sprite.getWidth(), 0);
    	/*BaseActivity.getSharedInstance().getEngine().registerUpdateHandler(new IUpdateHandler()
    	{
    	
			@Override
			public void onUpdate(float pSecondsElapsed)
			{
				if (dec)
				{
					curWidth--;
					sprite.setWidth(curWidth);
					
					if (sprite.getWidth() <= (BaseActivity.CAMERA_WIDTH * maxHP / shipHP))
						dec = false;
				}
			}

			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
    	});*/
    }
}
