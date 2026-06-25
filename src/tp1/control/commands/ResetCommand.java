package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;


public class ResetCommand extends NoParamsCommand {
	private static final String NAME = Messages.COMMAND_RESET_NAME;
    private static final String SHORTCUT = Messages.COMMAND_RESET_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_RESET_DETAILS;
    private static final String HELP = Messages.COMMAND_RESET_HELP;
    
    private int nLevel=-1;

	public ResetCommand (){
		super(NAME,SHORTCUT,DETAILS,HELP);
	}
	
	@Override
	public Command parse(String[] commandWords) {
		if(commandWords.length==2) {		
			this.nLevel=Integer.parseInt(commandWords[1]);
			if(NAME.equalsIgnoreCase(commandWords[0]) || SHORTCUT.equalsIgnoreCase(commandWords[0]))
					return this;
		}
		this.nLevel=-1;
		return super.parse(commandWords);
	}
	
	@Override
	public void execute(GameModel game,GameView view) {
		if(!game.reset(nLevel))
			view.showError(Messages.INVALID_NUMBER);
		else {
		view.showGame();
		}
	}
	
}
