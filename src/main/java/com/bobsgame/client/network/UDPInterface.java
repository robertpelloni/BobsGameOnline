package com.bobsgame.client.network;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;

//===============================================================================================
public interface UDPInterface
{//===============================================================================================

	public void sendAddressRequest();
	public void sendPeerConnectRequest();
	public void handleDisconnected();
	public void incomingPeerConnectResponse(MessageEvent e);
	public void update();
	public void handleMessage(ChannelHandlerContext ctx, MessageEvent e);

}