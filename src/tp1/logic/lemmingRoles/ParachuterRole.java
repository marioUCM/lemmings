package tp1.logic.lemmingRoles;

import tp1.logic.gameobjects.Lemming;
import tp1.view.Messages;

public class ParachuterRole extends AbstractRole{
	
	
	private static final String name=Messages.PARACHUTER_ROL_NAME;
	private	static final String shortcut = Messages.PARACHUTER_ROL_SHORTCUT;
	private static final String help=Messages.PARACHUTER_ROL_HELP;
	private	static final String icon=Messages.LEMMING_PARACHUTE;
	
	public ParachuterRole() {
		super(name,shortcut,help,icon);		
	}
	
	@Override
	public void start( Lemming lemming ) {
		if(!lemming.isInAir())
			lemming.disableRole();
		}
	
	@Override
	public void play( Lemming lemming ) {lemming.parachute(); }

	}
