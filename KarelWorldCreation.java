/*  Author: William Krug
    Class: CSCI 1203-90
    Assignment: Assignment #9
    Purpose: read in a .wld file and create a GUI representation
    FILE: KarelWorldCreation.java  */

package karel;

// import karel.*;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.FileInputStream;

public class KarelWorldCreation {
  private DataInputStream input = null;
  private String fileName = null;
  private int numberOfCorners;
  private int street;
  private int avenue;
  private int numberOfBeepers;
  private boolean verbose = true;

  public KarelWorldCreation(String inputFile) {
    fileName = inputFile;

    openFile();
    readHorizontalWalls();
    readVerticalWalls();
    readBeepers();
    closeFile();
  }

  public void openFile() {
    try {
      input = new DataInputStream(new FileInputStream(fileName));

      if(verbose) {
        System.out.println("\t\t **** File successfully opened ****");
      }
    }
    catch(IOException openFile) {
      System.err.println("Unable to open file " + fileName + "\n" + openFile.toString());
      System.exit(1);
    }
  }

  public void readHorizontalWalls() {
    try {
      // read in the number of corners that have a wall to the North
      numberOfCorners = input.readInt();

      if (verbose) {
        System.out.println(numberOfCorners + " corners were read in");
      }

      // for each corner that has a wall to the North, create a new intersection
      for(int i = 0; i < numberOfCorners; i++) {
        avenue = input.readInt();
        street = input.readInt();
        InterestingIntersections corner = new InterestingIntersections(street, avenue, 0, true, false);
        // add the "corner" to the InterestingIntersections ArrayList
        ur_Robot.world.intersections.add(corner);
      }

      if(verbose) {
        System.out.print("Read in:");
        ur_Robot.world.displayIntersections();
      }
    }
    catch(IOException readHorizontalWalls) {
      System.err.println("Error reading from " + fileName + "\n" + readHorizontalWalls.toString());
      System.exit(2);
    }
  }

  public void readVerticalWalls() {
    try {
      // read in the number of corners that have a wall to the East
      numberOfCorners = input.readInt();
      boolean alreadyACorner = false;
      int index;

      if (verbose) {
        System.out.println(numberOfCorners + " corners were read in");
      }

      for(int i = 0; i < numberOfCorners; i++) {
        avenue = input.readInt();
        street = input.readInt();

        // check the InterestingIntersections ArrayList to see
        // if the corner already exits
        for(index = 0; index < ur_Robot.world.intersections.size() && (alreadyACorner == false); index++) {
          // flag the index if the corner already exits
          if((ur_Robot.world.intersections.get(index).getAvenue() == avenue) && (ur_Robot.world.intersections.get(index).getStreet() == street)) {
               alreadyACorner = true;

            if(verbose) {
              System.out.println("(" + avenue + "," + street + ") found");
            }
          }
        }
        // if the corner exists, update with a wall to the East
        if(alreadyACorner) {
          ur_Robot.world.intersections.get((index - 1)).setWallToTheEast();
        }
        // if the corner doesn't exist, create a new intersection
        else {
          InterestingIntersections corner = new InterestingIntersections(street, avenue, 0, false, true);
          // add the "corner" to the InterestingIntersections ArrayList
          ur_Robot.world.intersections.add(corner);

          if(verbose) {
            System.out.println("(" + avenue + "," + street + ") is a new corner");
          }
        }
        alreadyACorner = false;
      }

      if(verbose) {
        System.out.print("Read in:");
        ur_Robot.world.displayIntersections();
      }
    }
    catch(IOException readVerticalWalls) {
      System.err.println("Error reading from " + fileName + "\n" + readVerticalWalls.toString());
      System.exit(2);
    }
  }

  public void readBeepers() {
    try {
      // read in the number of corners that have a beeper
      numberOfCorners = input.readInt();
      boolean alreadyACorner = false;
      int index;
      int beeperCount;

      if (verbose) {
        System.out.println(numberOfCorners + " corners were read in");
      }

      for(int i = 0; i < numberOfCorners; i++) {
        avenue = input.readInt();
        street = input.readInt();
        beeperCount = input.readInt();

        // check the InterestingIntersections ArrayList to see
        // if the corner already exits
        for(index = 0; index < ur_Robot.world.intersections.size() && (alreadyACorner == false); index++) {
          // flag the index if the corner already exits
          if((ur_Robot.world.intersections.get(index).getAvenue() == avenue) && (ur_Robot.world.intersections.get(index).getStreet() == street)) {
               alreadyACorner = true;

            if(verbose) {
              System.out.println("(" + avenue + "," + street + ") found");
            }
          }
        }
        // if the corner exists, update with beeper(s)
        if(alreadyACorner) {
          for(int j = 0; j < beeperCount; j++) {
            ur_Robot.world.intersections.get((index - 1)).incrementBeeperCount();
          }
        }
        // if the corner doesn't exist, create a new intersection
        else {
          InterestingIntersections corner = new
            InterestingIntersections(street, avenue, beeperCount, false, false);
          // add the "corner" to the InterestingIntersections ArrayList
          ur_Robot.world.intersections.add(corner);

          if(verbose) {
            System.out.println("(" + avenue + "," + street + ") is a new corner");
          }
        }
        alreadyACorner = false;
      }

      if(verbose) {
        System.out.print("Read in:");
        ur_Robot.world.displayIntersections();
      }
    }
    catch(IOException readBeepers) {
      System.err.println("Error reading from " + fileName + "\n" + readBeepers.toString());
      System.exit(2);
    }
  }

  public void closeFile() {
    try {
      input.close();

      if(verbose) {
        System.out.println("\t\t **** File successfully closed ****");
      }
    }
    catch(IOException closeFile) {
      System.err.println("Error closing file " + fileName + "\n" + closeFile.toString());
      System.exit(1);
    }
  }
}
