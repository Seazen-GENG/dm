package com.fuhui.util;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
//import org.json.JSONObject;
import org.springframework.util.StringUtils;

public class HTTPUtils {

	private static PoolingHttpClientConnectionManager connManager;
	private static RequestConfig requestConfig;
	private static final int MAX_TIMEOUT = 3600;

	static {
		// 设置连接池
		connManager = new PoolingHttpClientConnectionManager();
		// 设置连接池大小
		connManager.setMaxTotal(100);
		connManager.setDefaultMaxPerRoute(connManager.getMaxTotal());
		// 在提交请求之前 测试连接是否可用
//		connManager.setValidateAfterInactivity(3600);
		RequestConfig.Builder configBuilder = RequestConfig.custom();
		// 设置连接超时
		configBuilder.setConnectTimeout(MAX_TIMEOUT);
		// 设置读取超时
		configBuilder.setSocketTimeout(MAX_TIMEOUT);
		// 设置从连接池获取连接实例的超时
		configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
		requestConfig = configBuilder.build();
	}

	/**
	 * 发送 GET 请求（HTTP），不带输入数据
	 * 
	 * @param url
	 * @return
	 */
	public static String doGet(String url) {
		return doGet(url, new LinkedHashMap<String, Object>());
	}

	/*public static String getParams(String url, Map<String, Object> params) {
		if (StringUtils.isEmpty(url))
			return null;
		StringBuilder param = new StringBuilder();
		int i = 0;
		for (String key : params.keySet()) {
			if (i == 0)
				param.append("?");
			else
				param.append("&");
			param.append(key).append("=").append(params.get(key));
			i++;
		}
		url += param;
		return url;
	}*/

	/**
	 * 发送 GET 请求（HTTP），K-V形式
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doGet(String url, Map<String, Object> params) {
		if (StringUtils.isEmpty(url))
			return null;
		StringBuilder param = new StringBuilder();
		int i = 0;
		for (String key : params.keySet()) {
			if (i == 0)
				param.append("?");
			else
				param.append("&");
			param.append(key).append("=").append(params.get(key));
			i++;
		}
		url += param;
		String result = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpPost = new HttpGet(url);
			HttpResponse response = httpclient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			System.out.println("执行状态码 : " + statusCode);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				result = IOUtils.toString(instream, "UTF-8");
			}
		} catch (Exception e) {
			e.getMessage();
		}
		return result;
	}

	/**
	 * 发送 POST 请求（HTTP），不带输入数据
	 * 
	 * @param url
	 * @return
	 */
	public static String doPost(String url) {
		return doPost(url, new LinkedHashMap<String, Object>());
	}

	/**
	 * 发送 POST 请求（HTTP），K-V形式
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doPost(String url, Map<String, Object> params) {
		if (StringUtils.isEmpty(url))
			return null;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = null;
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = null;
		try {
			httpPost.setConfig(requestConfig);
			List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
				pairList.add(pair);
			}
			httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * 发送 POST 请求（HTTP），K-V形式
	 *
	 * @param url
	 * @param params
	 * @return
	 *//*
	public static String doPost(String url, Map<String, Object> params, Map<String, String> headers) {
		if (StringUtils.isEmpty(url))
			return null;

		CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = null;
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = null;
		Iterator<String> iterator = headers.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			String value = headers.get(key);
			httpPost.addHeader(key, value);
		}

		try {
			httpPost.setConfig(requestConfig);

			List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
				pairList.add(pair);
			}
			httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));

			response = httpClient.execute(httpPost);

			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, "UTF-8");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}*/

	/**
	 * 发送 POST 请求（HTTP），K-V形式
	 * 
	 * @param url
	 * @return
	 */
	public static String doPost(String url, String data) {
		if (StringUtils.isEmpty(url))
			return null;

		CloseableHttpClient httpClient = HttpClients.createDefault();
		String result = null;
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = null;

		try {
			httpPost.setConfig(requestConfig);

			StringEntity entiry = new StringEntity(data, "UTF-8");
			httpPost.setEntity(entiry);

			response = httpClient.execute(httpPost);

			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, "UTF-8");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * @Title: httpPostWithJSON
	 * @Description: 发送POST请求，参数为JSON(这里用一句话描述这个方法的作用)
	 * @param url
	 * @param params
	 * @return
	 * @return String    返回类型
	 */
	/*public static String httpPostWithJSON(String url, JSONObject params) {
		String result = null;
		CloseableHttpClient client = HttpClients.createDefault();
		HttpResponse resp = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			StringEntity entity = new StringEntity(params.toString(), "utf-8");
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			httpPost.setEntity(entity);
			resp = client.execute(httpPost);
			
			 * if (resp.getStatusLine().getStatusCode() == 200) { HttpEntity
			 * httpEntity = resp.getEntity(); result =
			 * EntityUtils.toString(httpEntity, "utf-8"); }
			 
			HttpEntity httpEntity = resp.getEntity();
			result = EntityUtils.toString(httpEntity, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (resp != null) {
				try {
					EntityUtils.consume(resp.getEntity());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
*/
	
	
}
