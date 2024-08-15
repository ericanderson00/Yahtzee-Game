package cpi221Assignment1;
import java.util.Arrays;
import java.util.ArrayList;


public class ScoreCard {
	
	private final String[] categories = {
            "Aces", "Twos", "Threes", "Fours", "Fives", "Sixes",
            "3 of a kind", "4 of a kind", "Full House", "Small Straight", "Large Straight", "Yahtzee", "Chance"
        	};
    
	private final Integer[] scores = new Integer[categories.length];
	private final boolean[] usedCategories = new boolean[categories.length];
    
    public ScoreCard() {
    	
        // Initialize all scores and used categories to null
        Arrays.fill(scores, null);
        Arrays.fill(usedCategories, false);
    }
    
    public boolean isCategoryAvailable(String category) {
        for (int i = 0; i < categories.length; i++) {
            
        	if (categories[i].equalsIgnoreCase(category)) {
                return !usedCategories[i];
            }
        }
        throw new IllegalArgumentException("Invalid category");
    }
    
    public String[] getCategories() {
        return categories; // Return the array of category names
    }

    // Method to record a score for a given category
    public void recordScore(String category, int score) {
        for (int i = 0; i < categories.length; i++) {
            
        	if (categories[i].equalsIgnoreCase(category)) {
                
            	if(usedCategories[i]) {
                	throw new IllegalStateException("Category already used");
                }
            	
            	scores[i] = score;
            	usedCategories[i] = true;
                return;
            }
        }
        throw new IllegalArgumentException("Invalid category");
    }
    
    // Method to print the score card
    public void printScoreCard() {
        int totalScore = 0;
        
        for (int i = 0; i < categories.length; i++) {
        	
            String scoreStr = scores[i] == null ? "--" : String.valueOf(scores[i]);
            System.out.printf("%-16s [%s]%n", categories[i] + ":", scoreStr);
            
            if (scores[i] != null) {
                totalScore += scores[i];
            }
        }
        
        System.out.println("-----------------------------");
        System.out.printf("%-16s %d%n", "Total:", totalScore);
    }
    
    
    public int calculateScore(String category, ArrayList<Die> dice) {
    	switch (category) {
    	
	    	case "ACES":
	    		return calculateAces(dice);
	    		
	    	case "TWOS":
	    		return calculateTwos(dice);
	    	
	    	case "THREES":
	    		return calculateThrees(dice);
	    		
	    	case "FOURS":
	    		return calculateFours(dice);
	    	case "FIVES":
	    		return calculateFives(dice);
	    		
	    	case "SIXES":
	    		return calculateSixes(dice);
	    	
	    	case "3 OF A KIND":
	    		return calculateThreeOfKind(dice);
	    	
	    	case "4 OF A KIND":
	    		return calculateFourOfAKind(dice);
	    	
	    	case "FULL HOUSE":
	    		return calculateFullHouse(dice);
	    		
	    	case "SMALL STRAIGHT":
	    		return calculateSmallStraight(dice);
	    		
	    	case "LARGE STRAIGHT":
	    		return calculateLargeStraight(dice);
	    		
	    	case "YAHTZEE":
	    		return calculateYahtzee(dice);
	    	
	    	case "CHANCE":
	    		return calculateChance(dice);
	    	
	    	default:
	            throw new IllegalArgumentException("Invalid category");
	    	}
	    	
    	}
    	
    private int calculateAces(ArrayList<Die> dice) {
        int score = 0;
        for (Die die : dice) {
            if (die.getValue() == 1) {
                score += 1; 
            }
        }
        return score;
    }
    private int calculateTwos(ArrayList<Die> dice) {
        int score = 0;
        for (Die die : dice) {
            if (die.getValue() == 2) {
                score += 2; 
            }
        }
        return score;
    }
    
    private int calculateThrees(ArrayList<Die> dice) {
        int score = 0;
        for (Die die : dice) {
            if (die.getValue() == 3) {
                score += 3; 
            }
        }
        return score;
    }
    
    private int calculateFours(ArrayList<Die> dice) {
        int score = 0;
        for (Die die : dice) {
            if (die.getValue() == 4) {
                score += 4; 
            }
        }
        return score;
    }
    
    private int calculateFives(ArrayList<Die> dice) {
        int score = 0;
        for (Die die : dice) {
            if (die.getValue() == 5) {
                score += 5; 
            }
        }
        return score;
    }
    private int calculateSixes(ArrayList<Die> dice) {
        int score = 0;
        for (Die die : dice) {
            if (die.getValue() == 6) {
                score += 6; 
            }
        }
        return score;
    }
    
    private int calculateThreeOfKind(ArrayList<Die> dice) {
        int[] counts = new int[6]; 
        for (Die die : dice) {
            counts[die.getValue() - 1]++; 
        }

        for (int i = 0; i < counts.length; i++) {
            if (counts[i] >= 3) {
                return (i + 1) * 3; 
            }
        }
        return 0;
    }
    
    
    private int calculateFourOfAKind(ArrayList<Die> dice) {
        int[] counts = new int[6]; 
        for (Die die : dice) {
            counts[die.getValue() - 1]++; 
        }
        
    	
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] >= 4) {
                return (i + 1) * 4; 
            }
        }
        return 0; 
    }
    
    private int calculateFullHouse(ArrayList<Die> dice) {
        boolean hasThreeOfAKind = false;
        boolean hasPair = false;   	
        int[] counts = new int[6];

        for (Die die : dice) {
            counts[die.getValue() - 1]++;
        }

        for (int count : counts) {
            if (count == 3) {
                hasThreeOfAKind = true;
            
            } else if (count == 2) {
                hasPair = true;
            }
        }
        //if hasThreeOfAKind and hasPair is true return 25 else 0
        return (hasThreeOfAKind && hasPair) ? 25 : 0; 
    }
    
    
    private int calculateSmallStraight(ArrayList<Die> dice) {
        if (isStraight(dice, 4)) {
            return 30; 
        }
        
        return 0;
    }

    private int calculateLargeStraight(ArrayList<Die> dice) {
        if (isStraight(dice, 5)) {
            return 40; 
        }
        return 0;
    }

    private boolean isStraight(ArrayList<Die> dice, int length) {
    	
    	//boolean array to track which values are present
        boolean[] values = new boolean[6]; 

        //loop which die values are present
        for (Die die : dice) {
        	values[die.getValue() - 1] = true;
        }

        int count = 0;
        for (boolean value : values) {
            if (value) {
            	count++;
                if (count >= length) {
                    return true;
                }
            } else {
            	count = 0;
            }
        }
        return false;
    }
    
    private int calculateYahtzee(ArrayList<Die> dice) {
        int firstValue = dice.get(0).getValue();
        
        for (Die die : dice) {
        	if (die.getValue() != firstValue) {
                return 0; 
            
            }
        }
        return 50; 
    }

    private int calculateChance(ArrayList<Die> dice) {
        int sum = 0;
        for (Die die : dice) {
            sum += die.getValue();
        }
        return sum; 
    }
    
    public int calculateTotalScore() {
        int totalScore = 0;
        for (Integer score : scores) {
            if (score != null) {
                totalScore += score;
            }
        }
        return totalScore;
    }
    	 
}
    
   
    
    
    
    
	


