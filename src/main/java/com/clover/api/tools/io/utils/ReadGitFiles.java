package com.clover.api.tools.io.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadGitFiles {
	public static void main(String[] args) throws IOException {
		String path = "E:\\workspace\\coms\\channel\\";
		File dir = new File(path);
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.getName().startsWith("chan")) {
				String filePath = file.getAbsolutePath() + "\\.git\\config";
				FileReader reader = new FileReader(filePath);
				BufferedReader br = new BufferedReader(reader);
				String str = null;
				String fileContent = "";
				while ((str = br.readLine()) != null) {
					if(str.contains("gitlab-console.asiainfo.com") && str.contains("http:")) {
						str = str.replace("http:", "https:").replaceAll("gitlab-console.asiainfo.com", "gitlab.asiainfo.com");
					}
					fileContent = fileContent + str + "\n";
				}
				System.out.println(fileContent);
				br.close();
				reader.close();
				
				FileWriter writer = new FileWriter(filePath);
				BufferedWriter bw = new BufferedWriter(writer);
				bw.write(fileContent.toString());

				bw.close();
				writer.close();
			}
		}
	}
}
