package com.clover.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clover.opt.InsertCode;

public class Test {
	public static void main(String[] args) throws Exception {
//		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("funcNo", "2001");
//		map.put("inter", "Test");
//		map.put("fileurl", "E:\\setting\\code\\");
//		list.add(map);
//		InsertCode ic = new InsertCode();
//		System.out.println(ic.create(list));
		
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		for(String s : list){
			if("2".equals(s)){
				list.remove(s);
			}
		}
		for(String s : list){
			System.out.println(s);
		}
	}
}
