package com.linguiqing.mychanage.ui.productSku;

import java.util.List;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/4/8 0008
 * ***************************************
 */

public class ProductBean {
    private PromotionProdInfoBean promotionProdInfo;
    private List<ProductDataBean> productData;
    private List<SkuListBean> skuList; // sku参加活动的结合
    private List<SkuProductListBean> skuProductList; // sku的所有组合
    private List<String> skuProductFailureList; // 不支持购买的sku组合
    private List<SkuTypeListBean> skuTypeList; // sku 具体数据

    public PromotionProdInfoBean getPromotionProdInfo() {
        return promotionProdInfo;
    }

    public void setPromotionProdInfo(PromotionProdInfoBean promotionProdInfo) {
        this.promotionProdInfo = promotionProdInfo;
    }

    public List<ProductDataBean> getProductData() {
        return productData;
    }

    public void setProductData(List<ProductDataBean> productData) {
        this.productData = productData;
    }

    public List<SkuListBean> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<SkuListBean> skuList) {
        this.skuList = skuList;
    }

    public List<SkuProductListBean> getSkuProductList() {
        return skuProductList;
    }

    public void setSkuProductList(List<SkuProductListBean> skuProductList) {
        this.skuProductList = skuProductList;
    }

    public List<SkuTypeListBean> getSkuTypeList() {
        return skuTypeList;
    }

    public void setSkuTypeList(List<SkuTypeListBean> skuTypeList) {
        this.skuTypeList = skuTypeList;
    }

    public List<String> getSkuProductFailureList() {
        return skuProductFailureList;
    }

    public void setSkuProductFailureList(List<String> skuProductFailureList) {
        this.skuProductFailureList = skuProductFailureList;
    }

    public static class PromotionProdInfoBean {

        private int maxBuyNum;
        private String merchantServicePhone;
        private String nowDate;
        private String prodId;
        private String prodMeasureUnit;
        private String prodName;
        private String prodPicUrl;
        private String prodPrice;
        private String prodStatus;
        private String productShareImage;
        private String productShareUrl;
        private String promotionDefineType;
        private String promotionEndTime;
        private String promotionId;
        private String promotionProdLeft;
        private String promotionProdPrice;
        private String showURL;

        public int getMaxBuyNum() {
            return maxBuyNum;
        }

        public void setMaxBuyNum(int maxBuyNum) {
            this.maxBuyNum = maxBuyNum;
        }

        public String getMerchantServicePhone() {
            return merchantServicePhone;
        }

        public void setMerchantServicePhone(String merchantServicePhone) {
            this.merchantServicePhone = merchantServicePhone;
        }

        public String getNowDate() {
            return nowDate;
        }

        public void setNowDate(String nowDate) {
            this.nowDate = nowDate;
        }

        public String getProdId() {
            return prodId;
        }

        public void setProdId(String prodId) {
            this.prodId = prodId;
        }

        public String getProdMeasureUnit() {
            return prodMeasureUnit;
        }

        public void setProdMeasureUnit(String prodMeasureUnit) {
            this.prodMeasureUnit = prodMeasureUnit;
        }

        public String getProdName() {
            return prodName;
        }

        public void setProdName(String prodName) {
            this.prodName = prodName;
        }

        public String getProdPicUrl() {
            return prodPicUrl;
        }

        public void setProdPicUrl(String prodPicUrl) {
            this.prodPicUrl = prodPicUrl;
        }

        public String getProdPrice() {
            return prodPrice;
        }

        public void setProdPrice(String prodPrice) {
            this.prodPrice = prodPrice;
        }

        public String getProdStatus() {
            return prodStatus;
        }

        public void setProdStatus(String prodStatus) {
            this.prodStatus = prodStatus;
        }

        public String getProductShareImage() {
            return productShareImage;
        }

        public void setProductShareImage(String productShareImage) {
            this.productShareImage = productShareImage;
        }

        public String getProductShareUrl() {
            return productShareUrl;
        }

        public void setProductShareUrl(String productShareUrl) {
            this.productShareUrl = productShareUrl;
        }

        public String getPromotionDefineType() {
            return promotionDefineType;
        }

        public void setPromotionDefineType(String promotionDefineType) {
            this.promotionDefineType = promotionDefineType;
        }

        public String getPromotionEndTime() {
            return promotionEndTime;
        }

        public void setPromotionEndTime(String promotionEndTime) {
            this.promotionEndTime = promotionEndTime;
        }

        public String getPromotionId() {
            return promotionId;
        }

        public void setPromotionId(String promotionId) {
            this.promotionId = promotionId;
        }

        public String getPromotionProdLeft() {
            return promotionProdLeft;
        }

        public void setPromotionProdLeft(String promotionProdLeft) {
            this.promotionProdLeft = promotionProdLeft;
        }

        public String getPromotionProdPrice() {
            return promotionProdPrice;
        }

        public void setPromotionProdPrice(String promotionProdPrice) {
            this.promotionProdPrice = promotionProdPrice;
        }

        public String getShowURL() {
            return showURL;
        }

        public void setShowURL(String showURL) {
            this.showURL = showURL;
        }
    }

    public static class ProductDataBean {

        private int evaSize;
        private String inStore;
        private String merchantId;
        private String merchantServicePhone;
        private String prodName;
        private String prodPrice;
        private String productId;
        private String productImage;
        private String productShareImage;
        private String showURL;
        private StoreBean store;
        private String totalSell;

        public int getEvaSize() {
            return evaSize;
        }

        public void setEvaSize(int evaSize) {
            this.evaSize = evaSize;
        }

        public String getInStore() {
            return inStore;
        }

        public void setInStore(String inStore) {
            this.inStore = inStore;
        }

        public String getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(String merchantId) {
            this.merchantId = merchantId;
        }

        public String getMerchantServicePhone() {
            return merchantServicePhone;
        }

        public void setMerchantServicePhone(String merchantServicePhone) {
            this.merchantServicePhone = merchantServicePhone;
        }

        public String getProdName() {
            return prodName;
        }

        public void setProdName(String prodName) {
            this.prodName = prodName;
        }

        public String getProdPrice() {
            return prodPrice;
        }

        public void setProdPrice(String prodPrice) {
            this.prodPrice = prodPrice;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getProductImage() {
            return productImage;
        }

        public void setProductImage(String productImage) {
            this.productImage = productImage;
        }

        public String getProductShareImage() {
            return productShareImage;
        }

        public void setProductShareImage(String productShareImage) {
            this.productShareImage = productShareImage;
        }

        public String getShowURL() {
            return showURL;
        }

        public void setShowURL(String showURL) {
            this.showURL = showURL;
        }

        public StoreBean getStore() {
            return store;
        }

        public void setStore(StoreBean store) {
            this.store = store;
        }

        public String getTotalSell() {
            return totalSell;
        }

        public void setTotalSell(String totalSell) {
            this.totalSell = totalSell;
        }

        public static class StoreBean {

            private double describeAvg;
            private double logisticsAvg;
            private double serviceAvg;
            private String storeId;
            private String storeLOG;
            private String storeMsg;
            private String storeName;

            public double getDescribeAvg() {
                return describeAvg;
            }

            public void setDescribeAvg(double describeAvg) {
                this.describeAvg = describeAvg;
            }

            public double getLogisticsAvg() {
                return logisticsAvg;
            }

            public void setLogisticsAvg(double logisticsAvg) {
                this.logisticsAvg = logisticsAvg;
            }

            public double getServiceAvg() {
                return serviceAvg;
            }

            public void setServiceAvg(double serviceAvg) {
                this.serviceAvg = serviceAvg;
            }

            public String getStoreId() {
                return storeId;
            }

            public void setStoreId(String storeId) {
                this.storeId = storeId;
            }

            public String getStoreLOG() {
                return storeLOG;
            }

            public void setStoreLOG(String storeLOG) {
                this.storeLOG = storeLOG;
            }

            public String getStoreMsg() {
                return storeMsg;
            }

            public void setStoreMsg(String storeMsg) {
                this.storeMsg = storeMsg;
            }

            public String getStoreName() {
                return storeName;
            }

            public void setStoreName(String storeName) {
                this.storeName = storeName;
            }
        }
    }

    public static class SkuListBean {
        /**
         * promotionSkuPrice : 11.0
         * skuId : 00000000000000006627
         */

        private double promotionSkuPrice;
        private String skuId;

        public double getPromotionSkuPrice() {
            return promotionSkuPrice;
        }

        public void setPromotionSkuPrice(double promotionSkuPrice) {
            this.promotionSkuPrice = promotionSkuPrice;
        }

        public String getSkuId() {
            return skuId;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }
    }

    public static class SkuProductListBean {

        private String originPrice;
        private String prodBulk;
        private String prodColorPropImg;
        private String prodImg;
        private String prodPropName;
        private String prodSkuId;
        private String prodWeight;
        private String promationFalg;
        private String promotionSkuPrice;
        private String skuGroup;
        private String skuPrice;
        private String skuStorage;
        private String status;

        public String getOriginPrice() {
            return originPrice;
        }

        public void setOriginPrice(String originPrice) {
            this.originPrice = originPrice;
        }

        public String getProdBulk() {
            return prodBulk;
        }

        public void setProdBulk(String prodBulk) {
            this.prodBulk = prodBulk;
        }

        public String getProdColorPropImg() {
            return prodColorPropImg;
        }

        public void setProdColorPropImg(String prodColorPropImg) {
            this.prodColorPropImg = prodColorPropImg;
        }

        public String getProdImg() {
            return prodImg;
        }

        public void setProdImg(String prodImg) {
            this.prodImg = prodImg;
        }

        public String getProdPropName() {
            return prodPropName;
        }

        public void setProdPropName(String prodPropName) {
            this.prodPropName = prodPropName;
        }

        public String getProdSkuId() {
            return prodSkuId;
        }

        public void setProdSkuId(String prodSkuId) {
            this.prodSkuId = prodSkuId;
        }

        public String getProdWeight() {
            return prodWeight;
        }

        public void setProdWeight(String prodWeight) {
            this.prodWeight = prodWeight;
        }

        public String getPromationFalg() {
            return promationFalg;
        }

        public void setPromationFalg(String promationFalg) {
            this.promationFalg = promationFalg;
        }

        public String getPromotionSkuPrice() {
            return promotionSkuPrice;
        }

        public void setPromotionSkuPrice(String promotionSkuPrice) {
            this.promotionSkuPrice = promotionSkuPrice;
        }

        public String getSkuGroup() {
            return skuGroup;
        }

        public void setSkuGroup(String skuGroup) {
            this.skuGroup = skuGroup;
        }

        public String getSkuPrice() {
            return skuPrice;
        }

        public void setSkuPrice(String skuPrice) {
            this.skuPrice = skuPrice;
        }

        public String getSkuStorage() {
            return skuStorage;
        }

        public void setSkuStorage(String skuStorage) {
            this.skuStorage = skuStorage;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class SkuTypeListBean {
        /**
         * skuAttributeList : [{"skuAttribute":"200x230cm","skuAttributeCode":"0000023343"}]
         * skuTitle : 尺寸
         * skuTitleCode : 0000100306
         */

        private String skuTitle;
        private String skuTitleCode;
        private List<SkuAttributeListBean> skuAttributeList;
        private List<String> skuAttributeFailureList;

        public String getSkuTitle() {
            return skuTitle;
        }

        public void setSkuTitle(String skuTitle) {
            this.skuTitle = skuTitle;
        }

        public String getSkuTitleCode() {
            return skuTitleCode;
        }

        public void setSkuTitleCode(String skuTitleCode) {
            this.skuTitleCode = skuTitleCode;
        }

        public List<SkuAttributeListBean> getSkuAttributeList() {
            return skuAttributeList;
        }

        public void setSkuAttributeList(List<SkuAttributeListBean> skuAttributeList) {
            this.skuAttributeList = skuAttributeList;
        }

        public List<String> getSkuAttributeFailureList() {
            return skuAttributeFailureList;
        }

        public void setSkuAttributeFailureList(List<String> skuAttributeFailureList) {
            this.skuAttributeFailureList = skuAttributeFailureList;
        }

        public static class SkuAttributeListBean {
            /**
             * skuAttribute : 200x230cm
             * skuAttributeCode : 0000023343
             */

            private String skuAttribute;
            private String skuAttributeCode;

            public String getSkuAttribute() {
                return skuAttribute;
            }

            public void setSkuAttribute(String skuAttribute) {
                this.skuAttribute = skuAttribute;
            }

            public String getSkuAttributeCode() {
                return skuAttributeCode;
            }

            public void setSkuAttributeCode(String skuAttributeCode) {
                this.skuAttributeCode = skuAttributeCode;
            }
        }
    }
}
