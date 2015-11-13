package demo.datasource.client;

/**
 * Created on 08.11.2015.
 */
public class RESTException extends Throwable {

	private int code;

	public RESTException(int code) {
		this.code = code;
	}

	public RESTException(String detailMessage, int code) {
		super(detailMessage);
		this.code = code;
	}

	public RESTException(String detailMessage, Throwable cause, int code) {
		super(detailMessage, cause);
		this.code = code;
	}

	public RESTException(Throwable cause, int code) {
		super(cause);
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
