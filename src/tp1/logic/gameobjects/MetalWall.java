package tp1.logic.gameobjects;

import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.view.Messages;

public class MetalWall extends GameObject{
	
	public MetalWall(GameWorld g,Position p) {
		super(g,p);
	}

	public  boolean setRole(LemmingRole role) {return false;}

	public boolean isSolid() {return true;}

	public String getIcon() {return Messages.METALWALL;}

	@Override
	public boolean receiveInteraction(GameItem other) {
		return other.interactWith(this);
	}
	
	@Override
	public String toString() {
		return "("+pos.getX()+","+pos.getY()+") MetalWall "+Messages.LINE_SEPARATOR;
	}
}

