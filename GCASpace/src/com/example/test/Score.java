package com.example.test;


import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.text.Text;
public class Score
{
	private int score;
	private String displayScore;
	public Text text1;
	
	Score()
	{
		score = 0;
		displayScore = "Score: ";
		text1 = new Text(0, 0, BaseActivity.getSharedInstance().mFont, displayScore + score, 50, BaseActivity.getSharedInstance().getVertexBufferObjectManager());
		text1.setPosition(0, 0);
	}
	
	public void enemyKilled()
	{
		score += 20;
		
	}
	
	public void displayScore()
	{
		text1.setText(displayScore + score);
	}
	
	public void applyPointsPU(int p)
	{
		score += p;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public void startTimer()
	{
		BaseActivity.getSharedInstance().getEngine().registerUpdateHandler(new TimerHandler(0.5f, true, new ITimerCallback(){
            @Override
            public void onTimePassed(TimerHandler pTimerHandler) {
            	score += 1;
            	displayScore();
            }
        }));
	}
	
	public void resetScore()
	{
		score = 0;
	}
}
