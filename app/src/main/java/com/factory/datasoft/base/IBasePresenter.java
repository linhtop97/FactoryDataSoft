package com.factory.datasoft.base;

/**
 * Class cơ sở cho các presenter
 *
 * @param <T> thể hiện của view
 * @Created_by nblinh on 26/04/2019
 */
public interface IBasePresenter<T extends IBaseView> {
    /**
     * Phương thức truyền view cho presenter
     *
     * @param view - view mvp
     * @Created_by nblinh on 26/04/2019
     */
    void setView(T view);

    /**
     * Phương thức khởi chạy đầu tiên khi presenter bắt đầu hoạt động
     * Created_by Nguyễn Bá Linh on 27/03/2019
     */
    void onStart();

    /**
     * Phương thức kết thúc tương ứng khi presenter hoàn thành công việc của mình và ngừng hoạt động
     *
     * @Created_by nblinh on 26/04/2019
     */
    void onStop();
}
