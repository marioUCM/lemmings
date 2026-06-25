package tp1.logic.lemmingRoles;

import tp1.logic.gameobjects.*;

public interface LemmingRole {
	 public void start( Lemming lemming );
	 public void play( Lemming lemming );
	 public String getIcon( Lemming lemming );
	 public String getHelp();
	 public String getName();
	 public String getShortcut();
	 public void fall();
	 public boolean fallen();
	 public void resetFall();
	 public int getFall();
	 public void setFall(int altura);
	 public LemmingRole clone();
	 
	 public boolean receiveInteraction(GameItem other, Lemming owner);
	 public boolean interactWith(Lemming receiver, Lemming owner);
	 public boolean interactWith(Wall wall, Lemming owner);
	 public boolean interactWith(ExitDoor door, Lemming owner);
	 
}
