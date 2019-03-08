package jsz_demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;

import com.alibaba.fastjson.JSON;

import com.baidu.aip.ocr.AipOcr;

import jsz_demo.test.BaiDuOCRBean;
import jsz_demo.test.BaiDuOCRBean.Words_result;
/**
 * 
 * @author zoutao
 * 参考文档：http://ai.baidu.com/docs#/OCR-Java-SDK/3b4ed0e7
 * web版本：https://www.jianshu.com/p/cecc20281124
 *
 */
public class jsz_ocr {
	//填入自己申请的AAP_ID APP_KEY SECRET_KEY
	public static final String APP_ID = "15489090";
    public static final String API_KEY = "b07uuBREHdQwD3BjCzETIi7a";
    public static final String SECRET_KEY = "1GczeRXnN4mLLyquRvA3v7OKxAq6lW4M";
	    
    public static void main(String[] args) throws IOException {
    	
    	long now = System.currentTimeMillis();
		
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
    	HashMap<String, String> options = new HashMap<String, String>();  // 传入可选参数调用接口
    	options.put("detect_direction", "false");		//是否检测图像朝向
		
        // 调用接口
    	String path = "/home/xxy/eclipse-workspace/jsz_demo/img/timg.jpeg";	//需要识别的本地图片所在文件夹路径
        //JSONObject res = client.drivingLicense(path, new HashMap<String, String>());
    	JSONObject res =client.drivingLicense(path,options);
        System.out.println("识别结果："+res.toString(2));
        System.out.println("耗时：" + (System.currentTimeMillis() - now) / 1000 + "s");
        
       //下面进行JSONobjec数据的解析，提取出需要的信息
//        JSONArray results = res.getJSONArray("data");	 	//将返回的JSONObject中result数组的转化成JSONarray，下面利用循环分别获得每一种车型结果数据		
//		for(int j = 0;j<results.length();j++) {
//			JSONObject obj = (JSONObject)results.get(j);			//将JSONOarray中第j个JSONObject数据拿出	
//			String type = (String) obj.get("words");				//利用键“name”获取车型值
//			System.out.println(type);
//		}
      //JSONObject构造2，参数传入json格式的字符串
        JSONObject obj2 = new JSONObject(res.toString(2));
        System.out.println(obj2.toString());

        //JSONObject属性遍历
        Iterator<String> it = obj2.keySet().iterator();
        while (it.hasNext()) {
        	String key = it.next();
        	System.out.println(key+"="+(Long)obj2.get(key));
        }
//		List<Words_result> list = baiDuOCRBean.getWords_result();
//		for (int i = 0; i < baiDuOCRBean.length; i++) {
//			System.out.println(baiDuOCRBean(i));
//		}
    }
}
