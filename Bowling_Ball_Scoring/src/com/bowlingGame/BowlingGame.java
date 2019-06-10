package com.bowlingGame;

import java.util.Scanner;

import com.frame.Frame;
import com.frame.PinException;


/*
    We took a class frame having instance variables : 
				1. isStrike - Used to check whether the given frame was a strike or not
				2. isKnock - Used to check whether the given frame was a knock or not
				3. totalPins - Used to check whether the given frame is a strike or knock or not and has valid number of total pins or not.
				4. totalPoints - Used to store the total points earned in a given frame 
				5. tries - Used to store total pins for each roll
				6. totalTries - Used to store the total rolls in each frame
				
	For calculation of points we check whether the given frame was a strike or not if it was a strike then we use the total tries of each frame
	to get the next 2 rolls. Similarly, we check for knock and calculate the points of the frame using total tries.
*/


public class BowlingGame {
	
	
	
	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		BowlingGame bg = new BowlingGame();
		bg.calculatePoints();
	}

	private void calculatePoints() {
		
		int i, j, k, pin, sum = 0;
		
		// Total number of frames are 10 hence size of array of type frame is set to 10
		Frame[] frame = new Frame[10];
		try {
			
			// Loop from 0 to 9 as 10th frame is a special case.
			for (i = 0; i < 9; i++) {
				
				// Maximum number of tries allowed each frame is 2
				frame[i] = new Frame(2);
				frame[i].setTotalTries(2);
				
				for (j = 0; j < 2; j++) {
					
					System.out.println("Enter number of pin in try " + (j + 1) + " of frame " + (i + 1) + " : ");
					pin = sc.nextInt();
					
					// Total number of pins can't be greater than 10
					if (pin > 10) {
						throw new PinException("Number of pin can not be greater than 10");
					}
					
					frame[i].setTotalPins(frame[i].getTotalPins() + pin);
					
					if (frame[i].getTotalPins() > 10) {
						throw new PinException("Number of pin can not be greater than 10");
					}
					
					frame[i].setTries(j, pin);
					
					// Check if the given frame was a strike or knock or neither
					if (frame[i].getTotalPins() == 10 && j == 0) {
						System.out.println("Great Strike!!!");
						frame[i].setStrike(true);
						frame[i].setTotalTries(1);
						break;
					} 
					
					else if (frame[i].getTotalPins() == 10) {
						System.out.println("Excellent Knock!!");
						frame[i].setKnock(true);
					}
					
				}
			}
			
			// Maximum number of tries allowed are 3
			frame[i] = new Frame(3);
			frame[i].setTotalTries(3);
			
			for (j = 0; j < 3; j++) {
				
				System.out.println("Enter number of pin in try " + (j + 1) + " of frame " + (i + 1) + " : ");
				pin = sc.nextInt();
				
				// Pins can't be greater than 10
				if (pin > 10) {
					throw new PinException("Number of pin can not be greater than 10");
				}
				
				frame[i].setTotalPins(frame[i].getTotalPins() + pin);
				
				// Total number of pins can't be greater than 20 or 30 is 10th
				// frame was a strike or a knock
				if (frame[i].getTotalPins() > 20 && j != 2) {
					throw new PinException("Number of pin can not be greater than 10");
				}
				
				else if (frame[i].getTotalPins() > 30) {
					throw new PinException("Number of pin can not be greater than 10");
				}
				
				frame[i].setTries(j, pin);
				
				// Check if the 10th frame was a strike or a knock
				if (frame[i].getTotalPins() == 10 && j == 0) {
					System.out.println("Great Strike!!!");
					frame[i].setStrike(true);
				} 
				
				else if (frame[i].getTotalPins() == 10) {
					System.out.println("Excellent Knock!!");
					frame[i].setKnock(true);
				}
				
				// If the 10th frame was neither strike nor knock then maximum
				// tries allowed are 2
				if (j == 1 && !(frame[i].isKnock() || frame[i].isStrike())) {
					frame[i].setTotalTries(2);
					j++;
				}
				
			}
			
			//To check the pins for each frame 
			/*for (i = 0; i < 9; i++) {
				
				for (j = 0; j < frame[i].getTotalTries(); j++) {
					System.out.print(frame[i].getTries(j));
				}
				
				System.out.println();
			}*/
			 
			
			for (i = 0; i < 9; i++) {
				
				//Check whether given frame was a strike or a knock or neither 
				//If it was a strike then we have to add the points of next two rolls
				if (frame[i].isStrike()) {
					frame[i].setPoints(10);
					j = i + 1;
					k = 0;
					while (k < frame[j].getTotalTries() && k < 2) {
						frame[i].setPoints(frame[i].getPoints() + frame[j].getTries(k));
						k++;
					}
					if (k < 2) {
						j++;
						frame[i].setPoints(frame[i].getPoints() + frame[j].getTries(0));
					}
				}
				
				//If it was a knock then we have to add the point of next roll
				else if (frame[i].isKnock()) {
					frame[i].setPoints(10);
					j = i + 1;
					frame[i].setPoints(frame[i].getPoints() + frame[j].getTries(0));

				} 
				
				else {
					frame[i].setPoints(frame[i].getTries(0) + frame[i].getTries(1));
				}
				
				sum += frame[i].getPoints();
				if(i>2)
					System.out.println("Points scored in "+ ( i + 1 ) +"th frame is "+frame[i].getPoints() + " and total points is " + sum);
				else if(i==0){
					System.out.println("Points scored in "+ ( i + 1 ) +"st frame is "+frame[i].getPoints() + " and total points is " + sum);
				}
				else if(i==1){
					System.out.println("Points scored in "+ ( i + 1 ) +"nd frame is "+frame[i].getPoints() + " and total points is " + sum);
				}
				else if(i==2){
					System.out.println("Points scored in "+ ( i + 1 ) +"rd frame is "+frame[i].getPoints() + " and total points is " + sum);
				}
			}
			
			frame[i].setPoints(frame[i].getTotalPins());
			sum += frame[i].getPoints();
			System.out.println("Points scored in "+ ( i + 1 ) +"th frame is "+frame[i].getPoints() + " and total points is " + sum);
		}
		
		catch (PinException e) {
			System.out.println(e.getMessage());
		}
		
		finally {
			sc.close();
		}
	}
}