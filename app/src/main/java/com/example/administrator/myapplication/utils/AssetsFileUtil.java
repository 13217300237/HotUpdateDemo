package com.example.administrator.myapplication.utils;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AssetsFileUtil {

    /**
     * 将assets中的文件拷贝到app的缓存目录，并且返回拷贝之后文件的绝对路径
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String copyAssetToCache(Context context, String fileName) {

        File cacheDir = context.getFilesDir();//app的缓存目录
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();//如果没有缓存目录，就创建
        }
        File outPath = new File(cacheDir, fileName);//创建输出的文件位置
        if (outPath.exists()) {
            outPath.delete();//如果该文件已经存在，就删掉
        }
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            boolean res = outPath.createNewFile();//创建文件，如果创建成功，就返回true
            if (res) {
                is = context.getAssets().open(fileName);//拿到main/assets目录的输入流，用于读取字节
                fos = new FileOutputStream(outPath);//读取出来的字节最终写到outPath
                byte[] buf = new byte[is.available()];//缓存区
                int byteCount;
                while ((byteCount = is.read(buf)) != -1) {//循环读取
                    fos.write(buf, 0, byteCount);
                }
                Toast.makeText(context, "加载成功", Toast.LENGTH_SHORT).show();
                return outPath.getAbsolutePath();
            } else {
                Toast.makeText(context, "创建失败", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fos) {
                    fos.flush();
                    fos.close();
                }
                if (null != is)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
