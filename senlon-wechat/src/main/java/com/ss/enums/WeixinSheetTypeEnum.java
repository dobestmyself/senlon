package com.ss.enums;

public enum WeixinSheetTypeEnum {


    wx_sheet_type_tp("WXTP","图片"),
    wx_sheet_type_gzyd("WXGZYD","关注引导"),
    wx_sheet_type_nrq("WXNRQ","内容区"),
    wx_sheet_type_ywyd("WXYWYD","原文引导"),
    wx_sheet_type_bt("WXBT","标题"),
    wx_sheet_type_fgx("WXFGX","分割线"),
    wx_sheet_type_wxhtzh("WXHTZH","互推账号"),
    wx_sheet_type_wxqt("WXQT","其他"),
    wx_sheet_type_wxwdys("WXWDYS","我的样式");


    private String code;
    private String value;

    private WeixinSheetTypeEnum(String code,String value){
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
