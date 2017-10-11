package com.xms.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dell on 2017/8/9.
 */

public class BxjlBean implements Serializable {


    /**
     * curDateTime : 1502335548411
     * info : 操作完成
     * recode : 10001000
     * result : {"repairList":[{"contact":"15779160456","createDate":1502330416574,"detail":"恩恩","id":"506be0a0-d2e3-422d-96b1-5bcbd0779c3d","iegUser":{"company":{"address":"江西省南昌市东湖区雅苑东路","area":"江西 南昌","createDate":1501662262926,"detail":"<p>百电<\/p>","id":"0ccf0b38-7447-42be-988a-09235320b7d0","iegCustomerList":[{"address":"江西省南昌市东湖区雄州路265号","createDate":1501816508629,"detail":"","id":"2597d9e6-3c74-47f1-aeac-94d6b57cb42b","iegCompanyList":[null],"lat":"28.691262","linkman":"李","linkphone":"150111111111","listPrice":20,"lng":"115.862674","minusDeviation":-0.23,"name":"莫奈瓷砖","position":0,"positiveDeviation":0.03,"shortNote":1,"status":10001000,"weChatNote":0}],"lat":"28.696775","lng":"115.863752","name":"江西百电信息产业有限公司","position":0,"status":10001000},"createDate":1502069519006,"headIcon":{"byteSize":36355,"createDate":1502069519006,"fileName":"U6QM5KU.jpg","id":"f52d4ac5-8ce9-4d76-94d4-26aafabdd6a0","name":"U6QM5KU","oldFileName":"U6QM5KU.jpg","position":0,"status":10001000},"id":"652730f1-8b62-4d5e-9247-e0872b68b31e","mobilephone":"15779160456","name":"彭其煊","password":"1234567","position":0,"pushSet":0,"status":10001000,"warnSet":0,"wechatUser":{"city":"","country":"中国","groupid":"0","headimgurl":"http://wx.qlogo.cn/mmopen/8L6JXBHx6iaNPMG6pbOib01XvLuLiaQqC8IwY8clWyZwngFNLRFpEIpxatYHGxGN7ahibBZYzZceI1nD9TwmqaRCFUkWqQ7JyooR/0","language":"zh_CN","nickname":"世界这么乱，装纯给谁看","province":"江西","remark":"","sex":1,"subscribe":1,"subscribeTime":1500880804,"tagidList":"","wechatUserPK":{"appId":"wx75d005778ae46441","openid":"otEmQxESWdUYRkAdr1lziCAVvbfw"}}},"position":0,"replyList":[],"status":10001000,"title":"no"}],"userId":"652730f1-8b62-4d5e-9247-e0872b68b31e"}
     * sessionId : 0B516133352F8B2E8C0F5AEE12FAD91A
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

    public static class ResultBean implements Serializable{
        /**
         * repairList : [{"contact":"15779160456","createDate":1502330416574,"detail":"恩恩","id":"506be0a0-d2e3-422d-96b1-5bcbd0779c3d","iegUser":{"company":{"address":"江西省南昌市东湖区雅苑东路","area":"江西 南昌","createDate":1501662262926,"detail":"<p>百电<\/p>","id":"0ccf0b38-7447-42be-988a-09235320b7d0","iegCustomerList":[{"address":"江西省南昌市东湖区雄州路265号","createDate":1501816508629,"detail":"","id":"2597d9e6-3c74-47f1-aeac-94d6b57cb42b","iegCompanyList":[null],"lat":"28.691262","linkman":"李","linkphone":"150111111111","listPrice":20,"lng":"115.862674","minusDeviation":-0.23,"name":"莫奈瓷砖","position":0,"positiveDeviation":0.03,"shortNote":1,"status":10001000,"weChatNote":0}],"lat":"28.696775","lng":"115.863752","name":"江西百电信息产业有限公司","position":0,"status":10001000},"createDate":1502069519006,"headIcon":{"byteSize":36355,"createDate":1502069519006,"fileName":"U6QM5KU.jpg","id":"f52d4ac5-8ce9-4d76-94d4-26aafabdd6a0","name":"U6QM5KU","oldFileName":"U6QM5KU.jpg","position":0,"status":10001000},"id":"652730f1-8b62-4d5e-9247-e0872b68b31e","mobilephone":"15779160456","name":"彭其煊","password":"1234567","position":0,"pushSet":0,"status":10001000,"warnSet":0,"wechatUser":{"city":"","country":"中国","groupid":"0","headimgurl":"http://wx.qlogo.cn/mmopen/8L6JXBHx6iaNPMG6pbOib01XvLuLiaQqC8IwY8clWyZwngFNLRFpEIpxatYHGxGN7ahibBZYzZceI1nD9TwmqaRCFUkWqQ7JyooR/0","language":"zh_CN","nickname":"世界这么乱，装纯给谁看","province":"江西","remark":"","sex":1,"subscribe":1,"subscribeTime":1500880804,"tagidList":"","wechatUserPK":{"appId":"wx75d005778ae46441","openid":"otEmQxESWdUYRkAdr1lziCAVvbfw"}}},"position":0,"replyList":[],"status":10001000,"title":"no"}]
         * userId : 652730f1-8b62-4d5e-9247-e0872b68b31e
         */

        private String userId;
        private List<RepairListBean> repairList;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public List<RepairListBean> getRepairList() {
            return repairList;
        }

        public void setRepairList(List<RepairListBean> repairList) {
            this.repairList = repairList;
        }

        public static class RepairListBean implements Serializable{
            /**
             * contact : 15779160456
             * createDate : 1502330416574
             * detail : 恩恩
             * id : 506be0a0-d2e3-422d-96b1-5bcbd0779c3d
             * iegUser : {"company":{"address":"江西省南昌市东湖区雅苑东路","area":"江西 南昌","createDate":1501662262926,"detail":"<p>百电<\/p>","id":"0ccf0b38-7447-42be-988a-09235320b7d0","iegCustomerList":[{"address":"江西省南昌市东湖区雄州路265号","createDate":1501816508629,"detail":"","id":"2597d9e6-3c74-47f1-aeac-94d6b57cb42b","iegCompanyList":[null],"lat":"28.691262","linkman":"李","linkphone":"150111111111","listPrice":20,"lng":"115.862674","minusDeviation":-0.23,"name":"莫奈瓷砖","position":0,"positiveDeviation":0.03,"shortNote":1,"status":10001000,"weChatNote":0}],"lat":"28.696775","lng":"115.863752","name":"江西百电信息产业有限公司","position":0,"status":10001000},"createDate":1502069519006,"headIcon":{"byteSize":36355,"createDate":1502069519006,"fileName":"U6QM5KU.jpg","id":"f52d4ac5-8ce9-4d76-94d4-26aafabdd6a0","name":"U6QM5KU","oldFileName":"U6QM5KU.jpg","position":0,"status":10001000},"id":"652730f1-8b62-4d5e-9247-e0872b68b31e","mobilephone":"15779160456","name":"彭其煊","password":"1234567","position":0,"pushSet":0,"status":10001000,"warnSet":0,"wechatUser":{"city":"","country":"中国","groupid":"0","headimgurl":"http://wx.qlogo.cn/mmopen/8L6JXBHx6iaNPMG6pbOib01XvLuLiaQqC8IwY8clWyZwngFNLRFpEIpxatYHGxGN7ahibBZYzZceI1nD9TwmqaRCFUkWqQ7JyooR/0","language":"zh_CN","nickname":"世界这么乱，装纯给谁看","province":"江西","remark":"","sex":1,"subscribe":1,"subscribeTime":1500880804,"tagidList":"","wechatUserPK":{"appId":"wx75d005778ae46441","openid":"otEmQxESWdUYRkAdr1lziCAVvbfw"}}}
             * position : 0
             * replyList : []
             * status : 10001000
             * title : no
             */

            private String contact;
            private long createDate;
            private String detail;
            private String id;
            private IegUserBean iegUser;
            private int position;
            private int status;
            private String title;
            private List<?> replyList;

            public String getContact() {
                return contact;
            }

            public void setContact(String contact) {
                this.contact = contact;
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

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public IegUserBean getIegUser() {
                return iegUser;
            }

            public void setIegUser(IegUserBean iegUser) {
                this.iegUser = iegUser;
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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public List<?> getReplyList() {
                return replyList;
            }

            public void setReplyList(List<?> replyList) {
                this.replyList = replyList;
            }

            public static class IegUserBean implements Serializable{
                /**
                 * company : {"address":"江西省南昌市东湖区雅苑东路","area":"江西 南昌","createDate":1501662262926,"detail":"<p>百电<\/p>","id":"0ccf0b38-7447-42be-988a-09235320b7d0","iegCustomerList":[{"address":"江西省南昌市东湖区雄州路265号","createDate":1501816508629,"detail":"","id":"2597d9e6-3c74-47f1-aeac-94d6b57cb42b","iegCompanyList":[null],"lat":"28.691262","linkman":"李","linkphone":"150111111111","listPrice":20,"lng":"115.862674","minusDeviation":-0.23,"name":"莫奈瓷砖","position":0,"positiveDeviation":0.03,"shortNote":1,"status":10001000,"weChatNote":0}],"lat":"28.696775","lng":"115.863752","name":"江西百电信息产业有限公司","position":0,"status":10001000}
                 * createDate : 1502069519006
                 * headIcon : {"byteSize":36355,"createDate":1502069519006,"fileName":"U6QM5KU.jpg","id":"f52d4ac5-8ce9-4d76-94d4-26aafabdd6a0","name":"U6QM5KU","oldFileName":"U6QM5KU.jpg","position":0,"status":10001000}
                 * id : 652730f1-8b62-4d5e-9247-e0872b68b31e
                 * mobilephone : 15779160456
                 * name : 彭其煊
                 * password : 1234567
                 * position : 0
                 * pushSet : 0
                 * status : 10001000
                 * warnSet : 0
                 * wechatUser : {"city":"","country":"中国","groupid":"0","headimgurl":"http://wx.qlogo.cn/mmopen/8L6JXBHx6iaNPMG6pbOib01XvLuLiaQqC8IwY8clWyZwngFNLRFpEIpxatYHGxGN7ahibBZYzZceI1nD9TwmqaRCFUkWqQ7JyooR/0","language":"zh_CN","nickname":"世界这么乱，装纯给谁看","province":"江西","remark":"","sex":1,"subscribe":1,"subscribeTime":1500880804,"tagidList":"","wechatUserPK":{"appId":"wx75d005778ae46441","openid":"otEmQxESWdUYRkAdr1lziCAVvbfw"}}
                 */

                private CompanyBean company;
                private long createDate;
                private HeadIconBean headIcon;
                private String id;
                private String mobilephone;
                private String name;
                private String password;
                private int position;
                private int pushSet;
                private int status;
                private int warnSet;
                private WechatUserBean wechatUser;

                public CompanyBean getCompany() {
                    return company;
                }

                public void setCompany(CompanyBean company) {
                    this.company = company;
                }

                public long getCreateDate() {
                    return createDate;
                }

                public void setCreateDate(long createDate) {
                    this.createDate = createDate;
                }

                public HeadIconBean getHeadIcon() {
                    return headIcon;
                }

                public void setHeadIcon(HeadIconBean headIcon) {
                    this.headIcon = headIcon;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getMobilephone() {
                    return mobilephone;
                }

                public void setMobilephone(String mobilephone) {
                    this.mobilephone = mobilephone;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPassword() {
                    return password;
                }

                public void setPassword(String password) {
                    this.password = password;
                }

                public int getPosition() {
                    return position;
                }

                public void setPosition(int position) {
                    this.position = position;
                }

                public int getPushSet() {
                    return pushSet;
                }

                public void setPushSet(int pushSet) {
                    this.pushSet = pushSet;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public int getWarnSet() {
                    return warnSet;
                }

                public void setWarnSet(int warnSet) {
                    this.warnSet = warnSet;
                }

                public WechatUserBean getWechatUser() {
                    return wechatUser;
                }

                public void setWechatUser(WechatUserBean wechatUser) {
                    this.wechatUser = wechatUser;
                }

                public static class CompanyBean implements Serializable{
                    /**
                     * address : 江西省南昌市东湖区雅苑东路
                     * area : 江西 南昌
                     * createDate : 1501662262926
                     * detail : <p>百电</p>
                     * id : 0ccf0b38-7447-42be-988a-09235320b7d0
                     * iegCustomerList : [{"address":"江西省南昌市东湖区雄州路265号","createDate":1501816508629,"detail":"","id":"2597d9e6-3c74-47f1-aeac-94d6b57cb42b","iegCompanyList":[null],"lat":"28.691262","linkman":"李","linkphone":"150111111111","listPrice":20,"lng":"115.862674","minusDeviation":-0.23,"name":"莫奈瓷砖","position":0,"positiveDeviation":0.03,"shortNote":1,"status":10001000,"weChatNote":0}]
                     * lat : 28.696775
                     * lng : 115.863752
                     * name : 江西百电信息产业有限公司
                     * position : 0
                     * status : 10001000
                     */

                    private String address;
                    private String area;
                    private long createDate;
                    private String detail;
                    private String id;
                    private String lat;
                    private String lng;
                    private String name;
                    private int position;
                    private int status;
                    private List<IegCustomerListBean> iegCustomerList;

                    public String getAddress() {
                        return address;
                    }

                    public void setAddress(String address) {
                        this.address = address;
                    }

                    public String getArea() {
                        return area;
                    }

                    public void setArea(String area) {
                        this.area = area;
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

                    public String getLng() {
                        return lng;
                    }

                    public void setLng(String lng) {
                        this.lng = lng;
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

                    public int getStatus() {
                        return status;
                    }

                    public void setStatus(int status) {
                        this.status = status;
                    }

                    public List<IegCustomerListBean> getIegCustomerList() {
                        return iegCustomerList;
                    }

                    public void setIegCustomerList(List<IegCustomerListBean> iegCustomerList) {
                        this.iegCustomerList = iegCustomerList;
                    }

                    public static class IegCustomerListBean implements Serializable{
                        /**
                         * address : 江西省南昌市东湖区雄州路265号
                         * createDate : 1501816508629
                         * detail :
                         * id : 2597d9e6-3c74-47f1-aeac-94d6b57cb42b
                         * iegCompanyList : [null]
                         * lat : 28.691262
                         * linkman : 李
                         * linkphone : 150111111111
                         * listPrice : 20
                         * lng : 115.862674
                         * minusDeviation : -0.23
                         * name : 莫奈瓷砖
                         * position : 0
                         * positiveDeviation : 0.03
                         * shortNote : 1
                         * status : 10001000
                         * weChatNote : 0
                         */

                        private String address;
                        private long createDate;
                        private String detail;
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

                public static class HeadIconBean implements Serializable{
                    /**
                     * byteSize : 36355
                     * createDate : 1502069519006
                     * fileName : U6QM5KU.jpg
                     * id : f52d4ac5-8ce9-4d76-94d4-26aafabdd6a0
                     * name : U6QM5KU
                     * oldFileName : U6QM5KU.jpg
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

                public static class WechatUserBean implements Serializable{
                    /**
                     * city :
                     * country : 中国
                     * groupid : 0
                     * headimgurl : http://wx.qlogo.cn/mmopen/8L6JXBHx6iaNPMG6pbOib01XvLuLiaQqC8IwY8clWyZwngFNLRFpEIpxatYHGxGN7ahibBZYzZceI1nD9TwmqaRCFUkWqQ7JyooR/0
                     * language : zh_CN
                     * nickname : 世界这么乱，装纯给谁看
                     * province : 江西
                     * remark :
                     * sex : 1
                     * subscribe : 1
                     * subscribeTime : 1500880804
                     * tagidList :
                     * wechatUserPK : {"appId":"wx75d005778ae46441","openid":"otEmQxESWdUYRkAdr1lziCAVvbfw"}
                     */

                    private String city;
                    private String country;
                    private String groupid;
                    private String headimgurl;
                    private String language;
                    private String nickname;
                    private String province;
                    private String remark;
                    private int sex;
                    private int subscribe;
                    private int subscribeTime;
                    private String tagidList;
                    private WechatUserPKBean wechatUserPK;

                    public String getCity() {
                        return city;
                    }

                    public void setCity(String city) {
                        this.city = city;
                    }

                    public String getCountry() {
                        return country;
                    }

                    public void setCountry(String country) {
                        this.country = country;
                    }

                    public String getGroupid() {
                        return groupid;
                    }

                    public void setGroupid(String groupid) {
                        this.groupid = groupid;
                    }

                    public String getHeadimgurl() {
                        return headimgurl;
                    }

                    public void setHeadimgurl(String headimgurl) {
                        this.headimgurl = headimgurl;
                    }

                    public String getLanguage() {
                        return language;
                    }

                    public void setLanguage(String language) {
                        this.language = language;
                    }

                    public String getNickname() {
                        return nickname;
                    }

                    public void setNickname(String nickname) {
                        this.nickname = nickname;
                    }

                    public String getProvince() {
                        return province;
                    }

                    public void setProvince(String province) {
                        this.province = province;
                    }

                    public String getRemark() {
                        return remark;
                    }

                    public void setRemark(String remark) {
                        this.remark = remark;
                    }

                    public int getSex() {
                        return sex;
                    }

                    public void setSex(int sex) {
                        this.sex = sex;
                    }

                    public int getSubscribe() {
                        return subscribe;
                    }

                    public void setSubscribe(int subscribe) {
                        this.subscribe = subscribe;
                    }

                    public int getSubscribeTime() {
                        return subscribeTime;
                    }

                    public void setSubscribeTime(int subscribeTime) {
                        this.subscribeTime = subscribeTime;
                    }

                    public String getTagidList() {
                        return tagidList;
                    }

                    public void setTagidList(String tagidList) {
                        this.tagidList = tagidList;
                    }

                    public WechatUserPKBean getWechatUserPK() {
                        return wechatUserPK;
                    }

                    public void setWechatUserPK(WechatUserPKBean wechatUserPK) {
                        this.wechatUserPK = wechatUserPK;
                    }

                    public static class WechatUserPKBean implements Serializable{
                        /**
                         * appId : wx75d005778ae46441
                         * openid : otEmQxESWdUYRkAdr1lziCAVvbfw
                         */

                        private String appId;
                        private String openid;

                        public String getAppId() {
                            return appId;
                        }

                        public void setAppId(String appId) {
                            this.appId = appId;
                        }

                        public String getOpenid() {
                            return openid;
                        }

                        public void setOpenid(String openid) {
                            this.openid = openid;
                        }
                    }
                }
            }
        }
    }
}
