package com.space.game.domain;

/**
 * Enumerator to Ship types.
 * 
 * @author kapin
 *
 */
public enum Ship {
	TYPE1(10000, 6000, 20, "TYPE1");

	private final int health;
	private final int shield;
	private final int attackPower;
	private final String name;
	

	Ship(int health, int attackPower, int shield, String name) {
		this.health = health;
		this.shield = shield;
		this.attackPower = attackPower;
		this.name = name;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getShield() {
		return shield;
	}
	
	public int getAttackPower() {
		return attackPower;
	}
	
	public String getName() {
		return name;
	}
}
