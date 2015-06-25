package com.example.liam.androidframework.exampleGame;

import android.graphics.Rect;
import android.util.Log;

import com.example.liam.androidframework.framework.Image;


public class Tile {

	private int tileX, tileY, type, damage;
	private byte speedX;
	private Image tileImage;
	private Player player;
	private Background bg; 
	private Rect r;
	
	/**Custom Constructor that gives a tile within a 2D array a image to render on screen.
	 * 
	 * @param x tile location on the X axis
	 * @param y tile location on the Y axis
	 * @param typeInt item within the array 
	 */
	public Tile(int x, int y, int typeInt) {
		tileX = x * 40; 
		tileY = y * 40;
		
		player = GameScreen.getPlayer();
		bg = GameScreen.getBg1();
		
		type = typeInt;
		
		r = new Rect();
		
		if (type == 1){
			tileImage = Assets.getTileDirt();
			damage =0;
		}
		else if (type == 2){
			tileImage = Assets.getTileGrassDirt();
			damage =0; 
		}
		else if (type == 3){
			tileImage = Assets.getTileSpike();
			damage =100;
		}
		else {
			type = 0;
			damage =0;
		}
		
	}
	
	/**Set the speed at which the tiles move when the player moves
	 * This is also used for parallax scrolling. 
	 */
	public void update() { 
		speedX = (byte) (bg.getSpeedX()*5);
		tileX += speedX;
		r.set(tileX, tileY, tileX+40, tileY+40);
		
		if( r.intersect(GameScreen.getPlayer().getCheck()) &&  type != 0){
			checkVerticalCollision(GameScreen.getPlayer().getBottom(), GameScreen.getPlayer().getHead());
			checkSideCollision(GameScreen.getPlayer().getLeftHand(), GameScreen.getPlayer().getRightHand());
			Log.d("CollisionCheck", "Checking Collison");
		}
	}

	public int getType(){
		return type;
	}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public Image getTileImage() {
		return tileImage;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}
	
	public void checkVerticalCollision(Rect rbot, Rect rtop){
		if(Rect.intersects(rtop, r)){
			
		}
		
		if(r.intersect(rbot)){
			Log.d("CollisionCheck", "Hit Floor");
			player.setJumped(false);
			player.setSpeedY((byte) 0);
			player.setCenterY(tileY);
			player.setHealth(player.getHealth() - damage);

		}
	}
	
	public void checkSideCollision(Rect rleft, Rect rright){
		if(type != 0 ) {
			
			if(Rect.intersects(rleft,r)) {
				System.out.println("in left side intersect");
				player.setCenterX(tileX + 88);
				player.setSpeedX((byte) 0);
			}
		/*   else if (leftFoot.intersects(r)) {
				System.out.println("in left foot intersect");
				player.setCenterX(tileX);
				player.setSpeedX(0);
			} */ 
			
			if(Rect.intersects(rright,r)) {
				System.out.println("in right side intersect");
				player.setCenterX(tileX + 10);
				player.setSpeedX((byte) 0);
			}
			
			/* else if (rightFoot.intersects(r)){
				System.out.println("in right foot intersect");
				player.setCenterX(tileX);
				player.setSpeedX(0);
			 } */
			
			
		}
		
		
	}
	
	
	
}
