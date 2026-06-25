package tp1.control.commands;

import java.util.Arrays;
import java.util.List;

import tp1.exceptions.CommandParseException;
import tp1.view.Messages;

public class CommandGenerator {

	private static final List<Command> availableCommands =
			  Arrays.asList(new SetRoleCommand(),
							new UpdateCommand(),
							new ResetCommand(), 
							new LoadCommand(),
							new SaveCommand(),
							new HelpCommand(),
							new ExitCommand()
							);

	public static Command parse(String[] commandWords) throws CommandParseException {
		try {
			for (Command c : availableCommands) {
				Command aux = c.parse(commandWords);
				if (aux != null)
					return aux;
			}
		} catch (CommandParseException e) {
			throw e;
			}
		throw new CommandParseException(Messages.UNKNOWN_COMMAND.formatted(commandWords[0]));
	}

	public static String commandHelp() {
		StringBuilder commands = new StringBuilder();

		for (Command c : availableCommands) {
			commands.append(c.helpText());
		}

		return commands.toString();
	}

}
