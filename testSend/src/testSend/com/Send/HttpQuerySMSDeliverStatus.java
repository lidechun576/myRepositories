package testSend.com.Send;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpQuerySMSDeliverStatus 
{
	public static void main(String[] args) 
	{
		/**
		 * host
		 * authUser
		 * authPasswd
		 * phone
		 * port
		 * userid
		 * 
		 * String host,String authUser,String authPasswd,String phone,String port,String userid
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long start = System.currentTimeMillis();
		String time_after = sdf.format(start);
		
		String url ="http://localhost:8080/HttpClientSend/querySMSDeliverStatus";
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
		NameValuePair[] data = 
			{  
             new NameValuePair("host", "192.168.21.251"),  
             new NameValuePair("authUser", "admin"),  
             new NameValuePair("authPasswd", "admin"),
             
             new NameValuePair("phone", "15882279820"),
             new NameValuePair("port", "3"),  
             new NameValuePair("time_after", time_after)
             
             //new NameValuePair("userid", "68")
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
