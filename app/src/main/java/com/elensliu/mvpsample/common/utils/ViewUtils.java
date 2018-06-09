package com.elensliu.mvpsample.common.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.R.attr.path;

/**
 * Created by Jerome on 2017/8/4.
 */

public class ViewUtils {

    public static Bitmap viewToBitmap(View view, int viewWidth, int viewHeight) {
        view.layout(0, 0, viewWidth, viewHeight);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    public static Bitmap viewToBitmap(ViewGroup view, int viewWidth, int viewHeight) {
//        view.layout(0, 0, viewWidth, viewHeight);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }

    public static void saveImage(Context context, Bitmap bmp, String name) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file
                    .getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse
                ("file://" + path)));
    }

    public static Bitmap createOneBitmap(View fromView, ImageView toView) {
        fromView.setDrawingCacheEnabled(true);
        Bitmap drawingCache = fromView.getDrawingCache();
        toView.setImageBitmap(drawingCache);
        return null;
    }
}
