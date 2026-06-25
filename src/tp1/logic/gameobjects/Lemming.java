package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.Direction;
import tp1.logic.lemmingRoles.*;
import tp1.view.Messages;
import tp1.logic.Position;

public class Lemming extends GameObject{
	
	private static final String name="Lemming";
	private Direction dir;
	private LemmingRole role;
	
	public Lemming(GameWorld g, Position p,LemmingRole r) {
		super(g,p);
		this.dir=Direction.RIGHT;
		this.role=r;
	}
	
	public Lemming(GameWorld g, Position p,Direction d,int a,LemmingRole r) {
		super(g,p);
		this.dir=d;
		this.role=r;
		role.setFall(a);
	}
	
	public Lemming() {
		super();
	}

	public Lemming(Lemming other) {
		super(other.game,other.pos);
		this.dir=other.dir;
		this.role=other.role.clone();
	}

	public Lemming clone() {
		return new Lemming(this);
	}
	
	@Override
	public void update() {
		game.receiveInteractionsFrom(this);
		if(this.isAlive) {
			role.start(this);
			role.play(this);
		if(!this.dentroDelTablero(this.pos))
			this.dead();
		}
	}
	
	public void dead() {
		isAlive=false;
		game.dead();
	}
		
	public boolean isInAir() {
		return game.isInAir(this.pos);
	}
	
	public void downcaver() {
		pos.operador(0,1);
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
			Position p = new Position(pos.getCol()+dir.getCol(),pos.getFila()+dir.getFila());
			if(game.isSolid(p) || p.getCol()==-1 || p.getCol()==10) {
				if(this.dir.equals(Direction.RIGHT))
					this.dir=Direction.LEFT;
				else this.dir=Direction.RIGHT;
				}
			else
				pos=p;
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
		Position aux= new Position(pos.getCol(),pos.getFila()+k);
		if(game.isSolid(aux) || !dentroDelTablero(aux))
			return k-1;
		else
			return altura(k+1);
	}
	
	public String toString() {
		return "("+pos.getFila()+","+pos.getCol()+") Lemming "+ dir.toString()+
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
	
	@Override
	public boolean interactWith(ExitDoor exit) {
		if(exit.isInPosition(this.pos)) {
			this.isAlive=false;
			game.arrived();
			return true;
		}
		else return false;

	}
	
	public boolean wallDown(Wall wall) {
		Position aux=new Position(pos.getCol(),pos.getFila()+1);
		return wall.isInPosition(aux);
	}
	
	private static int getLemmingHeigthFrom(String line) throws ObjectParseException {
		try {
			int altura=Integer.parseInt(line);
			return altura;
		}
		catch(NumberFormatException e) {
			throw new ObjectParseException("Invalid lemming heigth: "+line);
		}
	}

	@Override
	public GameObject parse(String line, GameWorld game) throws OffBoardException, ObjectParseException{
		String[] words = line.trim().split("\\s+");			
		if(words[1].equalsIgnoreCase(name)) {
			try{
				Position p=Position.getPositionFrom(words[0]);
				Direction d=Direction.getLemmingDirectionFrom(words[2]); 
				int altura=Lemming.getLemmingHeigthFrom(words[3]);
				LemmingRole r=LemmingRoleFactory.getLemmingRoleFrom(words[4]);
				return new Lemming(game,p,d,altura,r);
			}
			catch(OffBoardException e) {
				throw new OffBoardException(e.getMessage()+line+"\"");
			}
			catch(ObjectParseException e) {
				throw new ObjectParseException(e.getMessage()+line+"\"");
			}
		}
		else
			return null;
		}
}
