package bma.common.esp.server.processor;

import java.io.IOException;

import bma.common.esp.server.core.ESNPServerScoket;
import bma.common.esp.transport.ERequest;
import bma.common.esp.transport.EResponse;

/**
 * 
* @ClassName: EFunction 
* @Description: 业务处理对象方法 
* @author zhongrisheng
* @date 2014-3-11 上午10:19:10 
*
 */
public abstract class EFunction {
	
	/**
	 * 
	* @Title: execute 
	* @Description: 方法执行
	* @param @param eTransport
	* @param @param eRequest
	* @param @param eResponse    
	* @return void    
	* @throws
	 */
	public abstract void execute(ESNPServerScoket eTransport,
			ERequest eRequest, EResponse eResponse) throws IOException;

}
