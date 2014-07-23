package bma.common.esp.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import bma.common.esp.client.core.ESNPSocketClient;
import bma.common.esp.exception.EspExecption;
import bma.common.esp.po.EsnpAddressPo;
import bma.common.esp.transport.ERequest;
import bma.common.esp.transport.EResponse;
import bma.common.esp.base.EBase;

public class Test{
	
	private Iface iface;

	public interface Iface{
		
		public int add(int a,int b) throws EspExecption;
				
	}
	
	/*
	 * 同步客户端
	 */
	public class SyncClient extends ESNPSocketClient implements Iface {
		
	    public void send_add(int a, int b) throws EspExecption {
	      add_arg args = new add_arg();
	      args.a = a;
	      args.b = b;
	      sendEsnp(new EsnpAddressPo("Test","add"), args);
	    }

	    public int recv_add() throws EspExecption {
	      add_result result = new add_result();
	      reviceEsnp(result, new EsnpAddressPo("Test","add"));
	      return result.c;
	    }

		@Override
		public int add(int a, int b) throws EspExecption {
			send_add(a,b);
			return recv_add();
		}
		
		
	}
	
	//参数对象
	public static class add_arg extends EBase {
		public int a;
		public int b;
		
		private static final Map<Short, String> argMap = new HashMap<Short, String>(){{
	          put( (short)1,  "a" );
	          put( (short)2,  "b" );
        }};
        
        public add_arg(){
        	
        }
		
		//初始化对象数据
		public add_arg(ERequest eRequest) throws EspExecption{
			if(eRequest == null){
				throw new EspExecption("request content is error!");
			}
			//获取请求参数
			Map<String, Object> dataMap = eRequest.getData();
			
			//设置对象参数
			for(Entry<Short, String> e: argMap.entrySet()){
				setArgFactory(dataMap, e.getKey());
			}
			
		}	
		
		public void setArgFactory(Map<String, Object> dataMap,short key) throws EspExecption{
			if(!dataMap.containsKey(argMap.get(key))){
				throw new EspExecption("arg " + argMap.get(key) + " doesn't exist!");
			}
			
			Object obj = dataMap.get(argMap.get(key));
			
			switch (key) {
			case (short)1:
				if(!(obj instanceof Integer)){
					throw new EspExecption("arg " + argMap.get(key) + " is not Integer !");
				}
				this.a = (Integer)obj;
				break;
			case (short)2:
				if(!(obj instanceof Integer)){
					throw new EspExecption("arg " + argMap.get(key) + " is not Integer !");
				}
				this.b = (Integer)obj;
				break;
			default:
				break;
			}
		}
		
	}
	
	//结果对象
	public static class add_result extends EBase {
		public int c;
		
		private static final Map<Short, String> resultMap = new HashMap<Short, String>(){{
	          put( (short)1,  "c" );
		}};
		
		public void writeData(EResponse eResponse) throws EspExecption{
			if(eResponse == null){
				throw new EspExecption("response content is error!");
			}
			
			//设置对象参数
			for(Entry<Short, String> e: resultMap.entrySet()){
				writeResultFactory(eResponse, e.getKey(), e.getValue());
			}
			
		}
		
		public void writeResultFactory(EResponse eResponse,short key,String resultName) throws EspExecption{
			switch (key) {
			case (short)1:
				eResponse.setData(resultName, this.c);
				break;
			default:
				break;
			}
		}		
		
	}

	public Iface getIface() {
		return iface;
	} 

	public void setIface(Iface iface) {
		this.iface = iface;
	}
	

}
