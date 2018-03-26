package com.clover.api.tools.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class DomUtils {
	private String driverName;
	private String url;
	private String user;
	private String pwd;
	private String table;

	@SuppressWarnings("unchecked")
	public DomUtils() {
		SAXBuilder builder = new SAXBuilder();
		InputStream file = ClassLoader.getSystemResourceAsStream("db.xml");
		try {
			Document document = builder.build(file);
			Element root = document.getRootElement();// ��ø��ڵ�
			List<Element> list = root.getChildren("db");
			for (Iterator<Element> it = list.iterator(); it.hasNext();) {
				Element element = it.next();
				this.driverName = element.getChildText("driverName");
				this.url = element.getChildText("url");
				this.user = element.getChildText("user");
				this.pwd = element.getChildText("password");
				this.table = element.getChildText("dafault-table");
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getDriverName() {
		return driverName;
	}

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPwd() {
		return pwd;
	}

	public String getTable() {
		return table;
	}
}
