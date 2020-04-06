/**
 * this class is showing a coordinate and has methods for
 * using in a HashSet class
 *  @author Setayesh
 */
public class Point {
    int x, y;
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * this method don't let repeated objects add to Set
     * @param o the entry object
     * @return boolean output
     */
    @Override
    public boolean equals(Object o) {
        if (o.hashCode()==this.hashCode())
            return true;
        else
            return false;
    }

    /**
     * Set can't be define just by equal method so
     * we use hashcode method to have different objects in Set
     * @return the integer of unique code of unique object of Set
     */
    @Override
    public int hashCode() {
        return Integer.parseInt(x + "" + y);//the input must be string while x , y are ints so we use "".
    }
}
