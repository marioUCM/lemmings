package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.view.GameView;

public abstract class NoParamsCommand extends Command{

	public NoParamsCommand(String name, String shortcut, String details, String help) {
		super(name, shortcut, details, help);
	}
	
	@Override
	public Command parse(String[] commandWords) {
		return super.matchCommandName(commandWords[0]) ? this : null;
	}
	
	@Override
	public abstract void execute(GameModel game, GameView view);	  
	
}
