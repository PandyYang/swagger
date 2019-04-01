package com.leyou.upload.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.common.enums.ExceptionEnums;
import com.leyou.common.exception.LyException;
import com.leyou.upload.config.UploadProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


/**
 * @Author: Pandy
 * @Date: 2019/3/6 16:50
 * @Version 1.0
 */
@Service
@Slf4j
@EnableConfigurationProperties({UploadProperties.class})
public class UploadService {

    @Autowired
    private UploadProperties prop;

    @Autowired
    FastFileStorageClient storageClient;

    //private static final List<String> ALLOW_TYPES = Arrays.asList("image/jpeg","image/png","image/bmp");
    public String uploadImage(MultipartFile file) {
        try {
            //校验文件类型
            String contentType = file.getContentType();
            if (!prop.getAllowTypes().contains(contentType)){
                throw new LyException(ExceptionEnums.INVALID_FILE_TYPE);
            }
            //校验文件内容
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image==null){
                throw new LyException(ExceptionEnums.INVALID_FILE_TYPE);
            }
            //String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(",")+1);
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(),".");
            StorePath storePath = storageClient.uploadFile(file.getInputStream(),file.getSize(),
                    extension,null);

            /*//准备目标路径
            //this.getClass().getClassLoader().getResource("").getFile()
            File dest = new File("D:\\IdeaWorkSpace\\乐优商城上传图片库",file.getOriginalFilename());
            //保存文件至本地

            file.transferTo(dest);
            //返回路径*/
            return prop.getBaseUrl()+storePath.getFullPath();
        } catch (IOException e) {
            //上传失败 记录日志 返回结果
            //log.error(xxx,e);
            throw new LyException(ExceptionEnums.UPLOAD_ERROR);
        }
    }
}
