package tp1.logic;

import tp1.logic.gameobjects.GameItem;

public interface GameWorld {
	public boolean isInAir(Position pos);
	
	public boolean isSolid(Position pos);
			
	public boolean dentroDelTablero(Position p);
	
	public boolean receiveInteractionsFrom(GameItem obj);
	
	public void arrived();
	
	public void dead();
}
