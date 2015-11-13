package demo.datasource.repository;

/**
 * Created on 08.11.2015.
 */
public class DataSourceException extends Throwable {

	private int code;

	public DataSourceException(Throwable cause, int code) {
		super(cause);
		this.code = code;
	}

	public DataSourceException(int code) {
		this.code = code;
	}

	public DataSourceException(String detailMessage, int code) {
		super(detailMessage);
		this.code = code;
	}

	public DataSourceException(String detailMessage, Throwable cause, int code) {
		super(detailMessage, cause);
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
