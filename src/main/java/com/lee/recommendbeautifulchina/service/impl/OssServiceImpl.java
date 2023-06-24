package com.lee.recommendbeautifulchina.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.lee.recommendbeautifulchina.service.OssService;
import com.lee.recommendbeautifulchina.util.ConstantPropertiesUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @ClassName OssServiceImpl
 * @Description 阿里云 Oss 服务层
 * @Author lee
 * @Date 2023/1/19 15:10
 * @Version 1.0
 */
@Service
public class OssServiceImpl implements OssService {
    /**
     * 上传用户头像
     *
     * @param file
     * @return
     */
    @Override
    public String uploadFile(MultipartFile file, String folder) {

        // 工具类获取值
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try {
            // 创建OSS实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            // 获取文件名称
            String fileName = file.getOriginalFilename();

            // 1 在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            // yuy76t5rew01.jpg
            fileName = uuid+fileName;

            // 拼接
            //  avatar/ewtqr313401.jpg
            fileName = folder+"/"+fileName;

            // 调用oss方法实现上传
            // 第一个参数  Bucket名称
            // 第二个参数  上传到oss文件路径和文件名称   aa/bb/1.jpg
            // 第三个参数  上传文件输入流
            ossClient.putObject(bucketName,fileName,inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();

            //  https://study-helper-bucket.oss-cn-beijing.aliyuncs.com/01.jpg
            String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;

        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除文件
     * @param filePath  -> 删除文件路径举例为 user/avatar/4558afc4f306403f8cd27bd61ca421f51425192535736.jpg
     * @return
     */
    @Override
    public String delFile(String filePath) {

        // 工具类获取值
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 删除文件
            ossClient.deleteObject(bucketName, filePath);
        } catch (Exception e) {
            return "删除失败";
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return "删除成功";
    }
}
