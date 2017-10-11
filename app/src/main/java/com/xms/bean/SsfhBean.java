package com.xms.bean;

import java.util.List;

/**
 * Created by dell on 2017/7/24.
 */

public class SsfhBean {

    /**
     * id : chartEle
     * yUnit : 电流(A)
     * xData : ["00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"]
     * sData : [{"name":"A相电流","value":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]},{"name":"B相电流","value":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]},{"name":"C相电流","value":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}]
     */

    private String id;
    private String yUnit;
    private List<String> xData;
    private List<SDataBean> sData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYUnit() {
        return yUnit;
    }

    public void setYUnit(String yUnit) {
        this.yUnit = yUnit;
    }

    public List<String> getXData() {
        return xData;
    }

    public void setXData(List<String> xData) {
        this.xData = xData;
    }

    public List<SDataBean> getSData() {
        return sData;
    }

    public void setSData(List<SDataBean> sData){
        this.sData = sData;
    }

    public static class SDataBean {
        /**
         * name : A相电流
         * value : [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
         */

        private String name;
        private List<Integer> value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Integer> getValue() {
            return value;
        }

        public void setValue(List<Integer> value) {
            this.value = value;
        }
    }
}
