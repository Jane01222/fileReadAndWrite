package com.xuanwu.demo.filereadandwrite;

import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @Description ������׷�ӵ��ļ�β�������ַ�ʽ��
 * 			1.A����׷���ļ���ʹ��RandomAccessFile
 * 			2.B����׷���ļ���ʹ��FileWriter
 * @author Jane1222
 * @Date 2017-10-11
 *
 */
public class AppendToFile {
	public static void main(String[] args) {
		String fileName = "C:\\Users\\Administrator\\Desktop\\ss1\\temp.txt";
        String content = "wakaka, I believe i can ! ";
   
        //������A׷���ļ�
        AppendToFile.appendToFileA(fileName, content);
        AppendToFile.appendToFileA(fileName, "append end. \n");
        
        //��ʾ�ļ�����
        JavaReadFileMethod.readFileByLines(fileName);
        
        //������B׷���ļ�
        AppendToFile.appendToFileB(fileName, content);
        AppendToFile.appendToFileB(fileName, "append end. \n");
        
        //��ʾ�ļ�����
        JavaReadFileMethod.readFileByLines(fileName);
	}
	
	
	/**
	 * A����׷���ļ���ʹ��RandomAccessFile
	 * @param filePath
	 * @param content
	 */
	public static void appendToFileA(String filePath,String content){
		try {
			RandomAccessFile randomFile = new RandomAccessFile(filePath,"rw");	//��һ����������ļ���������д��ʽ
			long fileLength = randomFile.length();	//�ļ�����
			randomFile.seek(fileLength);	//��д�ļ�ָ�������ļ�β
			randomFile.writeBytes(content);
			randomFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * B����׷���ļ���ʹ��FileWriter
	 * @param filePath
	 * @param content
	 */
	public static void appendToFileB(String filePath,String content){
		try {
			//��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�
			FileWriter fileWriter = new FileWriter(filePath,true);
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
