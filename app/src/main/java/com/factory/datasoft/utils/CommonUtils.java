package com.factory.datasoft.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.util.List;

import static android.content.Context.WINDOW_SERVICE;

public class CommonUtils {
    /**
     * Phương thức kiểm tra quyền cho ứng dụng
     *
     * @param context     - context ứng dụng
     * @param permissions - danh sách quyền
     * @return 0 -> đã được cấp, ngược lại là đã có quyền không được cấp
     * @Created_by nblinh on 26/04/2019
     */
    public static int checkPermissions(Context context, String[] permissions) {
        try {
            int permissionCheck = PackageManager.PERMISSION_GRANTED;
            for (String permission : permissions) {
                permissionCheck += ContextCompat.checkSelfPermission(context, permission);
            }
            return permissionCheck;
        } catch (Exception e) {
            handleException(e);
        }
        return -1;
    }

    /**
     * Phương thức lấy kích thước của màn hình
     *
     * @return - point chứa thông số màn hình
     * @Created_by nblinh on 26/04/2019
     */
    public static Point getRealScreenSize(Context context) {
        try {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            Point size = new Point();
            display.getRealSize(size);
            return size;
        } catch (Exception e) {
            handleException(e);
        }
        return null;
    }

    /**
     * lấy chiều rộng của màn hình
     *
     * @param context - context
     * @Created_by nblinh on 26/04/2019
     */
    public static int getScreenWidthInPixels(Context context) {
        try {
            DisplayMetrics dm = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(dm);
            return dm.widthPixels;
        } catch (Exception e) {
            handleException(e);
        }
        return -1;
    }

    /**
     * lấy chiều cao của màn hình
     *
     * @param context - context
     * @Created_by nblinh on 26/04/2019
     */
    public static int getScreenHeightInPixels(Context context) {
        try {
            DisplayMetrics dm = new DisplayMetrics();
            WindowManager windowManager = (WindowManager) context.getSystemService(WINDOW_SERVICE);
            windowManager.getDefaultDisplay().getMetrics(dm);
            return dm.heightPixels;
        } catch (Exception e) {
            handleException(e);
        }
        return -1;
    }

    /**
     * Chuyển đổi dp sang pixel
     *
     * @param dp - dp
     * @return
     * @Created_by nblinh on 26/04/2019
     */
    public static float convertDpToPixel(float dp) {
        try {
            DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
            float px = dp * (metrics.densityDpi / 160f);
            return Math.round(px);
        } catch (Exception e) {
            handleException(e);
        }
        return -1;
    }

    /**
     * Phương thức sinh hash key cho ứng dụng
     *
     * @param context - context ứng dụng
     * @return - hash key
     * @Created_by nblinh on 26/04/2019
     */
    public static String printKeyHash(Activity context) {
        PackageInfo packageInfo;
        String key = null;
        try {
            String packageName = context.getApplicationContext().getPackageName();
            packageInfo = context.getPackageManager().getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : packageInfo.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                key = new String(Base64.encode(md.digest(), 0));
            }
        } catch (Exception e) {
            handleException(e);
        }
        return key;
    }

    /**
     * Phương thức lấy json string từ đường dẫn file
     *
     * @param context  - context ứng dụng
     * @param filePath - đường dẫn file
     * @return - chuỗi json
     * @Created_by nblinh on 26/04/2019
     */
    public static String loadJSONFromAsset(Context context, String filePath) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filePath);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            handleException(ex);
        }
        return json;

    }

    /**
     * Phương thức lấy danh sách đối tượng từ chuỗi json
     *
     * @param json - chuỗi json
     * @return - danh sách đối tượng
     * @Created_by nblinh on 26/04/2019
     */
    public static List<Object> getListObjectFromJsonString(String json) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<Object>>() {
        }.getType();
        return gson.fromJson(json, collectionType);
    }

    /**
     * Phương thức kiểm tra tra có kết nối mạng không
     *
     * @return - thiết bị đang có kết nối mạng hay không
     * @Created_by nblinh on 26/04/2019
     */
    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (Exception e) {
            handleException(e);
        }
        return false;
    }

    /**
     * Phương thức xử lý ngoại lệ
     *
     * @param ex - lỗi ngoại lệ
     * @Created_by nblinh on 26/04/2019
     */
    public static void handleException(Exception ex) {
        //xử lý ngoại lệ
        ex.printStackTrace();
    }
}
