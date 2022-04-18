package com.meter.thorclient.console;
import com.alibaba.fastjson.JSON;

import com.meter.thorclient.clients.base.SubscribeSocket;
import com.meter.thorclient.clients.base.SubscribeSocketTransfers;
import com.meter.thorclient.clients.base.SubscribingCallback;
import com.meter.thorclient.clients.SubscribeClient;
import com.meter.thorclient.core.model.blockchain.EventSubscribingResponse;
import com.meter.thorclient.core.model.blockchain.MTRGEventSubscribingResponse;
import com.meter.thorclient.core.model.blockchain.TransferSubscribingResponse;

import org.eclipse.jetty.websocket.api.Session;





public class SubscribeClientConsole {
 

 

    public static void subcribeEvent() {
        SubscribingCallback<EventSubscribingResponse> callback = new SubscribingCallback<EventSubscribingResponse>() {
            @Override
            public void onClose(int statusCode, String reason) {
            System.out.println( "on close:" + statusCode + " reason:" + reason );
            }

            @Override
            public void onConnect(Session session) {
                System.out.println( "On connect:" + session.toString() );
            }

            @Override
            public Class<EventSubscribingResponse> responseClass() {
                return EventSubscribingResponse.class;
            }

            private String returnData(String data){
                return data;
            }

            @Override
            public void onSubscribe(EventSubscribingResponse response) {
                System.out.println( "Event Response :" + JSON.toJSONString(response) );
                returnData(JSON.toJSONString(response));
                
            }

            
        };
        SubscribeSocket socket;
        try {
            socket = SubscribeClient.subscribeEvent( null,  callback);
            Thread.sleep( 20000 );
            socket.close( 0, "user close" );
        } catch (Exception e) {
            
            
            e.printStackTrace();
        }
        

        
    }
	

    public static void subcribeTransfer() {
        SubscribingCallback<TransferSubscribingResponse> callback = new SubscribingCallback<TransferSubscribingResponse>() {
            @Override
            public void onClose(int statusCode, String reason) {
                System.out.println( "on close:" + statusCode + " reason:" + reason );
            }

            @Override
            public void onConnect(Session session) {
                System.out.println( "On connect:" + session.toString() );
            }

            @Override
            public Class<TransferSubscribingResponse> responseClass() {
                return TransferSubscribingResponse.class;
            }
            

            @Override
            public void onSubscribe(TransferSubscribingResponse response) {
                System.out.println( "Transfer Response :" + JSON.toJSONString(response) );
            }
        };
        SubscribeSocket socket;
        try {
            socket = SubscribeClient.subscribeTransfer( null,  callback);
            Thread.sleep( 20000 );
            socket.close( 0, "user close" );
        } catch (Exception e) {
            
            
            e.printStackTrace();
        }
    
    
    }	

    
    public static void subscribeMTRGTransfer() throws Exception {
        SubscribingCallback<MTRGEventSubscribingResponse> callback = new SubscribingCallback<MTRGEventSubscribingResponse>() {
            @Override
            public void onClose(int statusCode, String reason) {
                System.out.println( "on close:" + statusCode + " reason:" + reason );
            }

            @Override
            public void onConnect(Session session) {
                System.out.println( "On connect:" + session.toString() );
            }

            @Override
            public Class<MTRGEventSubscribingResponse> responseClass() {
                return MTRGEventSubscribingResponse.class;
            }

            @Override
            public void onSubscribe(MTRGEventSubscribingResponse response) {
                System.out.println( "MTRG Transfer Response :" + JSON.toJSONString(response) );
            }
        };
        SubscribeSocketTransfers socket = SubscribeClient.subscribeMTRGTransfer( null,  callback);
        Thread.sleep( 20000 );
        socket.close( 0, "user close" );
    }
	
}
