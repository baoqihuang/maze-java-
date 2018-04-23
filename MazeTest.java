package cs146_project2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.Test;

class MazeTest {

	@Test
	public void testRandom() 
	{
		Maze maze = new Maze(0);
		Random random = new Random(0);
		for(int i = 0; i < 5; i++)
		{
			assertEquals(maze.myrandom(), random.nextDouble());
		}
	}
	public void testBuildAllWall()
	{
		Maze maze = new Maze(3);
		ArrayList<Room> cells = maze.getCells();
		assertEquals(cells.size(), 9);

		for(Room room: cells)
		{
			if(room.getData() != 0 && room.getData() != 8)
			{
				assertTrue(room.getNorthWall() == 1);
				assertTrue(room.getSouthWall() == 1);
			}
			else
			{
				assertTrue(cells.get(0).getNorthWall() == 0);
				assertTrue(cells.get(8).getSouthWall() == 0);
			}
			assertTrue(room.getWestWall() == 1);
			assertTrue(room.getEastWall() == 1);
				
		}

	}
	
	public void testBFS()
	{
		Maze maze = new Maze(3);
		ArrayList<Room> cells = maze.getCells();
		ArrayList<Integer> shortestPath = maze.BFS(0);
		for(Room room: cells)
		{
			assertTrue(shortestPath.contains(room.getData()));
			assertTrue(room.getVisitedOrder() == room.getData());
		}
	}
	public void testDFS()
	{
		Maze maze = new Maze(3);
		ArrayList<Room> cells = maze.getCells();
		ArrayList<Integer> shortestPath = maze.DFS(0);
		for(Room room: cells)
		{
			assertTrue(shortestPath.contains(room.getData()));
			assertTrue(room.getVisitedOrder() == room.getData());
		}
	}
	

}
