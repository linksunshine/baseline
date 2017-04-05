package com.linksunshine.baseline.server.web.dto;

/**
 * Created by ucmed on 2017/4/3.
 */
public class ReturnBodyDTO {
    private int status;
    private String msg;
    private Object data;
    private PageDTO page;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public PageDTO getPage() {
        return page;
    }

    public void setPage(PageDTO page) {
        this.page = page;
    }
}
