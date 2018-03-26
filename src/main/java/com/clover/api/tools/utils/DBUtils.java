package com.clover.api.tools.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	private static Connection connect = null;

	public static Connection connect(String driver,String dburl,String dbuser,String pwd) {
		try {
			// ����Oracle������
			Class.forName(driver);
			
			// ��ȡ���ݿ�����
			connect = DriverManager.getConnection(dburl, dbuser, pwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connect;
	}
}
