/*  Author: William Krug
    Class: CSCI 1203-90
    Assignment: Assignment #6
    Purpose: create a World for the Karel Robot to navigate, that is independent of the ur_Robot/Robot
    FILE: InterestingIntersections.java  */

package karel;

public class InterestingIntersections {
  private int street;
  private int avenue;
  private int beeperCount;
  private boolean wallToTheNorth;
  private boolean wallToTheEast;
  private boolean verbose = false;

  public InterestingIntersections(int st, int ave, int bc, boolean n, boolean e) {
    street = st;
    avenue = ave;
    beeperCount = bc;
    wallToTheNorth = n;
    wallToTheEast = e;
  }

  public int getStreet() {
    return street;
  }

  public int getAvenue() {
    return avenue;
  }

  public int getBeeperCount() {
    return beeperCount;
  }

  public void decrementBeeperCount() {
    beeperCount--;

    if(verbose) {
      System.out.println("Beeper removed from the world");
    }
  }

  public void incrementBeeperCount() {
    beeperCount++;

    if(verbose) {
      System.out.println("Beeper added to the world");
    }
  }

  public boolean getWallToTheNorth() {
    return wallToTheNorth;
  }

  public void setWallToTheNorth() {
    wallToTheNorth = true;
  }

  public boolean getWallToTheEast() {
    return wallToTheEast;
  }

  public void setWallToTheEast() {
    wallToTheEast = true;
  }

  // enchanded toString() method
  public void displayCorner() {
    System.out.println("Intersection:");
    System.out.println("\t                   Street: " + street);
    System.out.println("\t                   Avenue: " + avenue);
    System.out.println("\tNumber of beepers present: " + beeperCount);
    System.out.println("\t        Wall to the North: " + wallToTheNorth);
    System.out.println("\t         Wall to the East: " + wallToTheEast);
  }

  public String toString() {
    return ("St: " + street + "  Ave: " + avenue + "  Beeper Count: " + beeperCount + "  Wall to the North: " + wallToTheNorth +
            "  Wall to the East: " + wallToTheEast);
  }
}
