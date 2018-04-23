package cs146_project2;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
/**
 * Maze class to implement maze shape and BFS AND DFS
 * @author michaelhuang
 *
 */
public class Maze 
{
	//instinct variables
	private Random myRanGen; 
	private int diamension;
	private ArrayList<Room> cells;
	//constants
	static final int WHITE = 0;
	static final int GREY = 1;
	static final int BLACK = 2;	
	static final int BFS_PRINT = 3;
	static final int BFS_SHORTESTPATH = 4;
	static final int DFS_PRINT = 5;
	static final int DFS_SHORTESTPATH = 6;
	static final int ORIGINAL = 7;
	static final int STARTINDEX = 0;
	/**
	 * Constructor of Maze class
	 * @param diamension
	 */
	public Maze(int diamension)
	{
		this.diamension = diamension;
		this.myRanGen = new Random(0);
		this.cells = new ArrayList<Room>();
		//initialized each room with the index as the data
		for(int i = 0; i < this.diamension * this.diamension; i++)
		{
			Room cell = new Room(i);
			this.cells.add(cell);
		}
		buildAllWalls();   //initialied all walls
	}
	/**
	 * return the random double value between o - 1 
	 * @return
	 */
	public double myrandom()
	{
		return this.myRanGen.nextDouble(); //random in 0-1
	}
	/**
	 * build all walls of the maze except the up-left and bottom-right
	 */
	public void buildAllWalls()
	{	
		for(int row = 0; row < this.diamension; row++)   //for each row
		{
			for(int column = 0; column < this.diamension; column++)   //for each column
			{
				int index = row * this.diamension + column;	 //identify the index of each room
				if(row != 0)  //if not row 1 set the up room as the north room
				{
					cells.get(index).setNorthRoom(this.cells.get(index - this.diamension));   
					cells.get(index).buildNeighbourWall(cells.get(index).getNorthRoom());
				}
				else  //if the first row
				{
					if(column > 0) // and not the first room
					{
						cells.get(index).setNorthWall(1);  //build north wall 
					}
				}
				if(row != this.diamension - 1)    //if not the last row set the room below as the south room
				{
					cells.get(index).setSouthRoom(this.cells.get(index + this.diamension));
					cells.get(index).buildNeighbourWall(cells.get(index).getSouthRoom());
				}
				else  //if the last row
				{
					if(column < this.diamension - 1)  //and not the last room
					{
						cells.get(index).setSouthWall(1);  //build the south wall
					}
				}
				if(column != this.diamension - 1)  //if not the last column set the room on the right as the east room
				{
					cells.get(index).setEastRoom(cells.get(index + 1));
					cells.get(index).buildNeighbourWall(cells.get(index).getEastRoom());
				}
				else  //if the last column
				{
					cells.get(index).setEastWall(1);  //build the east wall
				}
				if(column != 0)  //if not the first column set the left room as the west room
				{
					cells.get(index).setWestRoom(cells.get(index - 1));
					cells.get(index).buildNeighbourWall(cells.get(index).getWestRoom());
				}
				else  //if the first column
				{ 
					cells.get(index).setWestWall(1);  //build the west wall
				}
			}
		}
		randomNeighbour();  //randomized the sequence of each neighbors list of each room
	}
	/**
	 * print all the maze: original maze, BFS, DFS
	 */
	public void printAll()
	{
		System.out.println("MAZE: ");
		print(ORIGINAL);
		System.out.println("BFS: ");
		print(BFS_PRINT);
		print(BFS_SHORTESTPATH);
		System.out.println("DFS: ");
		print(DFS_PRINT);
		print(DFS_SHORTESTPATH);
	}
	/**
	 * print each type of maze depends on the options
	 * @param printOption
	 */
	public void print(int printOption)
	{
		//the top wall
		for(int i = 0; i < this.diamension; i++) //first row
		{
			System.out.print("+"); //print corner
			if(cells.get(i).getNorthWall() == 1) //print - if the room has north wall
			{
				System.out.print("-");
			}
			else //print blank if the room does not have north wall
			{
				System.out.print(" ");
			}
		}
		System.out.print("+\n");  //last corner
		//rest of walls and labels
		int verticalWallRowCounter = 0; //the counter of the vertical wall
		int horizontalWallCounter = 0;  //the counter of the horizontal wall
		for(int row = 0; row < this.diamension; row++)  //for each row
		{
			System.out.print("|");  //the left-most wall
			for(int column1 = 0; column1 < this.diamension; column1++)  //for each column
			{
				int index = row * this.diamension + column1;	 //identify the index of each room of the vertical wall
				if(this.cells.get(verticalWallRowCounter).getEastWall() == 1)  //if the room has each wall
				{
					if(printOption == ORIGINAL)  //if the original maze
					{
						System.out.print(" |");
					}
					else if(printOption == BFS_PRINT)  //if BFS maze
					{
						BFS(STARTINDEX);
						int order = cells.get(index).getVisitedOrder();
						if(order != -1)
						{
							order %= 10;
							System.out.print(order + "|" );
						} 
						else
						{
							System.out.print(" |");
						}
					}
					else if(printOption == BFS_SHORTESTPATH)  //if the maze use BFS to find shortest path 
					{
						ArrayList<Integer> shortestPath = BFS(STARTINDEX);
						if(shortestPath.contains(index))
						{
							System.out.print("#|");
						}
						else
						{
							System.out.print(" |");
						}
					}
					else if(printOption == DFS_PRINT)  //if DFS maze
					{
						DFS(STARTINDEX);
						int order = cells.get(index).getVisitedOrder();
						if(order != -1)
						{
							order %= 10;
							System.out.print(order + "|");
						}
						else
						{
							System.out.print(" |");
						}
					}
					else if(printOption == DFS_SHORTESTPATH)  //if the maze use DFS to find shortest path 
					{
						ArrayList<Integer> shortestPath = DFS(STARTINDEX);
						if(shortestPath.contains(index))
						{
							System.out.print("#|");
						}
						else
						{
							System.out.print(" |");
						}
					}
				}
				else if(this.cells.get(verticalWallRowCounter).getEastWall() == 0)  //if the room doesn't have north wall
				{
					if(printOption == ORIGINAL)  //if the original maze
					{
						System.out.print("  ");
					}
					else if(printOption == BFS_PRINT)   //if the BFS maze
					{
						BFS(STARTINDEX);
						int order = cells.get(index).getVisitedOrder();
						if(order != -1)
						{
							order %= 10;
							System.out.print(order + " " );
						}
						else
						{
							System.out.print("  ");
						}
					}
					else if(printOption == BFS_SHORTESTPATH)  //if the maze use BFS to find shortest path
					{
						ArrayList<Integer> shortestPath = BFS(STARTINDEX);
						if(shortestPath.contains(index) && shortestPath.contains(index + 1))
						{
							System.out.print("##");
						}
						else if (shortestPath.contains(index) && shortestPath.contains(index + this.diamension))
						{
							System.out.print("# ");
						}
						else
						{
							System.out.print("  ");
						}
					}
					else if(printOption == DFS_PRINT)  //if the DFS maze
					{
						DFS(STARTINDEX);
						int order = cells.get(index).getVisitedOrder();
						if(order != -1)
						{
							order %= 10;
							System.out.print(order + " ");
						}
						else
						{
							System.out.print("  ");
						}
					}
					else if(printOption == DFS_SHORTESTPATH)  //if the maze use DFS to find shortest path
					{
						ArrayList<Integer> shortestPath = DFS(STARTINDEX);
						if(shortestPath.contains(index) && shortestPath.contains(index + 1))
						{
							System.out.print("##");
						}
						else if (shortestPath.contains(index) && shortestPath.contains(index + this.diamension))
						{
							System.out.print("# ");
						}
						else
						{
							System.out.print("  ");
						}
					}				
				}
				verticalWallRowCounter ++;
			}
			System.out.print("\n+");
			for(int column2 = 0; column2 < this.diamension; column2++)
			{
				int index = row * this.diamension + column2;	 //identify the index of each room of the horizontal wall	
				if(this.cells.get(horizontalWallCounter).getSouthWall() == 1)  //if the room has south wall
				{
					System.out.print("-");
				}
				else if (this.cells.get(horizontalWallCounter).getSouthWall() == 0) //if the room doesn't have the south wall
				{
					if(printOption == DFS_SHORTESTPATH)  //if the maze use DFS to find shortest path 
					{
						ArrayList<Integer> shortestPath = DFS(STARTINDEX);
						if(shortestPath.contains(index) && shortestPath.contains(index + this.diamension))
						{
							System.out.print("#");
						}
						else
						{
							System.out.print(" ");
						}
					}	
					else if(printOption == BFS_SHORTESTPATH)  //if the maze use BFS to find shortest path 
					{
						ArrayList<Integer> shortestPath = DFS(STARTINDEX);
						if(shortestPath.contains(index) && shortestPath.contains(index + this.diamension))
						{
							System.out.print("#");
						}
						else
						{
							System.out.print(" ");
						}
					}	
					else if(printOption == DFS_PRINT || printOption == BFS_PRINT || printOption == ORIGINAL) //if not shortest path
					{
						System.out.print(" ");
					}
				}
				System.out.print("+"); //last corner
				horizontalWallCounter ++;
			}
			System.out.print("\n");
		}
		System.out.print("\n\n");

	}
	/**
	 * Breadth First Traversal of the connected room and return the shortest from up-left to bottom-right 
	 * @param startIndex
	 * @return
	 */
	public ArrayList<Integer> BFS(int startIndex)
	{
		ArrayList<Integer> shortestPath = new ArrayList<Integer>();  //initialized the shortest path list
		for(Room cell: cells)  //for each room
		{
			cell.setColor(WHITE);  //set the color of room white color 
			cell.setRoot(null);  //set null as the root of the room
		}
		Queue<Integer> q = new PriorityQueue<Integer>();  //initialized a queue as the priority queue
		q.add(startIndex);  //enqueue the start index 
		int order = 0;	//set order to 0
		int currentIndex = startIndex;  //set start index to the current index
		//if the q is not empty and the current index of the room is not the finish room
		while(!q.isEmpty() && cells.get(currentIndex).getData() != (cells.size() - 1)) 
		{
			currentIndex = q.remove();	//set the current index as the value pop out from queue
			cells.get(currentIndex).settVisitedOrder(order);  //set current room's visited order
			order ++; //increase visited order 
			for(Room neighbour: cells.get(currentIndex).getConnectedNeighbours())  //for each connected neighbors
			{
				//if the color is white and not the finish room
				if(neighbour.getColor() == WHITE && neighbour.getData() != cells.size() - 1) 
				{
					neighbour.setColor(GREY);  //set the room's color as the grey
					neighbour.setRoot(cells.get(currentIndex));  //set the root of the room
					q.add(neighbour.getData());  //enqueue the index of the neighbor
				}
				else if(neighbour.getData() == cells.size() - 1) //if the neighbor is not the finish room
				{
					neighbour.setColor(GREY);  //set the neighbor's color is grey
					neighbour.setRoot(cells.get(currentIndex));  //set the root of the room as the current room
					neighbour.settVisitedOrder(order);  //set the visited order of the room
					currentIndex = neighbour.getData();  //set the current room as the finish room
				}				
			}
			cells.get(currentIndex).setColor(BLACK); //set the color of the room to the black
		}
		int index = cells.size() - 1;  //get the last index of the maze
		shortestPath.add(index);  //add the index of the last room to the shortest path list
		while(cells.get(index).getRoot() != null)  //when the room has the root 
		{
			index = cells.get(index).getRoot().getData();  //set the current's root data as the index
			shortestPath.add(cells.get(index).getData());  //add the index to the shortest path list
		}
		return shortestPath;  //return the shortest path list
	}
	/**
	 * Depth First Traversal of the connected room and return the shortest from up-left to bottom-right 
	 * @param startIndex
	 * @return
	 */
	public ArrayList<Integer> DFS(int startIndex)
	{
		ArrayList<Integer> shortestPath = new ArrayList<Integer>();  //initialized the shortest path list
		//initialized the room's color to white and the root to null
		for(Room cell: cells)
		{
			cell.setColor(WHITE);
			cell.setRoot(null);
		}
		int visitedOrder = 0;  //assigned 0 to the visit order
		//for each room with white color call DFS_Visit method
		for(Room cell: cells)
		{
			if(cell.getColor() == WHITE)
			{
				DFS_Visit(startIndex, visitedOrder);
			}
		}
		//get the shortest path from the finish room to the start room
		int index = cells.size() -1;
		shortestPath.add(index);  //add the finish room to the shortest path list
		while(cells.get(index).getRoot() != null)  //if the root of the room is not null
		{
			index = cells.get(index).getRoot().getData();  //get data of the room's root
			shortestPath.add(index);  //add the room to the shortest path list
		}
		return shortestPath;  //return the list 
	}
	/**
	 * DFS visit method
	 * @param currentIndex
	 * @param order
	 */
	public void DFS_Visit(int currentIndex, int order)
	{
		cells.get(currentIndex).setColor(GREY);  //set white to the current room's color
		cells.get(currentIndex).settVisitedOrder(order); //set the visit order to the current room
		//int size = cells.get(currentIndex).getConnectedNeighbours().size();
		for(Room neighbour: cells.get(currentIndex).getConnectedNeighbours())  //for each connected neighbors
		{
			//if the neighbor's color is white and the index of the neighbor is not the finish room's index
			if(neighbour.getColor() == WHITE && neighbour.getData() != cells.size() - 1)  
			{
				neighbour.setRoot(cells.get(currentIndex));  //set the current room as the neghbor's root
				order ++;  //increase the order 
				DFS_Visit(neighbour.getData(), order);  //recursively calling visit method
			}
			else if(neighbour.getData() == cells.size() - 1)  //if the neighbor is the finish room
			{
				order ++; //increase the visit order
				neighbour.setColor(GREY);  //set the grey to the finish room
				neighbour.settVisitedOrder(order);  //set the visited order to the finish room
				neighbour.setRoot(cells.get(currentIndex));  //set the finish room's root as the current 
				break;  //jump out the for loop
			}
		}
		cells.get(currentIndex).setColor(BLACK);  //set the current room's color to black
	}
	/**
	 * using DFS for randomly generalized the maze
	 */
	public void DFS_BuildMaze()
	{
		//initialized the each room' color to white
		for(Room cell: cells)
		{
			cell.setColor(WHITE);
		}
		int startIndex = 0; //set start index
		//if the room's color is white then visit it
		for(Room cell: cells)
		{
			if(cell.getColor() == WHITE)
			{
				DFSHelper_BuildMaze(startIndex);
			}
		}
	}
	/**
	 * the helper method for using DFS to generalized maze
	 * @param currentIndex
	 */
	public void DFSHelper_BuildMaze(int currentIndex)
	{		
		cells.get(currentIndex).setColor(GREY);  //set the color of the current room to white
		if(cells.get(currentIndex).getRoot() != null)  //if the root of current room is not null
		{
			//push down the wall between the current room and it's root
			cells.get(currentIndex).pushDownWall(cells.get(currentIndex).getRoot());  
		}
		int size = cells.get(currentIndex).getNeighbours().size();  //assign the neighbors's list size to size
		//visit each neighbor if the color of the room is white
		for(int i = 0; i < size; i ++)  
		{
			if(cells.get(currentIndex).getNeighbours().get(i).getColor() == WHITE)
			{
				cells.get(currentIndex).getNeighbours().get(i).setRoot(cells.get(currentIndex));	
				DFSHelper_BuildMaze(cells.get(currentIndex).getNeighbours().get(i).getData());
			}			
		}
		cells.get(currentIndex).setColor(BLACK);  //set white to current room's color
	}
	/**
	 * randomized the neighbors list
	 */
	public void randomNeighbour()
	{
		for(Room cell: cells)  // for each room
		{
			int size = cell.getNeighbours().size();  //get the size of the neighbors list
			for(int i = 0; i < size; i ++)  //for each neighbors
			{
				int random = (int)(this.myrandom() * (size - 1));  //get the random index
				//exchange the neighbor with the random neighbor
				Room temp = cell.getNeighbours().get(i);  
				cell.getNeighbours().set(i,  cell.getNeighbours().get(random));  
				cell.getNeighbours().set(random, temp);
			}
		}
	}
	/**
	 * return the list of rooms
	 * @return
	 */
	public ArrayList<Room> getCells()
	{
		return this.cells;
	}
	
}
