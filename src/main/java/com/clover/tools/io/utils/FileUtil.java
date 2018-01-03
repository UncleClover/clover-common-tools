package com.clover.tools.io.utils;

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
 * @author zhangdq
 * @time 2017年12月15日 下午5:51:24
 * @Email qiang900714@126.com
 */
public class FileUtil {

	/**
	 * 复制文件
	 * 
	 * @author zhangdq
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
		int length = 3500;
		RandomAccessFile raf = new RandomAccessFile("F:\\doc\\name\\name1.txt", "rw");
		FileChannel fc = raf.getChannel();
		int br = 0;
		for (int i = 0; i < raf.length(); i += length + 2) {
			MappedByteBuffer mbuff = fc.map(FileChannel.MapMode.READ_ONLY, i, length);
			System.out.println(Charset.defaultCharset().decode(mbuff).toString());
			br++;
			if (br == 10) {
				break;
			}
		}
		raf.close();
	}
}
