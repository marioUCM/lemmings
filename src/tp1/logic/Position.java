package tp1.logic;


public class Position {

	private int col;
	private int row;
	
	public Position(int c,int r) {
		this.col=c;
		this.row=r;
	}
	
	public void operador(int x,int y) {
		this.col+=x;
		this.row+=y;
	}
	
	public Position() {
		this(0,0);
	}
	
	public int getX() {
		return this.col;
	}
	
	public int getY() {
		return this.row;
	}
	
	public boolean equals(Position p) {
		return this.col==p.col && this.row==p.row;
	}
	
}
