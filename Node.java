package cs146_project2;
/**
 * Node class for each node 
 * @author michaelhuang
 *
 */
public class Node 
{
	//instinct variables
	private int color;
	private int distance;
	private int startTime;
	private int finishTime;
	private int data;
	/**
	 * Constructor of Node class
	 * @param data
	 */
	public Node(int data)
	{
		this.data = data;
		//this.isVisited = false;
		this.distance = 0;
	}
	/**
	 * return data of the node
	 * @return
	 */
	public int getData()
	{
		return this.data;
	}
	/**
	 * set data of the node
	 * @param data
	 */
	public void setData(int data)
	{
		this.data = data;
	}
	/**
	 * get color of the node
	 * @return
	 */
	public int getColor() 
	{
		return color;
	}
	/**
	 * set color of the node
	 * @param color
	 */
	public void setColor(int color) 
	{
		this.color = color;
	}
	/**
	 * get the distance from the original node
	 * @return
	 */
	public int getDistance() 
	{
		return distance;
	}
	/**
	 * set the distance
	 * @param distance
	 */
	public void setDistance(int distance) 
	{
		this.distance = distance;
	}
	/**
	 * get the start time when visited the node
	 * @return
	 */
	public int getStartTime() 
	{
		return startTime;
	}
	/**
	 * set the start time when visited the node
	 * @param startTime
	 */
	public void setStartTime(int startTime) 
	{
		this.startTime = startTime;
	}
	/**
	 * get the finished time when node turns black
	 * @return
	 */
	public int getFinishTime() 
	{
		return finishTime;
	}
	/**
	 * set the finished time 
	 * @param finishTime
	 */
	public void setFinishTime(int finishTime) 
	{
		this.finishTime = finishTime;
	}
}
