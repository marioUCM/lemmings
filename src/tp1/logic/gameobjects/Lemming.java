package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Direction;
import tp1.logic.lemmingRoles.*;
import tp1.view.Messages;
import tp1.logic.Position;

public class Lemming extends GameObject{
	private Direction dir;
	private LemmingRole role;
	
	public Lemming(GameWorld g, Position p,LemmingRole r) {
		super(g,p);
		this.dir=Direction.RIGHT;
		this.role=r;
	}
	
	@Override
	public void update() {
		role.start(this);
		if(this.isAlive) {
			role.play(this);
		}
		if(!this.dentroDelTablero(this.pos))
			isAlive=false;
	}
	
	public void dead() {
		isAlive=false;
	}
		
	public boolean isInAir() {
		return game.isInAir(this.pos);
	}
	
	public void downcaver() {
		Position aux=new Position(pos.getX(),pos.getY()+1);
		game.removeWall(aux);
		pos.operador(0, 1);
		
	}
	
	public void parachute() {
		pos.operador(0,1);
	}
	
	public void walkOrFall() {
		if(game.isInAir(this.pos)) {				
			role.fall();
			this.pos.operador(0, 1);
		}
		else {
			if(role.fallen())
				this.dead();
			else {
				role.resetFall();
				this.move();
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
							p.operador(0, 1);
			if(game.isSolid(p) || p.getX()==0 || p.getX()==10) {
				if(this.dir.equals(Direction.RIGHT))
					this.dir=Direction.LEFT;
				else this.dir=Direction.RIGHT;
				}
			else
				super.pos=p;
		}
	}
	
	@Override
	public boolean setRole(LemmingRole rol) { 
		if(!this.role.getName().equalsIgnoreCase(rol.getName())) {
			this.role=rol; 
			return  true;
		}
		else return false;
	}

	public void disableRole() { this.role=new WalkerRole(); }
	
	@Override
	public boolean arrived() {return game.lemmingArrived(pos);}

	@Override
	public String getIcon() {
		return role.getIcon(this);
	}
	
	public String walkerIcon() {
		if(this.dir==Direction.LEFT)
			return Messages.LEMMING_LEFT;
		
		else return Messages.LEMMING_RIGHT;	
	}
	
	public boolean dentroDelTablero(Position p) {
		return game.dentroDelTablero(p);
		}
	
	public int altura(int k) {
		Position aux= new Position(pos.getX(),pos.getY()+k);
		if(game.isSolid(aux) || !dentroDelTablero(aux))
			return k-1;
		else
			return altura(k+1);
	}
	
	public String toString() {
		return "("+pos.getX()+","+pos.getY()+") L "+ dir.toString()+
				" "+altura(1)+" "+role.getName()+Messages.LINE_SEPARATOR;
	}

	@Override
	public boolean receiveInteraction(GameItem other) {
		return other.interactWith(this);
	}
	
	@Override
	public boolean interactWith(Wall wall) {
		return  role.interactWith(wall, this);
	}
	
	public boolean interactions() {return game.receiveInteractionsFrom(this);}
	
	public boolean wallDown(Wall wall) {
		Position aux=new Position(pos.getX(),pos.getY()+1);
		return wall.isInPosition(aux);
	}
}
