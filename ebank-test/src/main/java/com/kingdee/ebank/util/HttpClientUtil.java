package com.kingdee.ebank.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
 
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
 
/**
 * 封装了一些采用HttpClient发�?HTTP请求的方�?
 * @see 本工具所采用的是�?��的HttpComponents-Client-4.2.1
 * @see 关于本工具类中的�?��解释说明,可参考下方列出的我的三篇文章
 * @see http://blog.csdn.net/jadyer/article/details/7615830
 * @see http://blog.csdn.net/jadyer/article/details/7615880
 * @see http://blog.csdn.net/jadyer/article/details/7802139
 * @create Feb 1, 2012 3:02:27 PM
 * @update Oct 8, 2012 3:48:55 PM
 * @author 玄玉<http://blog.csdn/net/jadyer>
 * @version v1.3
 * @history v1.0-->新建<code>sendGetRequest(String,String)</code>�?code>sendPostRequest(String,Map<String,String>,String,String)</code>方法
 * @history v1.1-->新增<code>sendPostSSLRequest(String,Map<String,String>,String,String)</code>方法,用于发�?HTTPS的POST请求
 * @history v1.2-->新增<code>sendPostRequest(String,String,boolean,String,String)</code>方法,用于发�?HTTP协议报文体为任意字符串的POST请求
 * @history v1.3-->新增<code>java.net.HttpURLConnection</code>实现�?code>sendPostRequestByJava()</code>方法
 */
public class HttpClientUtil {
    private HttpClientUtil(){}
     
    /**
     * 发�?HTTP_GET请求
     * @see 该方法会自动关闭连接,释放资源
     * @param requestURL    请求地址(含参�?
     * @param decodeCharset 解码字符�?解析响应数据时用�?其为null时默认采用UTF-8解码
     * @return 远程主机响应正文
     */
    public static String sendGetRequest(String reqURL, String decodeCharset){
        long responseLength = 0;       //响应长度
        String responseContent = null; //响应内容
        HttpClient httpClient = new DefaultHttpClient(); //创建默认的httpClient实例
        HttpGet httpGet = new HttpGet(reqURL);           //创建org.apache.http.client.methods.HttpGet
        try{
            HttpResponse response = httpClient.execute(httpGet); //执行GET请求
            HttpEntity entity = response.getEntity();            //获取响应实体
            if(null != entity){
                responseLength = entity.getContentLength();
                responseContent = EntityUtils.toString(entity, decodeCharset==null ? "UTF-8" : decodeCharset);
                EntityUtils.consume(entity); //Consume response content
            }
            System.out.println("请求地址: " + httpGet.getURI());
            System.out.println("响应状�?: " + response.getStatusLine());
            System.out.println("响应长度: " + responseLength);
            System.out.println("响应内容: " + responseContent);
        }catch(ClientProtocolException e){
            e.printStackTrace();
        }catch(ParseException e){
        	e.printStackTrace();
        }catch(IOException e){
        	e.printStackTrace();
        }finally{
            httpClient.getConnectionManager().shutdown(); //关闭连接,释放资源
        }
        return responseContent;
    }
     
     
    /**
     * 发�?HTTP_POST请求
     * @see 该方法为<code>sendPostRequest(String,String,boolean,String,String)</code>的简化方�?
     * @see 该方法在对请求数据的编码和响应数据的解码�?�?��用的字符集均为UTF-8
     * @see �?code>isEncoder=true</code>�?其会自动�?code>sendData</code>中的[中文][|][ ]等特殊字符进�?code>URLEncoder.encode(string,"UTF-8")</code>
     * @param isEncoder 用于指明请求数据是否�?��UTF-8编码,true为需�?
     */
    public static String sendPostRequest(String reqURL, String sendData, boolean isEncoder){
        return sendPostRequest(reqURL, sendData, isEncoder, null, null);
    }
     
     
    /**
     * 发�?HTTP_POST请求
     * @see 该方法会自动关闭连接,释放资源
     * @see �?code>isEncoder=true</code>�?其会自动�?code>sendData</code>中的[中文][|][ ]等特殊字符进�?code>URLEncoder.encode(string,encodeCharset)</code>
     * @param reqURL        请求地址
     * @param sendData      请求参数,若有多个参数则应拼接成param11=value11¶m22=value22¶m33=value33的形式后,传入该参数中
     * @param isEncoder     请求数据是否�?��encodeCharset编码,true为需�?
     * @param encodeCharset 编码字符�?编码请求数据时用�?其为null时默认采用UTF-8解码
     * @param decodeCharset 解码字符�?解析响应数据时用�?其为null时默认采用UTF-8解码
     * @return 远程主机响应正文
     */
    public static String sendPostRequest(String reqURL, String sendData, boolean isEncoder, String encodeCharset, String decodeCharset){
        String responseContent = null;
        HttpClient httpClient = new DefaultHttpClient();
         
        HttpPost httpPost = new HttpPost(reqURL);
        //httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8");
        httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
        try{
            if(isEncoder){
                List<NameValuePair> formParams = new ArrayList<NameValuePair>();
                for(String str : sendData.split("&")){
                    formParams.add(new BasicNameValuePair(str.substring(0,str.indexOf("=")), str.substring(str.indexOf("=")+1)));
                }
                httpPost.setEntity(new StringEntity(URLEncodedUtils.format(formParams, encodeCharset==null ? "UTF-8" : encodeCharset)));
            }else{
                httpPost.setEntity(new StringEntity(sendData));
            }
             
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                responseContent = EntityUtils.toString(entity, decodeCharset==null ? "UTF-8" : decodeCharset);
                EntityUtils.consume(entity);
            }
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
            httpClient.getConnectionManager().shutdown();
        }
        return responseContent;
    }
     
     
    /**
     * 发�?HTTP_POST请求
     * @see 该方法会自动关闭连接,释放资源
     * @see 该方法会自动�?code>params</code>中的[中文][|][ ]等特殊字符进�?code>URLEncoder.encode(string,encodeCharset)</code>
     * @param reqURL        请求地址
     * @param params        请求参数
     * @param encodeCharset 编码字符�?编码请求数据时用�?其为null时默认采用UTF-8解码
     * @param decodeCharset 解码字符�?解析响应数据时用�?其为null时默认采用UTF-8解码
     * @return 远程主机响应正文
     */
    public static String sendPostRequest(String reqURL, Map<String, Object> params, String encodeCharset, String decodeCharset) throws Exception{
        String responseContent = null;
        HttpClient httpClient = new DefaultHttpClient();
         
        HttpPost httpPost = new HttpPost(reqURL);
        List<NameValuePair> formParams = new ArrayList<NameValuePair>(); //创建参数队列
        for(Map.Entry<String,Object> entry : params.entrySet()){
            formParams.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
        }
        try{
            httpPost.setEntity(new UrlEncodedFormEntity(formParams, encodeCharset==null ? "UTF-8" : encodeCharset));
             
            HttpResponse response = httpClient.execute(httpPost);
            Header[] headers = response.getAllHeaders();
            for(int i=0;i<headers.length;i++){
            	System.out.println(headers[i]);
            }
            
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                responseContent = EntityUtils.toString(entity, decodeCharset==null ? "UTF-8" : decodeCharset);
                EntityUtils.consume(entity);
            }
        }finally{
            httpClient.getConnectionManager().shutdown();
        }
        return responseContent;
    }
     
     
    /**
     * 发�?HTTPS_POST请求
     * @see 该方法为<code>sendPostSSLRequest(String,Map<String,String>,String,String)</code>方法的简化方�?
     * @see 该方法在对请求数据的编码和响应数据的解码�?�?��用的字符集均为UTF-8
     * @see 该方法会自动�?code>params</code>中的[中文][|][ ]等特殊字符进�?code>URLEncoder.encode(string,"UTF-8")</code>
     */
    public static String sendPostSSLRequest(String reqURL, Map<String, Object> params){
        return sendPostSSLRequest(reqURL, params, null, null);
    }
     
     
    /**
     * 发�?HTTPS_POST请求
     * @see 该方法会自动关闭连接,释放资源
     * @see 该方法会自动�?code>params</code>中的[中文][|][ ]等特殊字符进�?code>URLEncoder.encode(string,encodeCharset)</code>
     * @param reqURL        请求地址
     * @param params        请求参数
     * @param encodeCharset 编码字符�?编码请求数据时用�?其为null时默认采用UTF-8解码
     * @param decodeCharset 解码字符�?解析响应数据时用�?其为null时默认采用UTF-8解码
     * @return 远程主机响应正文
     */
    public static String sendPostSSLRequest(String reqURL, Map<String, Object> params, String encodeCharset, String decodeCharset){
        String responseContent = "";
        HttpClient httpClient = new DefaultHttpClient();
        X509TrustManager xtm = new X509TrustManager(){
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
            public X509Certificate[] getAcceptedIssuers() {return null;}
        };
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, new TrustManager[]{xtm}, null);
            SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
            httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));
             
            HttpPost httpPost = new HttpPost(reqURL);
            List<NameValuePair> formParams = new ArrayList<NameValuePair>();
            for(Map.Entry<String,Object> entry : params.entrySet()){
                formParams.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(formParams, encodeCharset==null ? "UTF-8" : encodeCharset));
             
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                responseContent = EntityUtils.toString(entity, decodeCharset==null ? "UTF-8" : decodeCharset);
                EntityUtils.consume(entity);
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return responseContent;
    }
     
     
    /**
     * 发�?HTTP_POST请求
     * @see 若发送的<code>params</code>中含有中�?记得按照双方约定的字符集将中�?code>URLEncoder.encode(string,encodeCharset)</code>
     * @see 本方法默认的连接超时时间�?0�?默认的读取超时时间为30�?
     * @param reqURL 请求地址
     * @param params 发�?到远程主机的正文数据,其数据类型为<code>java.util.Map<String, String></code>
     * @return 远程主机响应正文`HTTP状�?�?�?code>"SUCCESS`200"</code><br>若�?信过程中发生异常则返�?Failed`HTTP状�?�?,�?code>"Failed`500"</code>
     */
    public static String sendPostRequestByJava(String reqURL, Map<String, Object> params){
        StringBuilder sendData = new StringBuilder();
        for(Map.Entry<String, Object> entry : params.entrySet()){
            sendData.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        if(sendData.length() > 0){
            sendData.setLength(sendData.length() - 1); //删除�?���?��&符号
        }
        return sendPostRequestByJava(reqURL, sendData.toString());
    }
     
     
    /**
     * 发�?HTTP_POST请求
     * @see 若发送的<code>sendData</code>中含有中�?记得按照双方约定的字符集将中�?code>URLEncoder.encode(string,encodeCharset)</code>
     * @see 本方法默认的连接超时时间�?0�?默认的读取超时时间为30�?
     * @param reqURL   请求地址
     * @param sendData 发�?到远程主机的正文数据
     * @return 远程主机响应正文`HTTP状�?�?�?code>"SUCCESS`200"</code><br>若�?信过程中发生异常则返�?Failed`HTTP状�?�?,�?code>"Failed`500"</code>
     */
    public static String sendPostRequestByJava(String reqURL, String sendData){
        HttpURLConnection httpURLConnection = null;
        OutputStream out = null; //�?
        InputStream in = null;   //�?
        int httpStatusCode = 0;  //远程主机响应的HTTP状�?�?
        try{
            URL sendUrl = new URL(reqURL);
            httpURLConnection = (HttpURLConnection)sendUrl.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);        //指示应用程序要将数据写入URL连接,其�?默认为false
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setConnectTimeout(30000); //30秒连接超�?
            httpURLConnection.setReadTimeout(30000);    //30秒读取超�?
             
            out = httpURLConnection.getOutputStream();
            out.write(sendData.toString().getBytes());
             
            //清空缓冲�?发�?数据
            out.flush();
             
            //获取HTTP状�?�?
            httpStatusCode = httpURLConnection.getResponseCode();
             
            //该方法只能获取到[HTTP/1.0 200 OK]中的[OK]
            //若对方响应的正文放在了返回报文的�?���?��,则该方法获取不到正文,而只能获取到[OK],稍显遗憾
            //respData = httpURLConnection.getResponseMessage();
             
//          //处理返回结果
//          BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
//          String row = null;
//          String respData = "";
//          if((row=br.readLine()) != null){ //readLine()方法在读到换行[\n]或回车[\r]�?即认为该行已终止
//              respData = row;              //HTTP协议POST方式的最后一行数据为正文数据
//          }
//          br.close();
             
            in = httpURLConnection.getInputStream();            
            byte[] byteDatas = new byte[in.available()];
            in.read(byteDatas);
            return new String(byteDatas) + "`" + httpStatusCode;
        }catch(Exception e){
        	e.printStackTrace();
            return "Failed`" + httpStatusCode;
        }finally{
            if(out != null){
                try{
                    out.close();
                }catch (Exception e){
                	e.printStackTrace();
                }
            }
            if(in != null){
                try{
                    in.close();
                }catch(Exception e){
                	e.printStackTrace();
                }
            }
            if(httpURLConnection != null){
                httpURLConnection.disconnect();
                httpURLConnection = null;
            }
        }
    }
}