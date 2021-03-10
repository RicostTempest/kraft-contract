package com.windsoft.kraft.contract.server.file.utils;

import com.alibaba.fastjson.JSONObject;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.UUID;

public class DFSFileUtils {

    public static final String GROUP="/group1";

    /**
     * 上传内容，HttpClient方式
     * @param file
     * @param path
     * @return
     */
    public static String upload(MultipartFile file, String path, String res){
        Map jsonToMap = null;
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            CloseableHttpResponse httpResponse = null;
            StringBuffer filename = new StringBuffer(UUID.randomUUID().toString());
            String originalFilename = file.getOriginalFilename();
            filename.append(originalFilename.substring(originalFilename.lastIndexOf('.')));
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(200000)
                    .setSocketTimeout(2000000)
                    .build();
            HttpPost httpPost = new HttpPost(path);
            httpPost.setConfig(requestConfig);
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create()
                    .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                    .setCharset(Charset.forName("UTF-8"))
                    .addTextBody("output", "json")
                    .addTextBody("path", res)
                    .addTextBody("filename", filename.toString())
                    .addBinaryBody("file", file.getInputStream(),
                            ContentType.DEFAULT_BINARY, file.getOriginalFilename());
            httpPost.setEntity(multipartEntityBuilder.build());
            httpResponse = httpClient.execute(httpPost);

            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                String respStr = EntityUtils.toString(httpResponse.getEntity());
                jsonToMap =  JSONObject.parseObject(respStr);
            }

            httpClient.close();
            httpResponse.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonToMap.get("src").toString();
    }

    /**
     * 文件上传 OkHttp3
     * @param file
     * @param host 服务器地址
     * @param path 服务器内存储路径 对应参数：path
     * @return
     */
    public static String uploadFile(MultipartFile file, String host, String path) {
        JSONObject jsonToMap = null;
        try {
            OkHttpClient httpClient = new OkHttpClient();
            StringBuffer filename = new StringBuffer(UUID.randomUUID().toString());
            String originalFilename = file.getOriginalFilename();
            filename.append(originalFilename.substring(originalFilename.lastIndexOf('.')));
            MultipartBody multipartBody = new MultipartBody.Builder().
                    setType(MultipartBody.FORM)
                    .addFormDataPart("file", file.getOriginalFilename(),
                            okhttp3.RequestBody.create(MediaType.parse("multipart/form-data;charset=utf-8"),
                                    file.getBytes()))
                    .addFormDataPart("output", "json")
                    .addFormDataPart("filename", filename.toString())
                    .addFormDataPart("path", path)
                    .build();

            Request request = new Request.Builder()
                    .url(host + GROUP + File.separator + "upload")
                    .post(multipartBody)
                    .build();

            String respStr = httpClient.newCall(request).execute().body().string();
            jsonToMap =  JSONObject.parseObject(respStr);
            return jsonToMap.get("path").toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }


    public static boolean deleteFile(String host, String path) {
        try {
            OkHttpClient httpClient = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(host + GROUP + File.separator + "delete?path=" +path)
                    .get()
                    .build();

            String respStr = httpClient.newCall(request).execute().body().string();
            JSONObject jsonToMap = JSONObject.parseObject(respStr);
            String status = jsonToMap.getString("status");
            if ("ok".equals(status)) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
