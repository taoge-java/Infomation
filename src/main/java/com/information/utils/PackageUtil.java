package com.information.utils;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.jfinal.kit.PathKit;

/**
 * 包扫描工具类
 * @author zengjintao
 * @version 1.0
 * @create_at 2017年7月3日下午5:54:31
 */
public class PackageUtil {
	
	public static <T> List<Class<? extends T>> scanPackage(String packageName,String classesPath,boolean isweb){ 
		if(StrKit.isEmpoty(packageName))
			throw new RuntimeException("packageName can not be null");
		String  filePath= null;
		if(isweb){
			filePath = PathKit.getWebRootPath()+"/" + classesPath + packageName.replace(".", "/");
			//filePath = PathKit.getWebRootPath()+File.separator + classesPath + packageName.replace(".", "\\"); 
		}else{
			filePath = ClassLoader.getSystemResource("").getPath() + packageName.replace(".", "\\");
		}
		List<String> classNames = getClassName(filePath);
		return getAllClass(classNames);
	}

	@SuppressWarnings({ "unchecked","rawtypes" })
	public static <T> List<Class<? extends T>> scanPackage(List<String> packageList,String classesPath,boolean isweb){
		List<Class<? extends T>> classList=new ArrayList();
		for(String packageName:packageList){
			String filePath =null;
			if(isweb){
				filePath = PathKit.getWebRootPath()+"/" + classesPath + packageName.replace(".", "/");
				//filePath = PathKit.getWebRootPath()+File.separator + classesPath + packageName.replace(".", "\\"); 
			}else{
				filePath = ClassLoader.getSystemResource("").getPath() + packageName.replace(".", "\\");
			}
			List<String> classNames = getClassName(filePath);
			classList.addAll(getAllClass(classNames));
		}
		return classList;
	}
	
	private  static List<String> getClassName(String filePath) {
        return  getClassName(filePath, null);
	}

	/**
	 * 获取包下所有类
	 * @param filePath
	 * @param object
	 * @return
	 */
	private  static List<String> getClassName(String filePath, Object object) {
		List<String> className = new ArrayList<String>();  
		File file=new File(filePath);
		File[] list=file.listFiles();//遍历包下的文件夹
		for(File children:list){
			if(children.isDirectory()){
			    className.addAll(getClassName(children.getPath(), className));
			}else{
				String childFilePath = children.getPath();  
				//linux
			//	childFilePath = childFilePath.substring(childFilePath.indexOf("/classes") + 9, childFilePath.lastIndexOf("."));  
			//	childFilePath = childFilePath.replace("/", "."); 
                childFilePath = childFilePath.substring(childFilePath.indexOf("\\classes") + 9, childFilePath.lastIndexOf("."));  
                childFilePath = childFilePath.replace("\\", ".");  
                className.add(childFilePath);  
			}
		}
	   return className;
	}
	
	
	@SuppressWarnings({"unchecked", "rawtypes" })
	public static <T> List<Class<? extends T>> getAllClass(List<String> classesNames){
		List<Class<? extends T>> classList = new ArrayList();
		for(String name:classesNames){
			try {
			    Class<?> cla= ClassUtil.getInstance(ClassUtil.forName(name)).get();
			    classList.add((Class<? extends T>)cla);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return  classList;
	}
	
	  /** 
     * 从项目文件获取某包下所有类 
     * @param filePath 文件路径 
     * @param className 类名集合 
     * @param childPackage 是否遍历子包 
     * @return 类的完整名称 
     */  
    @SuppressWarnings("unused")
	private static List<String> getClassNameByFile(String filePath, List<String> className, boolean childPackage) {  
        List<String> myClassName = new ArrayList<String>();  
        File file = new File(filePath);  
        File[] childFiles = file.listFiles();  
        for (File childFile : childFiles) {  
            if (childFile.isDirectory()) {  
                if (childPackage) {  
                    myClassName.addAll(getClassNameByFile(childFile.getPath(), myClassName, childPackage));  
                }  
            } else {  
                String childFilePath = childFile.getPath();  
                if (childFilePath.endsWith(".class")) {  
                    childFilePath = childFilePath.substring(childFilePath.indexOf("\\classes") + 9, childFilePath.lastIndexOf("."));  
                    childFilePath = childFilePath.replace("\\", ".");  
                    myClassName.add(childFilePath);  
                }  
            }  
        }  
  
        return myClassName;  
    }  
	
    
    /** 
     * 从所有jar中搜索该包，并获取该包下所有类 
     * @param urls URL集合 
     * @param packagePath 包路径 
     * @param childPackage 是否遍历子包 
     * @return 类的完整名称 
     */  
    @SuppressWarnings("unused")
	private static List<String> getClassNameByJars(URL[] urls, String packagePath, boolean childPackage) {  
        List<String> myClassName = new ArrayList<String>();  
        if (urls != null) {  
            for (int i = 0; i < urls.length; i++) {  
                URL url = urls[i];  
                String urlPath = url.getPath();  
                // 不必搜索classes文件夹  
                if (urlPath.endsWith("classes/")) {  
                    continue;  
                }  
                String jarPath = urlPath + "!/" + packagePath;  
                myClassName.addAll(getClassNameByJar(jarPath, childPackage));  
            }  
        }  
        return myClassName;  
    }

    /** 
     * 从jar获取某包下所有类 
     * @param jarPath jar文件路径 
     * @param childPackage 是否遍历子包 
     * @return 类的完整名称 
     */  
    @SuppressWarnings("resource")
	private static List<String> getClassNameByJar(String jarPath, boolean childPackage) {  
        List<String> myClassName = new ArrayList<String>();  
        String[] jarInfo = jarPath.split("!");  
        String jarFilePath = jarInfo[0].substring(jarInfo[0].indexOf("/"));  
        String packagePath = jarInfo[1].substring(1);  
        try {  
            JarFile jarFile = new JarFile(jarFilePath);  
            Enumeration<JarEntry> entrys = jarFile.entries();  
            while (entrys.hasMoreElements()) {  
                JarEntry jarEntry = entrys.nextElement();  
                String entryName = jarEntry.getName();  
                if (entryName.endsWith(".class")) {  
                    if (childPackage) {  
                        if (entryName.startsWith(packagePath)) {  
                            entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));  
                            myClassName.add(entryName);  
                        }  
                    } else {  
                        int index = entryName.lastIndexOf("/");  
                        String myPackagePath;  
                        if (index != -1) {  
                            myPackagePath = entryName.substring(0, index);  
                        } else {  
                            myPackagePath = entryName;  
                        }  
                        if (myPackagePath.equals(packagePath)) {  
                            entryName = entryName.replace("/", ".").substring(0, entryName.lastIndexOf("."));  
                            myClassName.add(entryName);  
                        }  
                    }  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return myClassName;  
    }  
}
