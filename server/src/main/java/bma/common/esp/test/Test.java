package bma.common.esp.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import bma.common.esp.exception.EspExecption;
import bma.common.esp.server.core.ESNPServerScoket;
import bma.common.esp.server.processor.EFunction;
import bma.common.esp.server.processor.EHandler;
import bma.common.esp.transport.ERequest;
import bma.common.esp.transport.EResponse;

public class Test extends EHandler{
	
	private Iface iface;

	public interface Iface{
		
		public int add(int a,int b) throws EspExecption;
				
	}
	
	public Test(){
		super.getFunctionMap().put("add", new Add());
	}
	
	public class Add extends EFunction{
		
		//获取参数
		public add_arg get_add_arg(ERequest eRequest) throws EspExecption{
			return new add_arg(eRequest);
		}
		
		//获取结果
		public add_result get_add_result(int c){
			add_result result = new add_result();
			result.c = c;
			return result;
		}
		
		//发送结果
		public void send_add_result(ESNPServerScoket eTransport,EResponse eResponse,add_result result) throws IOException{
			result.writeData(eResponse);
			result.flush(eTransport,eResponse);
		}

		@Override
		public void execute(ESNPServerScoket eTransport,
				ERequest eRequest, EResponse eResponse) throws IOException {
			
			//获取参数
			add_arg arg= this.get_add_arg(eRequest);
			//调用实现
			add_result result = this.get_add_result(iface.add(arg.a, arg.b));
			//发送结果
			this.send_add_result(eTransport, eResponse, result);

		}
	}
	
	//参数对象
	public static class add_arg {
		public int a;
		public int b;
		
		private static final Map<Short, String> argMap = new HashMap<Short, String>(){{
	          put( (short)1,  "a" );
	          put( (short)2,  "b" );
        }};
		
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
	public static class add_result {
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
		
		public void flush(ESNPServerScoket eTransport,EResponse eResponse) throws IOException{
			if(eTransport == null){
				throw new EspExecption("scoket is error!");
			}
			eTransport.write(eResponse);
			eTransport.flush();
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
