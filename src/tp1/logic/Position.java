package tp1.logic;

import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;

public class Position {

	private int fila;
	private int col;

	public Position(int c, int f) {
		this.col = c;	
		this.fila = f;
	}

	public void operador(int c, int f) {
		this.col += c;
		this.fila += f;
	}

	public Position() {
		this(0, 0);
	}

	public int getFila() {
		return this.fila;
	}

	public int getCol() {
		return this.col;
	}

	public boolean equals(Position p) {
		return this.col == p.col && this.fila == p.fila;
	}

	public String toString() {
		return "(" + fila + "," + col + ")";
	}

	public static Position getPositionFrom(String line)
							throws ObjectParseException, OffBoardException {
		line = line.trim().replaceAll("[()]", "");
	    String[] parts = line.split(",");
		try {
			int fila = Integer.parseInt(parts[0]);
			int col = Integer.parseInt(parts[1]);
			if (fila > 9 || col > 9 || fila < 0 || col < 0)
				throw new OffBoardException("Object position is off board: \"");
			return new Position(col,fila);
		} catch (NumberFormatException e) {
			throw new ObjectParseException("Invalid object position: \"");
		}
	}
}
