package tp1.logic;

import tp1.logic.gameobjects.*;
import tp1.logic.lemmingRoles.*;

public class Game implements GameStatus,GameModel,GameWorld{

	private GameObjectContainer container;
	private static int nLevel;
	private int currentCycle;					
	private int numLemmings;
	private int remaining;
	private int lemmingsExit;
	private int lemmingsToWin;
	private int lemmingsDead;
	private boolean doExit;

	public Game() {
		this(nLevel);
	}
	
	public Game(int nLevel) {
		Game.nLevel=nLevel;
		if(nLevel==0)
			initGame();
		else if(nLevel==1)
			initGame1();
			else initGame2();
	}

	private void initGame() {
		this.container=new GameObjectContainer();
		this.currentCycle=0;
		this.numLemmings=0;
		this.lemmingsExit=0;
		this.lemmingsToWin=2;
		this.lemmingsDead=0;
		this.doExit=false;

		container.add(new ExitDoor(this,new Position(4,5)));

		container.add(new Lemming(this, new Position(9,0),new WalkerRole()));
		container.add(new Lemming(this, new Position(2,3),new WalkerRole()));
		container.add(new Lemming(this, new Position(0,8),new WalkerRole()));
		numLemmings = 3;
		this.remaining=this.numLemmings;

		container.add(new Wall(this,new Position(0,9)));
		container.add(new Wall(this,new Position(1,9)));
		container.add(new Wall(this,new Position(2,4)));
		container.add(new Wall(this,new Position(3,4)));
		container.add(new Wall(this,new Position(4,4)));
		container.add(new Wall(this,new Position(4,6)));
		container.add(new Wall(this,new Position(5,6)));
		container.add(new Wall(this,new Position(6,6)));		
		container.add(new Wall(this,new Position(7,5)));
		container.add(new Wall(this,new Position(7,6)));
		container.add(new Wall(this,new Position(8,8)));
		container.add(new Wall(this,new Position(8,9)));
		container.add(new Wall(this,new Position(8,1)));		
		container.add(new Wall(this,new Position(9,1)));
		container.add(new Wall(this,new Position(9,9)));
	}
	
	private void initGame1() {
		this.container=new GameObjectContainer();
		this.currentCycle=0;
		this.lemmingsExit=0;
		this.lemmingsToWin=2;
		this.lemmingsDead=0;
		this.doExit=false;

		container.add(new ExitDoor(this,new Position(4,5)));

		container.add(new Lemming(this, new Position(9,0),new WalkerRole()));
		container.add(new Lemming(this, new Position(2,3),new WalkerRole()));
		container.add(new Lemming(this, new Position(0,8),new WalkerRole()));
		container.add(new Lemming(this,new Position(3,3),new WalkerRole()));
		numLemmings =4;
		this.remaining=this.numLemmings;

		container.add(new Wall(this,new Position(0,9)));
		container.add(new Wall(this,new Position(1,9)));
		container.add(new Wall(this,new Position(2,4)));
		container.add(new Wall(this,new Position(3,4)));
		container.add(new Wall(this,new Position(4,4)));
		container.add(new Wall(this,new Position(4,6)));
		container.add(new Wall(this,new Position(5,6)));
		container.add(new Wall(this,new Position(6,6)));		
		container.add(new Wall(this,new Position(7,5)));
		container.add(new Wall(this,new Position(7,6)));
		container.add(new Wall(this,new Position(8,8)));
		container.add(new Wall(this,new Position(8,9)));
		container.add(new Wall(this,new Position(8,1)));		
		container.add(new Wall(this,new Position(9,1)));
		container.add(new Wall(this,new Position(9,9)));
	}

	private void initGame2() {
		this.container=new GameObjectContainer();
		this.currentCycle=0;
		this.lemmingsExit=0;
		this.lemmingsToWin=2;
		this.lemmingsDead=0;
		this.doExit=false;

		container.add(new ExitDoor(this,new Position(4,5)));

		container.add(new Lemming(this, new Position(9,0),new WalkerRole()));
		container.add(new Lemming(this, new Position(2,3),new WalkerRole()));
		container.add(new Lemming(this, new Position(0,8),new WalkerRole()));
		container.add(new Lemming(this, new Position(3,3),new WalkerRole()));
		container.add(new Lemming(this, new Position(6,0),new WalkerRole()));
		container.add(new Lemming(this, new Position(6,0),new ParachuterRole()));
		this.numLemmings = 6;
		this.remaining=this.numLemmings;

		container.add(new Wall(this,new Position(0,9)));
		container.add(new Wall(this,new Position(1,9)));
		container.add(new Wall(this,new Position(2,4)));
		container.add(new Wall(this,new Position(3,4)));
		container.add(new Wall(this,new Position(4,4)));
		container.add(new Wall(this,new Position(4,6)));
		container.add(new Wall(this,new Position(5,6)));
		container.add(new Wall(this,new Position(6,6)));		
		container.add(new Wall(this,new Position(7,5)));
		container.add(new Wall(this,new Position(7,6)));
		container.add(new Wall(this,new Position(8,8)));
		container.add(new Wall(this,new Position(8,9)));
		container.add(new Wall(this,new Position(8,1)));		
		container.add(new Wall(this,new Position(9,1)));
		container.add(new Wall(this,new Position(9,9)));
		container.add(new Wall(this,new Position(3,5)));
		container.add(new MetalWall(this,new Position(3,6)));
	}	
	
	//------------------------
	
	// GameStatus methods
		
	@Override
	public int getCycle() {
		return this.currentCycle;
	}

	@Override
	public int numLemmingsInBoard() {
		return this.remaining;
	}

	@Override
	public int numLemmingsDead() {
		return this.lemmingsDead;
	}

	@Override
	public int numLemmingsExit() {
		return this.lemmingsExit;
	}

	@Override
	public int numLemmingsToWin() {
		return this.lemmingsToWin;
	}

	@Override
	public String positionToString(int col, int row) {
		return this.container.positionToString(new Position(col,row));
	}

	@Override
	public boolean playerWins() {
		return this.lemmingsExit>=this.lemmingsToWin & this.remaining==0;
	}

	@Override
	public boolean playerLooses() {
		return lemmingsExit+this.remaining<this.lemmingsToWin;
	}

	// GameModel methods
		
	@Override
	public void removeLemmingArrived() {
		int arrived=this.container.removeArrived();
		this.lemmingsExit+=arrived;
		this.remaining-=arrived;
	}
	
	@Override
	public void removeLemmingDead() {
		int dead=this.container.removeDead();
		this.lemmingsDead+=dead;
		this.remaining-=dead;
	}
	
	@Override
	public boolean reset(int level) {
		if(level==-1) {
			if(nLevel==0)
				initGame();
			else if(nLevel==1)
				initGame1();
			else initGame2();
			return true;
			}
		else {
			switch (level) {
			case 0:
				initGame();
				return true;
			case 1:
				initGame1();
				return true;
			case 2:
				initGame2();
				return true;
			default:
				return false;
			}
		}
	}	
	
	@Override
	public void update() {
		this.removeLemmingArrived();
		container.update();
		this.removeLemmingDead();	
		this.currentCycle++;
		if(this.remaining==0)
			this.doExit=true;
	}
	
	@Override
	public void exit() {this.doExit=true;}
	
	@Override
	public boolean isFinished() {return this.doExit;}
	
	@Override
	public boolean setRole(LemmingRole role,Position pos) {
		return container.setRole( role, pos);
	}

	// GameWorld methods (callbacks)
		
	@Override
	public boolean isInAir(Position pos) {
		Position aux=new Position(pos.getX(),pos.getY());
		aux.operador(0,1);
		return !this.container.isSolid(aux);
	}
	
	@Override
	public boolean isSolid(Position pos) {
		return this.container.isSolid(pos);
	}
	
	@Override
	public boolean lemmingArrived(Position pos) {
		return this.container.isExit(pos);
	}
	
	@Override
	public boolean dentroDelTablero(Position p) {
		return p.getX()>=0 && p.getY()>=0 && p.getX()<DIM_X && p.getY()<DIM_Y;
	}

	@Override
	public boolean receiveInteractionsFrom(GameItem obj) {
		return container.receiveInteractionsFrom(obj);
	}
	
	@Override 
	public void removeWall(Position pos) {
		container.removeWall(pos);
	}

	// Other methods
	public String toString() {
			return container.toString();
	}

}
