package com.xuanwu.demo.filereadandwrite;

import java.io.File;

/**
 * @Description ��ȡ��ɾ��һ���ļ����µ������ļ�
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
	 * ��ȡĳ���ļ����µ������ļ�
	 * @param filePath
	 * @return
	 */
	public static boolean readFiles (String filePath){
		try {
			File file = new File(filePath);
			if(!file.isDirectory()){ //�ļ�
				System.out.println("�ļ���path="+file.getPath()+" absolutePath="+
						file.getAbsolutePath()+" name="+file.getName());
			}else if(file.isDirectory()){//�ļ���
				String[] files = file.list();
				for(int i=0;i<files.length;i++){
					System.out.println("�ļ���");
					File readFile = new File(filePath+"\\"+files[i]);
					if(!readFile.isDirectory()){
						System.out.println("�ļ�"+readFile.getName()+": path="+readFile.getPath()+" absolutePath="+
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
	 * ɾ��ĳ���ļ����µ������ļ�
	 * @param filePath
	 * @return
	 */
	public static boolean deleteFiles(String filePath){
		try {
			File file = new File(filePath);
			if(!file.isDirectory()){
				System.out.println("ɾ���ļ�"+file.getName());
				file.delete();
			}else if(file.isDirectory()){
				System.out.println("�ļ���"+file.getName());
				String[] files = file.list();
				for(int i=0;i<files.length;i++){
					File delFile = new File(filePath+"\\"+files[i]);
					if(!delFile.isDirectory()){
						String fileName = delFile.getName();
						System.out.println(fileName+"�ļ��� path="
						+delFile.getPath()+" absolutePath="+delFile.getAbsolutePath());
						delFile.delete();
						System.out.println("�ļ�"+fileName+"ɾ���ɹ�");
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

