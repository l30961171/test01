package com.xp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class XPWriteAndReadText {
	/**
	 * 将内容写到文本中
	 * @param textname　文本名称
	 * @param content 写入的内容
	 * @param isAppend 是否追加
	 * @return
	 */
	public static boolean writeText(String path_name, String content,boolean isAppend) {
		boolean flag = false;
		File filePath = new File(path_name);
		if (!filePath.exists()) {
			try {
				filePath.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			FileWriter fw = new FileWriter(path_name,isAppend);
			fw.write(content);
			flag = true;
			if (fw != null)
				fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
    public static String readFileByChars(String fileName) {
    	String str = "";
        Reader reader = null;
        try {
            char[] tempchars = new char[1024];
            
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName),"UTF-8");
            while ((charread = reader.read(tempchars)) != -1) {
                if ((charread == tempchars.length)
                        && (tempchars[tempchars.length - 1] != '\r')) {
                    str += new String(tempchars);
                } else {
                    for (int i = 0; i < charread; i++) {
                        if (tempchars[i] == '\r') {
                            continue;
                        } else {
                            str += tempchars[i];
                        }
                    }
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return str;
    }
    
    
    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static String readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        String content = "";
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                content += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return content;
    }
    
    public static void main(String[] s){
    	String str = readFileByLines("D:/workspaces/jee_mars_activiti/BPM/res/version");
    	System.out.println(str);
    }
}
