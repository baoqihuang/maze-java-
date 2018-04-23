package cs146_project2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RoomTest {

	@Test
	public void test() 
	{
		//initialized room
		Room center = new Room(0); 
		Room north = new Room(1);
		Room south = new Room(2);
		Room west = new Room(3);
		Room east = new Room(4);
		
		center.setNorthRoom(north);
		center.setSouthRoom(south);
		center.setWestRoom(west);
		center.setEastRoom(east);
		//test getNorthRoom, getSouthRoom, getWestRoom, and getEastRoom
		assertEquals(center.getNorthRoom(), north);
		assertEquals(center.getSouthRoom(), south);
		assertEquals(center.getWestRoom(), west);
		assertEquals(center.getEastRoom(), east);
		//test getNorthWall, getSouthWal, getWestWall, getEastWall
		assertEquals(center.getNorthWall(), 0);
		assertEquals(center.getSouthWall(), 0);
		assertEquals(center.getWestWall(), 0);
		assertEquals(center.getEastWall(), 0);
		//test buildNeighbourWall 
		center.buildNeighbourWall(north);
		assertEquals(center.getNorthWall(),1);
		center.buildNeighbourWall(south);
		assertEquals(center.getSouthWall(),1);		
		center.buildNeighbourWall(west);
		assertEquals(center.getWestWall(),1);				
		center.buildNeighbourWall(east);
		assertEquals(center.getEastWall(),1);
		//test pushDownWall
		center.pushDownWall(north);
		assertEquals(center.getNorthWall(), 0);
		center.pushDownWall(south);
		assertEquals(center.getSouthWall(), 0);
		center.pushDownWall(west);
		assertEquals(center.getWestWall(), 0);
		center.pushDownWall(east);
		assertEquals(center.getEastWall(), 0);

	}

}
