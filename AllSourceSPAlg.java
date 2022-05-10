
package phase2;
/**
      * This is the AllSourceSPAlg class.
      * @author Daliah algarawi - Taif AlAmoudi - Renad FilFilan.
      */

import java.util.concurrent.TimeUnit;

public class AllSourceSPAlg extends ShortestPathAlgorithm {

    public int INFINITY = 99999;
   
    public AllSourceSPAlg(Graph graph) {
        this.graph = graph;
        
    }

    // Implementing floyd-warshall algorithm
    public void computeFloyedWarshalAlg(boolean printM) {
       
        int verticesNo = graph.verticesNo;
        
        //start time
        long StartTime = System.nanoTime();
       
        // v = source
        // u = destination 
        int v, u, k;
       
        // Adding vertices individually
        for (k = 0; k < verticesNo; k++) {
           
            for (v = 0; v < verticesNo; v++) {
                for (u = 0; u < verticesNo; u++) {
                    
                    if (graph.adjMatrix[v][k].weight != INFINITY && graph.adjMatrix[k][u].weight != INFINITY) {
                        graph.adjMatrix[v][u].weight = Math.min( graph.adjMatrix[v][u].weight,graph.adjMatrix[v][k].weight + graph.adjMatrix[k][u].weight );
                    }
                }
            }
            if (printM) {
                System.out.println("step: " + (k + 1));
                printMatrix();
            }
        }

        //finish time of the algorithm
        long FinishTime = System.nanoTime();
        //print the total time consumed by the algorithm
        System.out.println("taken time (floyd-warshall algorithm): " + (FinishTime - StartTime) + "ns (" + TimeUnit.NANOSECONDS.toMillis(FinishTime - StartTime) + "ms)");

    }//end computeFloyedWarshalAlg



    public void printMatrix() {
        //get the graph size 
        

        //------ Print Header ----------------
        System.out.print("   ");
        for (int i = 0; i < graph.verticesNo; i++) {
            System.out.printf("%-4s", (char) (i + 65));
        }
        System.out.println(); // Start new line

        // Print line
        for (int i = 0; i < graph.verticesNo + 1; i++) {
            System.out.print("----");
        }
        System.out.println();//new line

        //---- Print Matrix -------------------
        for (int i = 0; i < graph.verticesNo; i++) {
            System.out.print((char) (i + 65) + "| ");
            for (int j = 0; j < graph.verticesNo; j++) {
                if (graph.adjMatrix[i][j].weight == 99999) {
                    System.out.printf("%-3s,","∞");
                } else {
                    System.out.printf("%-3d,", graph.adjMatrix[i][j].weight);
                }
            }
            System.out.println();
        }
        System.out.println("\n");
    
    }//addEdge

}//end class
