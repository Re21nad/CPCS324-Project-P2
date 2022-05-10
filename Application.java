// The main class
package phase2;

 /**
      * This is the main class.
      * @author Daliah algarawi - Taif AlAmoudi - Renad FilFilan.
      */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Application {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        input = new Scanner(System.in); // Create a scanner.
        Graph g;
        boolean printM = true;
        int selection, graph_type, AlgorithmType;
        int VerticesNo = 0, EdgesNo = 0;
        System.out.println(" \t\t\tWelcome.");
        // ask the user to enter the choise 
        System.out.println("------------------------------------------------------------");
        System.out.println("Please, choose how to create the graph:");
        System.out.println("\t 1- Random graph");
        System.out.println("\t 2- Read graph from file");
        graph_type = getSelection();
        //Graph type
        System.out.println("choose algorithm:");
        System.out.println("\t 1- Dijkstra algorithm");
        System.out.println("\t 2- floyd-warshall algorithm");
        AlgorithmType = getSelection(); // Take the number.
        if (graph_type == 1) {
            //Random graph
            printM = false;
            System.out.println("Please choose the number of Vertices and Edges:");
            // Dijkstra Algorithm
            if (AlgorithmType == 1) {
                DijkstraRandomValues(); // Take the values.
                do {
                    selection = input.nextInt();
                    if (selection < 1 || selection > 5) {
                        System.out.println("Invalid input! Try again");
                    }
                } while (selection < 1 || selection > 5);
                switch (selection) {
                    case 1:
                        VerticesNo = 5000;
                        EdgesNo = 25000;
                        break;
                    case 2:
                        VerticesNo = 10000;
                        EdgesNo = 50000;
                        break;
                    case 3:
                        VerticesNo = 15000;
                        EdgesNo = 75000;
                        break;
                    case 4:
                        VerticesNo = 20000;
                        EdgesNo = 100000;
                        break;
                    default:
                        VerticesNo = 25000;
                        EdgesNo = 125000;
                }//end swich
                
                System.out.println("Is your graph directed or not?");
                System.out.println("\t 1- Yes");
                System.out.println("\t 2- No");
                selection = getSelection(); 
                g = new Graph(VerticesNo, EdgesNo, (selection == 1)); // Create a graph.
                g.makeGraph(VerticesNo,EdgesNo); // Make a graph.
                g.replaceNullValues();
                SingleSourceSPAlg dijkstra = new SingleSourceSPAlg(g,g.vertices[0]); // Compute the dijkstra.
                dijkstra.computeDijkstraAlg();
            } else {
                // Floyd-Warshall Algorithm
                FloydRandomValues(); // Take the values.
                do {
                    selection = input.nextInt();
                    if (selection < 1 || selection > 5) {
                        System.out.println("Invalid input! Try again");
                    }
                } while (selection < 1 || selection > 5);
                switch (selection) {
                    case 1:
                        VerticesNo = 2000;
                        EdgesNo = 10000;
                        break;
                    case 2:
                        VerticesNo = 3000;
                        EdgesNo = 15000;
                        break;
                    case 3:
                        VerticesNo = 4000;
                        EdgesNo = 20000;
                        break;
                    case 4:
                        VerticesNo = 5000;
                        EdgesNo = 250000;
                        break;
                    default:
                        VerticesNo = 6000;
                        EdgesNo = 30000;
                }//end swich
                System.out.println("Is your graph directed or not?");
                System.out.println("\t 1- Yes");
                System.out.println("\t 2- No");
                selection = getSelection();
                g = new Graph(VerticesNo, EdgesNo, (selection == 1)); // Create a graph.
                g.makeGraph(VerticesNo,EdgesNo); // Make a graph.
                g.replaceNullValues();
                AllSourceSPAlg floyd = new AllSourceSPAlg(g); // Compute the floyd-warshall.
                floyd.computeFloyedWarshalAlg(printM);
            }
        } else {
            // Implement the graph using read from file.
            g = new Graph();
            File newInput = new File("Graph.txt");
            g.readGraphFromFile(newInput);
            g.replaceNullValues();
            if (AlgorithmType == 1) {
                SingleSourceSPAlg dijkstra = new SingleSourceSPAlg(g,g.vertices[0]);
                dijkstra.computeDijkstraAlg();
                dijkstra.displayShortestPath(g);
                
            } else {
               
                AllSourceSPAlg floyd = new AllSourceSPAlg(g);
                floyd.computeFloyedWarshalAlg(printM);
                
            }
        }

    }//end main
// ************************************ DijkstraRandomValues ************************************ 
    public static void DijkstraRandomValues() {
        System.out.println("------------------------------------------------------------");
        System.out.println("(n): number of vertices and (m): number of edges)");
        System.out.println(" 1: n= 5000  and  m= 25000");
        System.out.println(" 2: n= 10000 and  m= 50000");
        System.out.println(" 3: n= 15000 and  m= 75000");
        System.out.println(" 4: n= 20000 and  m= 100000");
        System.out.println(" 5: n= 25000 and  m= 125000");
        System.out.println("------------------------------------------------------------");

    }

// ************************************ FloydRandomValues ************************************ 
    public static void FloydRandomValues() {
        System.out.println("------------------------------------------------------------");
        System.out.println("(n): number of vertices and (m): number of edges)");
        System.out.println(" 1: n= 2000  and  m= 1000");
        System.out.println(" 2: n= 3000  and  m= 15000");
        System.out.println(" 3: n= 4000  and  m= 20000");
        System.out.println(" 4: n= 5000  and  m= 25000");
        System.out.println(" 5: n= 6000  and  m= 30000");
        System.out.println("------------------------------------------------------------");

    }

// ************************************ getSelection ************************************     
    public static int getSelection() {
        int selection;
        do {
            selection = input.nextInt();
            if (selection != 1 && selection != 2) {
                System.out.println("Invalid input! Try again");
            }
        } while (selection != 1 && selection != 2);
        return selection;
    }
}//end class
