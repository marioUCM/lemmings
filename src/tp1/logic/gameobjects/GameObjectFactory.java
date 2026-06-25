package tp1.logic.gameobjects;

import java.util.Arrays;
import java.util.List;

import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.GameWorld;
import tp1.view.Messages;

public class GameObjectFactory {
	
	private static final List<GameObject> availableObjects = Arrays.asList(
			new Lemming(),
			new Wall(),
			new MetalWall(),
			new ExitDoor()
			);
	
	public static GameObject parse(String line,GameWorld game) 
					throws ObjectParseException,OffBoardException{
		for(GameObject o:availableObjects) {
			try{
				GameObject aux=o.parse(line,game);
				if(aux!=null)
					return aux;
			}
			catch(OffBoardException e) {
				throw e;
			}
			catch(ObjectParseException e) {
				throw e;
			}
		}
		throw new ObjectParseException(Messages.UNKNOWN_GAME_OBJECT.formatted("\""+line+"\""));
	}
	
}
