package tp1.control;

import tp1.logic.Game;
import tp1.view.ConsoleView;
import tp1.view.ConsoleColorsView;
import tp1.view.GameView;
/**
 *  Accepts user input and coordinates the game execution logic
 */
public class Controller {

	private Game game;
	private GameView view;

	public Controller(Game game, GameView view) {
		this.game = game;
		this.view = view;
	}


	/**
	 * Runs the game logic, coordinate Model(game) and View(view)
	 * 
	 */
	public void run() {
		view.showWelcome();
		//	The main loop that displays the game, asks the user for input, and executes the action.
		view.showGame();
		while(!game.isFinished()) {
			String[] entrada= view.getPrompt();
			switch(entrada[0]) {
				case "h":
				case "help":
					view.showHelp();
					break;
				case "r":
				case "reset":
					int nLevel = game.getLevel();
					game.finish();
					game = new Game(nLevel);
					this.view.setGame(this.game);
					view.showGame();
					break;
				case "s":
				case "R":
				case "setRole":
					break;
				case "e":
				case "exit":
					game.finish();
					break;
				case "":
				case"n":
				case "none":
					game.update();
					view.showGame();
					break;
				default :
					view.showError("Unknown command");
					break;
			}
		}
		view.showEndMessage();
	}

}
