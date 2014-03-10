package bma.common.esp.utils;

import bma.common.esp.common.VarTypeCommon;

public class BaseTypeTool {
	
	//java 合并两个byte数组
	public static byte[] byteMerger(byte[] firstByte, byte[] secondByte){
		byte[] resultByte = new byte[ firstByte.length + secondByte.length ];
		System.arraycopy(firstByte, 0, resultByte, 0, firstByte.length);
		System.arraycopy(secondByte, 0, resultByte, firstByte.length, secondByte.length);
		return resultByte;
	}
	
	/**
	 * 
	* @Title: getVarTypeByClass 
	* @Description: 根据类型名称获取类型值
	* @param @param className
	* @param @return    
	* @return int    
	* @throws
	 */
	public static int getVarTypeByClass(String className){
		if("String".equals(className)){
			return VarTypeCommon.TYPE_LEN_STRING;
		} else if("Float".equals(className)){
			return VarTypeCommon.TYPE_FLOAT32;
		} else if("Double".equals(className)){
			return VarTypeCommon.TYPE_FLOAT64;
		} else if("Boolean".equals(className)){
			return VarTypeCommon.TYPE_BOOLEAN;
		} else if("Short".equals(className)){
			return VarTypeCommon.TYPE_INT16;
		} else if("Integer".equals(className)){
			return VarTypeCommon.TYPE_INT32;
		} else if("Long".equals(className)){
			return VarTypeCommon.TYPE_INT64;
		} else if("Map".equals(className)){
			return VarTypeCommon.TYPE_MAP;
		} else if("List".equals(className)){
			return VarTypeCommon.TYPE_LIST;
		} else if("Byte".equals(className)){
			return VarTypeCommon.TYPE_LEN_BYTES;
		} else {
			return 0;
		}
	}
	
	

}

