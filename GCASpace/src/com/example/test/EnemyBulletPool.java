package com.example.test;

import org.andengine.util.adt.pool.GenericPool;

public class EnemyBulletPool extends GenericPool<EnemyBullet>
{
	public static EnemyBulletPool instance;
	
	public static EnemyBulletPool sharedBulletPool()
	{
		if (instance == null)
			instance = new EnemyBulletPool();

		return instance;
	}
	
	private EnemyBulletPool()
	{
		super();
	}
	
	@Override
	protected EnemyBullet onAllocatePoolItem()
	{
		return new EnemyBullet();
	}

	protected void onHandleRecycleItem(final EnemyBullet b)
	{
		b.sprite.clearEntityModifiers();
		b.sprite.clearUpdateHandlers();
		b.sprite.setVisible(false);
		b.sprite.detachSelf();
	}
}