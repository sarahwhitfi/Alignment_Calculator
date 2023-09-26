import java.util.*;
import java.io.*;


public class MainDijkstra {

	String s1, s2;						//strings for alignment
	int gap, mismatch, match;			//scoring values for alignment
	private boolean alignment;			//whether the format of the input is alignment or general
	private int startVertex;			//starting vertex for search
    private int cost[];                 //cost of each vertex
    private int path[];					//previous vertex in the path for each vertex
    private PriorityQueue<Vertex> pq;   
    private List<Integer> complete;     //list of finalized vectors
    List<List<Vertex>> neighbors;       //list of vertexes, each containing a list of their neighbors


  //constructor for the general case
    public MainDijkstra(int nVertex, int startVertex, boolean alignment)		
    {
    	this.alignment = alignment;
    	this.startVertex = startVertex;
        cost = new int[nVertex];
        path = new int[nVertex];
        pq = new PriorityQueue<Vertex>(nVertex, new Vertex());
        complete = new ArrayList<Integer>();
    }
    
    //constructor for the alignment case
    public MainDijkstra(int nVertex, boolean alignment, String s1, String s2, int match, int mismatch, int gap)		
    {
    	this.s1 = s1;
    	this.s2 = s2;
    	this.match = match;
    	this.gap = gap;
    	this.mismatch = mismatch;
    	this.alignment = alignment;
    	this.startVertex = 0;
        cost = new int[nVertex];
        path = new int[nVertex];
        pq = new PriorityQueue<Vertex>(nVertex, new Vertex());
        complete = new ArrayList<Integer>();
    }


    public static void main(String[] args) throws Exception		//main driving function
    {
    	String s1, s2;
    	boolean alignment;
    	int match, mismatch, gap;
    	MainDijkstra d;
    	
    	//read lines, skipping lines without data
        Scanner sc = new Scanner(new File(args[0]));
        sc.nextLine();									
        if(sc.nextInt() == 0)
        	alignment = false;
        else
        	alignment = true;
        
        sc.nextLine();		
        sc.nextLine();	
        
        //collect general input
        if(!alignment)
        {
	        Vertex.set_nVertex(sc.nextInt());				//set number of vertices
	        sc.nextLine(); 
	        sc.nextLine(); 
	        int startVertex = Integer.parseInt(sc.nextLine());	
	        sc.nextLine();
	        d = new MainDijkstra(Vertex.get_nVertex(), startVertex, alignment);   
        }
        //collect alignment input
        else
        {
        	s1 = sc.next();
        	s2 = sc.next();
        	sc.nextLine();
        	sc.nextLine();
        	match = sc.nextInt();
        	mismatch = sc.nextInt();
        	gap = sc.nextInt();
        	Vertex.set_nVertex((s1.length() + 1) * (s2.length() + 1));		//calculate size of edit graph to determine how many vertices to manage
        	d = new MainDijkstra(Vertex.get_nVertex() , alignment, s1, s2, match, mismatch, gap);
        	
        }
        
        
	       
        //allocating memory for lists of neighboring vertexes for both cases
        d.neighbors = new ArrayList<List<Vertex>>();  
        for(int i = 0; i < Vertex.get_nVertex(); i++)
        {
            List<Vertex> x = new ArrayList<Vertex>();
            d.neighbors.add(x);
        }

        //collect edge input for general case
        if(!alignment)
        {
	        while(sc.hasNext())
	        {
	            try
	            {
	                int parent = sc.nextInt();
	                int child = sc.nextInt();
	                int cost = sc.nextInt();
	                d.neighbors.get(parent).add(new Vertex(child, cost));
	            } catch (Exception e) { break; }		// prevents crashing from any additional newlines at the end of input file
	        }
        }
        
        //generate edges for alignment option
        else
        	d.generateAlignmentEdges();
        
        d.runDijkstra(d.neighbors, d.startVertex);	//run dijkstra on the vertexes
        
        if(!d.alignment)			//print the results
        	d.printPaths();								
        else
        	d.printAlignment();
    
    }
    
    
    
    //generate edges for the alignment option
    public void generateAlignmentEdges()
    {
    	for(int i = 0; i < Vertex.get_nVertex(); i++)
    	{
			int parent = i;
    		
    		//option to shift down (always creates a gap)
    		int child = i + s1.length() + 1;
    		if(child < Vertex.get_nVertex())	//checks that the lower row isn't past the end of the graph
    			neighbors.get(parent).add(new Vertex(child, gap));
    		
    		//option to shift right (always creates a gap)
    		child = i + 1;
    		if(getRow(i) == getRow(child))	//checks that the current vertex isn't the last in its row
    			neighbors.get(parent).add(new Vertex(child, gap));
    		
    		//option for diagonal (match or mismatch)
    		child = i + 2 + s1.length();
    		if(child < Vertex.get_nVertex() && getRow(i) + 1 == getRow(child))	//must be within the valid range of vertices and on the next row
    		{
        		if(s1.charAt(getCol(child) - 1) == s2.charAt(getRow(child) - 1))	//check for matching characters
        			neighbors.get(parent).add(new Vertex(child, match));
        		else
        			neighbors.get(parent).add(new Vertex(child, mismatch));	
    		}
    	}
    }
    
    
    
    //find corresponding row in the edit graph given the vertex index
    public int getRow(int n)
    {
    	int row = n / (s1.length() + 1);
    	return row;
    }
    
    //find corresponding column in the edit graph given the vertex index
    public int getCol(int n)
    {
    	int col = n % (s1.length() + 1);
    	return col;
    }
    
    
    
    public void runDijkstra(List<List<Vertex>> neighbors, int startVertex)	// does majority of dijkstra operations
    {
        this.neighbors = neighbors;

        for(int i = 0; i < Vertex.get_nVertex(); i++)		//initialize each vertex's cost to infinity
        {
        	cost[i] = Integer.MAX_VALUE;
        	path[i] = -1;
        }

        cost[startVertex] = 0;								//initialize the starting vertex's cost to 0
        pq.add(new Vertex(startVertex, 0));					//add starting vertex to pq

        while(complete.size() < Vertex.get_nVertex())		//continue while not all vertexes are complete
        {
            if(pq.isEmpty())								//stop if you run out of reachable incomplete neighbors
                break;

            int current = pq.remove().get_vertex();		
            
            if(complete.contains(current))					//if current vertex is already on the list, stop after removing it from the pq
                continue;
            
            complete.add(current);
            
            add_Neighbors(current);							//add all incomplete neighbors to the pq
            
        }
    }


    public void add_Neighbors(int current)			//add neighbors of the current vertex to the pq
    {
       
        for (int i = 0; i < neighbors.get(current).size(); i++)	
        {
            Vertex adj = neighbors.get(current).get(i);
            
            if(!complete.contains(adj.get_vertex()))
            {
                if((cost[current] + adj.get_cost()) < cost[adj.get_vertex()])		//calculates and compares new distance to original distance
                {
	            	cost[adj.get_vertex()] = cost[current] + adj.get_cost();
	            	path[adj.get_vertex()] = current;
	            	pq.add(new Vertex(adj.get_vertex(), cost[adj.get_vertex()]));	//add new vertex to pq if cost was updated
                }
            }
        }
    }
    
    
    
    public void printPaths()		//print results for general case
    {
    	System.out.println("\n\tResults with source = v" + startVertex + ":\n");
    	for(int i = 0; i < Vertex.get_nVertex(); i++)
    	{
    		ArrayList<Integer> tracePath = new ArrayList<Integer>();		//list of vertexes that form a path to the vertex at the index i

    		if(cost[i] != Integer.MAX_VALUE)	//if the vertex has been visited, build the path
			{
        		if(i != startVertex)
        			buildPath(path[i], tracePath, startVertex);		//builds path recursively
        		
        		tracePath.add(i);	//adds last element
        		
    			System.out.println("\t\tShortest path to vertex v" + i + " is " + cost[i]);
    			System.out.println("\t\t\tPath is: " + tracePath);

			}
    		else
    			System.out.println("\t\tVertex v" + i + " is unreachable!\n\t\t\tPath does not exist!");
    		
    	}
    }
    
    
    //recursively builds path to ensure the arraylist is in the correct order
    public void buildPath(int x, ArrayList<Integer> tracePath, int startVertex)
	{
    	
		if(x == startVertex)	//don't look for the path of the startVertex
		{
			tracePath.add(x);
			return;
		}
		
		buildPath(path[x], tracePath, startVertex);		//calls buildPath on the parent of x
		tracePath.add(x);								//add the current node to the list after all its parents have been added
	}
    
   
    //print alignment option results
    public void printAlignment()
    {
    	int i = Vertex.get_nVertex() - 1;		//find the index of the vertex in the opposite corner
    	if(cost[i] != Integer.MAX_VALUE)		//if it is reachable, print the alignment information
    	{
    		String a1 = "", a2 = "";										//initialize alignment strings to empty strings
    		ArrayList<Integer> tracePath = new ArrayList<Integer>();
    		buildPath(path[i], tracePath, 0);								//build path to the vertex
    		tracePath.add(i);
    		System.out.print("\n\tIn the form (row, column), the optimal alignment path is: \n\t");
    		for(int j = 0; j < tracePath.size(); j++)
    			System.out.print("(" + getRow(tracePath.get(j)) + ", " + getCol(tracePath.get(j)) + ") ");	//print row and column of each vertex
    		
    		for(int j = 1; j < tracePath.size(); j++)	//create alignment strings
    		{
    			//if matched or mismatched
    			if(getRow(tracePath.get(j-1)) + 1 == getRow(tracePath.get(j)) && getCol(tracePath.get(j-1)) + 1 == getCol(tracePath.get(j)))
    			{
    				a1 = a1 + s1.charAt(getCol(tracePath.get(j)) - 1);
    				a2 = a2 + s2.charAt(getRow(tracePath.get(j)) - 1);
    			}
    			//if shifted down
    			else if(getRow(tracePath.get(j-1)) + 1 == getRow(tracePath.get(j)))
    			{
    				a1 = a1 + "-";
    				a2 = a2 + s2.charAt(getRow(tracePath.get(j)) - 1);    				
    			}
    			//if shifted right
    			else
    			{
    				a1 = a1 + s1.charAt(getCol(tracePath.get(j)) - 1);
    				a2 = a2 + "-";    				
    			}		
    		}
    		
    		//print alignment information
    		System.out.println("\n\n\tThe optimal alignment is: \n\t" + a1 + "\n\t" + a2);
    		System.out.println("\n\tThe alignment score is: " + cost[i]);
    		
    	}
    	else
    		System.out.println("\n\tCannot reach alignment! The edge calculation is incorrect.\n");
    }   
}



class Vertex implements Comparator<Vertex> {

    private static int nVertex;         //number of vertices
    private int vertex;                 //vertex index/name
    private int cost;                   //vertex cost for pq sorting

    public Vertex() {}

    public Vertex(int vertex, int cost)
    {
        this.vertex = vertex;
        this.cost = cost;
    }


    @Override public int compare(Vertex v1, Vertex v2) //allow comparison
    {
        if (v1.cost < v2.cost)
            return -1;
 
        if (v1.cost > v2.cost)
            return 1;
 
        return 0;
    }


    // Set/Get values


    public static void set_nVertex(int nVertex)
    {
        Vertex.nVertex = nVertex;
    }

    public static int get_nVertex()
    {
        return Vertex.nVertex;
    }

    public void set_vertex(int vertex)
    {
        this.vertex = vertex;
    }

    public int get_vertex()
    {
        return this.vertex;
    }

    public void set_cost(int cost)
    {
        this.cost = cost;
    }

    public int get_cost()
    {
        return this.cost;
    }
    
}



