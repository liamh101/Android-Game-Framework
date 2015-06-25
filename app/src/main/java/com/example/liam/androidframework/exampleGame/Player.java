package com.example.liam.androidframework.exampleGame;

import android.graphics.Rect;

import java.util.ArrayList;

/** Class is make to control the player character sprite within the game.
 * 
 * @author Liam
 *
 */
public class Player {

	//Preset variables
	final byte JUMPSPEED = -15;
	final byte MOVESPEED = 5;
	
	//Array of projectiles (bullets)
	private ArrayList<Projectile> projectiles;
	
	private Rect bottom,head,leftHand,rightHand,check;

	
	//Location and speed variables
	private int centerX; 
	private int centerY;
	private byte speedX;
	private byte speedY;
	private int health;
	
	//Movement variables
	private boolean jumped;
	private boolean movingLeft;
	private boolean movingRight;
	private boolean ducked;
	private boolean readyToFire;
	private boolean lookup;
	
	private static Background bg1, bg2;

	/**Default Character constructor
	 * 
	 */
	public Player(){
		projectiles = new ArrayList<Projectile>();
		centerX = 100;
		centerY = 370;
		health = 100;
		speedX = 0;
		speedY = 0;
		jumped = false;
		movingLeft = false;
		movingRight = false;
		lookup = false;
		ducked = false;
		readyToFire = true;
		
		bottom = new Rect(centerX + 45, centerY + 65, centerX + 20, centerY + 55);
        bottom = new Rect(centerX + 45, centerY + 65, centerX + 20, centerY + 55);
        head = new Rect(centerX + 25 , centerY + 12, centerX + 38, centerY + 4);
        leftHand = new Rect(centerX + 13, centerY + 42, centerX + 23, centerY +  20);
        rightHand = new Rect(centerX + 50, centerY + 42, centerX + 40, centerY + 20);
        check = new Rect(centerX, centerY, centerX + 120, centerY +180);
		
		bg1 = GameScreen.getBg1();
		bg2 = GameScreen.getBg2();
	}
	
	/**Custom constructor that give custom location, speed and background.
	 * 
	 * @param centerX X location of character
	 * @param centerY Y location of character
	 * @param speedX left and right speed of character
	 * @param speedY up and down speed of character
	 * @param bg1 first background to display
	 * @param bg2 second background to display when reached the end of first background.
	 */
	public Player(int centerX, int centerY, byte speedX, byte speedY, int health,  Background bg1, Background bg2){
		projectiles = new ArrayList<Projectile>();
		this.centerX = centerX;
		this.centerY = centerY;
		this.speedX = speedX;
		this.speedY = speedY;
		this.health = health;
		this.jumped = false;
		this.bg1 = bg1;
		this.bg2 = bg2;

        bottom = new Rect(centerX + 45, centerY + 65, centerX + 20, centerY + 55);
        bottom = new Rect(centerX + 45, centerY + 65, centerX + 20, centerY + 55);
        head = new Rect(centerX + 25 , centerY + 12, centerX + 38, centerY + 4);
        leftHand = new Rect(centerX + 13, centerY + 42, centerX + 23, centerY +  20);
        rightHand = new Rect(centerX + 50, centerY + 42, centerX + 40, centerY + 20);
        check = new Rect(centerX, centerY, centerX + 120, centerY +180);
		
		lookup = false;
		movingLeft = false;
		movingRight = false;
		ducked = false;
		readyToFire = true;
	}
	
	
	public void restart(){
		centerX = 100;
		centerY = 440;
		speedX = 0;
		speedY = 0;
		health = 100;
		jumped = false;
		movingLeft = false;
		movingRight = false;
		ducked = false;
	}
	
	/**get current left location of character 
	 * 
	 * @return int of location 
	 */
	public int getCenterX() {
		return centerX;
	}

	/**Set x location of the character
	 * 
	 * @param centerX int of location 
	 */
	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	/**Get Y location of character
	 * 
	 * @return int of location
	 */
	public int getCenterY() {
		return centerY;
	}

	/**Set Y location of character
	 * 
	 * @param centerY int of location
	 */
	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	/**Return the max speed the character can go along the X axis
	 * 
	 * @return int of max speed
	 */
	public int getSpeedX() {
		return speedX;
	}

	/**Set the max speed the character can along the X axis
	 * 
	 * @param speedX byte max speed of character
	 */
	public void setSpeedX(byte speedX) {
		this.speedX = speedX;
	}

	/**Get max speed character can move along the Y axis
	 * 
	 * @return byte of max speed
	 */
	public byte getSpeedY() {
		return speedY;
	}

	/**Set max speed character can move along the Y axis 
	 * 
	 * @param speedY byte of max speed
	 */
	public void setSpeedY(byte speedY) {
		this.speedY = speedY;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	/**is the character jumping?
	 * 
	 * @return boolean
	 */
	public boolean isJumped() {
		return jumped;
	}

	/**Set if the character is jumping
	 * 
	 * @param jumped boolean
	 */
	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}

	/**is the character moving left? 
	 * 
	 * @return boolean 
	 */
	public boolean isMovingLeft() {
		return movingLeft;
	}

	/**Set if the character is moving left
	 * 
	 * @param movingLeft boolean
	 */
	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	/**Is the character moving right? 
	 * 
	 * @return boolean
	 */
	public boolean isMovingRight() {
		return movingRight;
	}

	/**Set if the character is moving right
	 * 
	 * @param movingRight boolean
	 */
	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	/**Set if the character is ducking
	 * 
	 * @return boolean
	 */
	public boolean isDucked() {
		return ducked;
	}

	/**Set if the character is ducking
	 * 
	 * @param ducked boolean
	 */
	public void setDucked(boolean ducked) {
		this.ducked = ducked;
	}
	
	public boolean isLookup() {
		return lookup;
	}

	public void setLookup(boolean lookup) {
		this.lookup = lookup;
	}

	/**returns background that is first displayed on level load  
	 * 
	 * @return background 1
	 */
	public static Background getBg1() {
		return bg1;
	}
	
	/**Set the background of first area
	 * 
	 * @param bg1 new background
	 */
	public static void setBg1(Background bg1) {
		Player.bg1 = bg1;
	}

	/**returns background that is displayed after the first on level load
	 * 
	 * @return background 2 
	 */
	public static Background getBg2() {
		return bg2;
	}

	/**Set the background of second area
	* 
	* @param bg2 new background
	*/
	public static void setBg2(Background bg2) {
		Player.bg2 = bg2;
	}
	
	/**returns the array of projectiles used by the character to destroy enemies
	 * 
	 * @return Array of type Projectile
	 */
	public ArrayList<Projectile> getProjectiles(){
		return projectiles;
	}
	
	public  Rect getBottom() {
		return bottom;
	}

	public  void setBottom(Rect rect) {
        this.bottom = rect;
	}

	public Rect getHead() {
		return head;
	}

	public void setHead(Rect head) {
        this.head = head;
	}

	public Rect getLeftHand() {
		return leftHand;
	}

	public void setLeftHand(Rect leftHand) {
        this.leftHand = leftHand;
	}

	public Rect getRightHand() {
		return rightHand;
	}

	public void setRightHand(Rect rightHand) {
        this.rightHand = rightHand;
	}
	
	public Rect getCheck() {
		return check;
	}

	public void setCheck(Rect check) {
		this.check = check;
	}

	/**Used on every game loop to make sure what is rendered on the screen
	 * in regards to the character
	 */
	public void update(){
		
		
		  // Moves Character or Scrolls Background accordingly.
        if (speedX < 0) {
            centerX += speedX;
        }
        if (speedX == 0 || speedX < 0) {
            bg1.setSpeedX((byte) 0);
            bg2.setSpeedX((byte) 0);

        }
        if (centerX <= 200 && speedX > 0) {
            centerX += speedX;
        }
        if (speedX > 0 && centerX > 200){
            bg1.setSpeedX((byte) (-MOVESPEED/5));
            bg2.setSpeedX((byte) (-MOVESPEED/5));
        }

        // Updates Y Position
        centerY += speedY;

        // Handles Jumping
     
        speedY += 1;
        
        if (speedY > 3) {
            jumped = true;
        }

        // Prevents going beyond X coordinate of 0
        if (centerX + speedX <= 60) {
            centerX = 61;
        }
        
        bottom.set(centerX + 45, centerY + 65, centerX + 20, centerY + 55);
        head.set(centerX + 25 , centerY + 12, centerX + 38, centerY + 4);
        leftHand.set(centerX + 13, centerY + 42, centerX + 23, centerY +  20);
        rightHand.set(centerX + 50, centerY + 42, centerX + 40, centerY + 20);
        check.set(centerX, centerY, centerX + 120, centerY +180);
        
	}
	
	/**Move character right if the character is not ducking
	 * 
	 */
	public void moveRight(){
		if (ducked == false)
			speedX = MOVESPEED; 
	}
	
	/**Move character left if the character is not ducking 
	* 
	*/
	public void moveLeft() {
		if (ducked == false)
			speedX = -MOVESPEED;
	}
	
	/**Stop the character moving right.
	 * 
	 */
	public void stopRight() {
        setMovingRight(false);
        stop();
    }
	
	/**Stop the character moving left.
	 * 
	 */
    public void stopLeft() {
        setMovingLeft(false);
        stop();
    }
	
    /**Used to check if the character needs to stop
     * 
     */
	public void stop(){
		if (isMovingRight() == false && isMovingLeft() == false) {
            speedX = 0;
        }

        if (isMovingRight() == false && isMovingLeft() == true) {
            moveLeft();
        }

        if (isMovingRight() == true && isMovingLeft() == false) {
            moveRight();
        } 
	}
	
	public boolean isReadyToFire() {
		return readyToFire;
	}

	public void setReadyToFire(boolean readyToFire) {
		this.readyToFire = readyToFire;
	}

	/** If the isn't already jumping make character jump
	 * 
	 */
	public void jump(){
		if(jumped == false){
			speedY = JUMPSPEED;
			jumped = true;
		}
	}
	
	/** Make the character shoot a projectile
	 * 
	 */
	public void shoot(){
		if(readyToFire){
			
			if(isLookup()){
				Projectile p = new Projectile(centerX + 0, centerY -25, 3);
				p.setEnemy(false);
				projectiles.add(p);
			}
				
			else{ 
				Projectile p = new Projectile(centerX + 0, centerY -25, 0);
				p.setEnemy(false);
				projectiles.add(p);
			}
			
		}
	}
	
	

	/**Debug each variable within the character class
	 * 
	 */
	@Override
	public String toString() {
		return "Character [JUMPSPEED=" + JUMPSPEED + ", MOVESPEED=" + MOVESPEED
				+ ", centerX=" + centerX + ", centerY="
				+ centerY + ", speedX=" + speedX + ", speedY=" + speedY
				+ ", jumped=" + jumped + ", movingLeft=" + movingLeft
				+ ", movingRight=" + movingRight + ", ducked=" + ducked + "]";
	}
	
	
}
