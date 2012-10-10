package com.example.test;

import java.io.IOException;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
//import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import android.graphics.Color;
import android.graphics.Typeface;

public class BaseActivity extends SimpleBaseGameActivity
{

	static final int CAMERA_WIDTH = 480;
	static final int CAMERA_HEIGHT = 800;
	
	public Font mFont;
	public Camera mCamera;
	
	
	//A reference to the current scene
	public Scene mCurrentScene;
	public static BaseActivity instance;
	
	private BitmapTextureAtlas mBitmapTextureAtlas;
	public static TextureRegion mShipTextureRegion;
	public static TextureRegion pheonix;
	public static TextureRegion voidray;
	public static TextureRegion carrier;
	public static TextureRegion shipBullet;
	public static TextureRegion enemyBullet;
	public static TextureRegion mBgTexture;
	public static TextureRegion menuBgTexture;
	public static TextureRegion startGameMenu;
	public static TextureRegion helpButton;
	public static TextureRegion creditsButton;
	public static TextureRegion helpBackground;
	public static TextureRegion	creditsBackground;
	public static TextureRegion	nebulaTitle;
	public static TextureRegion	backbutton;
	
	
	
	
	public static Music firstlvl;
	public static Music menuMusicSound;
	public static Sound enterthelvlSound;
	public static Sound splashSound;
	public static Sound shootingSound2;
	public static Sound explosionSound2;
	

	@Override
	public EngineOptions onCreateEngineOptions()
	{
	    instance = this;
	    mCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
	    
	    
	    EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.PORTRAIT_SENSOR, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), mCamera);
		
		 engineOptions.getAudioOptions().setNeedsSound(true);
		 engineOptions.getAudioOptions().setNeedsMusic(true);

		
		return engineOptions;
	}
	
	@Override
	protected void onCreateResources()
	{
		//TEXTURE LOADING
		mBitmapTextureAtlas = new BitmapTextureAtlas(mEngine.getTextureManager(),2048,2048,
		        TextureOptions.DEFAULT);
		
		    BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		
		    
		    mShipTextureRegion = BitmapTextureAtlasTextureRegionFactory
		        .createFromAsset(this.mBitmapTextureAtlas, this, "xwing.png",
		        0, 0);
		    
		    shipBullet = BitmapTextureAtlasTextureRegionFactory
			        .createFromAsset(this.mBitmapTextureAtlas, this, "greenbullet.png",
			        182, 0);
		   
		    enemyBullet = BitmapTextureAtlasTextureRegionFactory
			        .createFromAsset(this.mBitmapTextureAtlas, this, "bluebullet.png",
			        257, 0);
		
		    pheonix = BitmapTextureAtlasTextureRegionFactory
			        .createFromAsset(this.mBitmapTextureAtlas, this, "realPheonix.png",
			        1870, 0);
		    
		    mBgTexture = BitmapTextureAtlasTextureRegionFactory
			        .createFromAsset(this.mBitmapTextureAtlas, this, "startBackground.png",
			        0, 169);
		   
		    menuBgTexture = BitmapTextureAtlasTextureRegionFactory
			        .createFromAsset(this.mBitmapTextureAtlas, this, "Backgroundmenu.png",
			        0, 970);
		   
		    startGameMenu = BitmapTextureAtlasTextureRegionFactory
			        .createFromAsset(this.mBitmapTextureAtlas, this, "playGame.JPG",
			        495, 0);
		    
		    helpButton = BitmapTextureAtlasTextureRegionFactory
			        .createFromAsset(this.mBitmapTextureAtlas, this, "Help.JPG",
			        481, 170);
		    
		    creditsButton = BitmapTextureAtlasTextureRegionFactory
			        .createFromAsset(this.mBitmapTextureAtlas, this, "Credits.JPG",
			        500, 50);
		    
		    helpBackground = BitmapTextureAtlasTextureRegionFactory
			        .createFromAsset(this.mBitmapTextureAtlas, this, "Helpmenu.png",
			        1568, 1248);
		    
		    creditsBackground = BitmapTextureAtlasTextureRegionFactory
			        .createFromAsset(this.mBitmapTextureAtlas, this, "creditsmenu.png",
			        1088, 1248);
		    
		    nebulaTitle = BitmapTextureAtlasTextureRegionFactory
			        .createFromAsset(this.mBitmapTextureAtlas, this, "titlenebula.JPG",
			        1450, 0);
		    
		    backbutton = BitmapTextureAtlasTextureRegionFactory
			        .createFromAsset(this.mBitmapTextureAtlas, this, "back.JPG",
			        1600, 1024);
		   
		    voidray = BitmapTextureAtlasTextureRegionFactory
			        .createFromAsset(this.mBitmapTextureAtlas, this, "void_ray.png",
			        934, 1024);
		    
		    carrier = BitmapTextureAtlasTextureRegionFactory
			        .createFromAsset(this.mBitmapTextureAtlas, this, "carrier.png",
			        780, 1024);
		    
		    
		    
		    
		    
		    
		    mEngine.getTextureManager().loadTexture(mBitmapTextureAtlas);
		
		
		    
		    
		    //Sound LOADING
		    
		    SoundFactory.setAssetBasePath("mfx/");
		    MusicFactory.setAssetBasePath("mfx/");
		    
		    try {
				
		    	
		    	this.firstlvl = MusicFactory.createMusicFromAsset(this.mEngine.getMusicManager(), this, "bossbattle.ogg");
				this.firstlvl.setLooping(true);
				
				this.enterthelvlSound = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), this, "enterthelvl.ogg");
		    	this.menuMusicSound = MusicFactory.createMusicFromAsset(this.mEngine.getMusicManager(), this, "firstlvl.ogg");
		    	this.menuMusicSound.setLooping(true);
				this.splashSound = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), this, "splash.wav");
				this.shootingSound2 = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), this, "pewpew2.wav");
				this.explosionSound2 = SoundFactory.createSoundFromAsset(this.mEngine.getSoundManager(), this, "explosion2.wav");
				
				
				
			} catch (final IOException e) {
				Debug.e(e);
			}
		    
		    
		    
		
		
	    mFont = FontFactory.create(this.getFontManager(),this.getTextureManager(), 256, 256, 32, true, Color.WHITE);
	    mFont.load();
	    
	    
	}

	protected Scene onCreateScene()
	{
	    mEngine.registerUpdateHandler(new FPSLogger());
	    mCurrentScene = new SplashScene();
	    return mCurrentScene;
	}
	
	public static BaseActivity getSharedInstance()
	{
	    return instance;
	}

	// to change the current main scene
	public void setCurrentScene(Scene scene)
	{
	    mCurrentScene = scene;
	    getEngine().setScene(mCurrentScene);
	}
	
	@Override
	public void onBackPressed()
	{
		if (mCurrentScene instanceof GameScene)
			((GameScene) mCurrentScene).detach();
		
		mCurrentScene = null;
		SensorListener.instance = null;
		super.onBackPressed();
	}
}