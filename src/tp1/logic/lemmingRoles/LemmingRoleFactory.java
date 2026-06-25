package tp1.logic.lemmingRoles;

import java.util.Arrays;
import java.util.List;

import tp1.exceptions.ObjectParseException;
import tp1.exceptions.RoleParseException;
import tp1.view.Messages;

public class LemmingRoleFactory {

	private static final List<LemmingRole> availableRoles = 
			Arrays.asList(new DownCaverRole(), 
						  new ParachuterRole(),
						  new WalkerRole()
						  );

	public static LemmingRole parse(String[] input) throws RoleParseException {
		for (LemmingRole role : availableRoles) {
			if (input[1].equalsIgnoreCase(role.getName()) || input[1].equalsIgnoreCase(role.getShortcut()))
				return role;
		}
		throw new RoleParseException(Messages.UNKNOWN_ROLE.formatted(input[1]));
	}

	public static String helpText() {
		StringBuilder commands = new StringBuilder();
		for (LemmingRole role : availableRoles) {
			commands.append(Messages.LINE_2TABS.formatted(role.getHelp()));
		}
		return commands.toString();
	}

	public static LemmingRole getLemmingRoleFrom(String nombre) throws ObjectParseException {
		for(LemmingRole r:availableRoles) {
			if(r.getName().equalsIgnoreCase(nombre) || r.getShortcut().equalsIgnoreCase(nombre))
				return r;
		}
		throw new ObjectParseException("Invalid lemming role: \"");
	}
}
