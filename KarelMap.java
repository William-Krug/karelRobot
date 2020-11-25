/*  Author: William Krug
    Class: CSCI 1203-90
    Assignment: Assignment #8 & Assignment #9
    Purpose: create GUI showing a Karel World, Robots, Beepers and Walls
    FILE: KarelMap.java  */

package karel;

import javax.swing.JFrame;

public class KarelMap extends JFrame {
  public KarelMap(String s) {
    super(s);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
  }

  public void repaint() {
    this.getContentPane().repaint();
  }
}
