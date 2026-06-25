package tp1.logic;

import tp1.logic.gameobjects.*;
import tp1.view.Messages;

public class Game {

	public static final int DIM_X = 10;
	public static final int DIM_Y = 10;
	
	private GameObjectContainer container;
	private int currentCycle;					
	private int numLemmings;
	private int remaining;
	private int lemmingsExit;
	private int lemmingsToWin;
	private int lemmingsDead;
	private boolean doExit;
	private int nLevel;

	public Game(int nLevel) {
		this.nLevel=nLevel;
		if (nLevel == 1)
			initGame1();
		else if (nLevel == 2)
			initGame2();
		else
			initGame();
	}
	
	private void initGame() {
		this.container=new GameObjectContainer();
		this.currentCycle=0;
		this.lemmingsExit=0;
		this.lemmingsToWin=2;
		this.lemmingsDead=0;
		this.doExit=false;

		container.add(new Lemming(this, new Position(9,0)));
		container.add(new Lemming(this, new Position(2,3)));
		container.add(new Lemming(this, new Position(0,8)));
		numLemmings=3;
		
		container.add(new Wall(new Position(0,9)));
		container.add(new Wall(new Position(1,9)));
		container.add(new Wall(new Position(2,4)));
		container.add(new Wall(new Position(3,4)));
		container.add(new Wall(new Position(4,4)));
		container.add(new Wall(new Position(4,6)));
		container.add(new Wall(new Position(5,6)));
		container.add(new Wall(new Position(6,6)));		
		container.add(new Wall(new Position(7,5)));
		container.add(new Wall(new Position(7,6)));
		container.add(new Wall(new Position(8,8)));
		container.add(new Wall(new Position(8,9)));
		container.add(new Wall(new Position(8,1)));		
		container.add(new Wall(new Position(9,1)));
		container.add(new Wall(new Position(9,9)));

		container.add(new ExitDoor(new Position(4,5)));
		
		this.remaining=this.numLemmings;
	}
	
	private void initGame1() {
		this.container=new GameObjectContainer();
		this.currentCycle=0;
		this.lemmingsExit=0;
		this.lemmingsToWin=2;
		this.lemmingsDead=0;
		this.doExit=false;

		container.add(new Lemming(this, new Position(9,0)));
		container.add(new Lemming(this, new Position(2,3)));
		container.add(new Lemming(this, new Position(0,8)));
		container.add(new Lemming(this, new Position(3,3)));
		numLemmings=4;
		
		container.add(new Wall(new Position(0,9)));
		container.add(new Wall(new Position(1,9)));
		container.add(new Wall(new Position(2,4)));
		container.add(new Wall(new Position(3,4)));
		container.add(new Wall(new Position(4,4)));
		container.add(new Wall(new Position(4,6)));
		container.add(new Wall(new Position(5,6)));
		container.add(new Wall(new Position(6,6)));		
		container.add(new Wall(new Position(7,5)));
		container.add(new Wall(new Position(7,6)));
		container.add(new Wall(new Position(8,8)));
		container.add(new Wall(new Position(8,9)));
		container.add(new Wall(new Position(8,1)));		
		container.add(new Wall(new Position(9,1)));
		container.add(new Wall(new Position(9,9)));

		container.add(new ExitDoor(new Position(4,5)));
		
		this.remaining=this.numLemmings;
	}

	private void initGame2() {
		container = new GameObjectContainer();
		container.add(new Lemming(this, new Position(4,0)));
		container.add(new Lemming(this, new Position(5,0)));
		container.add(new Lemming(this, new Position(6,0)));
		container.add(new Lemming(this, new Position(7,0)));
		numLemmings = 4;
		container.add(new Wall(new Position(2,1)));
		container.add(new Wall(new Position(3,1)));
		container.add(new Wall(new Position(4,1)));
		container.add(new Wall(new Position(5,1)));
		container.add(new Wall(new Position(6,1)));
		container.add(new Wall(new Position(7,1)));
		container.add(new Wall(new Position(5,3)));
		container.add(new Wall(new Position(6,3)));
		container.add(new Wall(new Position(7,3)));
		container.add(new Wall(new Position(3,9)));
		container.add(new Wall(new Position(4,9)));
		container.add(new Wall(new Position(5,9)));
		container.add(new Wall(new Position(6,9)));
		container.add(new Wall(new Position(7,9)));
		container.add(new Wall(new Position(3,8)));
		container.add(new Wall(new Position(9,4)));
		container.add(new ExitDoor(new Position(7,8)));
		this.remaining = this.numLemmings;
		this.currentCycle=0;
		this.doExit=false;
		this.lemmingsDead=0;
		this.lemmingsToWin=2;
		this.lemmingsExit=0;
		
	}

	public int getCycle() {
		return this.currentCycle;
	}

	public int getLevel() { return this.nLevel; }

	public int numLemmingsInBoard() {
		return this.remaining;
	}

	public int numLemmingsDead() {
		return this.lemmingsDead;
	}

	public int numLemmingsExit() {
		return this.lemmingsExit;
	}

	public int numLemmingsToWin() {
		return this.lemmingsToWin;
	}

	public String positionToString(int col, int row) {
		return this.container.positionToString(new Position(col,row));
	}

	public boolean playerWins() {
		return this.lemmingsExit>=this.lemmingsToWin;
	}

	public boolean playerLooses() {
		return lemmingsExit+this.remaining<this.lemmingsToWin;
	}

	public String help() {
		return Messages.HELP;
	}
	
	public boolean isInAir(Position pos) {
		Position aux=new Position(pos.getX(),pos.getY());
		aux.operador(0,1);
		return !this.container.isSolid(aux);
	}
	
	public boolean isSolid(Position pos) {
		return this.container.isSolid(pos);
	}
	
	public boolean isExit(Position pos) {
		return this.container.isExit(pos);
	}
	
	public boolean isFinished() {
		return this.doExit;
	}
	
	public void update() {	
		this.lemmingExit();
		container.update();
		this.lemmingDead();	
		this.currentCycle++;
		if(this.remaining==0)
			this.doExit=true;
	}
	
	public void lemmingExit() {
		this.lemmingsExit+=container.numLemmingsExit();
		this.remaining-=container.numLemmingsExit();
		this.container.removeExit();
	}
	
	public void lemmingDead() {
		this.lemmingsDead+=container.numLemmingsDead();
		this.remaining-=container.numLemmingsDead();
		this.container.removeDead();
	}
	
	public void finish() {
		this.doExit=true;
	}
}
