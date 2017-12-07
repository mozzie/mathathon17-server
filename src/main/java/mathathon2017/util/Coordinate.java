package mathathon2017.util;

public class Coordinate {

    public int x, y;

    public Coordinate(){
    }
    
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public String toString() {
        return "{\"x\""+x+",\"y\":"+y+"}";
    }
    public Coordinate copy() {
        return new Coordinate(x,y);
    }
}
