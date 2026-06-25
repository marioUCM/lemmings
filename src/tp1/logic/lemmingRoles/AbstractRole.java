package tp1.logic.lemmingRoles;

import tp1.logic.gameobjects.Lemming;
import tp1.logic.gameobjects.*;
public abstract class AbstractRole implements LemmingRole{
	private String name;
	private String shortcut;
	private String help;
	private String icon;
	
	public AbstractRole(String name,String shortcut,String help,String icon) {
		this.name=name;
		this.shortcut=shortcut;
		this.help=help;
		this.icon=icon;
	}
	
	public void start( Lemming lemming ) {};
	public abstract void play( Lemming lemming );
	public String getIcon( Lemming lemming ) {return icon;}
	public String getHelp() {return help;	}
	public String getName() {return name;}
	public String getShortcut() {return shortcut;}
	public void fall() {}
	public boolean fallen() {return false;}
	public void resetFall() {}
	
	public boolean receiveInteraction(GameItem other, Lemming owner) {return other.interactWith(owner);}
	public boolean interactWith(Lemming receiver, Lemming owner) {return false;}
	public boolean interactWith(Wall wall, Lemming owner) {return false;}
	public boolean interactWith(ExitDoor door, Lemming owner) {return false;}
	public boolean interactWith(MetalWall metal,Lemming owner) {return false;}
}
