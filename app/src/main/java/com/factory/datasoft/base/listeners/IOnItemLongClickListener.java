package com.factory.datasoft.base.listeners;

/**
 * Là listener chịu trách nhiệm cho sự kiện long click cho View
 *
 * @param <T> là kiểu dữ liệu được truyền đi khi sự kiện long click xảy ra
 * @Created_by nblinh on 26/04/2019
 */
public interface IOnItemLongClickListener<T> {
    /**
     * Phương thức được sử dụng khi sự kiện click của View được kích hoạt
     *
     * @param data là tham số được truyền vào khi View được click
     * @Created_by nblinh on 26/04/2019
     */
    void onItemClick(T data);
}
