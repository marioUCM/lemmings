package tp1.logic.lemmingRoles;

import tp1.logic.gameobjects.Lemming;
import tp1.view.Messages;

public class WalkerRole extends AbstractRole{

	private static final int MAX_FALL = 2;
	private int fall;
	

	private static final String name=Messages.WALKER_ROL_NAME;
	private	static final String shortcut = Messages.WALKER_ROL_SHORTCUT;
	private static final String help=Messages.WALKER_ROL_HELP;
	private	static final String icon=Messages.LEMMING_RIGHT;
	
	public WalkerRole() {
		super(name,shortcut,help,icon);	
		this.fall=0;
	}

	public void start( Lemming lemming ) {}
	 
	public void play( Lemming lemming ) { lemming.walkOrFall(); }
	
	@Override
	public String getIcon( Lemming lemming ) {return lemming.walkerIcon();}
	
	
	@Override
	public boolean fallen() {
		return fall>MAX_FALL;
	}
	
	@Override
	public void fall() {
		this.fall++;
	}
	
	@Override
	public void resetFall() {
		this.fall=0;
	}
	
	

	
}
