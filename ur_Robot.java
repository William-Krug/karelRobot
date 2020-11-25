/*  Author: William Krug
    Class: CSCI 1203-90
    Assignment: Assignment #4
    Purpose: Create a Karel ur_Robot class using the Java language
    FILE: ur_Robot.java  */

package karel;

import java.util.ArrayList;
import karel.Direction;
import static karel.Direction.*;

public class ur_Robot {
  private int street;  // runs East/West
  private int avenue;  // runs North/South
  private Direction direction;
  private int numberOfBeepersInBag;
  private boolean poweredOn;
  private boolean verbose = false;  // used for debugging and tracking progress
  protected static ArrayList<ur_Robot> robotTracker = new ArrayList<ur_Robot>();
  public static KarelWorld world;

  // create a World for the robot(s) to interact with
  static {
    KarelWorld theWorld = new KarelWorld();
    world = theWorld;
  }

  // Default constructor
  public ur_Robot() {
    street = 1;
    avenue = 1;
    direction = North;
    numberOfBeepersInBag = 0;
    poweredOn = true;
    robotTracker.add(this);
  }

  // expanded constructor
  public ur_Robot(int st, int ave, Direction dir, int numBeep) {
    street = st;
    avenue = ave;
    direction = dir;
    numberOfBeepersInBag = numBeep;
    poweredOn = true;
    robotTracker.add(this);
  }

  // move the robot 1 block
  public void move() {
    if(poweredOn) {
      if(direction == North) {
        // hit wall
        if(world.getWallToTheNorth(street,avenue)) {
          poweredOn = false;

          if(verbose) {
            System.out.println("\t\t*** Error!! ***");
            System.out.println("\t     *** You hit a wall ***\n");
          }
        }
        // front is clear
        else {
          street++;

          if(verbose) {
            System.out.println("robot moved 1 block");
          }
        }
      }
      else if(direction == South) {
        // hit boundary wall
        if(street == 1) {
          turnOff();

          if(verbose){
            System.out.println("\t\t*** Error!! ***");
            System.out.println("\t*** You hit the boundary wall ***\n");
          }
        }
        // hit wall
        else if(world.getWallToTheSouth(street,avenue)) {
          turnOff();

          if(verbose){
            System.out.println("\t\t*** Error!! ***");
            System.out.println("\t     *** You hit a wall ***\n");
          }
        }
        // front is clear
        else {
          street--;

          if(verbose) {
            System.out.println("robot moved 1 block");
          }
        }
      }
      else if(direction == East) {
        // hit wall
        if(world.getWallToTheEast(street,avenue)) {
          poweredOn = false;

          if(verbose) {
            System.out.println("\t\t*** Error!! ***");
            System.out.println("\t     *** You hit a wall ***\n");
          }
        }
        // front is clear
        else {
          avenue++;

          if(verbose) {
            System.out.println("robot moved 1 block");
          }
        }
      }
      else if(direction == West) {
        // hit boundary wall
        if(avenue == 1) {
          turnOff();

          if(verbose) {
            System.out.println("\t\t*** Error!! ***");
            System.out.println("\t*** You hit the boundary wall ***\n");
          }
        }
        // hit wall
        else if(world.getWallToTheWest(street,avenue)) {
          turnOff();

          if(verbose){
            System.out.println("\t\t*** Error!! ***");
            System.out.println("\t     *** You hit a wall ***\n");
          }
        }
        // front is clear
        else {
          avenue--;

          if(verbose) {
            System.out.println("robot moved 1 block");
          }
        }
      }
    }
    // robot is off => no movement
    else {
      if(verbose) {
          System.out.println("\t\t*** Your robot is powered off ***");
      }
    }
  }

  // turn left
  public void turnLeft() {
    if(poweredOn) {
      if(direction == North) {
        direction = West;

        if(verbose) {
          System.out.println("Now facing West");
        }
      }
      else if(direction == South) {
        direction = East;

        if(verbose) {
          System.out.println("Now facing East");
        }
      }
      else if(direction == East) {
        direction = North;

        if(verbose) {
          System.out.println("Now facing North");
        }
      }
      else if(direction == West) {
        direction = South;

        if(verbose) {
          System.out.println("Now facing South");
        }
      }
    }
    // robot is off => no turning
    else {
      if(verbose) {
        System.out.println("\t\t*** Your robot is powered off ***");
      }
    }
  }

  // pick up beeper
  public void pickBeeper() {
    if(poweredOn) {
      /* check for beeper is present at the robot's current intersection to interact with World */
      if(world.getBeeperPresent(street,avenue)) {
        numberOfBeepersInBag++;
        world.pickUpBeeper(street,avenue);

        if(verbose) {
          System.out.println("Beeper Collected\n");
        }
      }
      // no beeper was present, turn robot off
      else {
        turnOff();

        if(verbose) {
          System.out.println("\t\t*** Error!! ***");
          System.out.println("\t*** No Beepers present ***\n");
        }
      }
    }
    // robot is off => no operation
    else {
      if(verbose) {
        System.out.println("\t\t*** Your robot is powered off ***");
      }
    }
  }

  // drop beeper
  public void putBeeper() {
    if(poweredOn) {
      if(numberOfBeepersInBag > 0) {
        numberOfBeepersInBag--;
        // update World about new beeper
        world.putDownBeeper(street,avenue);

        if(verbose) {
            System.out.println("Beeper Deposited\n");
        }
      }
      // no beepers in bag => turn off
      else {
        turnOff();

        if(verbose) {
          System.out.println("\t\t*** Error!! ***");
          System.out.println("\t*** No Beepers in Bag ***\n");
        }
      }
    }
    // robot is off => no operation
    else {
      if(verbose) {
        System.out.println("\t\t*** Your robot is powered off ***");
      }
    }
  }

  // turn off the robot
  public void turnOff() {
    poweredOn = false;

    if(verbose) {
      System.out.println("robot is powered off");
    }
  }

  // return the robot's street (y) location
  public int getStreet() {
    return street;
  }

  // return the robot's avenue (x) location
  public int getAvenue() {
    return avenue;
  }

  // return the direction the robot is facing
  public Direction getDirection() {
    return direction;
  }

  // return the number of beepers in the robot's bag
  public int getNumberOfBeepersInBag() {
    return numberOfBeepersInBag;
  }

  // check if robot is turned on
  public boolean getPoweredOn() {
    return poweredOn;
  }

  public boolean getVerbose() {
    return verbose;
  }

  // enchanced toString() method
  public void display() {
    System.out.println("ur_Robot Metrics:");
    System.out.println("\t                Location: " + street + " St and " + avenue + " Ave");
    System.out.println("\t               Direction: " + direction);
    System.out.println("\tNumber of beepers in bag: " + numberOfBeepersInBag);
    System.out.println("\t                  On/Off: " + poweredOn);
    System.out.println();
  }

  public String toSting() {
    return ("St: " + street + " Ave: " + avenue + " Dir: " + direction + " Beeper: " + numberOfBeepersInBag + " Power: " + poweredOn);
  }
}
