package com.dollars.util;

import java.io.ByteArrayOutputStream;   
import java.io.File;   
import java.io.FileOutputStream;   
import java.io.InputStream;   
import java.net.HttpURLConnection;   
import java.net.URL;

import org.apache.log4j.Logger;

/**
 * 新建线程下载图片至本地磁盘
 * @author tom
 * 2016.1.4
 */
public class DownloadImg implements Runnable{
	
	private static Logger logger = Logger.getLogger(DownloadImg.class);
	
	private String url = "";    //图片下载地址
	private String name = "";    //图片保存名字
	private String path = "";    //图片保存地址
	
	public DownloadImg(String url, String name, String path){
		this.url = url;
		this.name = name;
		this.path = path;
	}
	
	
	/**  
     * 将图片写入到磁盘
     * @param img 图片数据流  
     * @param fileName 文件保存时的名称  
     */  
    private void writeImageToDisk(byte[] img, String fileName){
        try {
            File file = new File(path + fileName);
            FileOutputStream fops = new FileOutputStream(file);   
            fops.write(img);
            fops.flush();
            fops.close();
            logger.info("图片存至目标地址");
        } catch (Exception e) {
            e.printStackTrace();   
        }
    }
    
    /**  
     * 根据地址获得数据的字节流
     * @param strUrl 网络连接地址
     * @return
     */  
    private byte[] getImageFromNetByUrl(String strUrl){   
        try {   
            URL url = new URL(strUrl);   
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();   
            conn.setRequestMethod("GET");   
            conn.setConnectTimeout(5 * 1000);   
            InputStream inStream = conn.getInputStream(); //通过输入流获取图片数据   
            byte[] btImg = readInputStream(inStream);     //得到图片的二进制数据   
            return btImg;   
        } catch (Exception e) {   
            e.printStackTrace();   
        }   
        return null;   
    }
    
    /**  
     * 从输入流中获取数据
     * @param inStream 输入流
     * @return
     * @throws Exception
     */  
    private byte[] readInputStream(InputStream inStream) throws Exception{   
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();   
        byte[] buffer = new byte[1024];   
        int len = 0;   
        while( (len=inStream.read(buffer)) != -1 ){   
            outStream.write(buffer, 0, len);   
        }   
        inStream.close();   
        return outStream.toByteArray();   
    }

	@Override
	public void run() {
		byte[] b = getImageFromNetByUrl(url);
		writeImageToDisk(b, name + ".jpg");
	}
	
	//启动线程开始下载
	public void start(){
		Thread t = new Thread(this);
		t.start();
	}
}
