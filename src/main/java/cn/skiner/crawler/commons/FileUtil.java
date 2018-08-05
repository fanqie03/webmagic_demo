package cn.skiner.crawler.commons;

import java.io.File;

/**
 * @创建人 skiner
 * @创建时间 2018/8/5
 * @描述
 */
public class FileUtil {
    /**
     * 检测文件，若文件存在，则添加数字后缀，不存在则返回原始文件
     * 文件数从1开始，最大为Integer.MAX_VALUE;
     * 遍历一遍后没有则返回null
     * @param path
     * @return
     */
    public static File get(String path,String name,String suffix){
        File file = new File(path+File.pathSeparator+name+"."+suffix);
        if(!file.exists()){
            return file;
        }

        for(int i = 1; i < Integer.MAX_VALUE; i++){
            file = new File(path+File.pathSeparator+name+"("+i+")."+suffix);
            if(!file.exists()){
                return file;
            }
        }
        return null;
    }
}
