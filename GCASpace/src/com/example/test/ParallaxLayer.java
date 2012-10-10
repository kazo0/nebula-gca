package com.example.test;

import java.util.ArrayList;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.Entity;
import org.andengine.entity.shape.IAreaShape;
import org.andengine.opengl.util.GLState;

public class ParallaxLayer extends Entity {
	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private final ArrayList<ParallaxEntity> mParallaxEntities = new ArrayList<ParallaxEntity>();
	private int mParallaxEntityCount;

	protected float mParallaxValue;
	protected float mParallaxScrollValue;
	
	protected float mParallaxChangePerSecond;
	
	protected float mParallaxScrollFactor = 0.2f;
	
	private Camera mCamera;
	
	private float mCameraPreviousY;
	private float mCameraOffsetY;
	
	private float	mLevelHeight = 0;
	
	private boolean mIsScrollable = false;

	
	// ===========================================================
	// Constructors
	// ===========================================================
	public ParallaxLayer() {
	}

	public ParallaxLayer(final Camera camera, final boolean mIsScrollable){
		this.mCamera = camera;
		this.mIsScrollable = mIsScrollable;
		
		mCameraPreviousY = camera.getCenterY();
	}
	
	public ParallaxLayer(final Camera camera, final boolean mIsScrollable, final int mLevelHeight){
		this.mCamera = camera;
		this.mIsScrollable = mIsScrollable;
		this.mLevelHeight = mLevelHeight;
		
		mCameraPreviousY = camera.getCenterY();
	}
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public void setParallaxValue(final float pParallaxValue) {
		this.mParallaxValue = pParallaxValue;
	}
	
	public void setParallaxChangePerSecond(final float pParallaxChangePerSecond) {
		this.mParallaxChangePerSecond = pParallaxChangePerSecond;
	}

	public void setParallaxScrollFactor(final float pParallaxScrollFactor){
		this.mParallaxScrollFactor = pParallaxScrollFactor;
	}
	
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void onManagedDraw(GLState pGLState, Camera pCamera) {
		super.preDraw(pGLState, pCamera);

		
		final float parallaxValue = this.mParallaxValue;
		final float parallaxScrollValue = this.mParallaxScrollValue;
		final ArrayList<ParallaxEntity> parallaxEntities = this.mParallaxEntities;

		for(int i = 0; i < this.mParallaxEntityCount; i++) {
			if(parallaxEntities.get(i).mIsScrollable){
				parallaxEntities.get(i).onDraw(pGLState, pCamera, parallaxScrollValue, mLevelHeight);
			} else {
				parallaxEntities.get(i).onDraw(pGLState, pCamera, parallaxValue, mLevelHeight);
			}

		}
	}
	
	@Override
	protected void onManagedUpdate(float pSecondsElapsed) {
		
		if(mIsScrollable && mCameraPreviousY != this.mCamera.getCenterY()){
				mCameraOffsetY = mCameraPreviousY - this.mCamera.getCenterY();
				mCameraPreviousY = this.mCamera.getCenterY();
				
				this.mParallaxScrollValue += mCameraOffsetY * this.mParallaxScrollFactor;
				mCameraOffsetY = 0;
		}
		
		this.mParallaxValue += this.mParallaxChangePerSecond * pSecondsElapsed;
		super.onManagedUpdate(pSecondsElapsed);
	}

	// ===========================================================
	// Methods
	// ===========================================================

	public void attachParallaxEntity(final ParallaxEntity parallaxEntity) {
		this.mParallaxEntities.add(parallaxEntity);
		this.mParallaxEntityCount++;
	}

	public boolean detachParallaxEntity(final ParallaxEntity pParallaxEntity) {
		this.mParallaxEntityCount--;
		final boolean success = this.mParallaxEntities.remove(pParallaxEntity);
		if(!success) {
			this.mParallaxEntityCount++;
		}
		return success;
	}
	
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================

	public static class ParallaxEntity {
		// ===========================================================
		// Constants
		// ===========================================================

		// ===========================================================
		// Fields
		// ===========================================================

		final float mParallaxFactor;
		final IAreaShape mAreaShape;
		final boolean mIsScrollable;

		final float shapeHeightScaled;

		// ===========================================================
		// Constructors
		// ===========================================================

		public ParallaxEntity(final float pParallaxFactor, final IAreaShape pAreaShape) {
			this.mParallaxFactor = pParallaxFactor;
			this.mAreaShape = pAreaShape;
			this.mIsScrollable = false;
			shapeHeightScaled = this.mAreaShape.getHeightScaled();
		}
		
		public ParallaxEntity(final float pParallaxFactor, final IAreaShape pAreaShape, final boolean mIsScrollable) {
			this.mParallaxFactor = pParallaxFactor;
			this.mAreaShape = pAreaShape;
			this.mIsScrollable = mIsScrollable;
			shapeHeightScaled = this.mAreaShape.getHeightScaled();
		}
		
		public ParallaxEntity(final float pParallaxFactor, final IAreaShape pAreaShape, final boolean mIsScrollable, final int mReduceFrequency) {
			this.mParallaxFactor = pParallaxFactor;
			this.mAreaShape = pAreaShape;
			this.mIsScrollable = mIsScrollable;
			shapeHeightScaled = this.mAreaShape.getHeightScaled() * mReduceFrequency;
		}

		// ===========================================================
		// Getter & Setter
		// ===========================================================

		// ===========================================================
		// Methods for/from SuperClass/Interfaces
		// ===========================================================

		// ===========================================================
		// Methods
		// ===========================================================

		public void onDraw(final GLState pGLState, final Camera pCamera, final float pParallaxValue, final float mLevelHeight) {
			pGLState.pushModelViewGLMatrix();
			{
				float HeightRange;
				
				if(mLevelHeight != 0){
					HeightRange = mLevelHeight;
				} else {
					HeightRange = pCamera.getHeight();
				}

				float baseOffset = (pParallaxValue * this.mParallaxFactor) % shapeHeightScaled;

				while(baseOffset > 0) {
					baseOffset -= shapeHeightScaled;
				}
				pGLState.translateModelViewGLMatrixf(0, baseOffset, 0);

				float currentMaxY = baseOffset;
				
				do {
					this.mAreaShape.onDraw(pGLState, pCamera);
					pGLState.translateModelViewGLMatrixf(0, shapeHeightScaled - 1, 0);
					currentMaxY += shapeHeightScaled;
				} while(currentMaxY < HeightRange);
			}
			pGLState.popModelViewGLMatrix();
		}

		// ===========================================================
		// Inner and Anonymous Classes
		// ===========================================================
	}


}