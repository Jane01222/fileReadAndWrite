package com.xuanwu.demo.filereadandwrite;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description Java�����ļ����µ������ļ�(�ݹ�ͷǵݹ����)
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
	 * ����һ����ʹ�õݹ���ñ����ļ����������ļ�
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
					System.out.println("�ļ���"+o.getName()+":  absolutePath="+o.getAbsolutePath());
					folderNum++;
					list.add(o);
				}else if(!o.isDirectory()){
					System.out.println("�ļ�"+o.getName()+":  absolutePath="+o.getAbsolutePath());
					fileNum++;
				}
			}
			File tempFile;
			while(!list.isEmpty()){
				tempFile = list.removeFirst();
				files = tempFile.listFiles();
				for(File o : files){
					if(o.isDirectory()){
						System.out.println("�ļ���"+o.getName()+":  absolutePath="+o.getAbsolutePath());
						list.add(o);
						folderNum++;
					}else if(!o.isDirectory()){
						System.out.println("�ļ�"+o.getName()+":  absolutePath="+o.getAbsolutePath());
						fileNum++;
					}
				}
			}
		}else{
			System.out.println("�ļ������ڣ�");
		}
		System.out.println("�ļ��й���:" + folderNum + "��,�ļ�����:" + fileNum+"��");
	}
	
	
	/**
	 * ��������ʹ�õݹ���ñ���
	 * @param filePath
	 */
	public static void traverseFolder2(String filePath){
		File file = new File(filePath);
		if(file.exists()){
			File[] files = file.listFiles();
			if(files.length==0){
				System.out.println("���ļ���Ϊ��");
				return;
			}else{
				for(File o:files){
					if(o.isDirectory()){
						System.out.println("�ļ���"+o.getName()+":  absolutePath="+o.getAbsolutePath());
						traverseFolder2(o.getAbsolutePath());
					}else{
						System.out.println("�ļ�"+o.getName()+":  absolutePath="+o.getAbsolutePath());
					}
				}
			}
		}else{
			System.out.println("�ļ�������");
		}
	}
	
	private static int  depth=1;
	/**
	 * �ݹ����һ���ļ����µ������ļ������ļ���Ȳ�����
	 * @param filePath
	 * @param depth
	 * @throws IOException
	 */
	public static void traverseFolder3(String filePath,int depth) throws IOException{
		int fileNum=0;
		File file = new File(filePath); //��ȡfilePath���ļ�����
		if(!file.exists()){
			System.out.println("�ļ�������");
			return;
		}
		if(!file.isDirectory()){
			if(file.isFile()){ //�ж��Ƿ�Ϊ�ļ��У������ļ����ж��Ƿ����ļ�����������ļ���Ϣ
				System.out.println("�ļ�"+file.getName()+
						":  absolutePath="+file.getAbsolutePath()+"  �ļ���С="+(file.getTotalSpace()/(8*1024*1024))+"kb");
				++fileNum;
				System.out.println("�����ļ�����"+fileNum);
			}
			return;
		}
		
		for (int j = 0; j < depth; j++) {  
            System.out.print("  ");  
        }  
        System.out.print("|--");  
        System.out.println(file.getName());  
        String[] fileList = file.list();  //��ȡ��Ŀ¼�µ������ļ�����Ŀ¼��  
        int currentDepth=depth+1;  
        for (int i = 0; i < fileList.length; i++) {  
            String string = fileList[i];  //�����ļ�Ŀ¼  
            File f = new File(file.getPath(),string);  //File("documentName","fileName")��File����һ��������  
            String name = f.getName();  
            if (f.isDirectory()) {  //�����һ��Ŀ¼���������depth++�����Ŀ¼���󣬽��еݹ�  
            	traverseFolder3(f.getCanonicalPath(),currentDepth); //�ݹ�   
            }else{ 
            	fileNum++;
                for (int j = 0; j < currentDepth; j++) {  //������ļ�����ֱ������ļ���  
                    System.out.print("   ");  
                }  
                System.out.print("|--");  
                System.out.println(name);  
            }  
        }  
//		System.out.println("�����ļ�����"+fileNum);
	}
	
	/**
	 * ���������ݹ鷵���ļ�����ָ�����͵��ļ�
	 * @param filePath
	 * @return
	 */
	public static List<File> getFileList(String filePath){
		List<File> fileList = new ArrayList<File>();
		File file = new File(filePath);
		File[] files = file.listFiles(); //���ļ�Ŀ¼���ļ�ȫ����������
		if(files !=null){
			for(int i=0;i<files.length;i++){
				String fileName = files[i].getName();
				if(files[i].isDirectory()){//�ж��Ƿ����ļ���
					getFileList(files[i].getAbsolutePath()); //��ȡ�ļ�����·��
				}else if(fileName.endsWith("java")){	//�ж��Ƿ���.java��β
					System.out.println("�ļ�"+files[i].getName()+":  absolutePath="+files[i].getAbsolutePath());
					fileList.add(files[i]);
				}
			}
		}else{
			System.out.println("���ļ���Ϊ�գ�");
		}
		return fileList;
	}
	


}
