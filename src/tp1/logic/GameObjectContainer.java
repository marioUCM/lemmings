package tp1.logic;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tp1.logic.gameobjects.GameObject;
import tp1.logic.gameobjects.GameItem;
import tp1.logic.lemmingRoles.LemmingRole;

public class GameObjectContainer {

	private List<GameObject> objects;

	public GameObjectContainer() {
		objects = new ArrayList<GameObject>();
	}

	public GameObjectContainer(GameObjectContainer other) {
		this.objects = new ArrayList<GameObject>();
		for(GameObject obj:other.objects)
			this.objects.add(obj.clone());
	}

	public void add(GameObject object) {
		objects.add(object);
	}

	public String positionToString(Position pos) {
		String salida = "";
		for (GameObject o : objects) {
			if (o.isInPosition(pos))
				salida += o.getIcon();
		}
		return salida;

	}

	public void update() {
		for (int i = objects.size()-1; i >=0; i--) {
			GameObject obj = objects.get(i);
			obj.update();
		}
	}

	public void remove() {
		for (int i = objects.size() - 1; i >= 0; i--) {
			GameObject obj = objects.get(i);
			if (!obj.isAlive()) {
				objects.remove(i);
			}
		}
	}

	public boolean isSolid(Position pos) {
		for (GameObject o : objects) {
			if (o.isSolid() && o.isInPosition(pos))
				return true;
		}
		return false;
	}

	public boolean setRole(LemmingRole role, Position pos) {
		for (GameObject o : objects) {
			if (o.isInPosition(pos) && o.setRole(role))
				return true;
		}
		return false;
	}

	public boolean receiveInteractionsFrom(GameItem obj) {
		for (GameObject o : objects) {
			if (o.receiveInteraction(obj))
				return true;
		}
		return false;
	}

	public void removeWall(Position pos) {
		for (int i = objects.size() - 1; i >= 0; i--)
			if (objects.get(i).isInPosition(pos) && objects.get(i).isSolid())
				objects.remove(i);
	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		for (GameObject o : objects)
			result.append(o.toString());
		return result.toString();
	}

    public void save(BufferedWriter salida) throws IOException{
    	for (GameObject o : objects)
			try {
				salida.write(o.toString());
			} catch (IOException e) {
				throw e;
			}
    }

}
