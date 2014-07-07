package bma.common.esp.coder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListCoder implements BaseCoder {
	
	/**
	 * List解码
	 * @param buf
	 * @return
	 * @throws IOException 
	 */
	public static List<Object> listDecoder(InputStream buf) throws IOException{
		List<Object> obj = new ArrayList<Object>();
		int size = Int32Coder.int32Decoder(buf);
		int mark = 1;
		while(mark <= size){
			obj.add(VarCoder.varDecoder(buf));
			mark++;
		}		
		return obj;				
	}
	
	/**
	 * List编码
	 * @param buf
	 * @param obj
	 * @throws IOException
	 */
	public static void listEncoder(OutputStream buf, List<Object> obj) throws IOException{
		if(obj == null){
			return;
		}
		//map长度
		Int32Coder.int32Encoder(buf, obj.size());		
		for(Object o : obj){
			VarCoder.varEncoder(buf, o);
		}
	}

	@Override
	public Object decoder(InputStream buf) throws IOException {
		// TODO Auto-generated method stub
		return listDecoder(buf);
	}

	@Override
	public void encoder(OutputStream buf, Object obj) throws IOException {
		if (obj instanceof List) {
			List<Object> s = (List<Object>) obj;
			listEncoder(buf,s);
			return ;
		}
		throw new IllegalArgumentException("not List type");		
	}

}
