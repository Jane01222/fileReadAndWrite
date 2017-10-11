package com.xuanwu.demo.filereadandwrite;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @Description 总结归纳Java写入文件的几种方式：
 * 			1.FileWriter写入：
 * 				字符流写入字符到文件。默认情况下，它会使用新的内容取代所有现有的内容；当指定一个true （布尔）值作为FileWritter构造函数的第二个参数，它会保留现有的内容，并追加新内容在文件的末尾。
 * 			2.BufferedWriter写入：
 * 				缓冲字符（BufferedWriter ）是一个字符流类来处理字符数据。不同于字节流（数据转换成字节），你可以直接写字符串，数组或字符数据保存到文件。
 * 			3.FileOutputStream写入：
 * 				文件输出流是一种用于处理原始二进制数据的字节流类。为了将数据写入到文件中，必须将数据转换为字节，并保存到文件。
 * 			4.RandomAccessFile写入：
 * 				通常可用于文件追加
 *	其中，BufferedWriter和FileOutputStream写入时若文件中存在内容，会先将文件清空再写入；FileWriter和RandomAccessFile则是追加。
 * @author Jane1222
 * @Date 2017-10-11
 *
 */
public class JavaWriteFileMethod {
	public static void main(String[] args) {
		String filePath = "C:\\Users\\Administrator\\Desktop\\ss1\\test.txt";
//		String content = "今夕何夕兮,搴舟中流.今日何日兮,得与王子同舟.蒙羞被好兮,不訾诟耻.心几烦而不绝兮,得知王子.山有木兮木有枝,心悦君兮君不知.";
		String content = "Hello World!";
		writeFileWithFileWritter(filePath,content);
		writeFileWithBufferedWriter(filePath,content);
		writeFileWithFileOutputStream(filePath,content);
		writeFileWithRandomAccessFile(filePath,content);
	}
	
	/**
	 * FileWritter写入:
	 * 			new FileWriter(file);   替换所有现有的内容与新的内容
	 * 			new FileWriter(file,true);  保留现有的内容和附加在该文件的末尾的新内容
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
			System.out.println("FileWriter 方式写入文件完成!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * BufferedWriter写入：
	 * 			缓冲字符（BufferedWriter ）是一个字符流类来处理字符数据。不同于字节流（数据转换成字节），你可以直接写字符串，数组或字符数据保存到文件
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
			System.out.println("BufferedWriter 方式写入文件完成!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * FileOutputStream方式写入文件：
	 * 		文件输出流是一种用于处理原始二进制数据的字节流类。为了将数据写入到文件中，必须将数据转换为字节，并保存到文件。
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
			System.out.println("FileOutputStream 方式写入文件完成!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * RandomAccessFile写入：通常可用于文件追加
	 * @param filePath
	 * @param content
	 */
	public static void writeFileWithRandomAccessFile(String filePath,String content){
		try {
			RandomAccessFile randomFile = new RandomAccessFile(filePath,"rw");	//打开一个随机访问文件流，按读写方式
			long fileLength = randomFile.length();	//文件长度
			randomFile.seek(fileLength);	//将写文件指针移至文件尾,不移动指针则默认追加至文件头部
			randomFile.writeBytes(content);
			randomFile.close();
			System.out.println("RandomAccessFile 方式写入文件完成!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
