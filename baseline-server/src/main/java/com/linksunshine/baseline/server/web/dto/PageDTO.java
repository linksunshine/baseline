package com.linksunshine.baseline.server.web.dto;

/**
 * Created by ucmed on 2017/4/3.
 */
public class PageDTO {
    private int pageNo = 1;//当前页

    private int pageSize = 10;//每页记录数

    private int totalRecord;//总记录数

    private int totalPage;//总页数

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
