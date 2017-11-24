package com.emcloud.loc.domain;

import java.util.List;

public class Emm {
        private  String status;

    public Emm() {
    }

    private  String msg;
        private List<AlArea> result;

    public Emm(String status, String msg, List<AlArea> result) {
        this.status = status;
        this.msg = msg;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public List<AlArea> getResult() {
        return result;
    }
    public void setResult(List<AlArea> result) {
        this.result = result;
    }
}
