package com.xms.bean;

import java.util.List;

/**
 * Created by dell on 2017/8/8.
 */

public class SsdlBean {

    /**
     * curDateTime : 1502671778092
     * info : 操作完成
     * recode : 10001000
     * result : {"chartData":{"sData":[{"name":"当天","value":[2920,2760,3152,3048,3000,3016,3016,2816,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]},{"name":"上一天","value":[3200,3208,3400,3472,3424,3448,3104,2424,2848,3056,2792,2864,3008,2816,3160,3144,3160,3072,2752,2376,2680,2920,2880,3040]},{"name":"去年同期","value":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}],"xData":["01","02","03","04","05","06","07"],"yUnit":"24点电量数据"},"day":14,"dayValue":23728,"imJaCd":[{"id":"170729141970222222222000","meterData":{"sData":[{"name":"小时电量数据数据","value":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}],"xData":["01","02","03","04","05","06","07"],"yUnit":"小时电量数据数据"},"name":"莫奈一号变（停运）"},{"id":"170719201298000000000108","meterData":{"sData":[{"name":"小时电量数据数据","value":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}],"xData":["01","02","03","04","05","06","07"],"yUnit":"小时电量数据数据"},"name":"莫奈二号变"},{"id":"170729172072000000000107","meterData":{"sData":[{"name":"小时电量数据数据","value":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}],"xData":["01","02","03","04","05","06","07"],"yUnit":"小时电量数据数据"},"name":"莫奈三号变"}],"isCustomer":true,"month":8,"monthValue":459096,"year":2017}
     * sessionId : 0401861BC776106BDEDBAA3E2CDB3C2C
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
         * chartData : {"sData":[{"name":"当天","value":[2920,2760,3152,3048,3000,3016,3016,2816,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]},{"name":"上一天","value":[3200,3208,3400,3472,3424,3448,3104,2424,2848,3056,2792,2864,3008,2816,3160,3144,3160,3072,2752,2376,2680,2920,2880,3040]},{"name":"去年同期","value":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}],"xData":["01","02","03","04","05","06","07"],"yUnit":"24点电量数据"}
         * day : 14
         * dayValue : 23728
         * imJaCd : [{"id":"170729141970222222222000","meterData":{"sData":[{"name":"小时电量数据数据","value":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}],"xData":["01","02","03","04","05","06","07"],"yUnit":"小时电量数据数据"},"name":"莫奈一号变（停运）"},{"id":"170719201298000000000108","meterData":{"sData":[{"name":"小时电量数据数据","value":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}],"xData":["01","02","03","04","05","06","07"],"yUnit":"小时电量数据数据"},"name":"莫奈二号变"},{"id":"170729172072000000000107","meterData":{"sData":[{"name":"小时电量数据数据","value":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}],"xData":["01","02","03","04","05","06","07"],"yUnit":"小时电量数据数据"},"name":"莫奈三号变"}]
         * isCustomer : true
         * month : 8
         * monthValue : 459096
         * year : 2017
         */

        private ChartDataBean chartData;
        private int day;
        private int dayValue;
        private boolean isCustomer;
        private int month;
        private int monthValue;
        private int year;
        private List<ImJaCdBean> imJaCd;

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

        public int getDayValue() {
            return dayValue;
        }

        public void setDayValue(int dayValue) {
            this.dayValue = dayValue;
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

        public int getMonthValue() {
            return monthValue;
        }

        public void setMonthValue(int monthValue) {
            this.monthValue = monthValue;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public List<ImJaCdBean> getImJaCd() {
            return imJaCd;
        }

        public void setImJaCd(List<ImJaCdBean> imJaCd) {
            this.imJaCd = imJaCd;
        }

        public static class ChartDataBean {
            /**
             * sData : [{"name":"当天","value":[2920,2760,3152,3048,3000,3016,3016,2816,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]},{"name":"上一天","value":[3200,3208,3400,3472,3424,3448,3104,2424,2848,3056,2792,2864,3008,2816,3160,3144,3160,3072,2752,2376,2680,2920,2880,3040]},{"name":"去年同期","value":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}]
             * xData : ["01","02","03","04","05","06","07"]
             * yUnit : 24点电量数据
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
                 * name : 当天
                 * value : [2920,2760,3152,3048,3000,3016,3016,2816,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
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

        public static class ImJaCdBean {
            /**
             * id : 170729141970222222222000
             * meterData : {"sData":[{"name":"小时电量数据数据","value":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}],"xData":["01","02","03","04","05","06","07"],"yUnit":"小时电量数据数据"}
             * name : 莫奈一号变（停运）
             */

            private String id;
            private MeterDataBean meterData;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public MeterDataBean getMeterData() {
                return meterData;
            }

            public void setMeterData(MeterDataBean meterData) {
                this.meterData = meterData;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public static class MeterDataBean {
                /**
                 * sData : [{"name":"小时电量数据数据","value":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}]
                 * xData : ["01","02","03","04","05","06","07"]
                 * yUnit : 小时电量数据数据
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
                     * name : 小时电量数据数据
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
        }
    }
}
