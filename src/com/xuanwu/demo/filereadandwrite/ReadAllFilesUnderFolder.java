package com.xuanwu.demo.filereadandwrite;

import java.io.File;

/**
 * @Description 读取、删除一个文件夹下的所有文件
 * @author Jane1222
 * @Date 2017-10-10
 *
 */
public class ReadAllFilesUnderFolder {
	public static void main(String[] args) {
		readFiles("C:\\Users\\Administrator\\Desktop\\ss");
		deleteFiles("C:\\Users\\Administrator\\Desktop\\ss1");
	}
	
	/**
	 * 读取某个文件夹下的所有文件
	 * @param filePath
	 * @return
	 */
	public static boolean readFiles (String filePath){
		try {
			File file = new File(filePath);
			if(!file.isDirectory()){ //文件
				System.out.println("文件：path="+file.getPath()+" absolutePath="+
						file.getAbsolutePath()+" name="+file.getName());
			}else if(file.isDirectory()){//文件夹
				String[] files = file.list();
				for(int i=0;i<files.length;i++){
					System.out.println("文件夹");
					File readFile = new File(filePath+"\\"+files[i]);
					if(!readFile.isDirectory()){
						System.out.println("文件"+readFile.getName()+": path="+readFile.getPath()+" absolutePath="+
								readFile.getAbsolutePath());
					}else if(readFile.isDirectory()){
						readFiles(filePath+"\\"+files[i]);
					}
				}
			}
			return true;
		} catch (Exception e) {
			System.out.println("readFiles error caused by:"+e.getMessage());
		}
		return false;
	}
	
	
	
	/**
	 * 删除某个文件夹下的所有文件
	 * @param filePath
	 * @return
	 */
	public static boolean deleteFiles(String filePath){
		try {
			File file = new File(filePath);
			if(!file.isDirectory()){
				System.out.println("删除文件"+file.getName());
				file.delete();
			}else if(file.isDirectory()){
				System.out.println("文件夹"+file.getName());
				String[] files = file.list();
				for(int i=0;i<files.length;i++){
					File delFile = new File(filePath+"\\"+files[i]);
					if(!delFile.isDirectory()){
						String fileName = delFile.getName();
						System.out.println(fileName+"文件： path="
						+delFile.getPath()+" absolutePath="+delFile.getAbsolutePath());
						delFile.delete();
						System.out.println("文件"+fileName+"删除成功");
					}else if(delFile.isDirectory()){
						deleteFiles(filePath+"\\"+files[i]);
					}
				}
				file.delete();
			}
			return true;
		} catch (Exception e) {
			System.out.println("deleteFiles error caused by:"+e.getMessage());
		}
		return false;
	}
}

