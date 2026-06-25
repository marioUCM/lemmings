package tp1.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.Scanner;

import tp1.exceptions.GameLoadException;
import tp1.exceptions.ObjectParseException;
import tp1.exceptions.OffBoardException;
import tp1.logic.gameobjects.GameObjectFactory;

public class FileGameConfiguration implements GameConfiguration {

	private GameWorld game;
	private int currentCycle;
	private int remaining;
	private int lemmingsExit;
	private int lemmingsToWin;
	private int lemmingsDead;
	private GameObjectContainer container;

	public static final GameConfiguration NONE = new FileGameConfiguration();

	public FileGameConfiguration() {
	}

	public FileGameConfiguration(String fileName, GameWorld game) throws GameLoadException {
		String rutaCompleta = Paths.get("conf", fileName).toString();
		try (Scanner entrada = new Scanner(new FileReader(rutaCompleta))){
			String line;
			if (entrada.hasNext()) {
				line = entrada.nextLine();
				String[] words = line.trim().split("\\s+");
				if (words.length != 5)
					throw new GameLoadException(new Throwable("Invalid game status \"" + line + "\""));
				try {
					this.game = game;
					this.currentCycle = Integer.parseInt(words[0]);
					this.remaining = Integer.parseInt(words[1]);
					this.lemmingsDead = Integer.parseInt(words[2]);
					this.lemmingsExit = Integer.parseInt(words[3]);
					this.lemmingsToWin = Integer.parseInt(words[4]);
				} catch (NumberFormatException e) {
					throw new GameLoadException(new Throwable("Invalid game status \"" + line + "\""));
				}
			}
			this.container = new GameObjectContainer();
			while (entrada.hasNext()) {
				line = entrada.nextLine();
				try {
					container.add(GameObjectFactory.parse(line, game));
				} catch (ObjectParseException e) {
					throw new GameLoadException(e);
				} catch (OffBoardException e) {
					throw new GameLoadException(e);
				}
			}
		} catch (FileNotFoundException e) {
			throw new GameLoadException(new Throwable("File not found: \"" + fileName + "\""));
		}

	}

	@Override
	public int getCycle() {
		return currentCycle;
	}

	@Override
	public int numLemmingsInBoard() {
		return remaining;
	}

	@Override
	public int numLemmingsDead() {
		return lemmingsDead;
	}

	@Override
	public int numLemingsExit() {
		return lemmingsExit;
	}

	@Override
	public int numLemmingToWin() {
		return lemmingsToWin;
	}

	@Override
	public GameObjectContainer getGameObjects() {
		return new GameObjectContainer(container);
	}
}
