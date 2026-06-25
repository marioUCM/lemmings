package tp1.logic;

import tp1.logic.lemmingRoles.LemmingRole;

public interface GameModel {
	public boolean reset(int level);
	public void update();
	public void exit();
	public boolean isFinished();
	public void removeLemmingArrived();
	public void removeLemmingDead();
	public boolean setRole(LemmingRole role,Position pos);
}
