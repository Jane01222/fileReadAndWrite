package com.xuanwu.demo.filereadandwrite;

import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @Description 将内容追加到文件尾部，两种方式：
 * 			1.A方法追加文件：使用RandomAccessFile
 * 			2.B方法追加文件：使用FileWriter
 * @author Jane1222
 * @Date 2017-10-11
 *
 */
public class AppendToFile {
	public static void main(String[] args) {
		String fileName = "C:\\Users\\Administrator\\Desktop\\ss1\\temp.txt";
        String content = "wakaka, I believe i can ! ";
   
        //按方法A追加文件
        AppendToFile.appendToFileA(fileName, content);
        AppendToFile.appendToFileA(fileName, "append end. \n");
        
        //显示文件内容
        JavaReadFileMethod.readFileByLines(fileName);
        
        //按方法B追加文件
        AppendToFile.appendToFileB(fileName, content);
        AppendToFile.appendToFileB(fileName, "append end. \n");
        
        //显示文件内容
        JavaReadFileMethod.readFileByLines(fileName);
	}
	
	
	/**
	 * A方法追加文件：使用RandomAccessFile
	 * @param filePath
	 * @param content
	 */
	public static void appendToFileA(String filePath,String content){
		try {
			RandomAccessFile randomFile = new RandomAccessFile(filePath,"rw");	//打开一个随机访问文件流，按读写方式
			long fileLength = randomFile.length();	//文件长度
			randomFile.seek(fileLength);	//将写文件指针移至文件尾
			randomFile.writeBytes(content);
			randomFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * B方法追加文件：使用FileWriter
	 * @param filePath
	 * @param content
	 */
	public static void appendToFileB(String filePath,String content){
		try {
			//打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			FileWriter fileWriter = new FileWriter(filePath,true);
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
