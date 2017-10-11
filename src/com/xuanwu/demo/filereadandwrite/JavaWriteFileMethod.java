package com.xuanwu.demo.filereadandwrite;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @Description �ܽ����Javaд���ļ��ļ��ַ�ʽ��
 * 			1.FileWriterд�룺
 * 				�ַ���д���ַ����ļ���Ĭ������£�����ʹ���µ�����ȡ���������е����ݣ���ָ��һ��true ��������ֵ��ΪFileWritter���캯���ĵڶ������������ᱣ�����е����ݣ���׷�����������ļ���ĩβ��
 * 			2.BufferedWriterд�룺
 * 				�����ַ���BufferedWriter ����һ���ַ������������ַ����ݡ���ͬ���ֽ���������ת�����ֽڣ��������ֱ��д�ַ�����������ַ����ݱ��浽�ļ���
 * 			3.FileOutputStreamд�룺
 * 				�ļ��������һ�����ڴ���ԭʼ���������ݵ��ֽ����ࡣΪ�˽�����д�뵽�ļ��У����뽫����ת��Ϊ�ֽڣ������浽�ļ���
 * 			4.RandomAccessFileд�룺
 * 				ͨ���������ļ�׷��
 *	���У�BufferedWriter��FileOutputStreamд��ʱ���ļ��д������ݣ����Ƚ��ļ������д�룻FileWriter��RandomAccessFile����׷�ӡ�
 * @author Jane1222
 * @Date 2017-10-11
 *
 */
public class JavaWriteFileMethod {
	public static void main(String[] args) {
		String filePath = "C:\\Users\\Administrator\\Desktop\\ss1\\test.txt";
//		String content = "��Ϧ��Ϧ��,�������.���պ�����,��������ͬ��.���߱�����,����ڸ��.�ļ�����������,��֪����.ɽ��ľ��ľ��֦,���þ������֪.";
		String content = "Hello World!";
		writeFileWithFileWritter(filePath,content);
		writeFileWithBufferedWriter(filePath,content);
		writeFileWithFileOutputStream(filePath,content);
		writeFileWithRandomAccessFile(filePath,content);
	}
	
	/**
	 * FileWritterд��:
	 * 			new FileWriter(file);   �滻�������е��������µ�����
	 * 			new FileWriter(file,true);  �������е����ݺ͸����ڸ��ļ���ĩβ��������
	 * @param filePath
	 * @param content
	 */
	public static void writeFileWithFileWritter(String filePath,String content){
		try {
			File file = new File(filePath);
			if(!file.exists()){
				file.createNewFile();
			}
			FileWriter fileWritter = new FileWriter(filePath,true);
			fileWritter.write(content);
			fileWritter.close();
			System.out.println("FileWriter ��ʽд���ļ����!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * BufferedWriterд�룺
	 * 			�����ַ���BufferedWriter ����һ���ַ������������ַ����ݡ���ͬ���ֽ���������ת�����ֽڣ��������ֱ��д�ַ�����������ַ����ݱ��浽�ļ�
	 * @param filePath
	 * @param content
	 */
	public static void writeFileWithBufferedWriter(String filePath,String content){
		try {
			File file = new File(filePath);
			if(!file.exists()){
				file.createNewFile();
			}
			BufferedWriter bw = 
					new BufferedWriter(new FileWriter(file.getAbsolutePath()));
			bw.write(content);
			bw.close();
			System.out.println("BufferedWriter ��ʽд���ļ����!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * FileOutputStream��ʽд���ļ���
	 * 		�ļ��������һ�����ڴ���ԭʼ���������ݵ��ֽ����ࡣΪ�˽�����д�뵽�ļ��У����뽫����ת��Ϊ�ֽڣ������浽�ļ���
	 * @param filePath
	 * @param content
	 */
	public static void writeFileWithFileOutputStream(String filePath,String content){
		File file = new File(filePath);
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			FileOutputStream fops = new FileOutputStream(file);
			byte[] contentBytes = content.getBytes();
			fops.write(contentBytes);
			fops.flush();
			fops.close();
			System.out.println("FileOutputStream ��ʽд���ļ����!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * RandomAccessFileд�룺ͨ���������ļ�׷��
	 * @param filePath
	 * @param content
	 */
	public static void writeFileWithRandomAccessFile(String filePath,String content){
		try {
			RandomAccessFile randomFile = new RandomAccessFile(filePath,"rw");	//��һ����������ļ���������д��ʽ
			long fileLength = randomFile.length();	//�ļ�����
			randomFile.seek(fileLength);	//��д�ļ�ָ�������ļ�β,���ƶ�ָ����Ĭ��׷�����ļ�ͷ��
			randomFile.writeBytes(content);
			randomFile.close();
			System.out.println("RandomAccessFile ��ʽд���ļ����!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
