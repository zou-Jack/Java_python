package jsz_demo;
import java.util.HashMap;
import java.util.Iterator;
import com.alibaba.fastjson.JSONObject;
/**
 * @author 
 * https://blog.csdn.net/qq_39135287/article/details/82494420
 */
public class json_map {
// 把json格式转换成HashMap-常用来提取jsonn字符串中某个段的值
	@SuppressWarnings("unchecked")
	public static HashMap<String, String> getHashMapByJson(String jsonResult) {
		@SuppressWarnings("rawtypes")
		HashMap map = new HashMap<String, String>();
		try {
			JSONObject jsonObject = JSONObject.parseObject(jsonResult.toString());
			JSONObject words_result = jsonObject.getJSONObject("data");
			Iterator<String> it = words_result.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				JSONObject result = words_result.getJSONObject(key);
				
				String value = result.getString("words_result");
				switch (key) {
				case "姓名":
					map.put("words", value);
					break;
				case "至":
					map.put("words", value);
					break;
				case "证号":
					map.put("words", value);
					break;
				case "出生日期":
					map.put("words", value);
					break;
				case "住址":
					map.put("words", value);
					break;
				case "性别":
					map.put("words", value);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	 
}
