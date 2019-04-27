package com.factory.datasoft.base.listeners;

/**
 * Là interface lắng nghe xứ lý khi lấy dữ liệu
 *
 * @param <T> là kiểu dữ liệu kết quả trả về
 * @Created_by nblinh on 26/04/2019
 */
public interface IDataCallBack<T> {

    /**
     * Được gọi khi lấy dữ liệu về thành công
     *
     * @param data là dữ liệu được trả về
     * @Created_by nblinh on 26/04/2019
     */
    void onDataSuccess(T data);

    /**
     * được gọi khi việc lấy dữ liệu bị thất bại
     *
     * @param msg là thông điệp muốn trả về để xử lý
     * @Created_by nblinh on 26/04/2019
     */
    void onDataFailed(int msg);
}
