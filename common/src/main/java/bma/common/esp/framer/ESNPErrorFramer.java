package bma.common.esp.framer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import bma.common.esp.coder.StringCoder;

public class ESNPErrorFramer extends ESNPBaseFramer {
	
	//错误信息
	private String errorMes;
	
	public String getErrorMes() {
		return errorMes;
	}

	public void setErrorMes(String errorMes) {
		this.errorMes = errorMes;
	}

	public ESNPErrorFramer() {
		super();
	}


	public ESNPErrorFramer(int framerType,InputStream in) throws IOException{
		super.setFramerType(framerType);
		super.setFramerBodyLength(in);
		this.setErrorFramer(in);
	}
	
	public void setErrorFramer(InputStream in) throws IOException {
		if(super.getFramerType() == 0 || super.getFramerBodyLength() == 0){
			return ;
		}
		
		int length = Long.valueOf(super.getFramerBodyLength()).intValue();
		if(in.available() < length){
			return ;
		}		
		this.setErrorMes(StringCoder.stringDecoder(in));
	}
	
	/**
	 * 
	* @Title: errorFramerToOutputStream 
	* @Description: 将错误帧写入流
	* @param @param out
	* @param @throws IOException    
	* @return void    
	* @throws
	 */
	public void errorFramerToOutputStream(OutputStream out) throws IOException{
		if(errorMes == null){
			return ;
		}
			
		ByteArrayOutputStream errorOut = new ByteArrayOutputStream();
		StringCoder.stringEncoder(errorOut, errorMes);

		byte[] errorByte = errorOut.toByteArray();
		super.toOutputStream(out,errorByte.length);	
		out.write(errorByte);
	}

	@Override
	public void readInputStream(int framerType, InputStream in)
			throws IOException {
		super.setFramerType(framerType);
		super.setFramerBodyLength(in);
		this.setErrorFramer(in);
	}

}
