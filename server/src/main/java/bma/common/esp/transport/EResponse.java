package bma.common.esp.transport;

import java.util.ArrayList;
import java.util.List;

import bma.common.esp.common.FramerCommon;
import bma.common.esp.exception.EspExecption;
import bma.common.esp.framer.ESNPDataFramer;
import bma.common.esp.framer.ESNPMesNoFramer;
import bma.common.esp.framer.ESNPMesSnoFramer;
import bma.common.esp.framer.ESNPMesTypeFramer;
import bma.common.esp.utils.BaseTypeTool;

/**
 * 
* @ClassName: EResponseTransport 
* @Description: ESNP响应 
* @author zhongrisheng
* @date 2014-3-10 下午03:11:56 
*
 */
public class EResponse {
	
	/**
	 * 消息ID
	 */
	private ESNPMesNoFramer mesNo;
	
	/**
	 * 消息源ID
	 */
	private ESNPMesSnoFramer mesSno;
	
	/**
	 * 消息类型
	 */
	private ESNPMesTypeFramer mesType;
	
	/**
	 * 数据
	 */
	private List<ESNPDataFramer> dataList;
	
	public EResponse(){
		dataList = new ArrayList<ESNPDataFramer>();
	}
	
	public void addDataFramer(ESNPDataFramer df){
		this.dataList.add(df);
	}
	
	/**
	 * @throws EspExecption 
	 * 
	* @Title: setData 
	* @Description: 设置业务数据
	* @param @param dataName
	* @param @param obj    
	* @return void    
	* @throws
	 */
	public void setData(String dataName,Object obj) throws EspExecption{
		if(obj == null){
			return ;
		}
		
		Class<?> classType = obj.getClass(); 
		int type = BaseTypeTool.getVarTypeByClass(classType.getSimpleName());
		
		if(type == 0){
			throw new EspExecption("obj not match type!");
		}
		
		ESNPDataFramer dataF = new ESNPDataFramer();
		dataF.setFramerType(FramerCommon.FRAMER_TYPE_DATA);
		dataF.setDataName(dataName);
		
		dataF.setDataType(type);
		dataF.setData(obj);	
		this.dataList.add(dataF);
	}

	public ESNPMesNoFramer getMesNo() {
		return mesNo;
	}

	public void setMesNo(ESNPMesNoFramer mesNo) {
		this.mesNo = mesNo;
	}

	public ESNPMesSnoFramer getMesSno() {
		return mesSno;
	}

	public void setMesSno(ESNPMesSnoFramer mesSno) {
		this.mesSno = mesSno;
	}

	public ESNPMesTypeFramer getMesType() {
		return mesType;
	}

	public void setMesType(ESNPMesTypeFramer mesType) {
		this.mesType = mesType;
	}

	public List<ESNPDataFramer> getDataList() {
		return dataList;
	}

	public void setDataList(List<ESNPDataFramer> dataList) {
		this.dataList = dataList;
	}
	
}
