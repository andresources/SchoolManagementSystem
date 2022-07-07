package com.pharma.model.parent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GalleryModel {

    @SerializedName("ref")
    @Expose
    private String ref;

    @SerializedName("imgTitle")
    @Expose
    private String imgTitle;

    @SerializedName("imgFolder")
    @Expose
    private String url;

    public String getUrl() {
        //return url;
        return "https://erp.tapasyaedu.com:9443/t/imgImgDisp.action?imgFolder="+url+"&refType=brg_&ref="+ref;
       // return "http://103.67.238.246/E:/GVT_FILES/"+url+"/brg_"+ref+".jpg";
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getImgTitle() {
        return imgTitle;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }
}
