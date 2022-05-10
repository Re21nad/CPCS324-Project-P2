
package phase2;
/**
      * This is the Edge class.
      * @author Daliah algarawi - Taif AlAmoudi - Renad FilFilan.
      */
public class Edge {
    // from
    Vertex v;
    //to
    Vertex u;
    //wight
    int weight;
    
    //constractor
    public Edge(Vertex v, Vertex u, int weight) {
    this.v = v;
    this.u = u;
    this.weight = weight;
  }
    
}//end class
