package com.factory.datasoft.base.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.factory.datasoft.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Class base cho các adapter dành cho recyclerView
 *
 * @param <T>
 * @Created_by nblinh on 26/04/2019
 */
public abstract class ListAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Context mContext;
    protected List<T> mListData;

    /**
     * Là phương thức khởi tạo cho ListAdapter
     *
     * @param context là được truyền tới từ context nơi khởi tạo thể hiện của lớp
     * @Created_by nblinh on 26/04/2019
     */
    public ListAdapter(Context context) {
        mContext = context;
        mListData = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return mListData != null ? mListData.size() : 0;
    }

    /**
     * Phương thức lấy danh sách dữ liệu hiện tại
     *
     * @return danh sách dữ liệu
     * @Created_by nblinh on 26/04/2019
     */
    public List<T> getListData() {
        return mListData;
    }

    /**
     * Phương thức truyền dữ liệu cho danh sách
     *
     * @param listData danh sách dữ liệu được truyền vào
     * @Created_by nblinh on 26/04/2019
     */
    public void setListData(List<T> listData) {
        try {
            mListData.clear();
            mListData.addAll(listData);
            notifyDataSetChanged();
        } catch (Exception e) {
            CommonUtils.handleException(e);
        }
    }

    /**
     * Phương thức thêm dữ liệu cho danh sách dữ liệu đã có
     *
     * @param listData danh sách dữ liệu được thêm vào
     * @Created_by nblinh on 26/04/2019
     */
    public void addData(List<T> listData) {
        try {
            mListData.addAll(listData);
            notifyDataSetChanged();
        } catch (Exception e) {
            CommonUtils.handleException(e);
        }
    }

    /**
     * Phương thức xóa dữ liệu của danh sách
     * Created_by Nguyễn Bá Linh on 27/03/2019
     */
    public void clearData() {
        try {
            if (mListData != null) {
                mListData.clear();
                mListData = new ArrayList<>();
                notifyDataSetChanged();
            }
        } catch (Exception e) {
            CommonUtils.handleException(e);
        }
    }

    /**
     * Phương thức lấy item tại vị trí position
     *
     * @param position là vị trí muốn lấy
     * @return item của list
     * @Created_by nblinh on 26/04/2019
     */
    public T getItem(int position) {
        return mListData.get(position);
    }

}
