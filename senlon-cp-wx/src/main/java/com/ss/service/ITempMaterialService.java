package com.ss.service;


import net.sf.json.JSONObject;

public interface ITempMaterialService {

    public JSONObject uploadTempMaterial(String accessToken, String type, String fileUrl);

}
