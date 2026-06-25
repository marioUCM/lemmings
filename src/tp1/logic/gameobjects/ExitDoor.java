package tp1.logic.gameobjects;

import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.GameWorld;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.view.Messages;

public class ExitDoor extends GameObject{

	private static final String name="ExitDoor";

	public ExitDoor(GameWorld g,Position p) {
		super(g,p);
	}
	
	
	public ExitDoor() {
		super();
	}

	public ExitDoor(ExitDoor other) {
		super(other.game,other.pos);
	}
	
	public ExitDoor clone() {
		return new ExitDoor(this);
	}
	
	@Override
	public String getIcon() {
		return Messages.EXIT_DOOR;
	}
	
	@Override
	public boolean setRole(LemmingRole role) {return false;}

	@Override
	public boolean receiveInteraction(GameItem other) {
		return other.interactWith(this);
	}
	
	@Override
	public String toString() {
		return "("+pos.getFila()+","+pos.getCol()+") ExitDoor "+Messages.LINE_SEPARATOR;
	}


	@Override
	public GameObject parse(String line, GameWorld game) throws OffBoardException, ObjectParseException {
		String[] words = line.trim().split("\\s+");			
		if(words[1].equalsIgnoreCase(name)) {
			try{
				Position aux=Position.getPositionFrom(words[0]);
				return new ExitDoor(game,aux);
			}
			catch(OffBoardException e) {
				throw new OffBoardException(e.getMessage()+line+"\"");
			}
			catch(ObjectParseException e) {
				throw new ObjectParseException(e.getMessage()+line);
			}
		}
		else
			return null;
	}
}
