package pl.air.internetShop.exception;

public class NoDataFoundException extends RuntimeException {

	private static final long serialVersionUID = 1911507578550952060L;

	
	public NoDataFoundException(String message) {
		super(message);
	}
	
}
