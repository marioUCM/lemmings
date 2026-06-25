package tp1.control.commands;

import tp1.logic.GameModel;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.logic.lemmingRoles.LemmingRoleFactory;
import tp1.view.GameView;
import tp1.view.Messages;

public class SetRoleCommand extends Command{
	
	private LemmingRole role;
	private Position pos;
	
	private static final String NAME = Messages.COMMAND_SETROLE_NAME;
	private static final String SHORTCUT = Messages.COMMAND_SETROLE_SHORTCUT;
	private static final String DETAILS = Messages.COMMAND_SETROLE_DETAILS;
	private static final String HELP = Messages.COMMAND_SETROLE_HELP;
	
	public SetRoleCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
	}
	
	@Override
	public String helpText() {
		StringBuilder commands = new StringBuilder();
		commands.append(Messages.LINE_TAB.formatted(
				Messages.COMMAND_HELP_TEXT.formatted(getDetails(), getHelp())));
		commands.append(LemmingRoleFactory.helpText());
		return commands.toString();
	}
	
	@Override
	public Command parse(String[] commandWords) {
		if(commandWords.length!=4)
			return null;
		else {
			if(super.matchCommandName(commandWords[0])) {
				this.role=LemmingRoleFactory.parse(commandWords);
				if(this.role != null) {
					int row = commandWords[2].toUpperCase().charAt(0)-'A';
					int col = Integer.parseInt(commandWords[3])-1;
					this.pos=new Position(col,row);
					return this;

				}
				else {
					return null;
				}
			}
			return null;
		}
	}
	
	@Override
	public void execute(GameModel game, GameView view) {
			if(!game.setRole(this.role,this.pos))
				view.showError(Messages.COMMAND_SETROLE_ERROR);
			else {
				game.update();
				view.showGame();
			}

	};
	
}
