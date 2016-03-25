package com.Test;

import java.io.*;
import java.util.HashMap;

import com.strong.GenerateDictionary;
import com.strong.ImportCorpus;
import com.strong.Segmentation;

public class Test {

	public static void main(String[] args) throws IOException {

		String filename = "dic.txt";
		HashMap hm = new HashMap();
		HashMap len = new HashMap();
		GenerateDictionary genDic = new GenerateDictionary();
		Segmentation seg;

		genDic.GenHashDic(filename, hm, len);

		System.out.println("请输入您需要分解的语句：");

		InputStreamReader reader = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(reader);
		String data = "";
		data = br.readLine();
		
		seg = new Segmentation(hm, len);
		
		String FmmTarget = seg.Fmm(data);
		String BmmTarget = seg.Bmm(data);
		
		System.out.println("FMM算法统计结果为: " + FmmTarget);
		System.out.println("BMM算法统计结果为: " + BmmTarget);
	}

}
