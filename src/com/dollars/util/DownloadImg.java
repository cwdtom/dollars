package com.dollars.util;

import java.io.ByteArrayOutputStream;   
import java.io.File;   
import java.io.FileOutputStream;   
import java.io.InputStream;   
import java.net.HttpURLConnection;   
import java.net.URL;

import org.apache.log4j.Logger;

/**
 * �½��߳�����ͼƬ�����ش���
 * @author tom
 * 2016.1.4
 */
public class DownloadImg implements Runnable{
	
	private static Logger logger = Logger.getLogger(DownloadImg.class);
	
	private String url = "";    //ͼƬ���ص�ַ
	private String name = "";    //ͼƬ��������
	private String path = "";    //ͼƬ�����ַ
	
	public DownloadImg(String url, String name, String path){
		this.url = url;
		this.name = name;
		this.path = path;
	}
	
	
	/**  
     * ��ͼƬд�뵽����
     * @param img ͼƬ������  
     * @param fileName �ļ�����ʱ������  
     */  
    private void writeImageToDisk(byte[] img, String fileName){
        try {
            File file = new File(path + fileName);
            FileOutputStream fops = new FileOutputStream(file);   
            fops.write(img);
            fops.flush();
            fops.close();
            logger.info("ͼƬ����Ŀ���ַ");
        } catch (Exception e) {
            e.printStackTrace();   
        }
    }
    
    /**  
     * ���ݵ�ַ������ݵ��ֽ���
     * @param strUrl �������ӵ�ַ
     * @return
     */  
    private byte[] getImageFromNetByUrl(String strUrl){   
        try {   
            URL url = new URL(strUrl);   
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();   
            conn.setRequestMethod("GET");   
            conn.setConnectTimeout(5 * 1000);   
            InputStream inStream = conn.getInputStream(); //ͨ����������ȡͼƬ����   
            byte[] btImg = readInputStream(inStream);     //�õ�ͼƬ�Ķ���������   
            return btImg;   
        } catch (Exception e) {   
            e.printStackTrace();   
        }   
        return null;   
    }
    
    /**  
     * ���������л�ȡ����
     * @param inStream ������
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
	
	//�����߳̿�ʼ����
	public void start(){
		Thread t = new Thread(this);
		t.start();
	}
}
