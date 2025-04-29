package cart.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// 用來讓不同的 xxxDao 透過 extends BaseDao 可以連接 MySQL
public class BaseDao {
	protected static Connection conn;

	static {
		// 連線參數
		String username = "root";
		String password = "abc123";
		String dbUrl = "jdbc:mysql://localhost:3306/cart?serverTimezone=Asia/Taipei&characterEncoding=utf-8&useUnicode=true&useSSL=false";

		// 建立連線
//    try {
//      Class.forName("com.mysql.cj.jdbc.Driver"); // 註冊 MySQL Driver
//      conn = DriverManager.getConnection(dbUrl, username, password); // 連線建立
//    } catch (Exception e) {
//      e.printStackTrace();
//    }

		try {
			// ... 省略連線參數 ...
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbUrl, username, password);
			if (conn == null) { // 理論上 getConnection 失敗會拋 SQLException，但多一層保險
				throw new RuntimeException("BaseDao 初始化失敗：getConnection 返回 null");
			}
			System.out.println("BaseDao: 資料庫連線成功建立。"); // 加入成功訊息方便除錯
		} catch (ClassNotFoundException e) {
			System.err.println("嚴重錯誤：找不到 MySQL JDBC 驅動程式！請將 mysql-connector-j JAR 檔加入 WEB-INF/lib。");
			e.printStackTrace();
			throw new RuntimeException("BaseDao 初始化失敗：找不到 MySQL 驅動程式", e); // 重新拋出
		} catch (SQLException e) {
			System.err.println("嚴重錯誤：無法連線到資料庫。 URL: " + dbUrl + ", User: " + username);
			e.printStackTrace();
			throw new RuntimeException("BaseDao 初始化失敗：資料庫連線錯誤", e); // 重新拋出
		} catch (Exception e) {
			System.err.println("嚴重錯誤：BaseDao 靜態初始化時發生預期外的錯誤。");
			e.printStackTrace();
			throw new RuntimeException("BaseDao 初始化失敗：預期外的錯誤", e); // 重新拋出
		}
	}
}