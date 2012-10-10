package com.example.test;


import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

import com.example.test.ParallaxLayer.ParallaxEntity;



//placeHolder scene class for the main menu, currently only includes a start menu item 
public class MainMenuScene extends MenuScene
{
	BaseActivity activity;
	final int MENU_START = 0;
	public ParallaxLayer backgroundParallax;


	public MainMenuScene()
	{
		
		
		super(BaseActivity.getSharedInstance().mCamera);
		
		activity = BaseActivity.getSharedInstance();
		/*
		SpriteBackground bg = new SpriteBackground(new Sprite(0, 0, BaseActivity.menuBgTexture,BaseActivity.getSharedInstance()
				.getVertexBufferObjectManager()));
        setBackground(bg);*/
        
		backgroundParallax = new ParallaxLayer(mCamera, true);	
		backgroundParallax.setParallaxChangePerSecond(8);
		backgroundParallax.setParallaxScrollFactor(1);
		Sprite bg1 = new Sprite(0, 0, BaseActivity.mBgTexture,BaseActivity.getSharedInstance().getVertexBufferObjectManager());
		backgroundParallax.attachParallaxEntity(new ParallaxEntity(15, bg1, false, 1));
		attachChild(backgroundParallax);
		
		Sprite titleSprite = new Sprite(30,31, BaseActivity.nebulaTitle, BaseActivity.getSharedInstance()
				.getVertexBufferObjectManager());
		
       
        ButtonSprite startbutton = new ButtonSprite(35, 300, BaseActivity.startGameMenu, activity.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if(pTouchEvent.isActionDown()) {
                	activity.setCurrentScene(new GameScene());
    	        	BaseActivity.menuMusicSound.stop();
    	        
                	
                }
                return super.onAreaTouched(pTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
	     };
	     
	     ButtonSprite helpbutton = new ButtonSprite(250, 480, BaseActivity.helpButton, activity.getVertexBufferObjectManager()) {
	         @Override
	         public boolean onAreaTouched(TouchEvent pTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
	             if(pTouchEvent.isActionDown()) {
	             	activity.setCurrentScene(new HelpScene());
	             	
	             }
	             return super.onAreaTouched(pTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
	         }
	  };
  
		  ButtonSprite creditsbutton = new ButtonSprite(35, 650, BaseActivity.creditsButton, activity.getVertexBufferObjectManager()) {
		      @Override
		      public boolean onAreaTouched(TouchEvent pTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
		          if(pTouchEvent.isActionDown()) {
		          	activity.setCurrentScene(new CreditsScene());

		          }
		          return super.onAreaTouched(pTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
		      }
		};
     
	
     this.attachChild(startbutton);
     this.attachChild(titleSprite);
     this.registerTouchArea(startbutton);
     this.attachChild(helpbutton);
     this.registerTouchArea(helpbutton);
     this.attachChild(creditsbutton);
     this.registerTouchArea(creditsbutton);
     this.setTouchAreaBindingOnActionDownEnabled(true);
		
		
	}
	
}
	
