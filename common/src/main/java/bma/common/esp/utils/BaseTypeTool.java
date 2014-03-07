package bma.common.esp.utils;

public class BaseTypeTool {
	
	//java 合并两个byte数组
	public static byte[] byteMerger(byte[] firstByte, byte[] secondByte){
		byte[] resultByte = new byte[ firstByte.length + secondByte.length ];
		System.arraycopy(firstByte, 0, resultByte, 0, firstByte.length);
		System.arraycopy(secondByte, 0, resultByte, firstByte.length, secondByte.length);
		return resultByte;
	}
	
	

}
