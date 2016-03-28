package com.dic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.sys.common.util.LogUtil;

/**
 * GenerateDictionary.java
 */
public class GenerateDictionary {

	
	public GenerateDictionary() {
		super();
	}

	public GenerateDictionary(String filename, HashMap<String, Integer> hm,
			HashMap<Integer, String> len) throws FileNotFoundException,
			IOException {
		super();
		genHashDic(filename, hm, len);
	}

	public void genHashDic(String filename, HashMap<String, Integer> hm,
			HashMap<Integer, String> len) throws FileNotFoundException,
			IOException {
		String s = new String();
		BufferedReader in = new BufferedReader(new FileReader(filename));
		LogUtil.info(getClass(), "Refresh Dictionary Cache...");
		while ((s = in.readLine()) != null) {
			hm.put(s, s.length());
			len.put(s.length(), s);
		}
		LogUtil.info(getClass(), "Refresh Dictionary Cache: 字典文件刷新成功；共计：{0}条数据", len.size());
		in.close();
	}

}
