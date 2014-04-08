package bma.common.esp.framer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import bma.common.esp.coder.FixUint64Coder;
import bma.common.esp.common.FramerCommon;

/**
 * 
* @ClassName: ESNPMesNoFramer 
* @Description: 消息编号帧 
* @author zhongrisheng
* @date 2014-3-7 下午04:23:14 
*
 */
public class ESNPMesNoFramer extends ESNPBaseFramer {
	
	/**
	 * ID编号
	 */
	private long mesNo;
	
	/**
	 * 
	* <p>Title: </p> 
	* <p>Description: 从流中获取数据构建对象</p> 
	* @param framerType
	* @param in
	* @throws IOException
	 */
	public ESNPMesNoFramer(int framerType,InputStream in) throws IOException{
		super.setFramerType(framerType);
		super.setFramerBodyLength(in);
		this.setMesNo(in);
	}

	public ESNPMesNoFramer() {
		super();
	}

	public long getMesNo() {
		return mesNo;
	}

	public void setMesNo(long mesNo) {
		this.mesNo = mesNo;
	}
	
	/**
	 * 
	* @Title: setMesNo 
	* @Description: 从流中读取消息编号
	* @param @param in
	* @param @throws IOException    
	* @return void    
	* @throws
	 */
	public void setMesNo(InputStream in) throws IOException {
		if(super.getFramerType() == 0 || super.getFramerBodyLength() == 0){
			return ;
		}
		this.mesNo = FixUint64Coder.fixUint64Decoder(in);
	}
	
	/**
	 * 
	* @Title: mesNoFramerToOutputStream 
	* @Description: 将消息帧写入流
	* @param @param out
	* @param @throws IOException    
	* @return void    
	* @throws
	 */
	public void mesNoFramerToOutputStream(OutputStream out) throws IOException{	
		ByteArrayOutputStream mesNoOut = new ByteArrayOutputStream();
		FixUint64Coder.fixUint64Encoder(mesNoOut, this.mesNo);
		byte[] dataByte = mesNoOut.toByteArray();
		super.toOutputStream(out,dataByte.length);	
		out.write(dataByte);		
	}

	@Override
	public void readInputStream(int framerType, InputStream in)
			throws IOException {
		super.setFramerType(framerType);
		super.setFramerBodyLength(in);
		this.setMesNo(in);
		
	}
	
	/**
	 * 生成源帧
	 * @param mnf
	 * @return
	 */
	public ESNPMesSnoFramer tranToMesSnoFramer(){
		ESNPMesSnoFramer msf = new ESNPMesSnoFramer();
		msf.setMesSno(this.getMesNo());
		msf.setFramerType(FramerCommon.FRAMER_TYPE_SID);
		return msf;
	}
}
