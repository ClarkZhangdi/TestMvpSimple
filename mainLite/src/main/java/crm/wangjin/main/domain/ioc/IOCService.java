package crm.wangjin.main.domain.ioc;

import android.support.annotation.NonNull;
import android.util.Log;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import crm.wangjin.main.domain.executor.Executor;
import crm.wangjin.main.domain.ioc.inject.NotNull;
import crm.wangjin.main.domain.utils.LogUtil;


/**
 * Created by elensliu on 2016/11/1.
 */

public class IOCService {

    public static <T> T doInject(T objcc) {

        Class<?> c = objcc.getClass();
        objcc = doInjectClass(objcc,
                c);

        return objcc;
    }

    private static <T> T doInjectClass(T objcc, Class<?> c) {


        Field[] fs = c.getDeclaredFields();
        if (fs != null) {
            for (Field f : fs) {

                try {
                    //第一次先获取反射出来的类的类型
                    Class<?> fc = f.getType();
                    if (fc.isPrimitive()) {
                        continue;
                    }

                    f.setAccessible(true);
                    Object o = f.get(objcc);
                    if (o == null) {
                        if (fc.isAssignableFrom(String.class)) {
                            o = "";
                        } else if (fc.isAssignableFrom(List.class)) {
                            o = new ArrayList<>(0);
                        } else if (fc.isArray()) {

                            Class<?> type = fc.getComponentType();
                            o = Array.newInstance(type,
                                    0);
                        } else {

                            try {
                                o = fc.newInstance();
                                if (fc.isAnnotationPresent(NotNull.class)) {
                                    o = doInject(o);
                                }
                            } catch (Exception e) {
                                LogUtil.w("注入出错:" + e.getLocalizedMessage());
                            }

                        }
                    } else {
                        //当对象不为空时再根据对象获取参数的真实类型
                        Class<?> tc = o.getClass();
                        if (tc.isAnnotationPresent(NotNull.class)) {
                            o = doInject(o);
                        }
                    }
                    f.set(objcc,
                            o);
                } catch (Exception e) {
                    LogUtil.w("注入出错:" + e.getLocalizedMessage());
                }
            }
        }
        Class<?> sc = c.getSuperclass();
        if (sc != null) {
            objcc = doInjectClass(objcc,
                    sc);
        }
        return objcc;
    }
}
