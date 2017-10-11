package com.xms.bean;

import java.util.List;

/**
 * Created by dell on 2017/8/9.
 */

public class NhtjBean {


    /**
     * curDateTime : 1502331293789
     * info : 操作完成
     * recode : 10001000
     * result : {"chartData":{"day":{"sData":[0,0,38112,36744,0,0,26184,26064,30176,18952,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],"xData":["01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"],"xUnit":"日"},"hour":{"sData":[2952,2936,2880,1448,1320,1248,1312,1392,1640,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],"xData":["01","02","03","04","05","06","07","08","09","10"],"xUnit":"点"},"month":{"sData":[0,0,0,0,0,0,288752,176232,0,0,0,0],"xData":["01","02","03","04","05","06","07","08","09","10","11","12"],"xUnit":"月"}},"day":10,"month":8,"year":2017}
     * sessionId : FED22D418D9082BBF47250A3A6CA5262
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
         * chartData : {"day":{"sData":[0,0,38112,36744,0,0,26184,26064,30176,18952,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],"xData":["01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"],"xUnit":"日"},"hour":{"sData":[2952,2936,2880,1448,1320,1248,1312,1392,1640,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],"xData":["01","02","03","04","05","06","07","08","09","10"],"xUnit":"点"},"month":{"sData":[0,0,0,0,0,0,288752,176232,0,0,0,0],"xData":["01","02","03","04","05","06","07","08","09","10","11","12"],"xUnit":"月"}}
         * day : 10
         * month : 8
         * year : 2017
         */

        private ChartDataBean chartData;
        private int day;
        private int month;
        private int year;

        public ChartDataBean getChartData() {
            return chartData;
        }

        public void setChartData(ChartDataBean chartData) {
            this.chartData = chartData;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
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
             * day : {"sData":[0,0,38112,36744,0,0,26184,26064,30176,18952,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],"xData":["01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"],"xUnit":"日"}
             * hour : {"sData":[2952,2936,2880,1448,1320,1248,1312,1392,1640,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],"xData":["01","02","03","04","05","06","07","08","09","10"],"xUnit":"点"}
             * month : {"sData":[0,0,0,0,0,0,288752,176232,0,0,0,0],"xData":["01","02","03","04","05","06","07","08","09","10","11","12"],"xUnit":"月"}
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
                 * sData : [0,0,38112,36744,0,0,26184,26064,30176,18952,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
                 * xData : ["01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"]
                 * xUnit : 日
                 */

                private String xUnit;
                private List<Integer> sData;
                private List<String> xData;

                public String getXUnit() {
                    return xUnit;
                }

                public void setXUnit(String xUnit) {
                    this.xUnit = xUnit;
                }

                public List<Integer> getSData() {
                    return sData;
                }

                public void setSData(List<Integer> sData) {
                    this.sData = sData;
                }

                public List<String> getXData() {
                    return xData;
                }

                public void setXData(List<String> xData) {
                    this.xData = xData;
                }
            }

            public static class HourBean {
                /**
                 * sData : [2952,2936,2880,1448,1320,1248,1312,1392,1640,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
                 * xData : ["01","02","03","04","05","06","07","08","09","10"]
                 * xUnit : 点
                 */

                private String xUnit;
                private List<Integer> sData;
                private List<String> xData;

                public String getXUnit() {
                    return xUnit;
                }

                public void setXUnit(String xUnit) {
                    this.xUnit = xUnit;
                }

                public List<Integer> getSData() {
                    return sData;
                }

                public void setSData(List<Integer> sData) {
                    this.sData = sData;
                }

                public List<String> getXData() {
                    return xData;
                }

                public void setXData(List<String> xData) {
                    this.xData = xData;
                }
            }

            public static class MonthBean {
                /**
                 * sData : [0,0,0,0,0,0,288752,176232,0,0,0,0]
                 * xData : ["01","02","03","04","05","06","07","08","09","10","11","12"]
                 * xUnit : 月
                 */

                private String xUnit;
                private List<Integer> sData;
                private List<String> xData;

                public String getXUnit() {
                    return xUnit;
                }

                public void setXUnit(String xUnit) {
                    this.xUnit = xUnit;
                }

                public List<Integer> getSData() {
                    return sData;
                }

                public void setSData(List<Integer> sData) {
                    this.sData = sData;
                }

                public List<String> getXData() {
                    return xData;
                }

                public void setXData(List<String> xData) {
                    this.xData = xData;
                }
            }
        }
    }
}
