package tp1.logic;

import tp1.logic.gameobjects.*;
import tp1.view.Messages;

public class GameObjectContainer {
	public static final int MAX_LEMMINGS=100;
	private Lemming[] lemmings;
	private int numLemmings;
	private Wall[] walls;
	private int numWalls;
	private ExitDoor[] doors;
	private int numExitDoors;
	
	public GameObjectContainer() {
		this.numLemmings=0;
		this.lemmings=new Lemming[100];
		this.numWalls=0;
		this.walls=new Wall[100];
		this.numExitDoors=0;
		this.doors=new ExitDoor[100];
	}
	
	public void add(Lemming lemming) {
		this.lemmings[numLemmings]=lemming;
		this.numLemmings++;
	}
	public void add(Wall wall) {
		this.walls[numWalls]=wall;
		this.numWalls++;
	}
	public void add(ExitDoor door) {
		this.doors[numExitDoors]=door;
		this.numExitDoors++;
	}
	
	public void update() {
		for(int i =0;i<this.numLemmings;i++)
			lemmings[i].update();
	}	
	
	public String positionToString(Position pos) {
		if(isSolid(pos))
			return Messages.WALL;
		else {
			String salida="";
			if(isExit(pos))
				salida+= Messages.EXIT_DOOR;
			for(int i=0;i<this.numLemmings;i++)
					if(this.lemmings[i].isInPosition(pos))
						salida+= this.lemmings[i].getIcon();
			return salida;
		}
	}
	
	public void removeDead() {
		for(int i=numLemmings-1;i>=0;i--)
			if(!lemmings[i].isAlive()) {
				for(int j=i;j<numLemmings;j++)
					this.lemmings[j]=lemmings[j+1];
				this.numLemmings--;
			}
	}
	
	public void removeExit() {
		for(int i=numLemmings-1;i>=0;i--)
			if(lemmings[i].isExit()) {
				for(int j=i;j<numLemmings;j++)
					this.lemmings[j]=lemmings[j+1];
				this.numLemmings--;
			}
	}
	
	public int numLemmingsDead() {
		int aux=0;
		for(int i=0;i<this.numLemmings;i++)
			if(this.lemmings[i].isAlive()==false)
				aux++;
		return aux;
	}
	
	public int numLemmingsExit() {
		int aux=0;
		for(int i=0;i<this.numLemmings;i++)
			if(lemmings[i].isExit())
				aux++;
		return aux;
	}

	
	public boolean isSolid(Position pos) {
		for(int i=0;i<this.numWalls;i++)
			if(this.walls[i].isInPosition(pos))
				return true;
		return false;
	}
	
	public boolean isExit(Position pos) {
		for(int i=0;i<this.numExitDoors;i++)
			if(this.doors[i].isInPosition(pos))
				return true;
		return false;
	}
}
