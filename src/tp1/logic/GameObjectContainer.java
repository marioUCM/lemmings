package tp1.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.GameItem;
import tp1.logic.lemmingRoles.LemmingRole;

public class GameObjectContainer {

	private List<GameObject> objects;

	public GameObjectContainer() {objects = new ArrayList<GameObject>();}
	
	public void add(GameObject object) {
		objects.add(object);
	}
	
	public String positionToString(Position pos) {
		String salida="";
		for(GameObject o: objects) {
			if(o.isInPosition(pos))
				salida+=o.getIcon();
		}
		return salida;

	}
	
	public void update() {
		for(int i=0;i<objects.size();i++) {
			GameObject obj=objects.get(i);
			obj.update();
		}
	};
	
	public int removeDead() {
		int dead=0;
		for(int i=objects.size()-1;i>=0;i--) {
			GameObject obj=objects.get(i);
			if(!obj.isAlive()) {
				objects.remove(i);
				dead++;
				}
		}
		return dead;
	}
	
	public int removeArrived() {
		int arrived=0;
		for(int i=objects.size()-1;i>=0;i--) {
			GameObject obj=objects.get(i);
			if(obj.arrived()) {
				arrived++;
				objects.remove(i);
				}
		}
		return arrived;
	}
	
	public boolean isSolid(Position pos) {
		for (GameObject o:objects) {
			if(o.isSolid() && o.isInPosition(pos))
				return true;
		}
		return false;
	}
	
	public boolean isExit(Position pos) {
		for (GameObject o:objects) {
			if(o.isExit() && o.isInPosition(pos))
				return true;
		}
		return false;
	}
	
	public boolean setRole(LemmingRole role,Position pos) {
		for(GameObject o:objects) {
			if(o.isInPosition(pos) && o.setRole(role))
				return true;		
		}
		return false;
	}

	public boolean receiveInteractionsFrom(GameItem obj) {
		  for(GameObject o:objects) {
			  if (o.receiveInteraction(obj))
				  return true;
		  }
		  return false;
	}

	public void removeWall(Position pos) {
		for(int i=objects.size()-1;i>=0;i--)
			if(objects.get(i).isInPosition(pos) && objects.get(i).isSolid())
				objects.remove(i);
	}
	
	public String toString() {
		StringBuilder result=new StringBuilder();
		for(GameObject o:objects)
			result.append(o.toString());
		return result.toString();
	}
}
