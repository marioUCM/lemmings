package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.view.Messages;

public class ExitDoor extends GameObject{
	
	public ExitDoor(GameWorld g,Position p) {
		super(g,p);
	}	
	
	@Override
	public boolean isExit() {return true;}
	
	
	@Override
	public String getIcon() {return Messages.EXIT_DOOR;}
	
	@Override
	public boolean setRole(LemmingRole role) {return false;}

	@Override
	public boolean receiveInteraction(GameItem other) {
		return other.interactWith(this);
	}
	
	@Override
	public String toString() {
		return "("+pos.getX()+","+pos.getY()+") ExitDoor "+Messages.LINE_SEPARATOR;
	}
}
