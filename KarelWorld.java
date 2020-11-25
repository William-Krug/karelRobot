/*  Author: William Krug
    Class: CSCI 1203-90
    Assignment: Assignment #6
    Purpose: create a World for the Karel Robot to navigate, that is independent of the ur_Robot/Robot
    FILE: KarelWorld.java  */

package karel;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class KarelWorld {
  private boolean verbose = false;
  public ArrayList<InterestingIntersections> intersections = new ArrayList<InterestingIntersections>();
  public static KarelMap map;

  // display the World in a GUI
  static {
    KarelMap atlas = new KarelMap("Karel World");

    atlas.getContentPane().add(new KarelPanel());
    atlas.setSize(800,800);
    atlas.setVisible(true);

    map = atlas;
  }

  /* check the list of InterestingIntersections for a beeper at the desiganed corner */
  public boolean getBeeperPresent(int st, int ave) {
    boolean beeperHere = false;

    for(int i = 0; i < intersections.size() && (beeperHere == false); i++) {
      if((intersections.get(i).getStreet() == st) && (intersections.get(i).getAvenue() == ave) && (intersections.get(i).getBeeperCount() > 0)) {
        beeperHere = true;

        if(verbose) {
          System.out.println("Beeper(s) present at this corner");
        }
      }
    }

    if(verbose) {
      if(!beeperHere) {
        System.out.println("There are no beepers present at this corner");
      }
    }
    return beeperHere;
  }

  public void pickUpBeeper(int st, int ave) {
    int loc = -1;
    boolean empty = false;

    // pick up a beeper from a corner
    for(int i = 0; i < intersections.size() && (empty == false); i++) {
      if((intersections.get(i).getStreet() == st) && (intersections.get(i).getAvenue() == ave) && (intersections.get(i).getBeeperCount() > 0)) {
        intersections.get(i).decrementBeeperCount();
        empty = true;

        if(verbose) {
          System.out.println("Beeper picked up");
        }
        // if last beeper at corner, flag for removal
        if((intersections.get(i).getStreet() == st) && (intersections.get(i).getAvenue() == ave) && (intersections.get(i).getBeeperCount() == 0) &&
           (intersections.get(i).getWallToTheNorth() == false) && (intersections.get(i).getWallToTheEast() == false)) {
          loc = i;
        }
      }
    }
    // remove corner if beeper was the only "interesting" thing
    if(loc != -1) {
      intersections.remove(loc);

      if(verbose) {
        System.out.println("Intersection removed");
      }
    }
  }

  public void putDownBeeper(int st, int ave) {
    boolean found = false;

    // put down a beeper at the corner
    for(int i = 0; i < intersections.size() && (found == false); i++) {
      if((intersections.get(i).getStreet() == st) && (intersections.get(i).getAvenue() == ave)) {
        intersections.get(i).incrementBeeperCount();
        found = true;

        if(verbose) {
          System.out.println("Beeper put down");
        }

      }
    }
    /* create a new InterestingIntersections if the first beeper is added to the corner */
    if(!found) {
      InterestingIntersections n = new InterestingIntersections(st,ave,1,false, false);
      intersections.add(n);

      if(verbose) {
        System.out.println("Intersection added");
      }
    }
  }

  public boolean getWallToTheNorth (int st, int ave) {
    boolean wall = false;

    for(int i = 0; i < intersections.size() && (wall == false); i++) {
      if((intersections.get(i).getStreet() == st) && (intersections.get(i).getAvenue() == ave) && (intersections.get(i).getWallToTheNorth())) {
        wall = true;

        if(verbose) {
          System.out.println("There is a wall to the North");
        }

      }
    }

    if(verbose) {
      if(!wall) {
        System.out.println("There is no wall to the North");
      }
    }

    return wall;
  }

  public boolean getWallToTheSouth(int st, int ave) {
    boolean wall = false;

    for(int i = 0; i < intersections.size() && (wall == false); i++) {
      if((intersections.get(i).getStreet() == (st - 1)) && (intersections.get(i).getAvenue() == ave) && (intersections.get(i).getWallToTheNorth())) {
        wall = true;

        if(verbose) {
          System.out.println("There is a wall to the South");
        }

      }
    }

    if(verbose) {
      if(!wall) {
        System.out.println("There is no wall to the South");
      }
    }

    return wall;
  }

  public boolean getWallToTheEast(int st, int ave) {
    boolean wall = false;

    for(int i = 0; i < intersections.size() && (wall == false); i++) {
      if((intersections.get(i).getStreet() == st) && (intersections.get(i).getAvenue() == ave) && (intersections.get(i).getWallToTheEast())) {
        wall = true;

        if(verbose) {
          System.out.println("There is a wall to the East");
        }

      }
    }

    if(verbose) {
      if(!wall) {
        System.out.println("There is no wall to the East");
      }
    }

    return wall;
  }

  public boolean getWallToTheWest(int st, int ave) {
    boolean wall = false;

    for(int i = 0; i < intersections.size() && (wall == false); i++) {
      if((intersections.get(i).getStreet() == st) && (intersections.get(i).getAvenue() == (ave - 1)) && (intersections.get(i).getWallToTheEast())) {
        wall = true;

        if(verbose) {
          System.out.println("There is a wall to the West");
        }

      }
    }

    if(verbose) {
      if(!wall) {
        System.out.println("There is no wall to the West");
      }
    }

    return wall;
  }

  // enhanced toString() to list all InterestingIntersections
  public void displayIntersections() {
    System.out.println("\nInteresting Intersections:");
    for(InterestingIntersections i : intersections) {
      System.out.println(i.toString());
    }
    System.out.println("\n");
  }

  // display the World in a GUI
  public void drawMap() {
    map.getContentPane().repaint();
  }

  public String toString() {
    return ("There are " + intersections.size() + " interesting intersections in this world");
  }
}
