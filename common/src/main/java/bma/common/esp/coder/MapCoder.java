package bma.common.esp.coder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class MapCoder implements BaseCoder {
	
	/**
	 * map解码
	 * @param buf
	 * @return
	 * @throws IOException 
	 */
	public static Map<String,Object> MapDecoder(InputStream buf) throws IOException{
		Map<String,Object> obj = new HashMap<String, Object>();
		int size = Int32Coder.int32Decoder(buf);
		String key;
		int mark = 1;
		while(mark <= size){
			key = LenStringCoder.lenStringDecoder(buf);
			obj.put(key, VarCoder.varDecoder(buf));
			mark++;
		}		
		return obj;		
	}
	
	/**
	 * map编码
	 * @param buf
	 * @param obj
	 * @throws IOException 
	 */
	public static void MapEncoder(OutputStream buf, Map<String,Object> obj) throws IOException{
		if(obj == null){
			return;
		}
		//map长度
		Int32Coder.int32Encoder(buf, obj.size());		
		for(Object o : obj.keySet()){
			LenStringCoder.lenStringEncoder(buf, String.valueOf(o));
			VarCoder.varEncoder(buf, obj.get(o));
		}
	}

	@Override
	public Object decoder(InputStream buf) throws IOException {
		return MapDecoder(buf);
	}

	@Override
	public void encoder(OutputStream buf, Object obj) throws IOException {
		if (obj instanceof Map) {
			Map<String,Object> s = (Map<String, Object>) obj;
			MapEncoder(buf,s);
			return ;
		}
		throw new IllegalArgumentException("not Map type");		
		
	}

}
