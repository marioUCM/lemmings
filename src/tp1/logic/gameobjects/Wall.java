package tp1.logic.gameobjects;

import tp1.logic.Position;

public class Wall {
	private Position pos;
	
	public Wall() {
		pos=new Position(0,0);
	}
	public Wall(Position p) {
		this.pos=p;
		}
	
	public boolean isInPosition(Position p) {
		return p.equals(this.pos);
	}
}
