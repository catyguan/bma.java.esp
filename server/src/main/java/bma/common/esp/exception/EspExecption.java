package bma.common.esp.exception;

import java.io.IOException;

public class EspExecption extends IOException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EspExecption() {
		super();
	}

	public EspExecption(String message, Throwable cause) {
		super(message, cause);
	}

	public EspExecption(String message) {
		super(message);
	}

	public EspExecption(Throwable cause) {
		super(cause);
	}
}
