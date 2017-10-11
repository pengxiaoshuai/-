package com.xms.bean;

import java.util.List;

/**
 * Created by dell on 2017/8/9.
 */

public class PcgjBean {

    /**
     * curDateTime : 1502265743393
     * info : 操作完成
     * recode : 10001000
     * result : {"iegCustomerList":[{"address":"江西省南昌市东湖区雄州路265号","createDate":1501816508629,"detail":"","differRatio":-0.5286,"id":"2597d9e6-3c74-47f1-aeac-94d6b57cb42b","iegCompanyList":[null],"lat":"28.691262","linkman":"李","linkphone":"150111111111","listPrice":20,"lng":"115.862674","minusDeviation":-0.13,"name":"莫奈瓷砖","position":0,"positiveDeviation":0.03,"shortNote":1,"status":10001000,"weChatNote":1}]}
     * sessionId : 300BE4288C5DB24AFC022BE23A3AA4BB
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
        private List<IegCustomerListBean> iegCustomerList;

        public List<IegCustomerListBean> getIegCustomerList() {
            return iegCustomerList;
        }

        public void setIegCustomerList(List<IegCustomerListBean> iegCustomerList) {
            this.iegCustomerList = iegCustomerList;
        }

        public static class IegCustomerListBean {
            /**
             * address : 江西省南昌市东湖区雄州路265号
             * createDate : 1501816508629
             * detail :
             * differRatio : -0.5286
             * id : 2597d9e6-3c74-47f1-aeac-94d6b57cb42b
             * iegCompanyList : [null]
             * lat : 28.691262
             * linkman : 李
             * linkphone : 150111111111
             * listPrice : 20
             * lng : 115.862674
             * minusDeviation : -0.13
             * name : 莫奈瓷砖
             * position : 0
             * positiveDeviation : 0.03
             * shortNote : 1
             * status : 10001000
             * weChatNote : 1
             */

            private String address;
            private long createDate;
            private String detail;
            private double differRatio;
            private String id;
            private String lat;
            private String linkman;
            private String linkphone;
            private int listPrice;
            private String lng;
            private double minusDeviation;
            private String name;
            private int position;
            private double positiveDeviation;
            private int shortNote;
            private int status;
            private int weChatNote;
            private List<Integer> iegCompanyList;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public double getDifferRatio() {
                return differRatio;
            }

            public void setDifferRatio(double differRatio) {
                this.differRatio = differRatio;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLinkman() {
                return linkman;
            }

            public void setLinkman(String linkman) {
                this.linkman = linkman;
            }

            public String getLinkphone() {
                return linkphone;
            }

            public void setLinkphone(String linkphone) {
                this.linkphone = linkphone;
            }

            public int getListPrice() {
                return listPrice;
            }

            public void setListPrice(int listPrice) {
                this.listPrice = listPrice;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public double getMinusDeviation() {
                return minusDeviation;
            }

            public void setMinusDeviation(double minusDeviation) {
                this.minusDeviation = minusDeviation;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getPosition() {
                return position;
            }

            public void setPosition(int position) {
                this.position = position;
            }

            public double getPositiveDeviation() {
                return positiveDeviation;
            }

            public void setPositiveDeviation(double positiveDeviation) {
                this.positiveDeviation = positiveDeviation;
            }

            public int getShortNote() {
                return shortNote;
            }

            public void setShortNote(int shortNote) {
                this.shortNote = shortNote;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getWeChatNote() {
                return weChatNote;
            }

            public void setWeChatNote(int weChatNote) {
                this.weChatNote = weChatNote;
            }

            public List<Integer> getIegCompanyList() {
                return iegCompanyList;
            }

            public void setIegCompanyList(List<Integer> iegCompanyList) {
                this.iegCompanyList = iegCompanyList;
            }
        }
    }
}
