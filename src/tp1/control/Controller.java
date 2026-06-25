package tp1.control;

import tp1.control.commands.Command;
import tp1.control.commands.CommandGenerator;
import tp1.exceptions.CommandException;
import tp1.logic.Game;
import tp1.view.GameView;

public class Controller {

	private Game game;
	private GameView view;

	public Controller(Game game, GameView view) {
		this.game = game;
		this.view = view;
	}

	public void run() {
		String[] words = null;

		view.showWelcome();
		view.showGame();
		while (!game.isFinished()) {
			try {
				words = view.getPrompt();
				Command command = CommandGenerator.parse(words);
				command.execute(game, view);
			} catch (CommandException e) {
				Throwable cause = e.getCause();
				if (cause != null) {
					view.showError2(e.getMessage()); // Igual pero sin salto de linea extra
					view.showError(cause.getMessage());
				}
				else view.showError(e.getMessage());

			}

		}
		view.showEndMessage();
	}

}
