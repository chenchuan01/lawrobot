package com.dic.impl;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.dic.DictionaryManage;
import com.dic.GenerateDictionary;
import com.dic.Segmentation;
import com.sys.common.AppExpection;
import com.sys.common.ConfigKeys;
import com.sys.common.util.ConfigUtil;

/**
 *
 *DictionaryManageImpl.java
 */
@Component
public class DictionaryManageImpl implements DictionaryManage {
	private Segmentation segmentation;
	
	@Override
	public Segmentation getSegmentation() throws AppExpection {
		if(segmentation==null||
				segmentation.getMapDic()==null){
			refreshDictionary();
		}
		return segmentation;
	}

	@Override
	public void refreshDictionary() throws AppExpection {
		segmentation = null;
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		HashMap<Integer, String> len = new HashMap<Integer, String>();
		GenerateDictionary genDic = new GenerateDictionary();
		try {
			genDic.genHashDic(ConfigUtil.getStrVal(ConfigKeys.DIC_FILE), hm, len);
		} catch (Exception e) {
			throw new AppExpection("DictionaryManageImpl.refreshDictionary()", "×ÖµäÎÄ¼þ¶ÁÈ¡Ê§°Ü",e);
		}
		segmentation = new Segmentation(hm, len);
	}

}
