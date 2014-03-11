package bma.common.esp.server.processor;

import java.util.HashMap;
import java.util.Map;

/**
 * 
* @ClassName: EHandler 
* @Description: 服务器业务处理对象
* @author zhongrisheng
* @date 2014-3-11 上午10:18:42 
*
 */
public abstract class EHandler {
	
	private Map<String,EFunction> functionMap = new HashMap<String, EFunction>();;

	public Map<String, EFunction> getFunctionMap() {
		return functionMap;
	}

	public void setFunctionMap(Map<String, EFunction> functionMap) {
		this.functionMap = functionMap;
	}
	
}
