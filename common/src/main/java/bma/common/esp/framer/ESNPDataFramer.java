package bma.common.esp.framer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import bma.common.esp.coder.LenStringCoder;
import bma.common.esp.coder.VarCoder;
import bma.common.esp.framer.vo.DataBody;

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
	 * 数据list
	 */
	private List<DataBody> dataList;
	
	public ESNPDataFramer(){
		super();
	}
	
	public ESNPDataFramer(int framerType,InputStream in) throws IOException{
		super.setFramerType(framerType);
		super.setFramerBodyLength(in);
		this.setDataList(in);
	}
	
	public List<DataBody> getDataList() {
		return dataList;
	}

	public void setDataList(List<DataBody> dataList) {
		this.dataList = dataList;
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
	public void setDataList(InputStream in) throws IOException{
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
		
		DataBody dataBody;
		dataList = new ArrayList<DataBody>();
		int t;
		while(dataIn.available() != 0){
			dataBody = new DataBody();
			dataBody.setDataName(LenStringCoder.lenStringDncoder(dataIn));
			t = dataIn.read();
			dataBody.setDataType(t);
			dataBody.setData(VarCoder.varDncoder(dataIn, t, false));
			dataList.add(dataBody);
		}	
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
		if(dataList == null || dataList.isEmpty()){
			return ;
		}
		
		ByteArrayOutputStream dataOut = new ByteArrayOutputStream();
		for(DataBody d : dataList){
			LenStringCoder.lenStringEncoder(dataOut, d.getDataName());
			VarCoder.varEncoder(dataOut, d.getDataType(), d.getData());
		}
		byte[] dataByte = dataOut.toByteArray();
		super.toOutputStream(out,dataByte.length);	
		out.write(dataByte);
	}

	

}
