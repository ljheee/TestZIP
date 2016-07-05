package com.ljheee.zip.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class TestZIP {

	/**
	 * ����:ѹ������ļ���һ��zip�ļ�
	 * ѹ�����[��]�ļ�����֧���ļ��к��ļ�һ��ѹ����
	 * @param srcfile��Դ�ļ��б�
	 * @param zipfile��ѹ������ļ�
	 */
	public static void zipFiles(File[] srcfile, File zipfile) {
	
		byte[] buf = new byte[1024];
		
		try {
			// ZipOutputStream�ࣺ����ļ����ļ��е�ѹ��
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
			
			FileInputStream in = null;
			int len;
			for (int i = 0; i < srcfile.length; i++) {
				in = new FileInputStream(srcfile[i]);
				out.putNextEntry(new ZipEntry(srcfile[i].getName()));
				
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.closeEntry();
				in.close();
			}
			out.close();
			
			System.out.println("ѹ�����.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	/**
	 * ����:��ѹ��
	 * 
	 * @param zipfile����Ҫ��ѹ�����ļ�
	 * @param descDir����ѹ���Ŀ��Ŀ¼
	 */
	public static void unZipFiles(File zipfile, String descDir) {
		try {
			ZipFile zf = new ZipFile(zipfile);
			for (Enumeration entries = zf.entries(); entries.hasMoreElements();) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				String zipEntryName = entry.getName();
				InputStream in = zf.getInputStream(entry);
				OutputStream out = new FileOutputStream(descDir + zipEntryName);
				byte[] buf1 = new byte[1024];
				int len;
				while ((len = in.read(buf1)) > 0) {
					out.write(buf1, 0, len);
				}
				in.close();
				out.close();
				System.out.println("��ѹ�����.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	public static void main(String[] args) {
		// 2��Դ�ļ�
		File f1 = new File("D:\\todo\\1.txt");
		File f2 = new File("D:\\todo\\2.jpg");
		File[] srcfile = { f1, f2 };

		// ѹ������ļ�
		File zipfile = new File("D:\\todo\\3.zip");
//		TestZIP.zipFiles(srcfile, zipfile);

		// ��Ҫ��ѹ�����ļ�
		File file = new File("D:\\todo\\3.zip");
		// ��ѹ���Ŀ��Ŀ¼
		String dir = "D:\\todo\\small\\";//��ѹ�� D:/todo��small�ļ�����
		TestZIP.unZipFiles(file, dir);
		
		
	}
}