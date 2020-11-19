package com.ss.service.impl;


import com.ss.service.ITempMaterialService;
import com.ss.util.WeixinUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * 临时素材业务类
 */
@Service
public class TempMaterialServiceImpl implements ITempMaterialService {

    public static Logger log = LoggerFactory.getLogger(TempMaterialServiceImpl.class);

    //上传临时素材url
    public static String upload_temp_material_url = "https://qyapi.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

    @Override
    public JSONObject uploadTempMaterial(String accessToken, String type, String fileUrl) {

        //1、创建本地文件
        File file = new File(fileUrl);
        //2、拼接请求url
        upload_temp_material_url = upload_temp_material_url.replace("ACCESS_TOKEN",accessToken).replace("TYPE",type);
        //3、调用接口，发送请求，上传文件到微信服务器
        String result = WeixinUtil.httpRequest(upload_temp_material_url,file);
        //4、就送字符串转对象，解析返回值，jsoo反序列化
        result = result.replace("[\\\\]","");
        JSONObject jsonObject = JSONObject.fromObject(result);
        //5、返回参数判断
        if(jsonObject != null){
            if(jsonObject.get("media_id") != null){
                log.info("上传{}临时素材成功",type);
                return jsonObject;
            }else{
                log.info("上传{}临时素材失败！",type);
            }
        }
        return null;
    }
}
