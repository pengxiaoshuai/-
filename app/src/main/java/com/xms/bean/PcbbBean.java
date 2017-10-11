package com.xms.bean;

import java.util.List;

/**
 * Created by dell on 2017/8/8.
 */

public class PcbbBean {


    /**
     * curDateTime : 1502450924978
     * info : 操作完成
     * recode : 10001000
     * result : {"chartData":{"day":{"sData":[{"name":"日偏差数据","value":[-1,-1,0.094,-0.1961,-1,-1,-0.2484,-0.2519,-0.0461,1.0214,0.6758]}],"xData":["01","02","03","04","05","06","07","08","09","10","11"],"yUnit":"日偏差数据"},"hour":{"sData":[{"name":"时偏差数据","value":[1.0336,0.9179,1.0171,1.0446,1.1052,1.1383,0.7525,0.7525,1.2706,1.3973,1.4469,1.3643,1.4028,1.4028,1.232,1.2651,1.1163,1.006,0.5541,0,0,0,0,0]}],"xData":["01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18"],"yUnit":"时偏差数据"},"month":{"sData":[{"name":"月偏差数据","value":[-0.7326,-0.2683]}],"xData":["07","08"],"yUnit":"月偏差数据"}},"contractDateValue":216000,"dataValue":58384,"day":11,"differRatio":0.6758,"hour":0,"isCustomer":false,"month":8,"year":2017}
     * sessionId : 27788F283724BDFE44520AF5CA6F4D46
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
         * chartData : {"day":{"sData":[{"name":"日偏差数据","value":[-1,-1,0.094,-0.1961,-1,-1,-0.2484,-0.2519,-0.0461,1.0214,0.6758]}],"xData":["01","02","03","04","05","06","07","08","09","10","11"],"yUnit":"日偏差数据"},"hour":{"sData":[{"name":"时偏差数据","value":[1.0336,0.9179,1.0171,1.0446,1.1052,1.1383,0.7525,0.7525,1.2706,1.3973,1.4469,1.3643,1.4028,1.4028,1.232,1.2651,1.1163,1.006,0.5541,0,0,0,0,0]}],"xData":["01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18"],"yUnit":"时偏差数据"},"month":{"sData":[{"name":"月偏差数据","value":[-0.7326,-0.2683]}],"xData":["07","08"],"yUnit":"月偏差数据"}}
         * contractDateValue : 216000
         * dataValue : 58384
         * day : 11
         * differRatio : 0.6758
         * hour : 0
         * isCustomer : false
         * month : 8
         * year : 2017
         */

        private ChartDataBean chartData;
        private double contractDateValue;
        private double dataValue;
        private int day;
        private double differRatio;
        private int hour;
        private boolean isCustomer;
        private int month;
        private int year;

        public ChartDataBean getChartData() {
            return chartData;
        }

        public void setChartData(ChartDataBean chartData) {
            this.chartData = chartData;
        }

        public double getContractDateValue() {
            return contractDateValue;
        }

        public void setContractDateValue(double contractDateValue) {
            this.contractDateValue = contractDateValue;
        }

        public double getDataValue(){
            return dataValue;
        }

        public void setDataValue(double dataValue){
            this.dataValue = dataValue;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public double getDifferRatio() {
            return differRatio;
        }

        public void setDifferRatio(double differRatio) {
            this.differRatio = differRatio;
        }

        public int getHour() {
            return hour;
        }

        public void setHour(int hour) {
            this.hour = hour;
        }

        public boolean isIsCustomer() {
            return isCustomer;
        }

        public void setIsCustomer(boolean isCustomer) {
            this.isCustomer = isCustomer;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public static class ChartDataBean {
            /**
             * day : {"sData":[{"name":"日偏差数据","value":[-1,-1,0.094,-0.1961,-1,-1,-0.2484,-0.2519,-0.0461,1.0214,0.6758]}],"xData":["01","02","03","04","05","06","07","08","09","10","11"],"yUnit":"日偏差数据"}
             * hour : {"sData":[{"name":"时偏差数据","value":[1.0336,0.9179,1.0171,1.0446,1.1052,1.1383,0.7525,0.7525,1.2706,1.3973,1.4469,1.3643,1.4028,1.4028,1.232,1.2651,1.1163,1.006,0.5541,0,0,0,0,0]}],"xData":["01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18"],"yUnit":"时偏差数据"}
             * month : {"sData":[{"name":"月偏差数据","value":[-0.7326,-0.2683]}],"xData":["07","08"],"yUnit":"月偏差数据"}
             */

            private DayBean day;
            private HourBean hour;
            private MonthBean month;

            public DayBean getDay() {
                return day;
            }

            public void setDay(DayBean day) {
                this.day = day;
            }

            public HourBean getHour() {
                return hour;
            }

            public void setHour(HourBean hour) {
                this.hour = hour;
            }

            public MonthBean getMonth() {
                return month;
            }

            public void setMonth(MonthBean month) {
                this.month = month;
            }

            public static class DayBean {
                /**
                 * sData : [{"name":"日偏差数据","value":[-1,-1,0.094,-0.1961,-1,-1,-0.2484,-0.2519,-0.0461,1.0214,0.6758]}]
                 * xData : ["01","02","03","04","05","06","07","08","09","10","11"]
                 * yUnit : 日偏差数据
                 */

                private String yUnit;
                private List<SDataBean> sData;
                private List<String> xData;

                public String getYUnit() {
                    return yUnit;
                }

                public void setYUnit(String yUnit) {
                    this.yUnit = yUnit;
                }

                public List<SDataBean> getSData() {
                    return sData;
                }

                public void setSData(List<SDataBean> sData) {
                    this.sData = sData;
                }

                public List<String> getXData() {
                    return xData;
                }

                public void setXData(List<String> xData) {
                    this.xData = xData;
                }

                public static class SDataBean {
                    /**
                     * name : 日偏差数据
                     * value : [-1,-1,0.094,-0.1961,-1,-1,-0.2484,-0.2519,-0.0461,1.0214,0.6758]
                     */

                    private String name;
                    private List<Double> value;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public List<Double> getValue() {
                        return value;
                    }

                    public void setValue(List<Double> value) {
                        this.value = value;
                    }
                }
            }

            public static class HourBean {
                /**
                 * sData : [{"name":"时偏差数据","value":[1.0336,0.9179,1.0171,1.0446,1.1052,1.1383,0.7525,0.7525,1.2706,1.3973,1.4469,1.3643,1.4028,1.4028,1.232,1.2651,1.1163,1.006,0.5541,0,0,0,0,0]}]
                 * xData : ["01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18"]
                 * yUnit : 时偏差数据
                 */

                private String yUnit;
                private List<SDataBeanX> sData;
                private List<String> xData;

                public String getYUnit() {
                    return yUnit;
                }

                public void setYUnit(String yUnit) {
                    this.yUnit = yUnit;
                }

                public List<SDataBeanX> getSData() {
                    return sData;
                }

                public void setSData(List<SDataBeanX> sData) {
                    this.sData = sData;
                }

                public List<String> getXData() {
                    return xData;
                }

                public void setXData(List<String> xData) {
                    this.xData = xData;
                }

                public static class SDataBeanX {
                    /**
                     * name : 时偏差数据
                     * value : [1.0336,0.9179,1.0171,1.0446,1.1052,1.1383,0.7525,0.7525,1.2706,1.3973,1.4469,1.3643,1.4028,1.4028,1.232,1.2651,1.1163,1.006,0.5541,0,0,0,0,0]
                     */

                    private String name;
                    private List<Double> value;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public List<Double> getValue() {
                        return value;
                    }

                    public void setValue(List<Double> value) {
                        this.value = value;
                    }
                }
            }

            public static class MonthBean {
                /**
                 * sData : [{"name":"月偏差数据","value":[-0.7326,-0.2683]}]
                 * xData : ["07","08"]
                 * yUnit : 月偏差数据
                 */

                private String yUnit;
                private List<SDataBeanXX> sData;
                private List<String> xData;

                public String getYUnit() {
                    return yUnit;
                }

                public void setYUnit(String yUnit) {
                    this.yUnit = yUnit;
                }

                public List<SDataBeanXX> getSData() {
                    return sData;
                }

                public void setSData(List<SDataBeanXX> sData) {
                    this.sData = sData;
                }

                public List<String> getXData() {
                    return xData;
                }

                public void setXData(List<String> xData) {
                    this.xData = xData;
                }

                public static class SDataBeanXX {
                    /**
                     * name : 月偏差数据
                     * value : [-0.7326,-0.2683]
                     */

                    private String name;
                    private List<Double> value;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public List<Double> getValue() {
                        return value;
                    }

                    public void setValue(List<Double> value) {
                        this.value = value;
                    }
                }
            }
        }
    }
}
