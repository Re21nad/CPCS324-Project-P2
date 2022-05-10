
package phase2;
/**
      * This is the Graph class.
      * @author Daliah algarawi - Taif AlAmoudi - Renad FilFilan.
      */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Graph {
    
    int verticesNo;
    //total number of adjList in the graph
    int edgeNo;
    // is graph directed or not.
    boolean isDigraph;
    //list of vertices of this graph
    public Vertex vertices[];
    // matrix of edges
  
    Edge [][] adjMatrix;
    
    int userVerticesNo;
    
    int userEdgesNo;

    public Graph() {
        vertices = new Vertex[verticesNo];
    }

    public Graph(int veticesNo, int edgeNo, boolean isDigraph) {
        this.userVerticesNo = veticesNo;
        this.userEdgesNo = edgeNo;
        this.isDigraph = isDigraph;
        vertices =  new Vertex[userVerticesNo];
        adjMatrix = new Edge[userVerticesNo][userVerticesNo];}
    
    public void makeGraph( int numberOfVertices, int numberOfEdges ) {
        Random random = new Random();
        for (int i = 0; i < numberOfVertices - 1; i++) {
            int randomWeight = random.nextInt(50)+1;
            addEdge(i, i + 1, randomWeight); 
        }
        
        //create edges.
        while (edgeNo < numberOfEdges) {
            int source = random.nextInt(numberOfVertices);
            int Destination = random.nextInt(numberOfVertices);
            
            int weight = random.nextInt(50)+1;
            if (source == Destination || adjMatrix[source][Destination]!=null) { // to avoid self loops and duplicate edges
               continue;
            } else {
                addEdge(source, Destination, weight);
            }
        }}



    public int getVerticesNo() {
        return this.verticesNo;
    }

    public int getEdgeNo() {
        return this.edgeNo;
    }

    public boolean getDirected() {
        return this.isDigraph;
    }

    public void readGraphFromFile(File inputfile) throws FileNotFoundException {
       Scanner input = new Scanner(inputfile);
       input.next();
       this.isDigraph = (input.next().contains("1"));
       userVerticesNo= input.nextInt();
       userEdgesNo= input.nextInt();
       // make a 2DIM array to create the matrix 
       vertices= new Vertex[userVerticesNo];
       adjMatrix = new Edge[userVerticesNo][userVerticesNo];
       
        while(edgeNo<userEdgesNo){
            char source = input.next().charAt(0);
            char destination = input.next().charAt(0);
            addEdge(source-65,destination-65, input.nextInt());
            addVertLabel(vertices[source-65]);
            addVertLabel(vertices[destination-65]);}}
    
    public Edge addEdge(int v, int u, int w) {
        if(vertices[v]==null){
                Vertex tempVert = new Vertex();
                vertices[v]= tempVert;
                tempVert.position=v;
                verticesNo++;
        }
         if(vertices[u]==null){
                Vertex tempVert = new Vertex();
                vertices[u]= tempVert;
                tempVert.position=u;
                verticesNo++;
        }
        
        adjMatrix[v][u] = new Edge(vertices[v], vertices[u], w);
        edgeNo++;
        if (!isDigraph) {
            adjMatrix[u][v] = new Edge(vertices[u], vertices[v], w);
            edgeNo++;
        }
        return adjMatrix[v][u];
    }//addEdge

   

    /**
     * add the label if not exist otherwise return false
     *  vLabel vertex that will be adding.
     *  true if vLable was added before.
     */
    public void addVertLabel(Vertex vLabel) {
        vLabel.label = (char)(65+vLabel.position);
        
    }
    
    public void replaceNullValues(){
        for (int i = 0; i < verticesNo; i++) {
            for (int j = 0; j < verticesNo; j++) {
                if(adjMatrix[i][j]==null)
                    adjMatrix[i][j]= new Edge(null, null, 99999);
                if(i==j)
                    adjMatrix[i][j]= new Edge(null, null, 0);   
            }
            
        }
    
    }
}//end class
