package com.xms.bean;

/**
 * Created by dell on 2017/8/10.
 */

public class XtszBean {

    /**
     * curDateTime : 1502346305872
     * info : 操作完成
     * recode : 10001000
     * result : {"pushSet":0,"warnSet":0}
     * sessionId : C15FEF937C93635C1393D254F626B02E
     * status : y
     * success : true
     */

    private long curDateTime;
    private String info;
    private int recode;
    private ResultBean result;
    private String sessionId;
    private String status;
    private boolean success;

    public long getCurDateTime() {
        return curDateTime;
    }

    public void setCurDateTime(long curDateTime) {
        this.curDateTime = curDateTime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getRecode() {
        return recode;
    }

    public void setRecode(int recode) {
        this.recode = recode;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class ResultBean {
        /**
         * pushSet : 0
         * warnSet : 0
         */

        private int pushSet;
        private int warnSet;

        public int getPushSet() {
            return pushSet;
        }

        public void setPushSet(int pushSet) {
            this.pushSet = pushSet;
        }

        public int getWarnSet() {
            return warnSet;
        }

        public void setWarnSet(int warnSet) {
            this.warnSet = warnSet;
        }
    }
}
