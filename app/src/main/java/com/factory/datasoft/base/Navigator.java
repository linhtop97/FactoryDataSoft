package com.factory.datasoft.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.factory.datasoft.R;
import com.factory.datasoft.utils.CommonUtils;

/**
 * Lớp điều hướng cho màn hình
 *
 * @Created_by nblinh on 26/04/2019
 */
public class Navigator {

    @NonNull
    private AppCompatActivity mActivity;
    @NonNull
    private Fragment mFragment;

    private ProgressDialog mDialog;

    /**
     * Phương thức khởi tạo lớp Navigator với tham số truyền vào là 1 activity
     *
     * @param activity - activity
     * @Created_by nblinh on 26/04/2019
     */
    public Navigator(@NonNull Activity activity) {
        mActivity = (AppCompatActivity) activity;
    }

    /**
     * Phương thức khởi tạo lớp Navigator với tham số truyền vào là 1 fragment
     *
     * @param fragment - fragment
     * @Created_by nblinh on 26/04/2019
     */
    public Navigator(@NonNull Fragment fragment) {
        try {
            mFragment = fragment;
            mActivity = (AppCompatActivity) fragment.getActivity();
        } catch (Exception e) {
            CommonUtils.handleException(e);
        }
    }

    /**
     * Phương thức khởi chạy 1 activity thông qua 1 intent
     *
     * @param intent - intent
     * @Created_by nblinh on 26/04/2019
     */
    public void startActivity(@NonNull Intent intent) {
        try {
            mActivity.startActivity(intent);
            setActivityTransactionAnimation(ActivityTransition.START);
        } catch (Exception e) {
            CommonUtils.handleException(e);
        }
    }

    /**
     * Phương thức khởi chạy 1 activity thông qua 1 intent và hiệu ứng
     *
     * @param intent - intent
     * @param anim   - hiệu ứng
     * @Created_by nblinh on 26/04/2019
     */
    public void startActivity(@NonNull Intent intent, int anim) {
        try {
            mActivity.startActivity(intent);
            setActivityTransactionAnimation(ActivityTransition.FINISH);
        } catch (Exception e) {
            CommonUtils.handleException(e);
        }
    }

    /**
     * Phương thức khởi chạy 1 activity thông qua tên class
     *
     * @param clazz - tên_lớp.class
     * @Created_by nblinh on 26/04/2019
     */
    public void startActivity(@NonNull Class<? extends Activity> clazz) {
        try {
            Intent intent = new Intent(mActivity, clazz);
            startActivity(intent);
        } catch (Exception e) {
            CommonUtils.handleException(e);
        }
    }

    /**
     * Phương thức khởi chạy 1 activity thông qua tên class và hiệu ứng khởi chạy
     *
     * @param clazz - tên_lớp.class
     * @param anim  - hiệu ứng
     * @Created_by nblinh on 26/04/2019
     */
    public void startActivity(@NonNull Class<? extends Activity> clazz, int anim) {
        try {
            Intent intent = new Intent(mActivity, clazz);
            startActivity(intent, anim);
        } catch (Exception e) {
            CommonUtils.handleException(e);
        }
    }

    /**
     * Phương thức khởi chạy 1 activity có tham số là tên activity và 1 bundle
     *
     * @param clazz - tên_lớp.class
     * @param args  - bundle
     * @Created_by nblinh on 26/04/2019
     */
    public void startActivity(@NonNull Class<? extends Activity> clazz, Bundle args) {
        try {
            Intent intent = new Intent(mActivity, clazz);
            intent.putExtras(args);
            startActivity(intent);
        } catch (Exception e) {
            CommonUtils.handleException(e);
        }
    }

    /**
     * Phương thức khởi chạy 1 activity và loại bỏ hết các activity khởi stack
     *
     * @param clazz - tên_lớp.class
     * @Created_by nblinh on 26/04/2019
     */
    public void startActivityAtRoot(@NonNull Class<? extends Activity> clazz) {
        try {
            Intent intent = new Intent(mActivity, clazz);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e) {
            CommonUtils.handleException(e);
        }
    }

    /**
     * Phươn thức khởi chạy 1 activity có đợi kq trả về
     *
     * @param intent      - intent
     * @param requestCode - request code
     * @Created_by nblinh on 26/04/2019
     */
    public void startActivityForResult(@NonNull Intent intent, int requestCode) {
        try {
            mActivity.startActivityForResult(intent, requestCode);
            setActivityTransactionAnimation(ActivityTransition.START);
        } catch (Exception e) {
            CommonUtils.handleException(e);
        }
    }

    /**
     * Phươn thức khởi chạy 1 activity có đợi kq trả về
     *
     * @param clazz       - tên_lớp.class
     * @param args        - bundle
     * @param requestCode - request code
     * @Created_by nblinh on 26/04/2019
     */
    public void startActivityForResult(@NonNull Class<? extends Activity> clazz, Bundle args,
                                       int requestCode) {
        try {
            Intent intent = new Intent(mActivity, clazz);
            intent.putExtras(args);
            startActivityForResult(intent, requestCode);
        } catch (Exception e) {
            CommonUtils.handleException(e);
        }
    }

    /**
     * Phương thức finish activity
     *
     * @Created_by nblinh on 26/04/2019
     */
    public void finishActivity() {
        try {
            mActivity.finish();
            setActivityTransactionAnimation(ActivityTransition.FINISH);
        } catch (Exception e) {
            CommonUtils.handleException(e);
        }
    }

    /**
     * Phương thức finish activity với kết quả trả về
     *
     * @param intent     - intent
     * @param resultCode - request code
     * @Created_by nblinh on 26/04/2019
     */
    public void finishActivityWithResult(Intent intent, int resultCode) {
        try {
            mActivity.setResult(resultCode, intent);
            finishActivity();
        } catch (Exception e) {
            CommonUtils.handleException(e);
        }
    }

    /**
     * Điều hướng tới 1 địa chỉ url
     *
     * @param url -địa chỉ web
     * @Created_by nblinh on 26/04/2019
     */
    public void openUrl(String url) {
        try {
            if (TextUtils.isEmpty(url) || !Patterns.WEB_URL.matcher(url).matches()) {
                return;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
            startActivity(intent);
        } catch (Exception e) {
            CommonUtils.handleException(e);
        }
    }

    /**
     * Thêm 1 fragment lên activity hiện tại
     *
     * @param containerViewId - id của layout muốn hiển thị fragment
     * @param fragment        - fragment muốn hiển thị
     * @param addToBackStack  - cờ có cho vào stack fragment hay không
     * @param animation       - hiệu ứng khi fragment được thêm
     * @param tag             - thẻ của fragment được thêm
     * @Created_by nblinh on 26/04/2019
     */
    public void addFragment(@IdRes int containerViewId, Fragment fragment,
                            boolean addToBackStack, int animation, String tag) {
        try {
            FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();
            setFragmentTransactionAnimation(transaction, animation);
            if (addToBackStack) {
                transaction.addToBackStack(fragment.getClass().getSimpleName());
            }
            transaction.replace(containerViewId, fragment, tag);
            transaction.commit();
        } catch (Exception e) {
            CommonUtils.handleException(e);
        }
    }

    /**
     * Thêm 1 fragment lên fragment hiện tại
     *
     * @param containerViewId - id của layout muốn hiển thị fragment
     * @param fragment        - fragment muốn hiển thị
     * @param addToBackStack  - cờ có cho vào stack fragment hay không
     * @param animation       - hiệu ứng khi fragment được thêm
     * @param tag             - thẻ của fragment được thêm
     * @Created_by nblinh on 26/04/2019
     */
    public void goNextChildFragment(@IdRes int containerViewId, Fragment fragment,
                                    boolean addToBackStack, int animation, String tag) {
        try {
            FragmentTransaction transaction = mFragment.getChildFragmentManager().beginTransaction();
            setFragmentTransactionAnimation(transaction, animation);
            if (addToBackStack) {
                transaction.addToBackStack(fragment.getClass().getSimpleName());
            }
            transaction.replace(containerViewId, fragment, tag);
            transaction.commitAllowingStateLoss();
            mFragment.getChildFragmentManager().executePendingTransactions();
        } catch (Exception e) {
            CommonUtils.handleException(e);
        }
    }

    /**
     * Phương thức trở về từ 1 fragment con của 1 fragment đảm bảo luôn có 1 fragment đang hiện
     *
     * @return - trở về/gỡ fragment có thành công hay không
     * @Created_by nblinh on 26/04/2019
     */
    public boolean goBackChildFragment() {
        try {
            boolean isShowPrevious = mFragment.getChildFragmentManager().getBackStackEntryCount() > 1;
            if (isShowPrevious) {
                mFragment.getChildFragmentManager().popBackStackImmediate();
            }
            return isShowPrevious;
        } catch (Exception e) {
            CommonUtils.handleException(e);
        }
        return false;
    }

    /**
     * Phương thức truyền hiệu ứng cho fragment
     *
     * @param transaction
     * @param animation
     * @Created_by nblinh on 26/04/2019
     */
    private void setFragmentTransactionAnimation(FragmentTransaction transaction,
                                                 @NavigateAnim int animation) {
        switch (animation) {
            case NavigateAnim.FADED:
                transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                        android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            case NavigateAnim.RIGHT_LEFT:
                transaction.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out,
                        R.anim.slide_left_in, R.anim.slide_right_out);
                break;
            case NavigateAnim.LEFT_RIGHT:
                transaction.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_right_out,
                        R.anim.slide_right_in, R.anim.slide_left_out);
                break;
            case NavigateAnim.BOTTOM_UP:
                transaction.setCustomAnimations(R.anim.slide_bottom_in, R.anim.slide_top_out,
                        R.anim.slide_top_in, R.anim.slide_bottom_out);
                break;
            case NavigateAnim.NONE:
                break;
            default:
                break;
        }
    }

    /**
     * Phương thức truyền hiệu ứng cho activity
     *
     * @param animation
     * @Created_by nblinh on 26/04/2019
     */
    private void setActivityTransactionAnimation(@ActivityTransition int animation) {
        switch (animation) {
            case ActivityTransition.START:
                mActivity.overridePendingTransition(R.anim.translate_left, R.anim.translate_still);
                break;
            case ActivityTransition.FINISH:
                mActivity.overridePendingTransition(R.anim.translate_still, R.anim.translate_right);
                break;
            case ActivityTransition.NONE:
                break;
            default:
                break;
        }
    }

    /**
     * Phương thức hiển thị thông báo
     *
     * @param stringId - id của string resource
     * @Created_by nblinh on 26/04/2019
     */
    public void showToast(@StringRes int stringId) {
        try {
            Toast.makeText(mActivity, stringId, Toast.LENGTH_SHORT).show();
        } catch (Resources.NotFoundException e) {
            CommonUtils.handleException(e);
        }
    }


    /**
     * Phương thức hiển thị thông báo
     *
     * @param message - thông điệp muốn hiển thị
     * @Created_by nblinh on 26/04/2019
     */
    public void showToast(String message) {
        try {
            if (message != null) {
                Toast.makeText(mActivity, message, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            CommonUtils.handleException(e);
        }
    }

    /**
     * Phương thức hiển thị thông báo
     *
     * @param stringId - id của string resource
     * @Created_by nblinh on 26/04/2019
     */
    public void showToastOnTopScreen(@StringRes int stringId) {
        try {
            Toast toast = Toast.makeText(mActivity,
                    stringId, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 200);
            toast.show();
        } catch (Resources.NotFoundException e) {
            CommonUtils.handleException(e);
        }
    }

    /**
     * Phương thức khởi tạo progressbar
     *
     * @Created_by nblinh on 26/04/2019
     */
    protected void initProgressBar(int message) {
        try {
            mDialog = new ProgressDialog(mActivity) {
                @Override
                public void onBackPressed() {
                    super.onBackPressed();
                }
            };
            mDialog.setCancelable(false);
            mDialog.setCanceledOnTouchOutside(false);
        } catch (Exception e) {
            CommonUtils.handleException(e);
        }
    }

    /**
     * Phương thức gán thông điệp cho dialog
     *
     * @param message - thông điệp cho dialog
     * @Created_by nblinh on 26/04/2019
     */
    protected void setDialogMessage(int message) {
        try {
            mDialog.setMessage(mActivity.getString(message));
        } catch (Exception e) {
            CommonUtils.handleException(e);
        }
    }

    /**
     * Phương thức hiển thị 1 dialog
     *
     * @Created_by nblinh on 26/04/2019
     */
    protected void showLoading() {
        try {
            if (mDialog != null && !mDialog.isShowing()) {
                mDialog.show();
            }
        } catch (Exception e) {
            CommonUtils.handleException(e);
        }
    }

    /**
     * Phương thức ẩn/đóng dialog
     *
     * @Created_by nblinh on 26/04/2019
     */
    protected void hideLoading() {
        try {
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
            }
        } catch (Exception e) {
            CommonUtils.handleException(e);
        }
    }

    /**
     * Phương thức ẩn bàn phím
     *
     * @Created_by nblinh on 26/04/2019
     */
    public void hideKeyboard() {
        try {
            InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            View view = mActivity.getCurrentFocus();
            if (view == null) {
                view = new View(mActivity);
            }
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
            CommonUtils.handleException(e);
        }
    }


    /**
     * Định nghĩa kiểu cho animation dành cho fragment
     *
     * @Created_by nblinh on 26/04/2019
     */
    @IntDef({
            NavigateAnim.RIGHT_LEFT, NavigateAnim.BOTTOM_UP, NavigateAnim.FADED, NavigateAnim.NONE,
            NavigateAnim.LEFT_RIGHT
    })
    public @interface NavigateAnim {
        int NONE = 0x00;
        int RIGHT_LEFT = 0x01;
        int BOTTOM_UP = 0x02;
        int FADED = 0x03;
        int LEFT_RIGHT = 0x04;
    }

    /**
     * Định nghĩa kiểu cho animation dành cho activity
     *
     * @Created_by nblinh on 26/04/2019
     */
    @IntDef({ActivityTransition.NONE, ActivityTransition.START, ActivityTransition.FINISH})
    public @interface ActivityTransition {
        int NONE = 0x00;
        int START = 0x01;
        int FINISH = 0x02;
    }
}
