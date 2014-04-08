package bma.common.esp.framer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import bma.common.esp.coder.LenStringCoder;
import bma.common.esp.coder.VarCoder;

/**
 * 
* @ClassName: ESNPDataFramer 
* @Description: 数据帧 
* @author zhongrisheng
* @date 2014-3-7 下午04:59:12 
*
 */
public class ESNPDataFramer extends ESNPBaseFramer {
	
	
	/**
	 * 数据名
	 */
	private String dataName;
	
	/**
	 * 数据类型
	 */
	private int dataType;
	
	
	/**
	 * 数据
	 */
	private Object data;
	
	
	public ESNPDataFramer(){
		super();
	}
	
	public ESNPDataFramer(int framerType,InputStream in) throws IOException{
		super.setFramerType(framerType);
		super.setFramerBodyLength(in);
		this.setDataFramer(in);
	}
		
	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 
	* @Title: setDataList 
	* @Description: 从流中读取数据信息
	* @param @param in
	* @param @throws IOException    
	* @return void    
	* @throws
	 */
	public void setDataFramer(InputStream in) throws IOException{
		if(super.getFramerType() == 0 || super.getFramerBodyLength() == 0){
			return ;
		}
		
		int length = Long.valueOf(super.getFramerBodyLength()).intValue();
		if(in.available() < length){
			return ;
		}
		byte[] dataByte = new byte[length];
		in.read(dataByte, 0, length);
		InputStream dataIn = new ByteArrayInputStream(dataByte);
		
		int t;
		this.setDataName(LenStringCoder.lenStringDecoder(dataIn));
		t = dataIn.read();
		this.setDataType(t);
		this.setData(VarCoder.varDecoder(dataIn, t, false));
		dataIn.close();
	}
	
	/**
	 * 
	* @Title: addressFramerToOutputStream 
	* @Description: 将地址帧写入流
	* @param @param out
	* @param @throws IOException    
	* @return void    
	* @throws
	 */
	public void dataFramerToOutputStream(OutputStream out) throws IOException{
		if(dataName == null || data == null){
			return ;
		}
		
		ByteArrayOutputStream dataOut = new ByteArrayOutputStream();
		LenStringCoder.lenStringEncoder(dataOut, dataName);
		VarCoder.varEncoder(dataOut, dataType, data);
		byte[] dataByte = dataOut.toByteArray();
		super.toOutputStream(out,dataByte.length);	
		out.write(dataByte);
	}

	@Override
	public void readInputStream(int framerType, InputStream in)
			throws IOException {
		super.setFramerType(framerType);
		super.setFramerBodyLength(in);
		this.setDataFramer(in);		
	}

	

}
