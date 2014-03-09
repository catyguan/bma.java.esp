package bma.common.esp.coder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import bma.common.esp.common.VarTypeCommon;

import com.google.gson.Gson;

public class VarCoder implements BaseCoder {


	public static void varEncoder(OutputStream buf,int type, Object obj) throws IOException{		
		switch (type) {
		case VarTypeCommon.TYPE_INT32://int32
			if (obj instanceof Integer) {
				Integer i = (Integer) obj;
				buf.write(type);
				Int32Coder.int32Encoder(buf, i);
				return ;
			}
			throw new IllegalArgumentException("not int type");	
		case VarTypeCommon.TYPE_INT64://int64
			if (obj instanceof Long) {
				Long l = (Long) obj;
				buf.write(type);
				Int64Coder.int64Encoder(buf, l);
				return ;
			}
			throw new IllegalArgumentException("not long type");
		case VarTypeCommon.TYPE_FLOAT32://float32
			if (obj instanceof Float) {
				Float f = (Float) obj;
				buf.write(type);
				Float32Coder.float32Encoder(buf, f);
				return ;
			}
			throw new IllegalArgumentException("not float type");
		case VarTypeCommon.TYPE_FLOAT64://float64
			if (obj instanceof Double) {
				Double d = (Double) obj;
				buf.write(type);
				Float64Coder.float64Encoder(buf, d);
				return ;
			}
			throw new IllegalArgumentException("not double type");
		case VarTypeCommon.TYPE_LEN_STRING://lenString
			if (obj instanceof String) {
				String s = (String) obj;
				buf.write(type);
				LenStringCoder.lenStringEncoder(buf, s);
				return ;
			}
			throw new IllegalArgumentException("not string type");
		default:
			break;
		}
	}
	

	public static Object varDncoder(InputStream buf,int type,boolean readType) throws IOException{
		int t = 0;
		if(readType){
			t = buf.read();
		}
		switch (type) {
		case VarTypeCommon.TYPE_INT32://int32
			if(!readType || (t == VarTypeCommon.TYPE_INT32 && readType)){
				return Int32Coder.int32Dncoder(buf);
			}
			throw new IllegalArgumentException("var type not match");
		case VarTypeCommon.TYPE_INT64://int64
			if(!readType || (t == VarTypeCommon.TYPE_INT64 && readType)){
				return Int64Coder.int64Dncoder(buf);
			}
			throw new IllegalArgumentException("var type not match");
		case VarTypeCommon.TYPE_FLOAT32://float32
			if(!readType || (t == VarTypeCommon.TYPE_FLOAT32 && readType)){
				return Float32Coder.float32Dncoder(buf);
			}
			throw new IllegalArgumentException("var type not match");
		case VarTypeCommon.TYPE_FLOAT64://float64
			if(!readType || (t == VarTypeCommon.TYPE_FLOAT64 && readType)){
				return Float64Coder.float64Dncoder(buf);
			}
			throw new IllegalArgumentException("var type not match");
		case VarTypeCommon.TYPE_LEN_STRING://lenString
			if(!readType || (t == VarTypeCommon.TYPE_LEN_STRING && readType)){
				return LenStringCoder.lenStringDncoder(buf);
			}
			throw new IllegalArgumentException("var type not match");
		default:
			break;
		}
		return null;
	}


	@Override
	public Object decoder(InputStream buf) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void encoder(OutputStream buf, Object obj) throws IOException {
		// TODO Auto-generated method stub
		
	}
}
