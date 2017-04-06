package com.test.iocard;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TimerTask;
import java.util.TooManyListenersException;

import com.xp.util.XPProperties;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

/**
 * 启动IO卡
 * @author Administrator
 *
 */
public class RunIOCard extends TimerTask implements SerialPortEventListener {

	private String cardNumber = XPProperties.get("iocard.CardNumber");
	private String endFlag = XPProperties.get("iocard.endFlag");
	private String portname = XPProperties.get("iocard.portname");
	
	protected CommPortIdentifier portId;
	protected SerialPort serialPort;
	protected OutputStream outputStream = null;   
	protected InputStream inputStream = null;
	
	@Override
	public void run() {
		openSerialPort();
		
	}
	
	/** 
     * 打开串行端口 
     * @since 2012-3-23 上午12:03:07 
     */  
    public void openSerialPort() {   
        // 获取要打开的端口  
        try {  
            portId = CommPortIdentifier.getPortIdentifier(portname);  
        } catch (NoSuchPortException e) {  
            System.out.println("抱歉,没有找到"+portname+"串行端口号!");  
            return ;  
        }  
        // 打开端口  
        try {  
            serialPort = (SerialPort) portId.open("JavaRs232", 2000);  
            System.out.println(portname+"串口已经打开!");  
        } catch (PortInUseException e) {  
        	System.out.println(portname+"端口已被占用,请检查!");  
            return ;  
        }  
          
        // 设置端口参数  
        try {  
            int rate = 9600;  
            int data = 8;  
            int stop = 1;  
            int parity = 0;  
            serialPort.setSerialPortParams(rate,data,stop,parity);  
        } catch (UnsupportedCommOperationException e) {  
        	System.out.println(e.getMessage());  
        }  
  
        // 打开端口的IO流管道   
        try {   
            outputStream = serialPort.getOutputStream();   
            inputStream = serialPort.getInputStream();   
        } catch (IOException e) {  
        	System.out.println(e.getMessage());  
        }   
  
        // 给端口添加监听器  
        try {   
            serialPort.addEventListener(this);   
        } catch (TooManyListenersException e) {  
        	System.out.println(e.getMessage());  
        }   
  
        serialPort.notifyOnDataAvailable(true);   
    }   
      
    /** 
     * 给串行端口发送数据 
     * @since 2012-3-23 上午12:05:00 
     */  
    public void sendDataToSeriaPort(String msg) {   
        try {   
        	System.out.println("发送指令："+msg);
            outputStream.write(msg.getBytes()); 
            outputStream.flush();   
        } catch (IOException e) {   
        	System.out.println(e.getMessage());  
        }   
    }   

    /** 
     * 端口事件监听 
     */  
    private String groupData = "";
    @Override
    public void serialEvent(SerialPortEvent event) {  
        switch (event.getEventType()) {  
            case SerialPortEvent.BI:  
            case SerialPortEvent.OE:  
            case SerialPortEvent.FE:  
            case SerialPortEvent.PE:  
            case SerialPortEvent.CD:  
            case SerialPortEvent.CTS:  
            case SerialPortEvent.DSR:  
            case SerialPortEvent.RI:  
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:  
                break;  
            case SerialPortEvent.DATA_AVAILABLE:  
                byte[] readBuffer = new byte[50];  
  
            try {  
                while (inputStream.available() > 0) {  
                    inputStream.read(readBuffer);  
                }  
                StringBuilder receivedMsg = new StringBuilder("");  
                receivedMsg.append(new String(readBuffer).trim()).append("");  
                groupData += receivedMsg.toString();
                if((groupData.startsWith(cardNumber) && groupData.endsWith(endFlag))){
                	receivedMsg.append("\n");
                	if(groupData.length() > 10){
	                	//清空
                		String msg = groupData.substring(0, 6) + endFlag;
	                	sendDataToSeriaPort(msg);
                	}
                	groupData = "";
                }
                System.out.println(receivedMsg.toString());  
            } catch (IOException e) {  
            	System.out.println(e.getMessage());  
            }  
        }  
    }  

	public static void main(String[] s) {
		RunIOCard inst = new RunIOCard();
		inst.run();
	}

}
