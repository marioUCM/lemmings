package tp1.exceptions;

import tp1.logic.Position;
import tp1.view.Messages;
public class OffBoardException extends GameModelException{
	
	public OffBoardException(Position pos) {
		super("Position "+Messages.POSITION.formatted(pos.getFila(), pos.getCol())+" is off board");
	}
	
	public OffBoardException() {
		super();
	}

	public OffBoardException(String message) {
		super(message);
	}

	public OffBoardException(String message, Throwable cause) {
		super(message, cause);
	}

	public OffBoardException(Throwable cause) {
		super(cause);
	}

	public OffBoardException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
