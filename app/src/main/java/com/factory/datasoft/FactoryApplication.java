package com.factory.datasoft;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.factory.datasoft.utils.CommonUtils;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Lớp ứng dụng
 *
 * @Created_by nblinhinh on 26/04/2019
 */
public class FactoryApplication extends Application {

    private static FactoryApplication sInstance;

    /**
     * Phương thức lấy context ứng dụng
     *
     * @return - app context
     * @Created_by nblinh on 26/04/2019
     */
    public static FactoryApplication getInstance() {
        return sInstance;
    }

    /**
     * Phương thức khởi tạo của lớp ứng dụng
     *
     * @Created_by nblinh on 26/04/2019
     */
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            sInstance = this;
            //đặt font chữ mặc định cho thư viện
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath(getString(R.string.nunito_regular))
                    .setFontAttrId(R.attr.fontPath)
                    .build()
            );
        } catch (Exception e) {
            CommonUtils.handleException(e);
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //Cài multi dex cho ứng dụng
        MultiDex.install(this);
    }
}
