package com.xms.bean;

/**
 * Created by dell on 2017/8/11.
 */

public class ChangeMessageBean {

    /**
     * curDateTime : 1502457264655
     * info : 操作完成
     * recode : 10001000
     * result : {"companyName":"江西百电信息产业有限公司","headIcon":{"byteSize":66416,"createDate":1502440992917,"fileName":"BA89Q9H.jpg","id":"6b8ce370-1400-45fe-82d4-417f7c840c07","name":"BA89Q9H","oldFileName":"BA89Q9H.jpg","position":0,"status":10001000},"userName":"彭其煊"}
     * sessionId : 6DAB8D6FCBCABD3C8AC233B6F980E143
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
         * headIcon : {"byteSize":66416,"createDate":1502440992917,"fileName":"BA89Q9H.jpg","id":"6b8ce370-1400-45fe-82d4-417f7c840c07","name":"BA89Q9H","oldFileName":"BA89Q9H.jpg","position":0,"status":10001000}
         * userName : 彭其煊
         */

        private String companyName;
        private HeadIconBean headIcon;
        private String userName;

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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
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
    }
}
