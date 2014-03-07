package bma.common.esp.coder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.google.gson.Gson;

public class VarCoder extends BaseCoder {


	public static void varEncoder(OutputStream buf,Gson g) throws IOException{

	}
	

	public static Gson varDncoder(InputStream buf) throws IOException{
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
