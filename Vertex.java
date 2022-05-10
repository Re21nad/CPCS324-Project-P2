
package phase2;
import java.util.List;
/**
      * This is the Vertex class.
      * @author Daliah algarawi - Taif AlAmoudi - Renad FilFilan.
      */
class Vertex implements Comparable<Vertex> {

    //vertex label
    char label;
    //position value               
    public int position;
    //visited flag
    public boolean isVisited;
    // we use the following valraible for Dijkstra's algorithm
    //distance value               
    public int distance;
    //parent of this vartex
    Vertex parent;

    //constractor
    Vertex(char label) {
        this.label = label;
        this.isVisited = false;
        this.position = 0;
        this.distance = Integer.MAX_VALUE;
        this.parent = null;
    }

    //constractor for default vertex.
    Vertex() {
        this.label = '0';
        this.isVisited = false;
        this.position = 0;
        this.distance = Integer.MAX_VALUE;
        this.parent = null;
    }

    // get position of the current vertex.
    /**
     *
     *  v list of all vertices.
     */
    public int getVertPos(List<Vertex> v) {
        return position;
    }

    // get label of the current vertex.
    public char getLabel() {
        return this.label;
    }

    @Override
    public int compareTo(Vertex o) {
        return (int) (this.distance - o.distance);
    }

    @Override
    public String toString() {
        return this.label+"";
    }
}//end vertax class.
