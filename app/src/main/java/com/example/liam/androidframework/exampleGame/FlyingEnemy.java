package com.example.liam.androidframework.exampleGame;

/***
 * Sub class of Enemy used to fly. 
 * 
 * @author Liam
 *
 */
public class FlyingEnemy extends Enemy {

	private int savedX, savedY;
	
	public FlyingEnemy(int centerX, int centerY) {
		setCenterX(centerX);
		setCenterY(centerY);
		setBg(GameScreen.getBg1());
		
		savedX = centerX;
		savedY = centerY;
	}
	
	@Override
	public void restart(){
		setCenterX(savedX);
		setCenterY(savedY);
		super.restart();
	}
	
	

}
