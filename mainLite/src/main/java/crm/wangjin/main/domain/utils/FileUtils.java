package crm.wangjin.main.domain.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by liuchengen on 2016/12/16.
 */

public class FileUtils {


    public static String readAssetsFileToString(Context context, String path) {

        StringBuilder builder = new StringBuilder();


        try {
            InputStream inputStream = context.getAssets().open(path);

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }
}
