package com.clover.tools.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clover.tools.utils.DBUtils;

public class InitColumn {
	private List<Map<String, String>> colList = new ArrayList<Map<String, String>>();

	public InitColumn(String tbName, String index, List<Map<String, String>> dbList) {
		index = index.substring(0, 1);
		int i = Integer.parseInt(index) - 1;
		Map<String, String> dbMap = dbList.get(i);
		String driver = dbMap.get("driver");
		String url = dbMap.get("url");
		String user = dbMap.get("user");
		String password = dbMap.get("password");
		Connection conn = DBUtils.connect(driver, url, user, password);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT T.COLUMN_NAME,T.DATA_TYPE,T.DATA_LENGTH,T.NULLABLE FROM USER_TAB_COLUMNS T WHERE T.TABLE_NAME=? ORDER BY T.COLUMN_ID ASC";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, tbName.toUpperCase());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("column", rs.getString("column_name").toLowerCase());
				map.put("type", rs.getString("data_type").toLowerCase());
				map.put("length", rs.getString("data_length").toLowerCase());
				map.put("nullable", rs.getString("nullable"));
				colList.add(map);
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

	public List<Map<String, String>> getColList() {
		return colList;
	}
}
