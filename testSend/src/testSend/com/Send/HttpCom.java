package testSend.com.Send;

import java.io.IOException;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
public class HttpCom 
{
      public static void main(String[] args)
      {
    	  String url ="http://localhost:8080/HttpclientCom/comSMS";
    	  
    	  /**
    	   * host
    	   * incomingSMSId
    	   * authUser
    	   * authPasswd
    	   */
    	  PostMethod postMethod = new PostMethod(url); 
    	  postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8"); 
    	  NameValuePair[] data = 
				{  
						new NameValuePair("host", "192.168.21.251"),  
						new NameValuePair("incomingSMSId", "3"),
						new NameValuePair("authUser", "admin"),  
						new NameValuePair("authPasswd", "admin")
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
              System.out.println("str 返回值接收短信接口=="+str);  
          }  
          postMethod.releaseConnection();   
         }  
//    	  String host = "192.168.21.251";//也要注意设备的端口号码
//    	  int port = 80;
//    	  String authUser = "admin";
//    	  String authPasswd = "admin";
//    	  Gateway gateway = new Gateway( host , port , authUser , authPasswd );
//    	  Integer incomingSMSId = 3;
//    	  com.dinstar.result.QueryIncommingSMSResult result = gateway.queryIncomingSMS(incomingSMSId,SMSStatusEnum.UNREAD);
//    	  System.out.println(result.getJsonString()+":result结果");
}
