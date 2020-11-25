/*  Author: William Krug
    Class: CSCI 1203-90
    Assignment: Assignment #8 & Assignment #9
    Purpose: helper class for display Karel World GUI
    FILE: KarelPanel.java  */

package karel;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Stroke;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D;
import karel.Direction;
import static karel.Direction.*;

public class KarelPanel extends JPanel {
  // define stroke size for differnet objects
  final static BasicStroke boundarWall = new BasicStroke(10.0f);
  final static BasicStroke artifacts = new BasicStroke(5.0f);
  final static BasicStroke robot = new BasicStroke(3.0f);

  public KarelPanel() {
    setBackground(Color.white);
    setForeground(Color.lightGray);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2D = (Graphics2D)g;

    int height = getHeight();
    int width = getWidth();
    // number of Streets that will fit on the panel
    int streetCount = height / 50;
    // number of Avenues that will fit on the panel
    int avenueCount = width / 50;
    // margin between panel edge and "World"
    int leftBorder = 75;
    // margin between panel bottom and "World"
    int bottomBorder = height - 75;

    drawStreetsAndAvenues(g2D, width, streetCount, avenueCount, leftBorder, bottomBorder);

    drawBoundaryWalls(g2D, width, leftBorder, bottomBorder);

    labelStreets(g2D, height, streetCount, bottomBorder);

    labelAvenues(g2D, height, width, avenueCount, leftBorder);

    drawRobots(g2D, leftBorder, bottomBorder);

    drawIntersections(g2D, leftBorder, bottomBorder);
  }

  private void drawStreetsAndAvenues(Graphics2D g2D, int width, int streetCount, int avenueCount, int leftBorder, int bottomBorder) {
    /* to set the streets running from bottom to top and avenues running from left to right:
         x-coordinate = leftBorder + (avenue * 50)
         y-coordinate = bottomBorder - (street * 50) */

    // set road GUI characteristics
    g2D.setPaint(Color.lightGray);
    Stroke road = g2D.getStroke();
    g2D.setStroke(road);

    // Draw the Streets
    for(int y = 0; y <= streetCount; y++) {
      g2D.draw(new Line2D.Double(leftBorder, (bottomBorder - (y * 50)), width, (bottomBorder - (y * 50))));
    }

    // Draw the Avenues
    for(int x = 0; x <= avenueCount; x++) {
      g2D.draw(new Line2D.Double((leftBorder + (x * 50)), 0, (leftBorder + (x * 50)), bottomBorder));
    }
  }

  private void drawBoundaryWalls(Graphics2D g2D, int width, int leftBorder, int bottomBorder) {

    // set boundary wall GUI characteristics
    g2D.setPaint(Color.black);
    g2D.setStroke(boundarWall);

    // draw the boundary walls
    g2D.draw(new Line2D.Double(leftBorder, bottomBorder, width, bottomBorder));
    g2D.draw(new Line2D.Double(leftBorder, 0, leftBorder, bottomBorder));
  }

  private void labelStreets(Graphics2D g2D, int height, int streetCount, int bottomBorder) {

    // label the axis
    String[] label = {"S","t","r","e","e","t"};
    for(int i = 0, y = 10; i < label.length; i++, y++) {
      g2D.drawString(String.valueOf(label[i]), 20, ((height / 2) - 100 + (y * 10)));
    }
    // g2D.drawString("Streets", 20, ((height / 2) + 5));

    // label the Streets
    for(int y = 1, i = 1; y <= streetCount; y++, i++) {
      g2D.drawString(String.valueOf(i), 50, (bottomBorder - (y * 50)));
    }
  }

  private void labelAvenues(Graphics2D g2D, int height, int width, int avenueCount, int leftBorder) {

    // label the axis
    g2D.drawString("Avenue", ((width / 2) + 5), (height - 20));

    // label the Avenues
    for(int x = 1, i = 1; x <= avenueCount; x++, i++) {
      g2D.drawString(String.valueOf(i), (leftBorder + (x * 50)), (height - 50));
    }
  }

  private void drawRobots(Graphics2D g2D, int leftBorder, int bottomBorder) {
    g2D.setStroke(robot);

    // run through ArrayList of robots to determine and paint their positions
    for(ur_Robot bot : ur_Robot.robotTracker) {
      int street = bot.getStreet();
      int avenue = bot.getAvenue();

      // display active robots in red
      if(bot.getPoweredOn()) {
        g2D.setColor(Color.RED);

        // orient the robot with the point in the direction of travel
        if(bot.getDirection() == North) {
          drawRobotFacingNorth(g2D, street, avenue, leftBorder, bottomBorder);
        }
        else if(bot.getDirection() == South) {
          drawRobotFacingSouth(g2D, street, avenue, leftBorder, bottomBorder);
        }
        else if(bot.getDirection() == East) {
          drawRobotFacingEast(g2D, street, avenue, leftBorder, bottomBorder);
        }
        else if(bot.getDirection() == West) {
          drawRobotFacingWest(g2D, street, avenue, leftBorder, bottomBorder);
        }
      }

      // display inactive robots in grey
      else {
        g2D.setColor(Color.GRAY);

        // orient the robot with the point in the direction of travel
        if(bot.getDirection() == North) {
          drawRobotFacingNorth(g2D, street, avenue, leftBorder, bottomBorder);
        }
        else if(bot.getDirection() == South) {
          drawRobotFacingSouth(g2D, street, avenue, leftBorder, bottomBorder);
        }
        else if(bot.getDirection() == East) {
          drawRobotFacingEast(g2D, street, avenue, leftBorder, bottomBorder);
        }
        else if(bot.getDirection() == West) {
          drawRobotFacingWest(g2D, street, avenue, leftBorder, bottomBorder);
        }
      }
    }
  }

  private void drawIntersections(Graphics2D g2D, int leftBorder, int bottomBorder) {
    g2D.setStroke(artifacts);

    // run through ArrayList of intersections to determine
    // and paint their attributes
    for(InterestingIntersections corner : ur_Robot.world.intersections) {
      int street = corner.getStreet();
      int avenue = corner.getAvenue();
      int beepers = corner.getBeeperCount();

      // check if beepers are present
      if(corner.getBeeperCount() > 0) {
        drawBeepers(g2D, street, avenue, beepers, leftBorder, bottomBorder);
      }

      // check if wall is present to the North
      if(corner.getWallToTheNorth()) {
        drawWallToTheNorth(g2D, street, avenue, leftBorder, bottomBorder);
      }

      // check if wall is present to the East
      if(corner.getWallToTheEast()) {
        drawWallToTheEast(g2D, street, avenue, leftBorder, bottomBorder);
      }
    }
  }

  private void drawBeepers(Graphics2D g2D, int street, int avenue, int beepers, int leftBorder, int bottomBorder) {
    // center beeper on intersection by offsetting drawing box
    int beeperLeftOffset = leftBorder - 5;
    int beeperBottomOffset = bottomBorder -5;
    // display beeper count to the top right of the beeper
    int countLeftOffset = leftBorder + 10;
    int countBottomOffset = bottomBorder - 10;

    // set beeper GUI characteristics
    g2D.setColor(Color.BLUE);

    // draw beeper
    g2D.draw(new Ellipse2D.Double((beeperLeftOffset + (avenue * 50)), (beeperBottomOffset - (street * 50)), 10, 10));
    // label beeper count
    g2D.drawString(String.valueOf(beepers), (countLeftOffset + (avenue *50)), (countBottomOffset - (street * 50)));
  }

  private void drawRobotFacingNorth(Graphics2D g2D, int street, int avenue, int leftBorder, int bottomBorder) {
    g2D.draw(new Line2D.Double((leftBorder + (avenue * 50) - 10), (bottomBorder - (street * 50) + 10), (leftBorder + (avenue * 50) + 10),
                               (bottomBorder - (street * 50) + 10)));
    g2D.draw(new Line2D.Double((leftBorder + (avenue * 50) - 10), (bottomBorder - (street * 50) + 10), (leftBorder + (avenue * 50)),
                               (bottomBorder - (street * 50) - 17.3)));
    g2D.draw(new Line2D.Double((leftBorder + (avenue * 50)), (bottomBorder - (street * 50) - 17.3), (leftBorder + (avenue * 50) + 10),
                               (bottomBorder - (street * 50) + 10)));
  }

  private void drawRobotFacingSouth(Graphics2D g2D, int street, int avenue, int leftBorder, int bottomBorder) {
    g2D.draw(new Line2D.Double((leftBorder + (avenue * 50) - 10), (bottomBorder - (street * 50) - 10), (leftBorder + (avenue * 50) + 10),
                               (bottomBorder - (street * 50) - 10)));
    g2D.draw(new Line2D.Double((leftBorder + (avenue * 50) - 10), (bottomBorder - (street * 50) - 10), (leftBorder + (avenue * 50)),
                               (bottomBorder - (street * 50) + 17.3)));
    g2D.draw(new Line2D.Double((leftBorder + (avenue * 50)), (bottomBorder - (street * 50) + 17.3), (leftBorder + (avenue * 50) + 10),
                               (bottomBorder - (street * 50) - 10)));
  }

  private void drawRobotFacingEast(Graphics2D g2D, int street, int avenue, int leftBorder, int bottomBorder) {
    g2D.draw(new Line2D.Double((leftBorder + (avenue * 50) - 10), (bottomBorder - (street * 50) - 10), (leftBorder + (avenue * 50) - 10),
                               (bottomBorder - (street * 50) + 10)));
    g2D.draw(new Line2D.Double((leftBorder + (avenue * 50) - 10), (bottomBorder - (street * 50) - 10), (leftBorder + (avenue * 50) + 17.3),
                               (bottomBorder - (street * 50))));
    g2D.draw(new Line2D.Double((leftBorder + (avenue * 50) + 17.3), (bottomBorder - (street * 50)), (leftBorder + (avenue * 50) - 10),
                               (bottomBorder - (street * 50) + 10)));
  }

  private void drawRobotFacingWest(Graphics2D g2D, int street, int avenue, int leftBorder, int bottomBorder) {
    g2D.draw(new Line2D.Double((leftBorder + (avenue * 50) + 10), (bottomBorder - (street * 50) - 10), (leftBorder + (avenue * 50) + 10),
                               (bottomBorder - (street * 50) + 10)));
    g2D.draw(new Line2D.Double((leftBorder + (avenue * 50) + 10), (bottomBorder - (street * 50) - 10), (leftBorder + (avenue * 50) - 17.3),
                               (bottomBorder - (street * 50))));
    g2D.draw(new Line2D.Double((leftBorder + (avenue * 50) - 17.3), (bottomBorder - (street * 50)), (leftBorder + (avenue * 50) + 10),
                               (bottomBorder - (street * 50) + 10)));
  }

  private void drawWallToTheNorth(Graphics2D g2D, int street, int avenue, int leftBorder, int bottomBorder) {
    // set wall GUI characteristics
    g2D.setColor(Color.BLACK);
    int offset = 25;

    // draw the wall halfway down the block
    g2D.draw(new Line2D.Double((leftBorder + (avenue * 50) - offset), (bottomBorder - (street * 50) - offset), (leftBorder + (avenue * 50) + offset),
                               (bottomBorder - (street * 50) - offset)));
  }

  private void drawWallToTheEast(Graphics2D g2D, int street, int avenue, int leftBorder, int bottomBorder) {
    // set wall GUI characteristics
    g2D.setColor(Color.BLACK);
    int offset = 25;

    // draw the wall halfway down the block
    g2D.draw(new Line2D.Double((leftBorder + (avenue * 50) + offset), (bottomBorder - (street * 50) + offset), (leftBorder + (avenue * 50) + offset),
                               (bottomBorder - (street * 50) - offset)));
  }
}
