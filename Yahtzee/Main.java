// Created 08-29-2021 by Chase Lake
// Main Object, contains primary game code

package Yahtzee;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Scanner;

public class Main {
    private static Dice[] DICE = new Dice[5];

    public static void main(String[] args) {
        initialize();
        boolean quit = false;

        while (!quit) {
            int rerolls = 2;
            while (rerolls > 0) {
                rollDice(DICE);
                listDice(DICE);
                writeln(rerolls + " rerolls left.");
                chooseKeep();

            }

        }
    }

    private static void initialize() {
        writeln("Welcome to the Yahtzee simulator!");

        for (int i=0; i<DICE.length; i++) {
            DICE[i] = new Dice();
        }
    }

    private static void rollDice(Dice[] dice) {
        try 
        {
            ExecutorService service = Executors.newCachedThreadPool();
            for (Dice die : dice) 
            {
                if (!die.getKeep()) {
                    service.execute(die);
                }
            }
            service.shutdown();
            
            while (!service.isTerminated()) { }

        } catch (Exception ex) {
            writeln(ex.getMessage());
        }
    }

    private static void listDice(Dice[] dice) {
        writeln("Current dice:");
        for (Dice d : dice) {
            System.out.print(d.getValue() + " ");
            writeln("");
        }
    }

    private static void chooseKeep() {
        Scanner cin = new Scanner(System.in);
        boolean done = false;
        writeln("Which numbers would you like to reroll? (enter 0 to stop entering)");

        while (!done) {
            int diceNum = cin.nextInt();
            for (Dice d : DICE) {
                if (d.getValue() == diceNum) {
                    d.setKeep(true);
                    break;
                }
            }
        }

        cin.close();
    }

    public static void writeln(String message) {
        System.out.println(message);
    }
}