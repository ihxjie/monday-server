package com.ihxjie.mondayserver.api.common;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @author xjie
 * @date 2021/4/15 21:36
 */
@Service
public class AliyunOss {

    String prefix = "https://monday-antd.oss-cn-beijing.aliyuncs.com/";

    // Endpoint以杭州为例，其它Region请按实际情况填写。
    String endpoint = "https://oss-cn-beijing.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
    String accessKeyId = "LTAI5tDQLBemTKmyNWFpfTCK";
    String accessKeySecret = "qEJS8MzbKsmTd7lqR4I75RLEQYR0AR";
    String bucketName = "monday-antd";
    // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
    String objectName = "monday/";

    public Map<String, String> upload(MultipartFile file) throws Exception {
        Map<String, String> res = new HashMap<>();
        String filename = UUID.randomUUID().toString();
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 获取文件后缀
        String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf("."));
        ossClient.putObject(bucketName, objectName + filename + suffix, file.getInputStream());
        // 关闭OSSClient。
        ossClient.shutdown();
        res.put("path", prefix + objectName + filename + suffix);
        res.put("filename", filename + suffix);
        return res;
    }

}
