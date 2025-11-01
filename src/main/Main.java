package main;

import main.controller.EngineController;
import main.controller.InterfaceController;
import main.controller.SolverController;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // the controller will manage taking user input and calling the specified engine
        EngineController controller;
        System.out.println("===============================================");
        System.out.println("Welcome to the Logical Expressions System");
        System.out.println("===============================================");
        System.out.println("Please choose a module to start:");
        System.out.println("1. Logical Expression Solver  (Evaluate logical expressions)");
        System.out.println("2. Inference Engine           (Apply inference rules)");
        System.out.println("Enter your choice (1 or 2):");

        int choice;


        try{
            Scanner input = new Scanner(System.in);
            choice = input.nextInt();
            if (choice == 1) {
                controller = new SolverController();
                controller.run();

            } else if (choice == 2) {
                controller = new InterfaceController();
                controller.run();
            }
        }catch(Exception e) {
            System.out.println("Invalid choice");
        }
    }
}

// I should make Util class which contains
// (1) isEquivalent function
// (2) isNegation function

// or can be written as small or capital v
// should small and capital letters stand for the same predicate