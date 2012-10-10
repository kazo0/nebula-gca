package com.example.test;

import java.util.Iterator;
import java.util.Random;

import org.andengine.engine.Engine;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.modifier.MoveYModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.sprite.Sprite;
import org.andengine.util.color.Color;
import org.andengine.util.math.MathUtils;



import android.annotation.SuppressLint;
import android.util.FloatMath;
import android.util.Log;

@SuppressLint("FloatMath")
public class GreenEnemy extends GenericEnemy
{
	protected final int MAX_HEALTH = 1;
	protected final int MAX_DAMAGE = 1;
	protected final int MAX_SIZE = 30;
	protected final int MAX_SPEED = 1;
	static Random r = new Random();
	float dx = (float) (Math.PI / 6);
    float dy = (float) (Math.PI / 6);
    private int rand;
    private boolean alive;
    private Engine g;
    int it = 0;
    int rev = 0;
    int degree = 0;
    float pos = 0;
    boolean firstTime;
    protected boolean collision;
	
	GreenEnemy()
	{
		collision = false;
		hp = MAX_HEALTH;
		damage = MAX_DAMAGE;
		speed = MAX_SPEED;
		g = BaseActivity.getSharedInstance().getEngine();
		

		firstTime = true;
		alive = true;
		type = MathUtils.random(1, 2);
		type = 1;
		switch(type){
		
		case 1 :
			sprite = new Sprite(512, 512, BaseActivity.pheonix, BaseActivity.getSharedInstance()
					.getVertexBufferObjectManager());
			sprite.setScale(0.3f);
			break;
		case 2 :
			sprite = new Sprite(512,512, BaseActivity.voidray, BaseActivity.getSharedInstance()
					.getVertexBufferObjectManager());
			sprite.setScale(0.35f);
			break;
		case 3 :
			sprite = new Sprite(512,512, BaseActivity.carrier, BaseActivity.getSharedInstance()
					.getVertexBufferObjectManager());
			sprite.setScale(0.4f);
			break;
			default:
				sprite = new Sprite(512,512, BaseActivity.pheonix, BaseActivity.getSharedInstance()
						.getVertexBufferObjectManager());
				sprite.setScale(0.3f);
				break;
			
		}
		
		sprite.setPosition(MathUtils.random(0, BaseActivity.CAMERA_WIDTH), 0);

		
		if (MathUtils.random(0, 1) >= 0.5)
		{
			rev = 1;
		}
		else
		{
			rev = -1;
		}
		
		//rand = r.nextInt(5) + 1;
		
        timer = new TimerHandler(1f, true, new ITimerCallback(){
            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {
            	if (hp > 0)
            	{
            		if (firstTime)
            		{
            			
            			rand = r.nextInt(99) + 1;
	            		if (rand % 10 == 0)
	            			shoot();
            		}
            	}
            }
        });
        g.registerUpdateHandler(timer);

	}
	
	public void init()
	{
		Log.v("HELLO", "does it get here");
		//r.setSeed(System.currentTimeMillis());
		alive = true;
		collision = false;
		speed = MAX_SPEED;
		hp = MAX_HEALTH;
		sprite.setPosition(MathUtils.random(0, BaseActivity.CAMERA_WIDTH), MathUtils.random(0, BaseActivity.CAMERA_HEIGHT / 5));
		sprite.setVisible(true);
       // sprite.registerEntityModifier(new MoveYModifier(3, -50, 75));

		
		
		
		// = r.nextInt(6) + 1;
		//timer.setTimerSeconds(rand);
      
	}
	
	synchronized public void clean(boolean unregisterTimer)
	{
		sprite.clearEntityModifiers();
		sprite.clearUpdateHandlers();
		//hp = MAX_HEALTH;
		
		if (unregisterTimer)
		{
	        g.unregisterUpdateHandler(timer);
	        timer = null;
		}
	}

	@Override
	public void shoot()
	{
		if (!alive)
			return;
		
		if (BaseActivity.getSharedInstance().mCurrentScene instanceof GameScene)
		{
			GameScene scene = (GameScene) BaseActivity.getSharedInstance().mCurrentScene;
	
	        EnemyBullet b = EnemyBulletPool.sharedBulletPool().obtainPoolItem();
	        b.sprite.setPosition(sprite.getX() + sprite.getWidth() / 2, sprite.getY());
	        MoveYModifier mod = new MoveYModifier(1.5f, b.sprite.getY(), BaseActivity.CAMERA_HEIGHT);
	
	        b.sprite.setVisible(true);
	        b.sprite.detachSelf();
	        scene.attachChild(b.sprite);
	        scene.enemyBulletList.add(b);
	        b.sprite.registerEntityModifier(mod);
	        scene.enemyBulletCount++;
	        BaseActivity.shootingSound2.setVolume(0.4f);
			BaseActivity.shootingSound2.play();
		}	
	}
	
	public void move()
	{
		switch(type)
		{
			case 1:
				moveOne();
				break;
			case 2:
				moveTwo();
				break;
			case 3:
				moveThree();
				break;
		}
	}

	private void moveOne()
	{
		int lL = 0;
        int rL = (int) (BaseActivity.CAMERA_WIDTH - (int) sprite.getWidth());
        float newX = sprite.getX() + (FloatMath.cos(dx) * speed);
        float newY = sprite.getY() + (FloatMath.sin(dy) * speed);

        // Calculate New X,Y Coordinates within Limits
        if (newX <= lL /*|| collision*/)
        {
        //	if (!collision)
        		newX = lL;
        	//else
        		//collision = false;
            dx += (float) Math.PI - 2 * dx;
        }
        else if (newX >= rL)
        {
        	newX = rL;
        	//collision = false;
            dx += (float) Math.PI - 2 * dx;
        }
        
        sprite.setPosition(newX, newY);
	}
	
	private void moveTwo()
	{
		int lL = 0;
        int rL = (int) (BaseActivity.CAMERA_WIDTH - (int) sprite.getWidth());
        float newX = 0;// = sprite.getX() + (FloatMath.cos(dx) * speed);
        float newY = 0;// = sprite.getY() + speed;
        
        it++;
        if ((it % 200) < 140)
        {
        	newX = sprite.getX();
        	newY = sprite.getY() + speed;
        }
        else
        {
        	newX = sprite.getX() + (speed * rev);
        	newY = sprite.getY() - speed;
        }

        // Calculate New X,Y Coordinates within Limits
        if (newX <= lL || collision)
        {
            newX = lL;
            rev *= -1;
            collision = false;
        }
        else if (newX >= rL || collision)
        {
            newX = rL;
            rev *= -1;
            collision = false;
        }
        
        sprite.setPosition(newX, newY);
	}
	
	private void moveThree()
	{
		int lL = 0;
        int rL = (int) (BaseActivity.CAMERA_WIDTH - (int) sprite.getWidth());
        pos += (Math.PI / 180);
        float newX = sprite.getX() + (FloatMath.cos(pos) * speed);
        float newY = (float) (sprite.getY() + (speed * 0.8));

        // Calculate New X,Y Coordinates within Limits
        if (newX <= lL || collision)
        {
            newX = lL;
            collision = false;
        }
        else if (newX >= rL || collision)
        {
            newX = rL;
            collision = false;
        }
        
        sprite.setPosition(newX, newY);
	}
	
	@Override
	public boolean registerHit()
	{
		hp--;
		
		if (hp <= 0)
		{
			alive = false;
			GreenEnemyPool.sharedEnemyPool().recyclePoolItem(this);
			return false;
		}
		else
			return true;
	}
	
	@Override
	public void outOfBounds()
	{
		hp = 0;
		GreenEnemyPool.sharedEnemyPool().recyclePoolItem(this);
	}
	
	public void collision(GenericEnemy e1)
	{
		e1.collision();
		collision = true;
	}
	
	public void collision()
	{
		collision = true;
	}
}
