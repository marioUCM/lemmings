package tp1.logic.lemmingRoles;

import java.util.Arrays;
import java.util.List;

import tp1.view.Messages;


public class LemmingRoleFactory {
	
	private static final List<LemmingRole> availableRoles = Arrays.asList(
						new DownCaverRole(),
						new ParachuterRole(),
						new WalkerRole()
	);
	
	 public static LemmingRole parse(String[] input) {
		 for(LemmingRole role: availableRoles) {
			 if(input[1].equalsIgnoreCase(role.getName()) ||
					 input[1].equalsIgnoreCase(role.getShortcut()))
				 return role;
		 }
		 return null;
	 }
	 
	 public static String helpText() {
		 StringBuilder commands=new StringBuilder();
		 for(LemmingRole role: availableRoles) {
			commands.append(Messages.LINE_2TABS.formatted(role.getHelp()));
		 }
		 return commands.toString();
	 }

}
