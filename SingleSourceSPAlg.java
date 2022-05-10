
package phase2;
/**
      * This is the SingleSourceSPAlg class.
      * @author Daliah algarawi - Taif AlAmoudi - Renad FilFilan.
      */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

public class SingleSourceSPAlg extends ShortestPathAlgorithm {

    //source vertex
    Vertex source;
    //Priority queue to queue vertices w.r.t least distance(distance).
    PriorityQueue<Vertex> vertexQueue = new PriorityQueue<>();
    //list to store results(shortest paths)
    List<String> resultsList;

    //constractor
    /**
     *  source chosen source
     */
    public SingleSourceSPAlg(Graph graph ,Vertex source) {
        this.source = source;
        this.graph= graph;
        resultsList = new ArrayList<>();
    }

    // Implementing Dijkstra algorithm
    public void computeDijkstraAlg() {
        //start time
        long StartTime = System.nanoTime();
        //Starting vertex distance should be 0
        source.distance = 0;
        //add source vertex to the queue
        vertexQueue.add(source);
        //run until queue is not empty
        while (!vertexQueue.isEmpty()) {
            //Get vertex from the top of queue 
            //i.e. vertex with least distance   
            Vertex actualVertex = vertexQueue.peek();
            //get adjacent vertices through connected edges from adjMatrix
            int weight;
            Vertex u;
            for (int i = 0; i < graph.adjMatrix.length; i++) {
                if (graph.adjMatrix[actualVertex.position][i].weight == 99999
                        || graph.adjMatrix[actualVertex.position][i].weight == 0) {
                    continue;
                }
                u = graph.vertices[i];
                weight = graph.adjMatrix[actualVertex.position][i].weight;
                if (!u.isVisited) {
                    if (actualVertex.distance + weight < u.distance) {
                        u.distance = actualVertex.distance + weight;
                        //also update its parent 
                        u.parent = actualVertex;
                        vertexQueue.add(u);
                    }
                }//end if
            }//end for
            //remove vertex from queue
            vertexQueue.poll();
            //mark vertex as visited                  
            actualVertex.isVisited = true;
        }//end while
        //finish time of the algorithm
        long FinishTime = System.nanoTime();
        //print the total time consumed by the algorithm
        System.out.println("taken time (Dijkstra algorithm): " + (FinishTime - StartTime) + "ns (" + TimeUnit.NANOSECONDS.toMillis(FinishTime - StartTime) + "ms)");
    }//end computeDijkstraAlg

    //Function to return the shortest path
    List<Vertex> shortestPath(Vertex v) {
        //list to hold path
        List<Vertex> shortPath = new ArrayList<>();
        //Trace route from the 'v' to the source vertex
        shortPath.add(v);
        while (v.parent != null) {
            shortPath.add(v.parent);
            v = v.parent;
        }
        //reverse the list to correct the order
        Collections.reverse(shortPath);

        //return list
        return shortPath;
    }

    //display shortest path for given graph in parameter.
    public void displayShortestPath(Graph g) {
        //print results:
        List<Vertex> path;
        resultsList.add("Shortest Paths:");
        System.out.println("Shortest Paths:");
        resultsList.add("taken source: " + g.vertices[0].getLabel());
        System.out.println("taken source: " + g.vertices[0].getLabel());
        for (int i = 1; i < g.getVerticesNo(); i++) {
            path = shortestPath(g.vertices[i]);
            resultsList.add(String.format("Shortest Paths from %s to %s :", g.vertices[0].getLabel(), g.vertices[i].getLabel()));
            System.out.println(String.format("Shortest Paths from %s to %s :", g.vertices[0].getLabel(), g.vertices[i].getLabel()));
            resultsList.add(path.toString());
            System.out.println(path);
            resultsList.add("distance: " + g.vertices[i].distance);
            System.out.println("distance: " + g.vertices[i].distance);
            if (i != g.getVerticesNo() - 1) {
                resultsList.add("------------------------------------------------------------");
                System.out.println("------------------------------------------------------------");
            }
        }
    }//end

    

}
