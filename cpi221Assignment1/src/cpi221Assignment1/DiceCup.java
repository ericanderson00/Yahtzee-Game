package cpi221Assignment1;

import java.util.ArrayList;




public class DiceCup {
	private ArrayList<Die> dice;



	public DiceCup() {
		
		dice = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            dice.add(new Die());
        }
	}
	
	public void rollAll() {
		for (Die die : dice) {
                die.roll();

	    }
	}
	
	public void roll(int numDice) {
        if (numDice > dice.size()) {
            System.out.println("Error: Trying to roll more dice than available in the cup.");
            return;
        }
        for (int i = 0; i < numDice; i++) {
        	dice.get(i).roll();
        }
    }
	
	public void holdDice(int[] diceToHold) {
        
        for (int index : diceToHold) {
            if (index >= 0 && index < dice.size()) {
                dice.get(index).setHeld(true);
            }
        }
	}
		
	
	
	public ArrayList<Integer> getValues() {
        ArrayList<Integer> values = new ArrayList<>();
        for (Die die : dice) {
            values.add(die.getValue());
        }
        return values;
    }
	
	
	public ArrayList<Die> getDice() {
	    return new ArrayList<>(dice); 
	}
	
	
	public String toString() {
        StringBuilder sbValues = new StringBuilder();
        StringBuilder sbStat = new StringBuilder();
        
        for (Die die : dice) {
        	sbValues.append(die.toString()).append("	");
        	
            if (die.isHeld()) {
            	sbStat.append("(x)	");
            } else {
            	sbStat.append("(-)	");
            }
        }
        return sbValues.toString().trim()+ "\n" + sbStat.toString().trim();
    }
	
	
	public void printUnheldDice() {
        for (Die die : dice) {
            if (!die.isHeld()) {
                System.out.print(die + " ");
            }
        }
        System.out.println(); 
    }
	
	
	public void printHeldDice() { 
        for (Die die : dice) {
            if (die.isHeld()) {
                System.out.print(die + " ");
            }
        }
        System.out.println(); 
    }
	

	public void rerollUnheldDice() {
		for (Die die : dice) {
			if (!die.isHeld()) {
				die.roll(); 
			}   
        }
    }
	
	 
	public ArrayList<Die> getUnheldDice() {
		
		ArrayList<Die> unheldDice = new ArrayList<>();
        for (Die die : dice) {
            if (!die.isHeld()) {
                unheldDice.add(die);
            }
        }
        return unheldDice;
	}
	
	public ArrayList<Die> getHeldDice(){
		ArrayList<Die> heldDice = new ArrayList<>();
		for(Die die : dice) {
			if(die.isHeld()) {
				heldDice.add(die);
			}
		}
		return heldDice;
	}
	
	public void holdAllDice() {
		for (Die die : dice) {
            die.setHeld(true);
        }
	}
	
	public void unHoldAllDice() {
		for (Die die : dice) {
            die.setHeld(false);
        }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
