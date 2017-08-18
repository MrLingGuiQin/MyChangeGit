package com.linguiqing.mychanage.ui.retrofit;

import java.util.List;

/**
 * ***************************************
 * statement: 轮播图bean
 * auther: lingguiqin
 * date created : 2017/8/16 0016
 * ***************************************
 */

public class BannerData {

    /**
     * data : {"adInfoMap":[{"adHrefUrl":"http://m.gxyj.com/active/cqtjgapp/index.html","imgUrl":"http://image.gxyj.com/v4/images/2/operationDefault/1502788284337.jpg","mallId":"00000000"},{"adHrefUrl":"http://m.gxyj.com/active/meiweidalianapp/index.html","imgUrl":"http://image.gxyj.com/v4/images/1/operationDefault/1502788908776.jpg","mallId":"00000000"},{"adHrefUrl":"http://m.gxyj.com/active/yikoulvcaoyuanapp/index.html","imgUrl":"http://image.gxyj.com/v4/images/1/operationDefault/1502443141536.jpg","mallId":"00000000"},{"adHrefUrl":"http://m.gxyj.com/active/nanwuyitechanguanapp/index.html","imgUrl":"http://image.gxyj.com/v4/images/1/operationDefault/1502364568304.jpg","mallId":"00000000"}]}
     * msg :
     * status : true
     */

    private DataBean data;
    private String msg;
    private boolean status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static class DataBean {
        private List<AdInfoMapBean> adInfoMap;

        public List<AdInfoMapBean> getAdInfoMap() {
            return adInfoMap;
        }

        public void setAdInfoMap(List<AdInfoMapBean> adInfoMap) {
            this.adInfoMap = adInfoMap;
        }

        public static class AdInfoMapBean {
            /**
             * adHrefUrl : http://m.gxyj.com/active/cqtjgapp/index.html
             * imgUrl : http://image.gxyj.com/v4/images/2/operationDefault/1502788284337.jpg
             * mallId : 00000000
             */

            private String adHrefUrl;
            private String imgUrl;
            private String mallId;

            public String getAdHrefUrl() {
                return adHrefUrl;
            }

            public void setAdHrefUrl(String adHrefUrl) {
                this.adHrefUrl = adHrefUrl;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getMallId() {
                return mallId;
            }

            public void setMallId(String mallId) {
                this.mallId = mallId;
            }
        }
    }
}
