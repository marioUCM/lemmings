package tp1.logic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import tp1.exceptions.GameLoadException;
import tp1.exceptions.GameModelException;
import tp1.exceptions.OffBoardException;
import tp1.logic.gameobjects.*;
import tp1.logic.lemmingRoles.*;

public class Game implements GameStatus, GameModel, GameWorld {

	private GameObjectContainer container;
	private int nLevel;
	private int currentCycle;
	private int numLemmings;
	private int remaining;
	private int lemmingsExit;
	private int lemmingsToWin;
	private int lemmingsDead;
	private boolean doExit;

	private GameConfiguration conf = FileGameConfiguration.NONE;

	public Game(int level) {
		this.nLevel = level;
		if (nLevel == 0)
			initGame();
		else if (nLevel == 1)
			initGame1();
		else
			initGame2();
	}

	private void initGame() {
		this.container = new GameObjectContainer();
		this.currentCycle = 0;
		this.lemmingsExit = 0;
		this.lemmingsToWin = 2;
		this.lemmingsDead = 0;
		this.doExit = false;

		container.add(new Lemming(this, new Position(9, 0), new WalkerRole()));
		container.add(new Lemming(this, new Position(2, 3), new WalkerRole()));
		container.add(new Lemming(this, new Position(0, 8), new WalkerRole()));
		this.numLemmings = 3;
		this.remaining = this.numLemmings;

		container.add(new Wall(this, new Position(0, 9)));
		container.add(new Wall(this, new Position(1, 9)));
		container.add(new Wall(this, new Position(2, 4)));
		container.add(new Wall(this, new Position(3, 4)));
		container.add(new Wall(this, new Position(4, 4)));
		container.add(new Wall(this, new Position(4, 6)));
		container.add(new Wall(this, new Position(5, 6)));
		container.add(new Wall(this, new Position(6, 6)));
		container.add(new Wall(this, new Position(7, 5)));
		container.add(new Wall(this, new Position(7, 6)));
		container.add(new Wall(this, new Position(8, 8)));
		container.add(new Wall(this, new Position(8, 9)));
		container.add(new Wall(this, new Position(8, 1)));
		container.add(new Wall(this, new Position(9, 1)));
		container.add(new Wall(this, new Position(9, 9)));
		container.add(new ExitDoor(this, new Position(4, 5)));
	}

	private void initGame1() {
		this.container = new GameObjectContainer();
		this.currentCycle = 0;
		this.lemmingsExit = 0;
		this.lemmingsToWin = 2;
		this.lemmingsDead = 0;
		this.doExit = false;

		container.add(new Lemming(this, new Position(9, 0), new WalkerRole()));
		container.add(new Lemming(this, new Position(2, 3), new WalkerRole()));
		container.add(new ExitDoor(this, new Position(4, 5)));
		container.add(new Lemming(this, new Position(0, 8), new WalkerRole()));
		container.add(new Lemming(this, new Position(3, 3), new WalkerRole()));
		numLemmings = 4;
		this.remaining = this.numLemmings;

		container.add(new Wall(this, new Position(0, 9)));
		container.add(new Wall(this, new Position(1, 9)));
		container.add(new Wall(this, new Position(2, 4)));
		container.add(new Wall(this, new Position(3, 4)));
		container.add(new Wall(this, new Position(4, 4)));
		container.add(new Wall(this, new Position(4, 6)));
		container.add(new Wall(this, new Position(5, 6)));
		container.add(new Wall(this, new Position(6, 6)));
		container.add(new Wall(this, new Position(7, 5)));
		container.add(new Wall(this, new Position(7, 6)));
		container.add(new Wall(this, new Position(8, 8)));
		container.add(new Wall(this, new Position(8, 9)));
		container.add(new Wall(this, new Position(8, 1)));
		container.add(new Wall(this, new Position(9, 1)));
		container.add(new Wall(this, new Position(9, 9)));
	}

	private void initGame2() {
		this.container = new GameObjectContainer();
		this.currentCycle = 0;
		this.lemmingsExit = 0;
		this.lemmingsToWin = 2;
		this.lemmingsDead = 0;
		this.doExit = false;

		container.add(new Lemming(this, new Position(9, 0), new WalkerRole()));
		container.add(new Lemming(this, new Position(2, 3), new WalkerRole()));
		container.add(new Lemming(this, new Position(0, 8), new WalkerRole()));
		container.add(new ExitDoor(this, new Position(4, 5)));
		container.add(new Lemming(this, new Position(3, 3), new WalkerRole()));
		container.add(new Lemming(this, new Position(6, 0), new WalkerRole()));
		container.add(new Lemming(this, new Position(6, 0), new ParachuterRole()));
		numLemmings = 6;
		this.remaining = this.numLemmings;

		container.add(new Wall(this, new Position(0, 9)));
		container.add(new Wall(this, new Position(1, 9)));
		container.add(new Wall(this, new Position(2, 4)));
		container.add(new Wall(this, new Position(3, 4)));
		container.add(new Wall(this, new Position(4, 4)));
		container.add(new Wall(this, new Position(4, 6)));
		container.add(new Wall(this, new Position(5, 6)));
		container.add(new Wall(this, new Position(6, 6)));
		container.add(new Wall(this, new Position(7, 5)));
		container.add(new Wall(this, new Position(7, 6)));
		container.add(new Wall(this, new Position(8, 8)));
		container.add(new Wall(this, new Position(8, 9)));
		container.add(new Wall(this, new Position(8, 1)));
		container.add(new Wall(this, new Position(9, 1)));
		container.add(new Wall(this, new Position(9, 9)));
		container.add(new Wall(this, new Position(3, 5)));
		container.add(new MetalWall(this, new Position(3, 6)));
		this.remaining = this.numLemmings;
	}

	// ------------------------

	// GameStatus methods

	@Override
	public int getCycle() {
		return this.currentCycle;
	}

	@Override
	public int numLemmingsInBoard() {
		return this.remaining;
	}

	@Override
	public int numLemmingsDead() {
		return this.lemmingsDead;
	}

	@Override
	public int numLemmingsExit() {
		return this.lemmingsExit;
	}

	@Override
	public int numLemmingsToWin() {
		return this.lemmingsToWin;
	}

	@Override
	public String positionToString(int fila, int col) {
		return this.container.positionToString(new Position(fila, col));
	}

	@Override
	public boolean playerWins() {
		return this.lemmingsExit >= this.lemmingsToWin && this.remaining == 0;
	}

	@Override
	public boolean playerLooses() {
		return lemmingsExit + this.remaining < this.lemmingsToWin;
	}

	// GameModel methods

	@Override
	public boolean reset(int level) {
		if (level == -1) {
			if (conf == FileGameConfiguration.NONE) {
				if (nLevel == 0)
					initGame();
				else if (nLevel == 1)
					initGame1();
				else
					initGame2();
				return true;
			} else {
				this.currentCycle = conf.getCycle();
				this.remaining = conf.numLemmingsInBoard();
				this.numLemmings = conf.numLemmingsInBoard();
				this.lemmingsDead = conf.numLemmingsDead();
				this.lemmingsToWin = conf.numLemmingToWin();
				this.lemmingsExit = conf.numLemingsExit();
				this.container = new GameObjectContainer(conf.getGameObjects());
				this.doExit = false;
				return true;
			}
		} else {
			if (level == 0)
				initGame();
			else if (level == 1)
				initGame1();
			else if (level == 2)
				initGame2();
			else
				return false;
			this.nLevel = level;
			conf = FileGameConfiguration.NONE;
			return true;
		}
	}

	@Override
	public void update() {
		container.update();
		this.container.remove();
		this.currentCycle++;
		if (this.remaining == 0)
			this.doExit = true;
	}

	@Override
	public void exit() {
		this.doExit = true;
	}

	@Override
	public boolean isFinished() {
		return this.doExit;
	}

	@Override
	public boolean setRole(LemmingRole role, Position pos) throws OffBoardException {
		if (!dentroDelTablero(pos))
			throw new OffBoardException(pos);
		else
			return container.setRole(role, pos);
	}

	@Override
	public void load(String fileName) throws GameLoadException {
		try {
			this.conf = new FileGameConfiguration(fileName, this);
			this.currentCycle = conf.getCycle();
			this.remaining = conf.numLemmingsInBoard();
			this.numLemmings = conf.numLemmingsInBoard();
			this.lemmingsDead = conf.numLemmingsDead();
			this.lemmingsToWin = conf.numLemmingToWin();
			this.lemmingsExit = conf.numLemingsExit();
			this.container = conf.getGameObjects();
			this.doExit = false;
		} catch (GameLoadException e) {
			throw e;
		}
	}

	public void save(String fileName) throws GameModelException {
		String rutaCompleta = Paths.get("conf", fileName).toString();

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaCompleta))) {
			writer.write(String.valueOf(this.currentCycle));
			writer.write(" ");
			writer.write(String.valueOf(this.remaining));
			writer.write(" ");
			writer.write(String.valueOf(this.lemmingsDead));
			writer.write(" ");
			writer.write(String.valueOf(this.lemmingsExit));
			writer.write(" ");
			writer.write(String.valueOf(this.lemmingsToWin));
			writer.newLine();
			container.save(writer);
			
		} catch (IOException e) {
			throw new GameModelException("Error al guardar la partida en \"" + fileName + "\"");
		}
	}

	// GameWorld methods (callbacks)

	@Override
	public boolean isInAir(Position pos) {
		Position aux = new Position(pos.getCol(), pos.getFila()+1);
		return !this.container.isSolid(aux);
	}

	@Override
	public boolean isSolid(Position pos) {
		return this.container.isSolid(pos);
	}

	@Override
	public boolean dentroDelTablero(Position p) {
		return p.getFila() >= 0 && p.getCol() >= 0 && p.getFila() < DIM_Y && p.getCol() < DIM_X;
	}

	@Override
	public boolean receiveInteractionsFrom(GameItem obj) {
		return container.receiveInteractionsFrom(obj);
	}

	@Override
	public void arrived() {
		this.lemmingsExit++;
		this.remaining--;
	}
	
	@Override
	public void dead() {
		this.lemmingsDead++;
		this.remaining--;
	}
	
	// Other methods
	public String toString() {
		return container.toString();
	}

}
