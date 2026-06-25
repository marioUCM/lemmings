package tp1.logic.gameobjects;

import tp1.logic.Position;
public class ExitDoor {
	private Position pos;
	
	public ExitDoor(Position p) {
		this.pos=p;
	}
	
	public ExitDoor() {
		this.pos=new Position();
	}
	
	public boolean isInPosition(Position p) {
		return p.equals(this.pos);
	}
}
