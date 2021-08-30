// Created 08-29-2021 by Chase Lake
// Dice object - can use threads to roll dice asynchronously

package Yahtzee;

import java.util.Random;

public class Dice extends Thread {
    private final int numSides = 6;
    private Random rand;
    private int value;
    private boolean keep = false;

    public Dice() {
        rand = new Random();
        value = roll();
    }

    public int getValue() { return value; }

    public boolean getKeep() { return keep; }
    public void setKeep(boolean k) { keep = k; }
    
    public synchronized int roll() {
        return rand.nextInt(numSides) + 1;
    }

    public void run() {
        try 
        {
            Thread.sleep(rand.nextInt(5000)); // playing around with making it seem more realistic
            value = roll();
            System.out.println("Rolled a " + value);
        } 
        catch (Exception ex) 
        {
            System.out.println("Error rolling die: " + ex.getMessage());
        }
    }

}
