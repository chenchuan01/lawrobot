package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import com.dic.GenerateDictionary;
import com.dic.Segmentation;
import com.sys.common.AppExpection;

public class Test {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws IOException, AppExpection {

		String filename = "dic.txt";
		HashMap hm = new HashMap();
		HashMap len = new HashMap();
		GenerateDictionary genDic = new GenerateDictionary();
		Segmentation seg;

		genDic.genHashDic(filename, hm, len);

		System.out.println("请输入您需要分解的语句：");

		InputStreamReader reader = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(reader);
		String data = "";
		data = br.readLine();
		
		seg = new Segmentation(hm, len);
		
//		String FmmTarget = seg.Fmm(data);
		String BmmTarget = seg.Bmm(data);
		
//		System.out.println("FMM算法统计结果为: " + FmmTarget);
		System.out.println("BMM算法统计结果为: " + BmmTarget);
	}

}
