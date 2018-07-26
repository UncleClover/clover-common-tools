package com.clover.api.tools.io.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * 文件工具类
 * 
 * @author UncleClover
 * @time 2017年12月15日 下午5:51:24
 * @Email qiang900714@126.com
 */
public class FileUtil {

	/**
	 * 复制文件
	 * 
	 * @author UncleClover
	 * @time 2017年12月15日 下午5:51:34
	 * @Email qiang900714@126.com
	 * @param args
	 * @throws IOException
	 */
	public void copy(String formPath, String toPath) {
		try {
			File srcFile = new File(formPath);
			File destFile = new File(toPath);
			InputStreamReader isr;

			isr = new InputStreamReader(new FileInputStream(srcFile), "GBK");
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(destFile), "UTF-8"); // 存为UTF-8
			int len = isr.read();
			while (-1 != len) {
				osw.write(len);
				len = isr.read();
			}

			// 刷新缓冲区的数据，强制写入目标文件
			osw.flush();
			osw.close();
			isr.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		RandomAccessFile raf = new RandomAccessFile("F:\\doc\\name\\name1.txt", "rw");

		while (raf.getFilePointer() < 100) {
			raf.seek(raf.getFilePointer());
			names(new String(raf.readLine().getBytes("ISO-8859-1"), "UTF-8"));
			break;
		}
		
		raf.close();
	}
	
	public static void names(String names){
		String[] nameArray = names.split(";");
		for(String name : nameArray){
			System.out.println(name);
		}
	}
}
