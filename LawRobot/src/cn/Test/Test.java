package cn.Test;

import java.io.*;
import java.util.HashMap;

import cn.strong.GenerateDictionary;
import cn.strong.ImportCorpus;
import cn.strong.Segmentation;

public class Test {

	public static void main(String[] args) throws IOException {

		String filename = "dic.txt";
		HashMap hm = new HashMap();
		HashMap len = new HashMap();
		GenerateDictionary genDic = new GenerateDictionary();
		Segmentation seg;

		genDic.GenHashDic(filename, hm, len);

		System.out.println("����������Ҫ�ֽ����䣺");

		InputStreamReader reader = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(reader);
		String data = "";
		data = br.readLine();
		
		seg = new Segmentation(hm, len);
		
		String FmmTarget = seg.Fmm(data);
		String BmmTarget = seg.Bmm(data);
		
		System.out.println("FMM�㷨ͳ�ƽ��Ϊ: " + FmmTarget);
		System.out.println("BMM�㷨ͳ�ƽ��Ϊ: " + BmmTarget);
	}

}
