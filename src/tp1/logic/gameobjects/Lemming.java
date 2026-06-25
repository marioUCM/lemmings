package tp1.logic.gameobjects;

import tp1.logic.Game;
import tp1.logic.Direction;
import tp1.logic.lemmingRoles.WalkerRole;
import tp1.view.Messages;
import tp1.logic.Position;

public class Lemming {
	private Position pos;
	private Direction dir;
	private boolean isAlive;
	private Game game;
	private WalkerRole rol;
	
	public Lemming(Game g, Position p) {
		this.game=g;
		this.pos=p;
		this.isAlive=true;
		this.dir=Direction.RIGHT;
		this.rol=new WalkerRole();
	}
	
	public Lemming() {
		this.game=new Game(game.getLevel());
		this.pos=new Position (0,0);
		this.isAlive=false;
		this.dir=Direction.NONE;
		this.rol=new WalkerRole();
	}
	
	public void update() {
		if(this.isAlive) {
			rol.play(this);
		}
		if(!this.dentroDelTablero(this.pos))
			this.isAlive=false;
	}
	
	public void dead() {
		this.isAlive=false;
	}
	
	public boolean isAlive() {
		return this.isAlive;
	}
	
	public boolean isInAir() {
		return game.isInAir(this.pos);
	}
	
	public void walkOrFall() {
		if(game.isExit(this.pos)) {
			game.lemmingExit();
		}
		else {
			if(game.isInAir(this.pos)) {				
				rol.fall();
				this.pos.operador(0, 1);
			}
			else {
				if(rol.fallen())
					this.dead();
				else {
					rol.resetFall();
					this.move();
					}
			}
		}
	}
	
	public void move() {
		if(this.isAlive) {
			Position p = new Position(pos.getX(),pos.getY());
			if(this.dir.equals(Direction.RIGHT))
				p.operador(1, 0);
			else 
				if(this.dir.equals(Direction.LEFT))
					p.operador(-1, 0);	
				else 
					if(this.dir.equals(Direction.UP))
						p.operador(0, -1);
					else 
						if(this.dir.equals(Direction.DOWN))
							p.operador(0, -1);
			if(game.isSolid(p) || p.getX()==0 || p.getX()==10) {
				if(this.dir.equals(Direction.RIGHT))
					this.dir=Direction.LEFT;
				else this.dir=Direction.RIGHT;
				}
			else
				this.pos=p;
		}
	}
	
	public boolean isInPosition(Position p) {
		return p.equals(this.pos);
	}
	
	public String getIcon() {
		if(this.dir==Direction.LEFT)
			return Messages.LEMMING_LEFT;
		
		else return Messages.LEMMING_RIGHT;	
	}
	
	public boolean dentroDelTablero(Position p) {
		return p.getX()>=0 && p.getY()>=0 && p.getX()<Game.DIM_X && p.getY()<Game.DIM_Y;
		}
	
	public int altura(int k) {
		Position aux= new Position(pos.getX(),pos.getY()+k);
		if(game.isSolid(aux) || !dentroDelTablero(aux))
			return k-1;
		else
			return altura(k+1);
	}
	
	public String toString() {
		return "("+pos.getX()+","+pos.getY()+") L "+ dir.toString()+" "+altura(1)+" "+rol.getName();
	}
	
	public boolean isExit() {
		return this.game.isExit(this.pos);
	}
}
