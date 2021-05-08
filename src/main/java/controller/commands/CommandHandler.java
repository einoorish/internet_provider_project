package controller.commands;

import java.util.Map;
import java.util.TreeMap;

public class CommandHandler{

	private static final Map<String, Command> commands = new TreeMap<>();

	static {
	    commands.put("home", new HomeCommand());
//	    commands.put("go_to_login", (request, response) -> Path.LOGIN);
	    commands.put("login", new LoginCommand());
	}
	
	private CommandHandler() {}
	
	public static Command get(String commandName) {
	    if (commandName == null) return commands.get("home");
	    if (commands.containsKey(commandName)) {
	        return commands.get(commandName);
	    }
	    return null;
	}
	
	public static boolean contains(String commandName) {
	    return commands.get(commandName) != null;
	}

}
