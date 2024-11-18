package com.itsjarvis;

public class JarvisFunctions implements Functions{

    @Override
    public void listenCommands() {
        System.out.println("Listening user commands..");
    }
    @Override
    public void walk() {
        System.out.println("Walking...");
    }
    @Override
    public void cook() {
        System.out.println("Cooking...");
    }
    @Override
    public void fight() {
        System.out.println("Fighting...");
    }

}
