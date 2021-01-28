package config;

public interface DBConfig {
	public String DB_DRIVER = "oracle.jdbc.OracleDriver";
	public String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	public String DB_USER = "bang";
	public String DB_PASS = "123456";
}
