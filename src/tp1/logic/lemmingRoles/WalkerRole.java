package tp1.logic.lemmingRoles;

import tp1.logic.gameobjects.Lemming;

public class WalkerRole {

	private static final int MAX_FALL = 2;
	
	private int fall;
	private boolean walker;
	
	public WalkerRole() {
		this.fall=0;
		this.walker=true;
	}
	
	public boolean caminante() {
		return walker;
	}
	public boolean fallen() {
		return fall>MAX_FALL;
	}
	
	public void play(Lemming lemming) {
		lemming.walkOrFall();
	}
	
	public void fall() {
		this.fall++;
	}
	
	public void resetFall() {
		this.fall=0;
	}
	
	public String  getName() {
		if(walker)
			return "W";
		return "";
	}
	
}
