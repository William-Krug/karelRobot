/*  Author: William Krug
    Class: CSCI 1203-90
    Assignment: Assignment #5
    Purpose: extend the ur_Robot class to provide additional functionality in the form of Boolean tests
    FILE: Robot.java  */

package karel;
import karel.Direction;
import static karel.Direction.*;

public class Robot extends ur_Robot {

  // Default constructor
  public Robot() {
    super();
  }

  // expanded constructor
  public Robot(int st, int ave, Direction dir, int numBeep) {
    super(st, ave, dir, numBeep);
  }

  // check if there is a wall in front of the robot
  public boolean frontIsClear() {
    boolean clearPath = true;

    if(getPoweredOn()) {
      if(getDirection() == North) {
        if(world.getWallToTheNorth(getStreet(),getAvenue())) {
          clearPath = false;

          if(getVerbose()) {
            System.out.println("There is a wall to the North");
          }
        }
      }
      if(getDirection() == South) {
        if(world.getWallToTheSouth(getStreet(),getAvenue())) {
          clearPath = false;

          if(getVerbose()) {
            System.out.println("There is a wall to the South");
          }
        }
        //Boundary wall
        else if((getStreet() == 1)) {
          clearPath = false;

          if(getVerbose()) {
            System.out.println("There is a boundary wall to the South");
          }
        }
      }
      if(getDirection() == East) {
        if(world.getWallToTheEast(getStreet(),getAvenue())) {
          clearPath = false;

          if(getVerbose()) {
            System.out.println("There is a wall to the East");
          }
        }
      }
      if(getDirection() == West) {
        if(world.getWallToTheWest(getStreet(),getAvenue())) {
          clearPath = false;

          if(getVerbose()) {
            System.out.println("There is a wall to the West");
          }
        }
        //Boundary Wall
        else if((getAvenue() == 1)) {
          clearPath = false;

          if(getVerbose()) {
            System.out.println("There is a boundary wall to the West");
          }
        }
      }
    }

    if(getVerbose()) {
      if(clearPath) {
        System.out.println("The path is clear ahead");
      }
    }
    return clearPath;
  }

  public boolean nextToABeeper() {
    boolean beeper = false;

    if(getPoweredOn()) {
      // check the World for a beeper
      if(world.getBeeperPresent(getStreet(),getAvenue())) {
        beeper = true;

        if(getVerbose()) {
          System.out.println("There is a beeper at this corner");
        }
      }
    }

    if(getVerbose()) {
      if(!beeper) {
        System.out.println("There are no beepers at this corner");
      }
    }
    return beeper;
  }

  public boolean anyBeepersInBeeperBag() {
    boolean loadedBag = false;

    if(getPoweredOn()) {
      // check for beepers in the robot's bag
      if(getNumberOfBeepersInBag() > 0) {
        loadedBag = true;

        if(getVerbose()) {
          System.out.println("there are beepers in the bag");
        }
      }
    }
    return loadedBag;
  }

  public boolean facingNorth() {
    boolean north = false;

    if(getPoweredOn()) {
      if(getDirection() == North) {
        north = true;

        if(getVerbose()) {
          System.out.println("looking North");
        }
      }
    }
    return north;
  }

  public boolean facingSouth() {
    boolean south = false;

    if(getPoweredOn()) {
      if(getDirection() == South) {
        south = true;

        if(getVerbose()) {
          System.out.println("looking South");
        }
      }
    }
    return south;
  }

  public boolean facingEast() {
    boolean east = false;

    if(getPoweredOn()) {
      if(getDirection() == East) {
        east = true;

        if(getVerbose()) {
          System.out.println("looking East");
        }
      }
    }
    return east;
  }

  public boolean facingWest() {
    boolean west = false;

    if(getPoweredOn()) {
      if(getDirection() == West) {
        west = true;

        if(getVerbose()) {
          System.out.println("looking West");
        }
      }
    }
    return west;
  }

  public boolean nextToARobot() {
    boolean buddy = false;

    if(getPoweredOn()) {
      // check location of other robot's in the World
      for(ur_Robot r : robotTracker) {
        if((r.getStreet() == getStreet()) && (r.getAvenue() == getAvenue()) && (r != this)) {
          buddy = true;
        }
      }
    }
    return buddy;
  }

  public String getPower() {
    return (getPoweredOn() == true) ? "On" : "Off";
  }

  // enhanced toString() method
  public void display() {
    System.out.println("Robot Metrics:");
    System.out.println("\t                Location: " + getStreet() + " St and " + getAvenue() + " Ave");
    System.out.println("\t               Direction: " + getDirection());
    System.out.println("\tNumber of beepers in bag: " + getNumberOfBeepersInBag());
    System.out.println("\t                  On/Off: " + getPower());
  }

  public String toString() {
    return ("St: " + getStreet() + " Ave: " + getAvenue() + " Dir: " + getDirection() + " Beepers : " + getNumberOfBeepersInBag() + " Power: " + getPower());
  }
}
