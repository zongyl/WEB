package com.longlong.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

public class FileUtils {
	
  private static final int BUFFER_SIZE = 16384;
 
  static Logger log = Logger.getLogger(FileUtils.class);
  
  /**
   * InputStream to File
   * @param in
   * @param filePath
   */
  public static void stream2file(InputStream in, String filePath) throws Exception{
	  
	  OutputStream os = new FileOutputStream(new File(filePath));		 
		 int bytesRead = 0;
		 byte[] buffer = new byte[8192];
		 while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
		 os.write(buffer, 0, bytesRead);
		 }
		 os.close();
		 in.close();
  }
  
  public static void newFolder(String folderPath)
  {
    try
    {
      String filePath = folderPath;
      filePath = filePath.toString();
      File myFilePath = new File(filePath);
      if (!myFilePath.exists())
        myFilePath.mkdir();
    }
    catch (Exception e) {
      System.out.println("新建目录操作出错 ");
      e.printStackTrace();
    }
  }

  public static void newFile(String filePathAndName, String fileContent)
  {
    try
    {
      String filePath = filePathAndName;
      filePath = filePath.toString();
      File myFilePath = new File(filePath);
      if (!myFilePath.exists()) {
        myFilePath.createNewFile();
      }
      FileWriter resultFile = new FileWriter(myFilePath);
      PrintWriter myFile = new PrintWriter(resultFile);
      String strContent = fileContent;
      myFile.println(strContent);
      resultFile.close();
    }
    catch (Exception e) {
      System.out.println("新建目录操作出错 ");
      e.printStackTrace();
    }
  }

  public static void delFile(String filePathAndName)
  {
    try
    {
      String filePath = filePathAndName;
      filePath = filePath.toString();
      File myDelFile = new File(filePath);
      myDelFile.delete();
    }
    catch (Exception e) {
      System.out.println("删除文件操作出错 ");
      e.printStackTrace();
    }
  }

  public static void delFolder(String folderPath)
  {
    try
    {
      delAllFile(folderPath);
      String filePath = folderPath;
      filePath = filePath.toString();
      File myFilePath = new File(filePath);
      myFilePath.delete();
    } catch (Exception e) {
      System.out.println("删除文件夹操作出错 ");
      e.printStackTrace();
    }
  }

  public static void delAllFile(String path)
  {
    File file = new File(path);
    if (!file.exists()) {
      return;
    }
    if (!file.isDirectory()) {
      return;
    }
    String[] tempList = file.list();
    File temp = null;
    for (int i = 0; i < tempList.length; i++) {
      if (path.endsWith(File.separator))
        temp = new File(path + tempList[i]);
      else {
        temp = new File(path + File.separator + tempList[i]);
      }
      if (temp.isFile()) {
        temp.delete();
      }
      if (temp.isDirectory()) {
        delAllFile(path + File.separator + tempList[i]);
        delFolder(path + File.separator + tempList[i]);
      }
    }
  }

  public static void copy(String srcFilePath, String dstFilePath) {
    try {
      InputStream in = null;
      OutputStream out = null;
      try {
        in = new BufferedInputStream(new FileInputStream(new File(srcFilePath)), BUFFER_SIZE);
        out = new BufferedOutputStream(new FileOutputStream(new File(dstFilePath)), BUFFER_SIZE);
        byte[] buffer = new byte[16384];
        while (in.read(buffer) > 0)
          out.write(buffer);
      }
      finally {
        if (in != null) {
          in.close();
        }
        if (out != null)
          out.close();
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void copy(File src, File dst) {
    try {
      InputStream in = null;
      OutputStream out = null;
      try {
        in = new BufferedInputStream(new FileInputStream(src), 16384);
        out = new BufferedOutputStream(new FileOutputStream(dst), 16384);
        byte[] buffer = new byte[16384];
        while (in.read(buffer) > 0)
          out.write(buffer);
      }
      finally {
        if (in != null) {
          in.close();
        }
        if (out != null)
          out.close();
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void copyFile(String oldPath, String newPath)
  {
    try
    {
      int bytesum = 0;
      int byteread = 0;
      File oldfile = new File(oldPath);
      if (oldfile.exists()) {
        InputStream inStream = new FileInputStream(oldPath);
        FileOutputStream fs = new FileOutputStream(newPath);
        byte[] buffer = new byte[1444];

        while ((byteread = inStream.read(buffer)) != -1) {
          bytesum += byteread;
          fs.write(buffer, 0, byteread);
        }
        inStream.close();
      } else {
        System.out.println("文件不存在");
        throw new Exception();
      }
    } catch (Exception e) {
      System.out.println("复制单个文件操作出错 ");
      e.printStackTrace();
    }
  }

  public static void copyFolder(String oldPath, String newPath)
  {
    try
    {
      new File(newPath).mkdirs();
      File a = new File(oldPath);
      String[] file = a.list();
      File temp = null;
      for (int i = 0; i < file.length; i++) {
        if (oldPath.endsWith(File.separator))
          temp = new File(oldPath + file[i]);
        else {
          temp = new File(oldPath + File.separator + file[i]);
        }

        if (temp.isFile()) {
          FileInputStream input = new FileInputStream(temp);
          FileOutputStream output = new FileOutputStream(newPath + 
            File.separator + temp.getName().toString());
          byte[] b = new byte[5120];
          int len;
          while ((len = input.read(b)) != -1)
          {
            int len1;
            output.write(b, 0, len);
          }
          output.flush();
          output.close();
          input.close();
        }
        if (temp.isDirectory())
          copyFolder(oldPath + File.separator + file[i], newPath + File.separator + 
            file[i]);
      }
    }
    catch (Exception e) {
      System.out.println("复制整个文件夹内容操作出错 ");
      e.printStackTrace();
    }
  }

  public static void moveFile(String oldPath, String newPath)
  {
    copyFile(oldPath, newPath);
    delFile(oldPath);
  }

  public static void moveFolder(String oldPath, String newPath)
  {
    copyFolder(oldPath, newPath);
    delFolder(oldPath);
  }

  public static void main(String[] args)
  {
    delFolder("D:\\Workspace-3.6\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\archives\\temp\\电子文件");

    delFolder("D:\\Workspace-3.6\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\archives\\temp\\扫描件");
  }
}