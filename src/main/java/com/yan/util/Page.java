package com.yan.util;

import java.util.List;

/**
 * Package ：com.yan.util
 * Description：
 * date： 2019/1/15 下午9:51
 * author： Li KaiYan
 */

public class Page<T> {
    private final int DEFAULT_SIZE = 20;
    private int currentPage;
    private int startPage;
    private int endPage;
    private int size;
    private List<T> data;

    public Page(){
        this.size = DEFAULT_SIZE;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
