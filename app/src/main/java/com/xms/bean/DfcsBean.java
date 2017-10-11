package com.xms.bean;

import java.util.List;

/**
 * Created by dell on 2017/8/8.
 */

public class DfcsBean {


    /**
     * curDateTime : 1502442655604
     * info : 操作完成
     * recode : 10001000
     * result : {"chartData":{"day":{"sData":[{"name":"总成本","value":[0,0,20961.6,15404.4,0,0,14401.2,14335.2,18277.6,38733.2,28476.8]},{"name":"总收入","value":[0,0,23629.44,17364.96,0,0,16234.08,16159.68,20603.84,43662.88,32101.12]},{"name":"总利润","value":[0,0,2667.84,1960.56,0,0,1832.88,1824.48,2326.24,4929.68,3624.32]}],"xData":["01","02","03","04","05","06","07","08","09","10","11"],"yUnit":"日电费数据"},"hour":{"sData":[{"name":"总成本","value":[1623.6,1531.2,1610.4,1632.4,1680.8,1707.2,1399.2,1399.2,1812.8,1914,1953.6,1887.6,1918.4,1918.4,1782,1808.4,0,0,0,0,0,0,0,0]},{"name":"总收入","value":[1830.24,1726.08,1815.36,1840.16,1894.72,1924.48,1577.28,1577.28,2043.52,2157.6,2202.24,2127.84,2162.56,2162.56,2008.8,2038.56,0,0,0,0,0,0,0,0]},{"name":"总利润","value":[206.64,194.88,204.96,207.76,213.92,217.28,178.08,178.08,230.72,243.6,248.64,240.24,244.16,244.16,226.8,230.16,0,0,0,0,0,0,0,0]}],"xData":["01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16"],"yUnit":"时电费数据"},"month":{"sData":[{"name":"总成本","value":[158813.6,150590]},{"name":"总收入","value":[179026.24,169756]},{"name":"总利润","value":[20212.64,19166]}],"xData":["07","08"],"yUnit":"月电费数据"}},"day":11,"hour":0,"isCustomer":false,"month":8,"type1":"1","type2":"0","type3":"0","year":2017}
     * sessionId : 2C1ECB30608181ABCEF98CB632F19602
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
         * chartData : {"day":{"sData":[{"name":"总成本","value":[0,0,20961.6,15404.4,0,0,14401.2,14335.2,18277.6,38733.2,28476.8]},{"name":"总收入","value":[0,0,23629.44,17364.96,0,0,16234.08,16159.68,20603.84,43662.88,32101.12]},{"name":"总利润","value":[0,0,2667.84,1960.56,0,0,1832.88,1824.48,2326.24,4929.68,3624.32]}],"xData":["01","02","03","04","05","06","07","08","09","10","11"],"yUnit":"日电费数据"},"hour":{"sData":[{"name":"总成本","value":[1623.6,1531.2,1610.4,1632.4,1680.8,1707.2,1399.2,1399.2,1812.8,1914,1953.6,1887.6,1918.4,1918.4,1782,1808.4,0,0,0,0,0,0,0,0]},{"name":"总收入","value":[1830.24,1726.08,1815.36,1840.16,1894.72,1924.48,1577.28,1577.28,2043.52,2157.6,2202.24,2127.84,2162.56,2162.56,2008.8,2038.56,0,0,0,0,0,0,0,0]},{"name":"总利润","value":[206.64,194.88,204.96,207.76,213.92,217.28,178.08,178.08,230.72,243.6,248.64,240.24,244.16,244.16,226.8,230.16,0,0,0,0,0,0,0,0]}],"xData":["01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16"],"yUnit":"时电费数据"},"month":{"sData":[{"name":"总成本","value":[158813.6,150590]},{"name":"总收入","value":[179026.24,169756]},{"name":"总利润","value":[20212.64,19166]}],"xData":["07","08"],"yUnit":"月电费数据"}}
         * day : 11
         * hour : 0
         * isCustomer : false
         * month : 8
         * type1 : 1
         * type2 : 0
         * type3 : 0
         * year : 2017
         */

        private ChartDataBean chartData;
        private int day;
        private int hour;
        private boolean isCustomer;
        private int month;
        private String type1;
        private String type2;
        private String type3;
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

        public String getType1() {
            return type1;
        }

        public void setType1(String type1) {
            this.type1 = type1;
        }

        public String getType2() {
            return type2;
        }

        public void setType2(String type2) {
            this.type2 = type2;
        }

        public String getType3() {
            return type3;
        }

        public void setType3(String type3) {
            this.type3 = type3;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public static class ChartDataBean {
            /**
             * day : {"sData":[{"name":"总成本","value":[0,0,20961.6,15404.4,0,0,14401.2,14335.2,18277.6,38733.2,28476.8]},{"name":"总收入","value":[0,0,23629.44,17364.96,0,0,16234.08,16159.68,20603.84,43662.88,32101.12]},{"name":"总利润","value":[0,0,2667.84,1960.56,0,0,1832.88,1824.48,2326.24,4929.68,3624.32]}],"xData":["01","02","03","04","05","06","07","08","09","10","11"],"yUnit":"日电费数据"}
             * hour : {"sData":[{"name":"总成本","value":[1623.6,1531.2,1610.4,1632.4,1680.8,1707.2,1399.2,1399.2,1812.8,1914,1953.6,1887.6,1918.4,1918.4,1782,1808.4,0,0,0,0,0,0,0,0]},{"name":"总收入","value":[1830.24,1726.08,1815.36,1840.16,1894.72,1924.48,1577.28,1577.28,2043.52,2157.6,2202.24,2127.84,2162.56,2162.56,2008.8,2038.56,0,0,0,0,0,0,0,0]},{"name":"总利润","value":[206.64,194.88,204.96,207.76,213.92,217.28,178.08,178.08,230.72,243.6,248.64,240.24,244.16,244.16,226.8,230.16,0,0,0,0,0,0,0,0]}],"xData":["01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16"],"yUnit":"时电费数据"}
             * month : {"sData":[{"name":"总成本","value":[158813.6,150590]},{"name":"总收入","value":[179026.24,169756]},{"name":"总利润","value":[20212.64,19166]}],"xData":["07","08"],"yUnit":"月电费数据"}
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
                 * sData : [{"name":"总成本","value":[0,0,20961.6,15404.4,0,0,14401.2,14335.2,18277.6,38733.2,28476.8]},{"name":"总收入","value":[0,0,23629.44,17364.96,0,0,16234.08,16159.68,20603.84,43662.88,32101.12]},{"name":"总利润","value":[0,0,2667.84,1960.56,0,0,1832.88,1824.48,2326.24,4929.68,3624.32]}]
                 * xData : ["01","02","03","04","05","06","07","08","09","10","11"]
                 * yUnit : 日电费数据
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
                     * name : 总成本
                     * value : [0,0,20961.6,15404.4,0,0,14401.2,14335.2,18277.6,38733.2,28476.8]
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
                 * sData : [{"name":"总成本","value":[1623.6,1531.2,1610.4,1632.4,1680.8,1707.2,1399.2,1399.2,1812.8,1914,1953.6,1887.6,1918.4,1918.4,1782,1808.4,0,0,0,0,0,0,0,0]},{"name":"总收入","value":[1830.24,1726.08,1815.36,1840.16,1894.72,1924.48,1577.28,1577.28,2043.52,2157.6,2202.24,2127.84,2162.56,2162.56,2008.8,2038.56,0,0,0,0,0,0,0,0]},{"name":"总利润","value":[206.64,194.88,204.96,207.76,213.92,217.28,178.08,178.08,230.72,243.6,248.64,240.24,244.16,244.16,226.8,230.16,0,0,0,0,0,0,0,0]}]
                 * xData : ["01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16"]
                 * yUnit : 时电费数据
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
                     * name : 总成本
                     * value : [1623.6,1531.2,1610.4,1632.4,1680.8,1707.2,1399.2,1399.2,1812.8,1914,1953.6,1887.6,1918.4,1918.4,1782,1808.4,0,0,0,0,0,0,0,0]
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
                 * sData : [{"name":"总成本","value":[158813.6,150590]},{"name":"总收入","value":[179026.24,169756]},{"name":"总利润","value":[20212.64,19166]}]
                 * xData : ["07","08"]
                 * yUnit : 月电费数据
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
                     * name : 总成本
                     * value : [158813.6,150590]
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
