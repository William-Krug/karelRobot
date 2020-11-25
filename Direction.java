// A class that provides four objects representing the four directions
// for Karel++ robots and worlds

package karel;

public class Direction{  // datatype to provide type checking, meaningful
                         // names and a specific set of legitimate values
                         // for directions
  public static final Direction North = new Direction(1);
  public static final Direction South = new Direction(2);
  public static final Direction East = new Direction(3);
  public static final Direction West = new Direction(4);

  int dir;

  private  Direction(int i)
  {
    dir = i;
  }

  public boolean equals(Direction d)
  {
    return dir == d.dir;
  }

  public int getDirection() {
    return dir;
  }

  public String toString( )
  {
    switch(dir){
                case 1:
                     return "North";
                case 2:
                     return "South";
                case 3:
                     return "East";
                case 4:
                     return "West";
                default:
                     return "Invalid";
    }
  }
}
