package com.example.liam.androidframework.exampleGame;

import android.graphics.Rect;
import java.util.ArrayList;

/**Superclass for managing the all enemy characters
 * 
 * @author Liam
 *
 */
public class Enemy {

	private int maxHealth, currentHealth, power, centerX, centerY, ifAttack;
	private byte speedX, movementSpeed;
	private ArrayList<Projectile> projectiles;
	private Background bg;
	private Rect hitbox;
	
	
	public Enemy(){
		projectiles = new ArrayList<Projectile>();
		ifAttack = 0;
		centerX = 0;
		centerY = 0;
		maxHealth = 100; 
		currentHealth = 100;
		hitbox = new Rect(0,0,0,0);
	}
	
	public void restart(){
		maxHealth = 100; 
		currentHealth = 100;
	}
	
	/**Updates an enemy character this needs to happen before each render. 
	 * 
	 */
	public void update(){
		follow();
		attack();
		centerX += speedX;
		speedX = (byte) (bg.getSpeedX()*5);
		speedX = (byte) (bg.getSpeedX() * 5 + movementSpeed);
		hitbox.set(centerX - 49, centerY - 49, 64, 64);
		
		if(hitbox.intersect(Player.getCheck())){
			checkCollision();
		}
	}
	
	private void checkCollision(){
		if(hitbox.intersect(Player.getBottom())|| hitbox.intersect(Player.getHead()) || hitbox.intersect(Player.getLeftHand()) || hitbox.intersect(Player.getRightHand())){
			System.out.println("collision");
		}
	}
	
	public void follow() {
		
		if (centerX < -95 || centerX > 810) 
			movementSpeed = 0;
		else if (Math.abs(GameScreen.getPlayer().getCenterX() - centerX) < 5)
			movementSpeed = 0;
		else {
			
			if(GameScreen.getPlayer().getCenterX() >= centerX)
				movementSpeed = 3;
			else
				movementSpeed = -2;
		}
		
		
	}
	
	public void die(){
		
	}
	
	public void attack(){
		if(ifAttack == 50){
			Projectile p = new Projectile(centerX + 0, centerY -25, 2);
			p.setEnemy(true);
			projectiles.add(p);
			ifAttack = 0; 
		}
		ifAttack++;
		
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	public int getCurrentHealth() {
		return currentHealth;
	}
	
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}
	
	public int getPower() {
		return power;
	}
	
	public void setPower(int power) {
		this.power = power;
	}
	
	public byte getSpeedX() {
		return speedX;
	}
	
	public void setSpeedX(byte speedX) {
		this.speedX = speedX;
	}
	
	public int getCenterX() {
		return centerX;
	}
	
	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}
	
	public int getCenterY() {
		return centerY;
	}
	
	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}
	
	public Background getBg() {
		return bg;
	}
	
	public void setBg(Background bg) {
		this.bg = bg;
	}
	
	public Rect getHitbox() {
		return hitbox;
	}


	public void setHitbox(Rect hitbox) {
		this.hitbox = hitbox;
	}

	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	public void setProjectiles(ArrayList<Projectile> projectiles) {
		this.projectiles = projectiles;
	}

	@Override
	public String toString() {
		return "Enemy [maxHealth=" + maxHealth + ", currentHealth="
				+ currentHealth + ", power=" + power + ", speedX=" + speedX
				+ ", centerX=" + centerX + ", centerY=" + centerY + ", bg="
				+ bg + "]";
	}
	
	
}