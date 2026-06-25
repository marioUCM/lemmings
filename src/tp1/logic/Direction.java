package tp1.logic;

import tp1.exceptions.ObjectParseException;

public enum Direction {
	LEFT(-1,0), RIGHT(1,0), DOWN(0,1), UP(0,-1), NONE(0,0);
	
	private int col;
	private int fila;
	
	private Direction(int c, int f) {
		this.col=c;
		this.fila=f;
	}
	
	public int getCol() {
		return col;
	}

	public int getFila() {
		return fila;
	}
	
	public String dirToString() {
		return this.name();
	}
	
	public static Direction getLemmingDirectionFrom(String line) throws ObjectParseException{
		switch(line.toUpperCase()) {
		case "RIGHT":
			return Direction.RIGHT;
		case "LEFT":
			return Direction.LEFT;
		default:
			throw new ObjectParseException("Invalid lemming direction: \"");
		}
	}
}
