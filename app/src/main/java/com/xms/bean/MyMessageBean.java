package com.xms.bean;

/**
 * Created by dell on 2017/8/9.
 */

public class MyMessageBean {

    /**
     * curDateTime : 1502273165091
     * info : 操作完成
     * recode : 10001000
     * result : {"companyName":"江西百电信息产业有限公司","dataValue":152104,"price":83657.2,"userName":"邱涛"}
     * sessionId : C2EA237A451074811F8D808BC0317B41
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
         * companyName : 江西百电信息产业有限公司
         * dataValue : 152104
         * price : 83657.2
         * userName : 邱涛
         */

        private String companyName;
        private int dataValue;
        private double price;
        private String userName;

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public int getDataValue() {
            return dataValue;
        }

        public void setDataValue(int dataValue) {
            this.dataValue = dataValue;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
