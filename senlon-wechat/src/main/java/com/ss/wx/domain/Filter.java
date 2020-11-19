package com.ss.wx.domain;

/**
 * 用于设定图文接收者
 */
public class Filter {
    private boolean is_to_all;
    private String group_id;
    private String tag_id;

    public boolean isIs_to_all() {
        return is_to_all;
    }

    public void setIs_to_all(boolean is_to_all) {
        this.is_to_all = is_to_all;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getTag_id() {
        return tag_id;
    }

    public void setTag_id(String tag_id) {
        this.tag_id = tag_id;
    }
}
