// Enemy.java

import java.util.Random;
import ansi_terminal.*;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class Enemy extends Character {
	private String name;
	private int damage;
	private int protection;
	private static Random rng;
	private boolean battleActive;

	public Enemy(String name, int row, int col, int hp, int damage, int protection) {
		super(row, col, '*', Color.RED, hp);
		this.name = name;
		this.damage = damage;
		this.protection = protection;
		this.battleActive = false;
		rng = new Random();
	}

	@Override
		public int getDamage() {
			return damage;
		}

	@Override
		public int getProtection() {
			return protection;
		}

	@Override
		public String getName() {
			return name;
		}

	public void setBattleActive() {
		battleActive = true;
	}

	// randomly move this enemy in the room
	public void walk(Room room, Room2 room2, Room3 room3) {
		// if a battle is active with this enemy, they DON'T walk right after
		if (battleActive) {
			battleActive = false;
			return;
		}

		// loop forever until we move correctly
		while (true) {
			int choice = rng.nextInt(4);
			switch (choice) {
				case 0:
					if (move(0, 1, room, room2, room3)) return;
					break;
				case 1:
					if (move(0, -1, room, room2, room3)) return;
					break;
				case 2:
					if (move(1, 0, room, room2, room3)) return;
					break;
				case 3:
					if (move(-1, 0, room, room2, room3)) return;
					break;
			}

		}
	}

	public void save(PrintWriter out) {
		super.save(out);
		out.println(name);
		out.println(damage);
		out.println(protection);
	}

	/*public Enemy load(Scanner in, File file){
		Enemy enemy = null;
		String line = " ";
		while(!line.equals("*")) {
			String name = in.nextLine();
			int row = in.nextInt();
			int col = in.nextInt();
			int HP = in.nextInt();
			int strength = in.nextInt();
			int defense = in.nextInt();
			enemy = new Enemy(name, row, col, HP, strength, defense);
			in.nextLine(); //avoid issues with scanning in different types
			line = in.nextLine(); //skips the delimiter between enemies
		}
		return enemy; //should we return an enemy or have it be a void method?
	}*/

	public Enemy(Scanner in) {
		super(in);
		name = in.nextLine();
		damage = in.nextInt();
		protection = in.nextInt();
	}

}




