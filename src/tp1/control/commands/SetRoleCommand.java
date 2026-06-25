package tp1.control.commands;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.exceptions.OffBoardException;
import tp1.exceptions.RoleParseException;
import tp1.logic.GameModel;
import tp1.logic.Position;
import tp1.logic.lemmingRoles.LemmingRole;
import tp1.logic.lemmingRoles.LemmingRoleFactory;
import tp1.view.GameView;
import tp1.view.Messages;

public class SetRoleCommand extends Command {

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
		commands.append(Messages.LINE_TAB.formatted(Messages.COMMAND_HELP_TEXT.formatted(getDetails(), getHelp())));
		commands.append(LemmingRoleFactory.helpText());
		return commands.toString();
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if (commandWords.length != 4 && super.matchCommandName(commandWords[0]))
			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		else {
			if (super.matchCommandName(commandWords[0])) {
				try {
					this.role = LemmingRoleFactory.parse(commandWords);
				} catch (RoleParseException e) {
					throw new CommandParseException(Messages.ERROR_COMMAND_PARSE, e);
				}
				try {
					int fila = commandWords[2].charAt(0) - 'A';
					int col = Integer.parseInt(commandWords[3]) - 1;
					this.pos = new Position(col, fila);
				} catch (NumberFormatException e) {
					throw new CommandParseException(Messages.INVALID_POSITION
							.formatted(Messages.POSITION.formatted(commandWords[2], commandWords[3])));
				}
				return this;

			} else {
				return null;
			}
		}
	}

	@Override
	public void execute(GameModel game, GameView view) throws CommandExecuteException {
		try {
			if (!game.setRole(this.role, this.pos))
				view.showError("No lemming in position " + pos.toString() + " admits role " + role.getName());
			else {
				game.update();
				view.showGame();
			}
		} catch (OffBoardException e) {
			throw new CommandExecuteException(Messages.ERROR_COMMAND_EXECUTE, e);
		}
	}

}
