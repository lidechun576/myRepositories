package testSend.com.Send;

import java.io.IOException;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
/**
 * Send HTTP request
 * 
 * @author MICLE
 *
 */
public class HttpSend {

	private String host;
	private int port;
	private String authUser;
	private String authPasswd;

	public HttpSend(String host, int port, String authUser,
			String authPasswd) {
		this.host = host;
		this.port = port;
		this.authUser = authUser;
		this.authPasswd = authPasswd;
	}

	/**
	 * send HTTP POST Request
	 * 
	 * @param strUrl
	 *            request URL
	 * @param jsonParams
	 *            request parameter
	 * @return String String of JSON
	 */
	public static void main(String[] args) 
	{
		String url ="http://localhost:8080/HttpClientSend/sendSMS";//注意加上http://
		/**
		 * 参数：
		 * @param host ip地址(如：192.168.21.251)
		 * @param port 端口号（String类型）
		 * @param authUser 登录用户名
		 * @param authPasswd 登录密码（明文格式不用加密）
		 * @param text 短信内容
		 * @param phone （String类型）
		 * @param userid 注意这个userid（user_id 可不断递增，使每条发送请求中的 user_id 都是唯一）作用：查询发送结果需要用到
		 */
		PostMethod postMethod = new PostMethod(url); 
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8"); 
		String text = "hello,your get message，来点中文试试,害怕了你不成？？*&12366《》；‘kk";
		NameValuePair[] data = 
				{  
                 new NameValuePair("host", "192.168.21.251"),  
                 new NameValuePair("ports", "3"),  
                 new NameValuePair("authUser", "admin"),  
                 new NameValuePair("authPasswd", "admin"),
                 new NameValuePair("text", text),
                 new NameValuePair("phone", "15882279820"),
                 new NameValuePair("userid", "68")
                 };
		postMethod.setRequestBody(data);
		 int statusCode = 0;  
         HttpClient httpClient = new HttpClient();
         try 
         {  
             statusCode = httpClient.executeMethod(postMethod);  
         } catch (HttpException e) {  
             e.printStackTrace();  
         } catch (IOException e) {  
             e.printStackTrace();  
         }  
         if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY  || statusCode == HttpStatus.SC_MOVED_TEMPORARILY)
         {  
             // 从头中取出转向的地址  
             Header locationHeader = postMethod.getResponseHeader("location");  
             String location = null;  
             if (locationHeader != null)
             {  
                 location = locationHeader.getValue();  
             } else
             {  
                 System.err.println("Location field value is null.");  
             }  
         } else {  
             System.out.println(postMethod.getStatusLine());  
             String str = "";  
             try {  
                 	str = postMethod.getResponseBodyAsString();  
             	} catch (IOException e) 
             	{  
             		e.printStackTrace();  
             	}  
             System.out.println("str 返回值=="+str);  
         }  
         postMethod.releaseConnection();   
        }  
}