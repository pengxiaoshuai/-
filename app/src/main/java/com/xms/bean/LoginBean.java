package com.xms.bean;

import java.util.List;

/**
 * Created by dell on 2017/8/11.
 */

public class LoginBean  {


    /**
     * curDateTime : 1502675154856
     * info : 操作完成
     * recode : 10001000
     * result : [{"bannerList":[{"createDate":1502459830407,"icon":{"byteSize":29753,"createDate":1502459825457,"fileName":"BTH317F.jpg","id":"8274b420-aceb-4ede-b196-4eb38997820c","name":"banner3","oldFileName":"banner3.jpg","position":0,"status":10001000},"id":"3adf2056-74bf-4b8f-87b7-793e6a871941","position":1,"status":10001000,"subTitle":"1","title":"1","url":"1"},{"createDate":1502439599944,"icon":{"byteSize":67206,"createDate":1502439566958,"fileName":"BAPF1AX.jpg","id":"e980409b-38dc-4c4b-a5dc-a3fcfa4d3f87","name":"banner1","oldFileName":"banner1.jpg","position":0,"status":10001000},"id":"df7bea64-b014-4aad-9568-256b2d60d498","position":2,"status":10001000,"subTitle":"","title":"能源","url":""},{"createDate":1502439578700,"icon":{"byteSize":61639,"createDate":1502439573564,"fileName":"BAPFCXF.jpg","id":"35a5020e-9a99-43ba-905b-8b9e4b4ed909","name":"banner2","oldFileName":"banner2.jpg","position":0,"status":10001000},"id":"33498549-0543-4086-b3f4-31fb1c5a568d","position":1,"status":10001000,"subTitle":"","title":"智慧","url":""}],"companyName":"江西百电信息产业有限公司","headIcon":{"byteSize":66416,"createDate":1502440992917,"fileName":"BA89Q9H.jpg","id":"6b8ce370-1400-45fe-82d4-417f7c840c07","name":"BA89Q9H","oldFileName":"BA89Q9H.jpg","position":0,"status":10001000},"iegCustomerList":[{"id":"2597d9e6-3c74-47f1-aeac-94d6b57cb42b","name":"莫奈瓷砖"}],"status":10001000,"userId":"652730f1-8b62-4d5e-9247-e0872b68b31e"}]
     * sessionId : E89557A245CA71BF494220F9846A92CF
     * status : y
     * success : true
     */

    private long curDateTime;
    private String info;
    private int recode;
    private String sessionId;
    private String status;
    private boolean success;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * bannerList : [{"createDate":1502459830407,"icon":{"byteSize":29753,"createDate":1502459825457,"fileName":"BTH317F.jpg","id":"8274b420-aceb-4ede-b196-4eb38997820c","name":"banner3","oldFileName":"banner3.jpg","position":0,"status":10001000},"id":"3adf2056-74bf-4b8f-87b7-793e6a871941","position":1,"status":10001000,"subTitle":"1","title":"1","url":"1"},{"createDate":1502439599944,"icon":{"byteSize":67206,"createDate":1502439566958,"fileName":"BAPF1AX.jpg","id":"e980409b-38dc-4c4b-a5dc-a3fcfa4d3f87","name":"banner1","oldFileName":"banner1.jpg","position":0,"status":10001000},"id":"df7bea64-b014-4aad-9568-256b2d60d498","position":2,"status":10001000,"subTitle":"","title":"能源","url":""},{"createDate":1502439578700,"icon":{"byteSize":61639,"createDate":1502439573564,"fileName":"BAPFCXF.jpg","id":"35a5020e-9a99-43ba-905b-8b9e4b4ed909","name":"banner2","oldFileName":"banner2.jpg","position":0,"status":10001000},"id":"33498549-0543-4086-b3f4-31fb1c5a568d","position":1,"status":10001000,"subTitle":"","title":"智慧","url":""}]
         * companyName : 江西百电信息产业有限公司
         * headIcon : {"byteSize":66416,"createDate":1502440992917,"fileName":"BA89Q9H.jpg","id":"6b8ce370-1400-45fe-82d4-417f7c840c07","name":"BA89Q9H","oldFileName":"BA89Q9H.jpg","position":0,"status":10001000}
         * iegCustomerList : [{"id":"2597d9e6-3c74-47f1-aeac-94d6b57cb42b","name":"莫奈瓷砖"}]
         * status : 10001000
         * userId : 652730f1-8b62-4d5e-9247-e0872b68b31e
         */

        private String companyName;
        private HeadIconBean headIcon;
        private int status;
        private String userId;
        private List<BannerListBean> bannerList;
        private List<IegCustomerListBean> iegCustomerList;

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public HeadIconBean getHeadIcon() {
            return headIcon;
        }

        public void setHeadIcon(HeadIconBean headIcon) {
            this.headIcon = headIcon;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public List<BannerListBean> getBannerList() {
            return bannerList;
        }

        public void setBannerList(List<BannerListBean> bannerList) {
            this.bannerList = bannerList;
        }

        public List<IegCustomerListBean> getIegCustomerList() {
            return iegCustomerList;
        }

        public void setIegCustomerList(List<IegCustomerListBean> iegCustomerList) {
            this.iegCustomerList = iegCustomerList;
        }

        public static class HeadIconBean {
            /**
             * byteSize : 66416
             * createDate : 1502440992917
             * fileName : BA89Q9H.jpg
             * id : 6b8ce370-1400-45fe-82d4-417f7c840c07
             * name : BA89Q9H
             * oldFileName : BA89Q9H.jpg
             * position : 0
             * status : 10001000
             */

            private int byteSize;
            private long createDate;
            private String fileName;
            private String id;
            private String name;
            private String oldFileName;
            private int position;
            private int status;

            public int getByteSize() {
                return byteSize;
            }

            public void setByteSize(int byteSize) {
                this.byteSize = byteSize;
            }

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
            }

            public String getFileName() {
                return fileName;
            }

            public void setFileName(String fileName) {
                this.fileName = fileName;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getOldFileName() {
                return oldFileName;
            }

            public void setOldFileName(String oldFileName) {
                this.oldFileName = oldFileName;
            }

            public int getPosition() {
                return position;
            }

            public void setPosition(int position) {
                this.position = position;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }

        public static class BannerListBean {
            /**
             * createDate : 1502459830407
             * icon : {"byteSize":29753,"createDate":1502459825457,"fileName":"BTH317F.jpg","id":"8274b420-aceb-4ede-b196-4eb38997820c","name":"banner3","oldFileName":"banner3.jpg","position":0,"status":10001000}
             * id : 3adf2056-74bf-4b8f-87b7-793e6a871941
             * position : 1
             * status : 10001000
             * subTitle : 1
             * title : 1
             * url : 1
             */

            private long createDate;
            private IconBean icon;
            private String id;
            private int position;
            private int status;
            private String subTitle;
            private String title;
            private String url;

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
            }

            public IconBean getIcon() {
                return icon;
            }

            public void setIcon(IconBean icon) {
                this.icon = icon;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public int getPosition() {
                return position;
            }

            public void setPosition(int position) {
                this.position = position;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getSubTitle() {
                return subTitle;
            }

            public void setSubTitle(String subTitle) {
                this.subTitle = subTitle;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public static class IconBean {
                /**
                 * byteSize : 29753
                 * createDate : 1502459825457
                 * fileName : BTH317F.jpg
                 * id : 8274b420-aceb-4ede-b196-4eb38997820c
                 * name : banner3
                 * oldFileName : banner3.jpg
                 * position : 0
                 * status : 10001000
                 */

                private int byteSize;
                private long createDate;
                private String fileName;
                private String id;
                private String name;
                private String oldFileName;
                private int position;
                private int status;

                public int getByteSize() {
                    return byteSize;
                }

                public void setByteSize(int byteSize) {
                    this.byteSize = byteSize;
                }

                public long getCreateDate() {
                    return createDate;
                }

                public void setCreateDate(long createDate) {
                    this.createDate = createDate;
                }

                public String getFileName() {
                    return fileName;
                }

                public void setFileName(String fileName) {
                    this.fileName = fileName;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getOldFileName() {
                    return oldFileName;
                }

                public void setOldFileName(String oldFileName) {
                    this.oldFileName = oldFileName;
                }

                public int getPosition() {
                    return position;
                }

                public void setPosition(int position) {
                    this.position = position;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }
            }
        }

        public static class IegCustomerListBean {
            public IegCustomerListBean(String id, String name) {
                this.id = id;
                this.name = name;
            }

            /**
             * id : 2597d9e6-3c74-47f1-aeac-94d6b57cb42b
             * name : 莫奈瓷砖
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
