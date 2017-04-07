package com.clover.opt;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class InsertCode {
	public static String create(List<Map<String, Object>> list) {
		String funcNo = list.get(0).get("funcNo").toString();
		String fileurl = list.get(0).get("fileurl").toString();
		try {
			File dirFile = new File(fileurl);
			if (!dirFile.exists()) {
				return "1001"; // 目录不存在
			}
			funcNo = "Function" + funcNo;
			String fileName = fileurl + File.separator + funcNo + ".java";
			File file = new File(fileName);
			if (file.exists()) {
				file.delete();
			}
			String fileContent = getHeader(funcNo) + getBody(list) + getFooter();
			FileOutputStream fo = new FileOutputStream(file);
			FileChannel fc = fo.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(fileContent.getBytes().length);
			buffer.put(fileContent.getBytes());
			buffer.flip();
			fc.write(buffer);
			fo.close();
			getBody(list);
		} catch (Exception e) {
			e.printStackTrace();
			return "9999";
		}
		return "0000";
	}

	public static String getHeader(String funcNo) {
		StringBuffer header = new StringBuffer();
		header.append("\n\nimport java.util.List;\n");
		header.append("import java.util.ArrayList;\n");
		header.append("import java.util.Map;\n");
		header.append("import java.util.HashMap;\n");
		header.append("import java.util.Iterator;\n");
		header.append("import org.apache.log4j.Logger;\n");
		header.append("import com.thinkive.base.jdbc.DataRow;\n");
		header.append("import com.thinkive.base.util.StringHelper;\n");
		header.append("import com.thinkive.server.BaseFunction;\n");
		header.append("import com.thinkive.server.InvokeException;\n");
		header.append("import com.thinkive.server.ResultVo;\n\n");
		header.append("/**\n");
		header.append(" *@desc \n");
		header.append(" *@author zhangdq\n");
		header.append(" *@time " + new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date()) + "\n");
		header.append(" *@version 1.0\n");
		header.append(" */\n");
		header.append("public class " + funcNo + " extends BaseFunction{\n");
		header.append("\tprivate static final Logger logger = Logger.getLogger(" + funcNo + ".class);\n");
		header.append("\tpublic ResultVo execute() throws InvokeException{\n");
		header.append("\t\tResultVo rv = new ResultVo();\n\n");
		return header.toString();
	}

	public static String getBody(List<Map<String, Object>> cols) {
		StringBuffer body = new StringBuffer();
		cols.remove(0);
		body.append("\t\tMap<String, String> dataMap = new HashMap<String, String>();\n");
		for (Map<String, Object> map : cols) {
			boolean flag = (Boolean) map.get("flag");
			String column = (String) map.get("column");
			body.append("\t\tString " + column + "= this.getStrParameter(\"" + column + "\");\n");
			if (flag) {
				body.append("\t\tif(StringHelper.isBlank(" + column + ")){\n");
				body.append("\t\t\tthrow new InvokeException(getErrorNo(0), \"" + column + "不能为空\");\n");
				body.append("\t\t}\n");
				body.append("\t\tdataMap.put(\"" + column + "\"," + column + ");\n\n");
			} else {
				body.append("\t\tif(StringHelper.isNotBlank(" + column + ")){\n");
				body.append("\t\t\tdataMap.put(\"" + column + "\"," + column + ");\n");
				body.append("\t\t}\n\n");
			}
		}
		return body.toString();
	}

	public static String getFooter() {
		StringBuffer footer = new StringBuffer();
		footer.append("\t\treturn rv;\n");
		footer.append("\t}\n");
		footer.append("}\n");
		return footer.toString();
	}
}
