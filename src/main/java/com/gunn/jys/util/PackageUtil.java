package com.gunn.jys.util;

import com.gunn.jys.annotation.Anon;
import com.gunn.jys.constant.shiro.ShiroConst;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PackageUtil {

    public static final Map<String, List<String>> PERMISSION_MAP = new HashMap<>();

    static {
        PERMISSION_MAP.put(ShiroConst.ANON, getAnnotationLink("com.gunn.jys.controller", Anon.class));
        PERMISSION_MAP.put(ShiroConst.PERMS, getLink("com.gunn.jys.controller"));
    }

    public static List<String> getLink(String packageName) {
        List<String> urlList = new ArrayList<>();
        List<Class> classList = getClassFromPackage(packageName);
        for (Class clazz : classList) {
            boolean hasClassRequestMappingAnnotation = clazz.isAnnotationPresent(RequestMapping.class);
            if (hasClassRequestMappingAnnotation) {
                RequestMapping rm = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
                String[] classValues = rm.value();
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    for (String classValule : classValues) {
                        boolean hasMethodRequestMappingAnnotation = method.isAnnotationPresent(RequestMapping.class);
                        if (hasMethodRequestMappingAnnotation) {
                            RequestMapping reMa = method.getAnnotation(RequestMapping.class);
                            String[] methodValues = reMa.value();
                            for (String methodValue : methodValues) {
                                String meva = StrUtil.dealSlash(methodValue);
                                urlList.add(classValule + meva);
                            }
                        }
                    }
                }
            }
        }
        return urlList;
    }

    /**
     *  获取指定包下使用指定注释的方法url
     * @param packageName
     * @param annotationClass
     * @param <T>
     * @return
     */
    public static <T extends Annotation> List<String> getAnnotationLink(String packageName, Class<T> annotationClass) {
        List<String> urls = new ArrayList<>();
        // 获取指定包下所有的类
        List<Class> classList = getClassFromPackage(packageName);
        for (Class clazz : classList) {
            boolean hasClassRequestMappingAnnotation = clazz.isAnnotationPresent(RequestMapping.class);
            // 只对有 RequestMapping 注解的类进行处理
            if (hasClassRequestMappingAnnotation) {
                RequestMapping rm = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
                String[] classValues = rm.value();
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    boolean hasAnnotation = method.isAnnotationPresent(annotationClass);
                    // 只对有指定注解的方法进行处理
                    if (hasAnnotation) {
                        boolean hasMethodRequestMappingAnnotation = method.isAnnotationPresent(RequestMapping.class);
                        // 只对有 RequestMapping 注解的方法进行处理
                        if (hasMethodRequestMappingAnnotation) {
                            for (String classValue : classValues) {
                                RequestMapping reMe = method.getAnnotation(RequestMapping.class);
                                String[] methodValues = reMe.value();
                                for (String methodValue : methodValues) {
                                    String meVa = StrUtil.dealSlash(methodValue);
                                    urls.add(classValue + meVa);
                                }
                            }
                        }
                    }
                }
            }
        }
        return urls;
    }

    public static List<Class> getClassFromPackage(String packageName) {
        List<Class> classes = new ArrayList<>();

        // 是否循环迭代
        boolean recursive = true;
        // 获取包的名字并进行替换
        String packageDirName = packageName.replace('.', '/');
        //定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            while (dirs.hasMoreElements()) {
                // 获取下一个元素
                URL url = dirs.nextElement();
                // 得到协议名称
                String protocol = url.getProtocol();
                // 如果是以文件的形式保存在服务器上
                if ("file".equals(protocol)) {
                    // 获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    // 以文件的方式扫描整个包下的文件 并添加到集合中
                    findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
                } else if ("jar".equals(protocol)) {
                    // 如果是jar包
                    //定义一个 JarFile
                    JarFile jarFile;
                    try {
                        jarFile = ((JarURLConnection) url.openConnection()).getJarFile();
                        //从此jar包获取一个枚举类
                        Enumeration<JarEntry> entries = jarFile.entries();
                        while (entries.hasMoreElements()) {
                            // 获取jar里的一个实体，可以是目录 和一些jar包的其他文件 如 META-INF 等文件
                            JarEntry jarEntry = entries.nextElement();
                            String name = jarEntry.getName();
                            // 如果是以/开头的
                            if (name.charAt(0) == '/') {
                                // 获取后面的字符串
                                name = name.substring(1);
                            }
                            // 如果前半部分和定义的包名相同
                            if (name.startsWith(packageDirName)) {
                                int idx = name.lastIndexOf('/');
                                // 如果以 / 结尾的是一个包
                                if (idx != -1) {
                                    // 获取包名 把 / 替换成 .
                                    packageName = name.substring(0, idx).replace('/', '.');
                                }
                                // 如果可以迭代下去并且是一个包
                                if ((idx != -1) || recursive) {
                                    // 如果是一个 .class文件而不是目录
                                    if (name.endsWith(".class") && !jarEntry.isDirectory()) {
                                        //去掉后面的 .class 获取真正类名
                                        String className = name.substring(packageName.length() + 1, name.length() - 6);
                                        try {
                                            classes.add(Class.forName(packageName + "." + className));
                                        } catch (ClassNotFoundException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classes;
    }

    public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, List<Class> classes) {
        // 获取此包的目录 建立一个file
        File dir = new File(packagePath);
        // 如果不存在 或者也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        // 如果存在 就获取包下所有文件 包括目录
        File[] dirFiles = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return (recursive && pathname.isDirectory()) || (pathname.getName().endsWith(".class"));
            }
        });
        // 循环所有文件
        for (File file : dirFiles) {
            // 如果是目录 则继续扫描
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
            } else {
                // 如果是 java类文件 去掉 .class 只留下类名
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    // 添加到集合去
                    classes.add(Class.forName(packageName + "." + className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
