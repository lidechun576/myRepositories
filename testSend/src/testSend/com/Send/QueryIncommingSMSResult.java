package testSend.com.Send;

import java.util.List;

import com.dinstar.result.QueryIncommingSMSResultItem;

public class QueryIncommingSMSResult 
{
	//count of SMS have been read
	private int read;
	//count of SMS have been unread
	private int unread;
	//200, Request has been processed
	//500: Other error
	private int errorCode;
	private List<QueryIncommingSMSResultItem> smsList;
}
