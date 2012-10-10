package com.example.test;


import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

//placeHolder scene class for the main menu, currently only includes a start menu item 
public class HelpScene extends MenuScene 
{
	BaseActivity activity;
	final int MENU_START = 0;

	public HelpScene()
	{
		super(BaseActivity.getSharedInstance().mCamera);
		activity = BaseActivity.getSharedInstance();

		SpriteBackground bg = new SpriteBackground(new Sprite(0, 0, BaseActivity.helpBackground,BaseActivity.getSharedInstance()
				.getVertexBufferObjectManager()));
        setBackground(bg);
        
        
        ButtonSprite backbutton = new ButtonSprite(219, 734, BaseActivity.backbutton, activity.getVertexBufferObjectManager()) {
            @Override
            public boolean onAreaTouched(TouchEvent pTouchEvent, float pTouchAreaLocalX, float pTouchAreaLocalY) {
                if(pTouchEvent.isActionDown()) {
                	activity.setCurrentScene(new MainMenuScene());
    	        	
                }
                return super.onAreaTouched(pTouchEvent, pTouchAreaLocalX, pTouchAreaLocalY);
            }
	     };
        
	     this.attachChild(backbutton);
	     this.registerTouchArea(backbutton);
	     this.setTouchAreaBindingOnActionDownEnabled(true);
	}
	
}