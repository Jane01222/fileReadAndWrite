package com.xuanwu.demo.filereadandwrite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;

/**
 * @Description �ܽ����Java�ļ���ȡ�ļ��ַ�ʽ��
 * 		1.���ֽڶ�ȡ�����ֽ�Ϊ��λ��ȡ�ļ��������ڶ��������ļ�����ͼƬ��������Ӱ����ļ���
 * 		2.���ַ���ȡ�����ַ�Ϊ��λ��ȡ�ļ��������ڶ��ı������ֵ����͵��ļ�
 * 		3.���ж�ȡ��    ����Ϊ��λ��ȡ�ļ��������ڶ������еĸ�ʽ���ļ�
 * 		4.�����ȡ��    �����ȡһ���ļ�����
 * @author Jane1222
 * @Date 2017-10-10
 *
 */
public class JavaReadFileMethod{
	public static void main(String[] args) {
		readFileByBytes("C:\\Users\\Administrator\\Desktop\\ss1\\statistics-config.json");
		readFileByChars("C:\\Users\\Administrator\\Desktop\\ss1\\statistics-config.json");
		readFileByLines("C:\\Users\\Administrator\\Desktop\\ss1\\statistics-config.json");
		readFileByRandomAccess("C:\\Users\\Administrator\\Desktop\\ss1\\statistics-config.json");
	}
	
	/**
	 * ���ֽ�Ϊ��λ��ȡ�ļ��������ڶ��������ļ�����ͼƬ��������Ӱ����ļ�
	 * @param filePath
	 */
	public static void readFileByBytes(String filePath){
		File file = new File(filePath);
		InputStream in = null;
		try {
			System.out.println("���ֽ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�ȡһ���ֽ�");
			in = new FileInputStream(file);	//һ�ζ�ȡһ���ֽ�
			int tempbyte;
			while((tempbyte=in.read())!=-1){
				System.out.write(tempbyte);
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		try {
			System.out.println("���ֽ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�ȡ����ֽ�");
			in = new FileInputStream(filePath);
			byte[] tempbytes = new byte[1024];
			int byteread  = 0;
			JavaReadFileMethod.showAvailableBytes(in);	//��ʾ��ǰ�ֽ���������ʣ����ֽ���
			while((byteread=in.read(tempbytes))!=-1){
				System.out.write(tempbytes,0,byteread);	// �������ֽڵ��ֽ������У�bytereadΪһ�ζ�����ֽ���
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally{
			if(in !=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * ���ַ�Ϊ��λ��ȡ�ļ��������ڶ�ȡ�ı������ֵ����͵��ļ�
	 * @param filePath
	 */
	public static void readFileByChars(String filePath){
		File file = new File(filePath);
		Reader reader = null;
		try {
			System.out.println("���ַ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�ȡһ���ַ�");
			reader = new InputStreamReader(new FileInputStream(filePath));
			int tempchar;
			while((tempchar=reader.read())!=-1){
				//����windows�£�\r\n�������ַ���һ��ʱ����ʾһ�����С�������������ַ��ֿ���ʾʱ���ỻ�����С� ��ˣ����ε�\r����������\n�����򣬽������ܶ����
				if(((char)tempchar)!='\r'){
					System.out.print((char)tempchar);
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("���ַ�Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�ȡ����ַ�");
			reader = new InputStreamReader(new FileInputStream(file));
			char[] tempchars = new char[100];
			int charread;
			// �������ַ����ַ������У�charreadΪһ�ζ�ȡ�ַ���
			while((charread=reader.read(tempchars))!=-1){
				//ͬ����Ҫ���ε�'\r'
				if((charread==tempchars.length)&&tempchars[tempchars.length - 1] != '\r'){
					System.out.print(tempchars);
				}else{
					for(int i=0;i<charread;i++){
						if(tempchars[i]=='\r') continue;
						else System.out.print(tempchars[i]);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * ����Ϊ��λ��ȡ�ļ��������ڶ������еĸ�ʽ���ļ�
	 * @param filePath
	 */
	public static void readFileByLines(String filePath){
//		File file = new File(filePath);
		BufferedReader reader = null;
		try {
			System.out.println("����Ϊ��λ��ȡ�ļ����ݣ�һ�ζ�ȡһ����");
			reader = new BufferedReader(new FileReader(filePath));
			String tempstr = null;
			int line = 1;
			//һ�ζ�ȡһ�У�֪����ȡnullΪ�ļ�����
			while((tempstr=reader.readLine())!=null){
				System.out.println("line"+line+": "+tempstr);	//��ʾ�к�
				line++;
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * �����ȡ�ļ�����
	 * @param filePath
	 */
	public static void readFileByRandomAccess(String filePath){
		RandomAccessFile randomFile = null;
		try {
			System.out.println("�����ȡһ���ļ�����");
			randomFile = new RandomAccessFile(filePath,"r");	//��һ����������ļ�������ֻ����ʽ
			long fileLength = randomFile.length();	//�ļ����ȣ��ֽ���
			int beginIndex = (fileLength > 4)? 4 : 0;	//���ļ�����ʼλ��
			randomFile.seek(beginIndex);	//�����ļ�����ʼλ������beginIndexλ��
			byte[] bytes = new byte[10];
			int byteread = 0;
			// һ�ζ�10���ֽڣ�����ļ����ݲ���10���ֽڣ����ʣ�µ��ֽڡ���һ�ζ�ȡ���ֽ�������byteread
			while((byteread=randomFile.read(bytes))!=-1){
				System.out.write(bytes, 0, byteread);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(randomFile !=null){
				try {
					randomFile.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * ��ʾ��ǰ���л�ʣ����ֽ���
	 * @param in
	 */
	public static void showAvailableBytes(InputStream in){
		try {
			System.out.println("��ǰ�ֽ��������е��ֽ���Ϊ:" + in.available());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
