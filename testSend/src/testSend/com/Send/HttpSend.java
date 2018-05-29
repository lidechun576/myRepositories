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
		String url ="http://localhost:8080/HttpClientSend/sendSMS";//ע�����http://
		/**
		 * ������
		 * @param host ip��ַ(�磺192.168.21.251)
		 * @param port �˿ںţ�String���ͣ�
		 * @param authUser ��¼�û���
		 * @param authPasswd ��¼���루���ĸ�ʽ���ü��ܣ�
		 * @param text ��������
		 * @param phone ��String���ͣ�
		 * @param userid ע�����userid��user_id �ɲ��ϵ�����ʹÿ�����������е� user_id ����Ψһ�����ã���ѯ���ͽ����Ҫ�õ�
		 */
		PostMethod postMethod = new PostMethod(url); 
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8"); 
		String text = "hello,your get message��������������,�������㲻�ɣ���*&12366��������kk";
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
             // ��ͷ��ȡ��ת��ĵ�ַ  
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
             System.out.println("str ����ֵ=="+str);  
         }  
         postMethod.releaseConnection();   
        }  
}