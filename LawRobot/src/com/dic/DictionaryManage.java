package com.dic;

import com.sys.common.AppExpection;


/**
 *
 *DictionaryManage.java
 */
public interface DictionaryManage {
	
	Segmentation getSegmentation() throws AppExpection;
	
	void refreshDictionary() throws AppExpection;
	
	
}
