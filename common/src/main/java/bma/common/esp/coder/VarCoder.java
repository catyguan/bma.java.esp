package bma.common.esp.coder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import bma.common.esp.common.VarTypeCommon;
import bma.common.esp.utils.BaseTypeTool;


public class VarCoder implements BaseCoder {
	
	public static void varEncoder(OutputStream buf, Object obj) throws IOException{
		if(obj == null){
			return ;
		}
		Class<?> classType = obj.getClass(); 
		varEncoder(buf,BaseTypeTool.getVarTypeByClass(classType.getSimpleName()),obj);
	}


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
	
	public static Object varDecoder(InputStream buf) throws IOException{
		int type = 0;
		return varDecoder(buf,type,true);
	}
	

	public static Object varDecoder(InputStream buf,int type,boolean readType) throws IOException{
		if(readType){
			type = buf.read();
		}
		switch (type) {
		case VarTypeCommon.TYPE_INT32://int32
			return Int32Coder.int32Decoder(buf);
		case VarTypeCommon.TYPE_INT64://int64
			return Int64Coder.int64Decoder(buf);
		case VarTypeCommon.TYPE_FLOAT32://float32
			return Float32Coder.float32Decoder(buf);
		case VarTypeCommon.TYPE_FLOAT64://float64
			return Float64Coder.float64Decoder(buf);
		case VarTypeCommon.TYPE_LEN_STRING://lenString
			return LenStringCoder.lenStringDecoder(buf);
		default:
			break;
		}
		return null;
	}


	@Override
	public Object decoder(InputStream buf) throws IOException {
		return varDecoder(buf);
	}


	@Override
	public void encoder(OutputStream buf, Object obj) throws IOException {
		varEncoder(buf,obj);	
	}
}
