/**
 * 
 */
package com.information.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 文件操作
 * 
 * @author defier.lai 
 * 2010-6-19 下午05:14:44
 * @version 1.0
 */
public class CmsFileUtils {

	/**
	 * 路径分隔符
	 */
	public static final char SPT = '/';

	/**
	 * 创建目录，支持多级 路径之间必须以"/"分隔
	 * 
	 * @param path
	 */
	public static void createDir(String path) {
		String[] paths = StringUtils.split(path, SPT);
		StringBuffer sb = new StringBuffer();
		for (String p : paths) {
			sb.append(p);
			sb.append(SPT);
			File dir = new File(sb.toString());
			if (!dir.exists()) {
				dir.mkdir();
			}
		}
	}

	public static boolean deleteFile(String path) {
		try {
			if (StringUtils.isEmpty(path)
					|| (path.startsWith("/") && WINDOWS.equals(getOsVersion()))) {
				return false; // 防止在windows下误删文件啊,太恐怖了
			}
			deleteFile(new File(path));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean deleteFile(File path) {
		try {
			if (path.isDirectory()) {
				File[] child = path.listFiles();
				if (child != null && child.length != 0) {
					for (int i = 0; i < child.length; i++) {
						deleteFile(child[i]);
						child[i].delete();
					}
				}
			}
			path.delete();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static final String WINDOWS = "windows";
	public static final String LINUX = "linux";

	public static String getOsVersion() {
		if ("\\".equals(File.separator)) {
			return WINDOWS;
		} else if ("/".equals(File.separator)) {
			return LINUX;
		} else {
			throw new IllegalAccessError("未知版本系统");
		}
	}

	/**
	 * 取得文件夹大小
	 * 
	 * @param realPath
	 * @return
	 */
	public static long getFileLength(String realPath) {
		try {
			return getFileLength(new File(realPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 取得文件夹大小
	 * 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public static long getFileLength(File f) throws Exception {
		long size = 0;
		File flist[] = f.listFiles();
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getFileLength(flist[i]);
			} else {
				size = size + flist[i].length();
			}
		}
		return size;
	}

	public static long getFileSize(String path) {
		return getFileSize(new File(path));
	}

	/**
	 * 递归求取目录文件个数
	 * 
	 * @param f
	 * @return
	 */
	public static long getFileSize(File f) {
		long size = 0;
		File flist[] = f.listFiles();
		size = flist.length;
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getFileSize(flist[i]);
				size--;
			}
		}
		return size;

	}

	public static String getFileContent(File file) {
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "utf-8"));
			StringBuffer buffer = new StringBuffer();
			String text;
			while ((text = input.readLine()) != null) {
				buffer.append(text + "\n");
			}
			input.close();
			return buffer.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}


	public static String toUploadPath(String webPath) {
		if (StringUtils.isEmpty(webPath)) {
			return webPath;
		}
		int idx = webPath.lastIndexOf("/upload");
		return webPath.substring(idx);
	}

	public static String getFileSizeDesc(long length) {
		if (length < 1024) {
			return length + "B";
		} else if (length < 1024 * 1024) {
			return length / 1024 + "K";
		} else {
			return length / (1024 * 1024) + "M";
		}
	}
	
	public static String getRandFileName() {
		String name = longToN36(System.currentTimeMillis());
		return RandomStringUtils.random(4, N36_CHARS) + name;
	}

	public static String replaceSpt(String path) {
		return path.replaceAll("\\\\", "/");
	}

	public static void moveFile(File srcFile, File destFile) throws IOException {
		if (srcFile == null) {
			throw new NullPointerException("Source must not be null");
		}
		if (destFile == null) {
			throw new NullPointerException("Destination must not be null");
		}
		if (!srcFile.exists()) {
			throw new FileNotFoundException("Source '" + srcFile
					+ "' does not exist");
		}
		if (srcFile.isDirectory()) {
			throw new IOException("Source '" + srcFile + "' is a directory");
		}
		if (destFile.exists()) {
			throw new IOException("Destination '" + destFile
					+ "' already exists");
		}
		if (destFile.isDirectory()) {
			throw new IOException("Destination '" + destFile
					+ "' is a directory");
		}
		boolean rename = srcFile.renameTo(destFile);
		if (!rename) {
			copyFile(srcFile, destFile);
			if (!srcFile.delete()) {
				throw new IOException("Failed to delete original file '"
						+ srcFile + "' after copy to '" + destFile + "'");
			}
		}
	}

	public static void copyFile(File srcFile, File destFile) throws IOException {
		copyFile(srcFile, destFile, true);
	}

	public static void copyFile(File srcFile, File destFile,
			boolean preserveFileDate) throws IOException {
		if (srcFile == null) {
			throw new NullPointerException("Source must not be null");
		}
		if (destFile == null) {
			throw new NullPointerException("Destination must not be null");
		}
		if (srcFile.exists() == false) {
			throw new FileNotFoundException("Source '" + srcFile
					+ "' does not exist");
		}
		if (srcFile.isDirectory()) {
			throw new IOException("Source '" + srcFile
					+ "' exists but is a directory");
		}
		if (srcFile.getCanonicalPath().equals(destFile.getCanonicalPath())) {
			throw new IOException("Source '" + srcFile + "' and destination '"
					+ destFile + "' are the same");
		}
		if (destFile.getParentFile() != null
				&& destFile.getParentFile().exists() == false) {
			if (destFile.getParentFile().mkdirs() == false) {
				throw new IOException("Destination '" + destFile
						+ "' directory cannot be created");
			}
		}
		if (destFile.exists() && destFile.canWrite() == false) {
			throw new IOException("Destination '" + destFile
					+ "' exists but is read-only");
		}
		doCopyFile(srcFile, destFile, preserveFileDate);
	}

	private static void doCopyFile(File srcFile, File destFile, boolean preserveFileDate) throws IOException {
		if (destFile.exists() && destFile.isDirectory()) {
			throw new IOException("Destination '" + destFile
					+ "' exists but is a directory");
		}

		FileInputStream input = new FileInputStream(srcFile);
		try {
			FileOutputStream output = new FileOutputStream(destFile);
			try {
				IOUtils.copy(input, output);
			} finally {
				IOUtils.closeQuietly(output);
			}
		} finally {
			IOUtils.closeQuietly(input);
		}

		if (srcFile.length() != destFile.length()) {
			throw new IOException("Failed to copy full contents from '"
					+ srcFile + "' to '" + destFile + "'");
		}
		if (preserveFileDate) {
			destFile.setLastModified(srcFile.lastModified());
		}
	}

	
	
	/**
	 * 长整数转换成N62
	 * 
	 * @param l
	 * @return
	 */
	public static String longToN62(long l) {
		return longToNBuf(l, N62_CHARS).reverse().toString();
	}

	public static String longToN36(long l) {
		return longToNBuf(l, N36_CHARS).reverse().toString();
	}
	
	private static StringBuilder longToNBuf(long l, char[] chars) {
		int upgrade = chars.length;
		StringBuilder result = new StringBuilder();
		int last;
		while (l > 0) {
			last = (int) (l % upgrade);
			result.append(chars[last]);
			l /= upgrade;
		}
		return result;
	}

	/**
	 * 长整数转换成N62
	 * 
	 * @param l
	 * @param length
	 *            如N62不足length长度，则补足0。
	 * @return
	 */
	public static String longToN62(long l, int length) {
		StringBuilder sb = longToNBuf(l, N62_CHARS);
		for (int i = sb.length(); i < length; i++) {
			sb.append('0');
		}
		return sb.reverse().toString();
	}

	public static String longToN36(long l, int length) {
		StringBuilder sb = longToNBuf(l, N36_CHARS);
		for (int i = sb.length(); i < length; i++) {
			sb.append('0');
		}
		return sb.reverse().toString();
	}

	/**
	 * N62转换成整数
	 * 
	 * @param n62
	 * @return
	 */
	public static long n62ToLong(String n62) {
		return nToLong(n62, N62_CHARS);
	}

	public static long n36ToLong(String n36) {
		return nToLong(n36, N36_CHARS);
	}

	private static long nToLong(String s, char[] chars) {
		char[] nc = s.toCharArray();
		long result = 0;
		long pow = 1;
		for (int i = nc.length - 1; i >= 0; i--, pow *= chars.length) {
			int n = findNIndex(nc[i], chars);
			result += n * pow;
		}
		return result;
	}
	
	public static final char[] N62_CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	public static final char[] N36_CHARS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };


	private static int findNIndex(char c, char[] chars) {
		for (int i = 0; i < chars.length; i++) {
			if (c == chars[i]) {
				return i;
			}
		}
		throw new RuntimeException("N62(N36)非法字符：" + c);
	}
}
