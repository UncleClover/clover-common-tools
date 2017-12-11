package com.clover.tools.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.clover.tools.utils.DBUtils;

public class InitTable {
	private List<String> tbList = new ArrayList<String>();

	public InitTable(String index, List<Map<String, String>> dbList) {
		if (index.equals("��ѡ�����ݿ�~~~")) {
			return;
		}
		index = index.substring(0, 1);
		int i = Integer.parseInt(index) - 1;
		Map<String, String> map = dbList.get(i);
		String driver = map.get("driver");
		String url = map.get("url");
		String user = map.get("user");
		String password = map.get("password");
		Connection conn = DBUtils.connect(driver, url, user, password);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT TABLE_NAME FROM USER_TABLES";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tbList.add(rs.getString("table_name").toLowerCase());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<String> getTbList() {
		return tbList;
	}
}
