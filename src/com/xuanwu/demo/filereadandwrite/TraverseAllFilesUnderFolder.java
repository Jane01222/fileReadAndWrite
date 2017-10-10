package com.xuanwu.demo.filereadandwrite;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description Java遍历文件夹下的所有文件(递归和非递归调用)
 * @author Jane1222
 * @Date 2017-10-10
 */
public class TraverseAllFilesUnderFolder {
	public static void main(String[] args) throws IOException {
		traverseFolder("C:\\Users\\Administrator\\Desktop\\ss1");
		traverseFolder2("C:\\Users\\Administrator\\Desktop\\ss1");
		getFileList("C:\\Users\\Administrator\\Desktop\\ss1");
		traverseFolder3("C:\\Users\\Administrator\\Desktop\\ss1",depth);
	}
	
	/**
	 * 方法一：不使用递归调用遍历文件夹下所有文件
	 * @param filePath
	 */
	public static void traverseFolder(String filePath){
		int fileNum=0,folderNum=0;
		File file = new File(filePath);
		if(file.exists()){
			LinkedList<File> list = new LinkedList<File>();
			File[] files = file.listFiles();
			for(File o:files){
				if(o.isDirectory()){
					System.out.println("文件夹"+o.getName()+":  absolutePath="+o.getAbsolutePath());
					folderNum++;
					list.add(o);
				}else if(!o.isDirectory()){
					System.out.println("文件"+o.getName()+":  absolutePath="+o.getAbsolutePath());
					fileNum++;
				}
			}
			File tempFile;
			while(!list.isEmpty()){
				tempFile = list.removeFirst();
				files = tempFile.listFiles();
				for(File o : files){
					if(o.isDirectory()){
						System.out.println("文件夹"+o.getName()+":  absolutePath="+o.getAbsolutePath());
						list.add(o);
						folderNum++;
					}else if(!o.isDirectory()){
						System.out.println("文件"+o.getName()+":  absolutePath="+o.getAbsolutePath());
						fileNum++;
					}
				}
			}
		}else{
			System.out.println("文件不存在！");
		}
		System.out.println("文件夹共有:" + folderNum + "个,文件共有:" + fileNum+"个");
	}
	
	
	/**
	 * 方法二：使用递归调用遍历
	 * @param filePath
	 */
	public static void traverseFolder2(String filePath){
		File file = new File(filePath);
		if(file.exists()){
			File[] files = file.listFiles();
			if(files.length==0){
				System.out.println("该文件夹为空");
				return;
			}else{
				for(File o:files){
					if(o.isDirectory()){
						System.out.println("文件夹"+o.getName()+":  absolutePath="+o.getAbsolutePath());
						traverseFolder2(o.getAbsolutePath());
					}else{
						System.out.println("文件"+o.getName()+":  absolutePath="+o.getAbsolutePath());
					}
				}
			}
		}else{
			System.out.println("文件不存在");
		}
	}
	
	private static int  depth=1;
	/**
	 * 递归遍历一个文件夹下的所有文件，按文件深度层次输出
	 * @param filePath
	 * @param depth
	 * @throws IOException
	 */
	public static void traverseFolder3(String filePath,int depth) throws IOException{
		int fileNum=0;
		File file = new File(filePath); //获取filePath的文件对象
		if(!file.exists()){
			System.out.println("文件不存在");
			return;
		}
		if(!file.isDirectory()){
			if(file.isFile()){ //判断是否为文件夹，不是文件夹判断是否是文件，是则输出文件信息
				System.out.println("文件"+file.getName()+
						":  absolutePath="+file.getAbsolutePath()+"  文件大小="+(file.getTotalSpace()/(8*1024*1024))+"kb");
				++fileNum;
				System.out.println("共有文件个数"+fileNum);
			}
			return;
		}
		
		for (int j = 0; j < depth; j++) {  
            System.out.print("  ");  
        }  
        System.out.print("|--");  
        System.out.println(file.getName());  
        String[] fileList = file.list();  //获取此目录下的所有文件名与目录名  
        int currentDepth=depth+1;  
        for (int i = 0; i < fileList.length; i++) {  
            String string = fileList[i];  //遍历文件目录  
            File f = new File(file.getPath(),string);  //File("documentName","fileName")是File的另一个构造器  
            String name = f.getName();  
            if (f.isDirectory()) {  //如果是一个目录，搜索深度depth++，输出目录名后，进行递归  
            	traverseFolder3(f.getCanonicalPath(),currentDepth); //递归   
            }else{ 
            	fileNum++;
                for (int j = 0; j < currentDepth; j++) {  //如果是文件，则直接输出文件名  
                    System.out.print("   ");  
                }  
                System.out.print("|--");  
                System.out.println(name);  
            }  
        }  
//		System.out.println("共有文件个数"+fileNum);
	}
	
	/**
	 * 方法三：递归返回文件夹中指定类型的文件
	 * @param filePath
	 * @return
	 */
	public static List<File> getFileList(String filePath){
		List<File> fileList = new ArrayList<File>();
		File file = new File(filePath);
		File[] files = file.listFiles(); //该文件目录下文件全部放入数组
		if(files !=null){
			for(int i=0;i<files.length;i++){
				String fileName = files[i].getName();
				if(files[i].isDirectory()){//判断是否是文件夹
					getFileList(files[i].getAbsolutePath()); //获取文件绝对路径
				}else if(fileName.endsWith("java")){	//判断是否以.java结尾
					System.out.println("文件"+files[i].getName()+":  absolutePath="+files[i].getAbsolutePath());
					fileList.add(files[i]);
				}
			}
		}else{
			System.out.println("该文件夹为空！");
		}
		return fileList;
	}
	


}
