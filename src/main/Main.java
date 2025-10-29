package main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println("Welcome to the Logical Expressions System");
        System.out.println("===============================================");
        System.out.println("Please choose a module to start:");
        System.out.println("1. Logical Expression Solver  (Evaluate logical expressions)");
        System.out.println("2. Inference Engine           (Apply inference rules)");
        System.out.println("Enter your choice (1 or 2):");

        int choice;

        Scanner input = new Scanner(System.in);
        choice = input.nextInt();
        if(choice == 1){
            System.out.println("Logical Expression Solver");
        }else if(choice == 2){
            System.out.println("Inference Engine");
        }else {
            System.out.println("Invalid choice");
        }
    }
}