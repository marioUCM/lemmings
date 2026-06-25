package tp1.logic.gameobjects;

import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.LemmingRole;

public abstract class GameObject implements GameItem {

	private final String name;
	protected Position pos;
	protected boolean isAlive;
	protected GameWorld game;

	public GameObject() {
		this.isAlive=true;
		this.name=this.getName();
	}

	public GameObject(GameWorld game, Position pos) {
		this.isAlive = true;
		this.pos = new Position(pos.getCol(),pos.getFila());
		this.game = game;
		this.name=this.getName();
	}
    public abstract GameObject clone();

	public boolean isInPosition(Position p) {
		return p.equals(this.pos);
	}

	public boolean isAlive() {
		return isAlive;
	}

	public abstract GameObject parse(String line, GameWorld game) throws OffBoardException, ObjectParseException;

	public abstract boolean setRole(LemmingRole role);

	public boolean isSolid() {
		return false;
	}

	public void update() {};

	public abstract String getIcon();

	public abstract String toString();

	public abstract boolean receiveInteraction(GameItem other);

	public boolean interactWith(Lemming lemming) {
		return false;
	}

	public boolean interactWith(Wall wall) {
		return false;
	}

	public boolean interactWith(ExitDoor door) {
		return false;
	}

	public boolean interactWith(MetalWall metal) {
		return false;
	}

	protected String getName() {
		return name;
	}

}
