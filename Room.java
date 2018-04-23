package cs146_project2;
import java.util.ArrayList;
/**
 * Room class extends Node class for each room of the maze
 * @author michaelhuang
 *
 */
public class Room extends Node
{
	//instinct variables
	private int northWall;
	private int southWall;
	private int westWall;
	private int eastWall;
	private Room northRoom;
	private Room southRoom;
	private Room westRoom;
	private Room eastRoom;
	private Room root;
	private int visitedOrder; 
	private ArrayList<Room> neighbours;
	private ArrayList<Room> connectedNeighbours;
	/**
	 * Constructor of the Room class
	 * @param data
	 */
	public Room(int data)
	{
		super(data);
		this.northWall = 0;
		this.southWall = 0;
		this.westWall = 0;
		this.eastWall = 0;
		this.eastRoom = null;
		this.northRoom = null;
		this.westRoom = null;
		this.southRoom = null;
		this.root = null;
		this.visitedOrder = -1;
		this.neighbours = new ArrayList<Room>();
		this.connectedNeighbours = new ArrayList<Room>();
	}
	/**
	 * get the north wall value
	 * @return
	 */
	public int getNorthWall() 
	{
		return northWall;
	}
	/**
	 * set the north wall
	 * @param northWall
	 */
	public void setNorthWall(int northWall) 
	{
		this.northWall = northWall;
	}
	/**
	 * get the south wall value
	 * @return
	 */
	public int getSouthWall() 
	{
		return southWall;
	}
	/**
	 * set the south wall 
	 * @param southWall
	 */
	public void setSouthWall(int southWall) 
	{
		this.southWall = southWall;
	}
	/**
	 * get the west wall value
	 * @return
	 */
	public int getWestWall() 
	{
		return westWall;
	}
	/**
	 * set the west wall
	 * @param westWall
	 */
	public void setWestWall(int westWall) 
	{
		this.westWall = westWall;
	}
	/**
	 * get the east wall value
	 * @return
	 */
	public int getEastWall() 
	{
		return eastWall;
	}
	/**
	 * set east wall
	 * @param eastWall
	 */
	public void setEastWall(int eastWall) 
	{
		this.eastWall = eastWall;
	}
	/**
	 * get the north room
	 * @return
	 */
	public Room getNorthRoom() 
	{
		return northRoom;
	}
	/**
	 * set the north room
	 * @param northRoom
	 */
	public void setNorthRoom(Room northRoom) 
	{
		this.northRoom = northRoom;
	}
	/**
	 * get the south room
	 * @return
	 */
	public Room getSouthRoom() 
	{
		return southRoom;
	}
	/**
	 * set the south room
	 * @param southRoom
	 */
	public void setSouthRoom(Room southRoom) 
	{
		this.southRoom = southRoom;
	}
	/**
	 * get the west room
	 * @return
	 */
	public Room getWestRoom() 
	{
		return westRoom;
	}
	/**
	 * set the west room
	 * @param westRoom
	 */
	public void setWestRoom(Room westRoom) 
	{
		this.westRoom = westRoom;
	}
	/**
	 * get the east room
	 * @return
	 */
	public Room getEastRoom() 
	{
		return eastRoom;
	}
	/**
	 * set the east room
	 * @param eastRoom
	 */
	public void setEastRoom(Room eastRoom) 
	{
		this.eastRoom = eastRoom;
	}
	/**
	 * get the connected neighbors
	 * @return
	 */
	public ArrayList<Room> getConnectedNeighbours() 
	{
		return this.connectedNeighbours;
	}
	/**
	 * push down the wall between this room and target room
	 * @param room
	 */
	public void pushDownWall(Room room) 
	{
		for(int i = 0; i < this.neighbours.size(); i++)   //for each neighbors
		{
			if(this.neighbours.get(i) == room)  //if the neighbor is the target 
			{
				this.connectedNeighbours.add(room);	   //add the neighbor to the connected list
				//decide which direction of the room is the the target room then push down the wall
				if(this.neighbours.get(i) == this.northRoom)  //if the target room is north room
				{
					this.getNorthRoom().getConnectedNeighbours().add(this);
					this.setNorthWall(0);
					this.getNorthRoom().setSouthWall(0);
				}
				else if(this.neighbours.get(i) == this.southRoom)  //if the target room is south room
				{
					this.getSouthRoom().getConnectedNeighbours().add(this);
					this.setSouthWall(0);
					this.getSouthRoom().setNorthWall(0);
				}
				else if(this.neighbours.get(i) == this.westRoom)   //if the target room is west room
				{
					this.getWestRoom().getConnectedNeighbours().add(this);
					this.setWestWall(0);
					this.getWestRoom().setEastWall(0);
				}
				else if(this.neighbours.get(i) == this.eastRoom)   //if the target room is east room
				{
					this.getEastRoom().getConnectedNeighbours().add(this);
					this.setEastWall(0);
					this.getEastRoom().setWestWall(0);
				}
			}
		}
	}
	/**
	 * build the wall between this room and the target room
	 * @param room
	 */
	public void buildNeighbourWall(Room room)
	{
		this.neighbours.add(room);   //add the room to the neighbor
		if(room == this.northRoom && this.northRoom != null)  //if the target room is north room
		{			
			this.getNorthRoom().setSouthRoom(this);
			this.setNorthWall(1);
			this.getNorthRoom().setSouthWall(1);
		}
		else if(room == this.southRoom && this.southRoom != null)  //if the target room is south room
		{
			this.getSouthRoom().setNorthRoom(this);
			this.setSouthWall(1);
			this.getSouthRoom().setNorthWall(1);
		}
		else if(room == this.westRoom && this.westRoom != null)  //if the target room is west room
		{
			this.getWestRoom().setEastRoom(this);
			this.setWestWall(1);
			this.getWestRoom().setEastWall(1);
		}else if(room == this.eastRoom && this.eastRoom != null)  //if the target room is east room
		{
			this.getEastRoom().setWestRoom(this);
			this.setEastWall(1);
			this.getEastRoom().setWestWall(1);
		}
		
	}
	/**
	 * get the list of the neighbors
	 * @return
	 */
	public ArrayList<Room> getNeighbours()
	{
		return this.neighbours;
	}
	/**
	 * get the root of the room
	 * @return
	 */
	public Room getRoot() 
	{
		return root;
	}
	/**
	 * set the root of the room
	 * @param root
	 */
	public void setRoot(Room root) 
	{
		this.root = root;
	}
	/**
	 * get the visited order of the room
	 * @return
	 */
	public int getVisitedOrder() 
	{
		return visitedOrder;
	}
	/**
	 * set the visited order of the room
	 * @param visitedOrder
	 */
	public void settVisitedOrder(int visitedOrder) 
	{
		this.visitedOrder = visitedOrder;
	}
}
