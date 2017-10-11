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
 * @Description 总结归纳Java文件读取的几种方式：
 * 		1.按字节读取：以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
 * 		2.按字符读取：以字符为单位读取文件，常用于读文本，数字等类型的文件
 * 		3.按行读取：    以行为单位读取文件，常用于读面向行的格式化文件
 * 		4.随机读取：    随机读取一段文件内容
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
	 * 以字节为单位读取文件：常用于读二进制文件，如图片、声音、影像等文件
	 * @param filePath
	 */
	public static void readFileByBytes(String filePath){
		File file = new File(filePath);
		InputStream in = null;
		try {
			System.out.println("以字节为单位读取文件内容，一次读取一个字节");
			in = new FileInputStream(file);	//一次读取一个字节
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
			System.out.println("以字节为单位读取文件内容，一次读取多个字节");
			in = new FileInputStream(filePath);
			byte[] tempbytes = new byte[1024];
			int byteread  = 0;
			JavaReadFileMethod.showAvailableBytes(in);	//显示当前字节输入流中剩余的字节数
			while((byteread=in.read(tempbytes))!=-1){
				System.out.write(tempbytes,0,byteread);	// 读入多个字节到字节数组中，byteread为一次读入的字节数
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
	 * 以字符为单位读取文件：常用于读取文本、数字等类型的文件
	 * @param filePath
	 */
	public static void readFileByChars(String filePath){
		File file = new File(filePath);
		Reader reader = null;
		try {
			System.out.println("以字符为单位读取文件内容，一次读取一个字符");
			reader = new InputStreamReader(new FileInputStream(filePath));
			int tempchar;
			while((tempchar=reader.read())!=-1){
				//对于windows下，\r\n这两个字符在一起时，表示一个换行。但如果这两个字符分开显示时，会换两次行。 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行
				if(((char)tempchar)!='\r'){
					System.out.print((char)tempchar);
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("以字符为单位读取文件内容，一次读取多个字符");
			reader = new InputStreamReader(new FileInputStream(file));
			char[] tempchars = new char[100];
			int charread;
			// 读入多个字符到字符数组中，charread为一次读取字符数
			while((charread=reader.read(tempchars))!=-1){
				//同样需要屏蔽掉'\r'
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
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 * @param filePath
	 */
	public static void readFileByLines(String filePath){
//		File file = new File(filePath);
		BufferedReader reader = null;
		try {
			System.out.println("以行为单位读取文件内容，一次读取一整行");
			reader = new BufferedReader(new FileReader(filePath));
			String tempstr = null;
			int line = 1;
			//一次读取一行，知道读取null为文件结束
			while((tempstr=reader.readLine())!=null){
				System.out.println("line"+line+": "+tempstr);	//显示行号
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
	 * 随机读取文件内容
	 * @param filePath
	 */
	public static void readFileByRandomAccess(String filePath){
		RandomAccessFile randomFile = null;
		try {
			System.out.println("随机读取一段文件内容");
			randomFile = new RandomAccessFile(filePath,"r");	//打开一个随机访问文件流，按只读方式
			long fileLength = randomFile.length();	//文件长度，字节数
			int beginIndex = (fileLength > 4)? 4 : 0;	//读文件的起始位置
			randomFile.seek(beginIndex);	//将读文件的起始位置移至beginIndex位置
			byte[] bytes = new byte[10];
			int byteread = 0;
			// 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。将一次读取的字节数赋给byteread
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
	 * 显示当前流中还剩余的字节数
	 * @param in
	 */
	public static void showAvailableBytes(InputStream in){
		try {
			System.out.println("当前字节输入流中的字节数为:" + in.available());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
