package bma.common.esp.client.core;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import bma.common.esp.client.handle.OutputHandle;

public class ESNPClient {
	final org.slf4j.Logger log = org.slf4j.LoggerFactory
			.getLogger(ESNPClient.class);

	protected String serverHost;
	protected int serverPost;

	protected ClientBootstrap bootstrap;

	public ESNPClient() {
		super();
	}	
	
	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	public int getServerPost() {
		return serverPost;
	}

	public void setServerPost(int serverPost) {
		this.serverPost = serverPost;
	}

	public ClientBootstrap getClient() {
        //创建客户端channel的辅助类,发起connection请求   
        bootstrap = new ClientBootstrap(  
                new NioClientSocketChannelFactory(  
                        Executors.newCachedThreadPool(),  
                        Executors.newCachedThreadPool()));  
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {  
            public ChannelPipeline getPipeline() {  
                ChannelPipeline pipeline =  Channels.pipeline();  
                pipeline.addLast("handler", new OutputHandle());  
                return pipeline;  
            }  
        }); 
        return bootstrap;
	};
	
	public ChannelFuture connet(){
		//创建无连接传输channel的辅助类(UDP),包括client和server  
        ChannelFuture future = bootstrap.connect(new InetSocketAddress(  
        		serverHost, serverPost));  
        return future;
	}

}
