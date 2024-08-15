package cpi221Assignment1;

import java.util.Random;

public class Die {
	private int value;
	private int sides;
	private Random random;
	private boolean isHeld;
	
	public Die() {
		
		sides = 6;
		value = 0;
		this.random = new Random();
		
	}
	
	public Die(int sides) {
		this.sides = sides;
		this.random = new Random();
		
	}
	
	public int roll() {
		value = random.nextInt(sides)+1;
		return value;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setHeld(boolean held) {
        this.isHeld = held;
    }

    public boolean isHeld() {
        return isHeld;
    }
    
    public void hold() {
        this.isHeld = true;
    }
	
	public String toString() {
		return "["+value+"]";
	}
	
	
}
