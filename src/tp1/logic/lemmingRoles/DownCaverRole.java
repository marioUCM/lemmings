package tp1.logic.lemmingRoles;

import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.Wall;
import tp1.view.Messages;

public class DownCaverRole extends AbstractRole{

	private static final String name=Messages.DOWNCAVER_ROL_NAME;
	private	static final String shortcut = Messages.DOWNCAVER_ROL_SHORTCUT;
	private static final String help=Messages.DOWNCAVER_ROL_HELP;
	private	static final String icon=Messages.LEMMING_DOWN_CAVER;
	
	private boolean hasCaved;
	
	public DownCaverRole() {
		super(name,shortcut,help,icon);
		hasCaved=false;
	}
	
	public void start( Lemming lemming ) {
		if(lemming.isInAir() || !lemming.interactions()) 
			lemming.disableRole();
	}
	
	public void play( Lemming lemming ) {
		 lemming.downcaver();
		 this.hasCaved=true;
	}
	
	@Override
	public boolean interactWith(Wall wall,Lemming owner) {
		return owner.wallDown(wall);
		}

}
