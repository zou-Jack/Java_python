package jsz_demo.test;

import java.net.URLEncoder;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.baidu.aip.util.Base64Util;

import jsz_demo.test.BaiDuOCRBean.Words_result;
import jsz_demo.test.HttpUtil;

public class TestOcr {
	public static void main(String[] args) throws Exception {
		String imgData = "/home/xxy/eclipse-workspace/jsz_demo/img/jsz.jpg";
		String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic?access_token=24.344a9223802190340b1f0171da8427f5.2592000.1551232891.282335-15489090";
		String base64 = Base64Util.encode(FileUtil.readFileByBytes(imgData));
		String param = "image="+URLEncoder.encode(base64,"UTF-8")+"&language_type=CHN_ENG&detect_direction=true&detect_language=true&probability=true";
		
		String accessToken = "24.344a9223802190340b1f0171da8427f5.2592000.1551232891.282335-15489090";
		
		String result = HttpUtil.post(url,accessToken,param);
		BaiDuOCRBean baiDuOCRBean = com.alibaba.fastjson.JSONObject.toJavaObject(JSON.parseObject(result), BaiDuOCRBean.class);
		
		List<Words_result> list = baiDuOCRBean.getWords_result();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getWords());
		}
	}

}
