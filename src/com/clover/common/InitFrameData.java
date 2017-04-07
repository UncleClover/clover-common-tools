package com.clover.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clover.utils.DBUtils;
import com.clover.utils.DomUtils;

public class InitFrameData {
	private List<Map<String, String>> dbList = new ArrayList<Map<String, String>>();

	public InitFrameData() {
		DomUtils dom = new DomUtils();
		Connection conn = DBUtils.connect(dom.getDriverName(), dom.getUrl(), dom.getUser(), dom.getPwd());
		String default_table = dom.getTable();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT T.DB_DRIVER,T.DB_HOST,T.DB_PORT,T.DB_SERVICE,T.DB_INSTANCE,T.DB_NAME,T.DB_PWD,T.DB_DESC FROM " + default_table + " T ORDER BY T.CREATE_TIME DESC";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("driver", rs.getString("db_driver"));
				map.put("url", "jdbc:oracle:thin:@" + rs.getString("db_host").toLowerCase() + ":" + rs.getString("db_port").toLowerCase() + ":" + rs.getString("db_service").toLowerCase());
				map.put("user", rs.getString("db_name"));
				map.put("password", rs.getString("db_pwd"));
				map.put("desc", rs.getString("db_desc"));
				dbList.add(map);
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

	public List<Map<String, String>> getDbList() {
		return dbList;
	}
}
