package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.LemmingRole;

public abstract class GameObject implements GameItem {
	
	protected Position pos;
	protected boolean isAlive;
	protected GameWorld game;
	
	public GameObject(GameWorld game, Position pos) {
		this.isAlive = true;
		this.pos = pos;
		this.game = game;
	}
	
	public boolean isInPosition(Position p) {
		return p.equals(this.pos);
	}
 	
	public boolean isAlive() {
		return isAlive;
	}
	
	public abstract boolean setRole(LemmingRole role);
	public boolean isSolid() {return false;}
	public boolean isExit() {return false;}
	public boolean arrived() {return false;}
	public void update() {};
	public abstract String getIcon();
	public abstract String toString();
	public abstract boolean receiveInteraction(GameItem other);
	public boolean interactWith(Lemming lemming) {return false;}
	public boolean interactWith(Wall wall) {return false;}
	public boolean interactWith(ExitDoor door) {return false;}
	public boolean interactWith(MetalWall metal) {return false;}
}
