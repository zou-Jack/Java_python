package jsz_demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import com.baidu.aip.imageclassify.AipImageClassify;


public class CarRecognition {
	//填入自己申请的AAP_ID APP_KEY SECRET_KEY
	public static final String APP_ID = "15489090";
    public static final String API_KEY = "b07uuBREHdQwD3BjCzETIi7a";
    public static final String SECRET_KEY = "1GczeRXnN4mLLyquRvA3v7OKxAq6lW4M";
    
	public static void main(String[] args) {
		AipImageClassify aic = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY); 
    	sample(aic);                        //调用识别方法   	
	}	
	public  static void sample(AipImageClassify client) {
		HashMap<String, String> options = new HashMap<String, String>();  // 传入可选参数调用接口
		String color = null;                //定义一个字符串用于后面存储车身颜色			
		String type = null;    				//定义一个字符串用于后面存储车辆型号
		
		options.put("top_num", "3");		//设置识别出的车型数量
		options.put("baike_num", "0");		//设置给出的结果中是否需要包含车辆的百度百科描述信息	
		String path = "/home/xxy/eclipse-workspace/jsz_demo/img/";	//需要识别的本地图片所在文件夹路径
		File file = new File(path); 		
		File[] files = file.listFiles();
		try {		
			BufferedWriter bufw = new BufferedWriter(new FileWriter("/home/xxy/eclipse-workspace/jsz_demo/carInfo.txt"));  //建一个输出流用于输出车辆识别结果信息
			bufw.write("文件名 $ 车型1 $车型2 $车型3 $颜色");      //首先在文档中写出表头，使用$符号分割方便后期将txt文档数据导入excel中做分割
			bufw.newLine();
			for(int i=0;i<files.length;i++) {
				System.out.println(files[i].getName());	    //打印一下当前正在处理的图片名称，方便看进度
				String image = files[i].getAbsolutePath();  //获取本地图片的绝对路径
				bufw.write(files[i].getName()+"$");			//第一项数据为文件名
				
				JSONObject res = client.carDetect(image, options);  //获取调用API接口所获取的车辆信息识别数据res，格式为JSONobject
				System.out.println("识别结果："+res.toString(2));
				 
				//下面进行JSONobjec数据的解析，提取出识别出的车型、颜色信息
				JSONArray results = res.getJSONArray("result");	 	//将返回的JSONObject中result数组的转化成JSONarray，下面利用循环分别获得每一种车型结果数据		
				for(int j = 0;j<results.length();j++) {
					JSONObject obj = (JSONObject)results.get(j);	//将JSONOarray中第j个JSONObject数据拿出	
					type = (String) obj.get("name");				//利用键“name”获取车型值
					bufw.write(type+"$");
				}
				color = (String) res.get("color_result");			//利用键“color_result”获取返回的JSONObject中车辆颜色信息值
				bufw.write(color);
				bufw.newLine();	
				bufw.flush();
			} 		
			bufw.flush();
			bufw.close();	
		}
		catch (IOException e) {		
			e.printStackTrace();
		}		
	}
}
