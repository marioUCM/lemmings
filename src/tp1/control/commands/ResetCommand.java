package tp1.control.commands;

import tp1.exceptions.CommandParseException;
import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class ResetCommand extends NoParamsCommand {
	private static final String NAME = Messages.COMMAND_RESET_NAME;
	private static final String SHORTCUT = Messages.COMMAND_RESET_SHORTCUT;
	private static final String DETAILS = Messages.COMMAND_RESET_DETAILS;
	private static final String HELP = Messages.COMMAND_RESET_HELP;

	private int nLevel;

	public ResetCommand() {
		super(NAME, SHORTCUT, DETAILS, HELP);
		nLevel = -1;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if (matchCommandName(commandWords[0])) {
			if (commandWords.length == 2) {
				try {
					this.nLevel = Integer.parseInt(commandWords[1]);
					return this;
				} catch (NumberFormatException e) {
					throw new CommandParseException("Invalid level: "+commandWords[1]);
				}
			} else if (commandWords.length == 1) {
				this.nLevel = -1;
				return this;
			} else {
				throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
			}
		} else
			return null;
	}

	@Override
	public void execute(GameModel game, GameView view) {
		if (!game.reset(this.nLevel))
			view.showError(Messages.INVALID_NUMBER);
		else {
			view.showGame();
		}
	}

}
