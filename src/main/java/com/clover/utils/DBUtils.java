package com.clover.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	private static Connection connect = null;

	public static Connection connect(String driver,String dburl,String dbuser,String pwd) {
		try {
			// 加载Oracle驱动类
			Class.forName(driver);
			
			// 获取数据库连接
			connect = DriverManager.getConnection(dburl, dbuser, pwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connect;
	}
}
