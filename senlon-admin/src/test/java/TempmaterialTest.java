import com.ss.SenlonApplication;
import com.ss.service.ITempMaterialService;
import com.ss.util.WeixinParamsUtil;
import com.ss.util.WeixinUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SenlonApplication.class)
public class TempmaterialTest {
    @Autowired
    private ITempMaterialService tempMaterialService;
    @Test
    public void testUploadTempMaterial(){
        //初始化参数
        String fileUrl = "F:/下载.jpg";
        String type = "image";
        String accessToken = WeixinUtil.getAccessToken(WeixinParamsUtil.corpId,WeixinParamsUtil.secret).getToken();
        //调用业务类，上传临时素材
        tempMaterialService.uploadTempMaterial(accessToken,type,fileUrl);
    }
}
