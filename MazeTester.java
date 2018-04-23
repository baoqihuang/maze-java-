package cs146_project2;

import java.util.ArrayList;
import java.util.Scanner;

public class MazeTester 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		System.out.println("Please enter the size of maze: ");
		Scanner scan = new Scanner(System.in);
		int diamension = scan.nextInt();
		System.out.println("The size of the maze is: " + diamension);
		Maze maze = new Maze(diamension);
		ArrayList<Room> cells = new ArrayList<Room>();

		//maze.buildMaze();
		maze.DFS_BuildMaze();
		maze.printAll();
		System.out.println("============================== ");
		System.out.println("       Program Completed! ");
		System.out.println("============================== ");


		cells = maze.getCells();
//		System.out.println("The following is the neighbor has the wall");
//		for(Room cell: cells)
//		{
//			System.out.print("Index " + cell.getData() + ": ");
//			for(Room neighbor: cell.getNeighbours())
//			{
//				System.out.print(neighbour.getData() + " ");
//			}
//			System.out.println();
//		}
		System.out.println("The following is the graph representation");
		for(Room cell: cells)
		{
			System.out.print("Index " + cell.getData() + ": ");
			for(Room connectedNeighbour: cell.getConnectedNeighbours())
			{
				System.out.print(connectedNeighbour.getData() + " ");
			}
			System.out.println();
		}
//		for (int i = 0; i < 5; i++)
//		{
//			double ii = maze.myrandom();
//			System.out.print(ii);
//		}
		scan.close();
	}

}
