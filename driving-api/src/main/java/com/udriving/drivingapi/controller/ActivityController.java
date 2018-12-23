package com.udriving.drivingapi.controller;

import com.udriving.drivingapi.controller.request.CreateActivityRequestParameter;
import com.udriving.drivingapi.controller.request.UploadFlockQrCodeRequestParameter;
import com.udriving.drivingapi.controller.response.Response;
import com.udriving.drivingapi.controller.response.UploadFlockQrCodeResponse;
import com.udriving.drivingapi.entity.activity.ActivityRepository;
import com.udriving.drivingapi.entity.activity.UDActiviti;
import com.udriving.drivingapi.util.JacksonUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.IOException;

import static com.udriving.drivingapi.entity.activity.ActivitiStatusConstant.*;

/**
 * 活动相关接口
 */
@RestController
public class ActivityController {
    @Autowired
    private ActivityRepository activityRepository;
    /**
     * 群二维码存储路径
     */
//    @Resource
    private String flockQrCodeStoragePath;
    /**
     * 系统域名
     */
//    @Resource
    private String systemDomain;

    /**
     * 创建一个新的活动
     *
     * @return 活动创建操作状态
     */
    @RequestMapping(value = "/createActivity", method = RequestMethod.POST)
    public Response createActivity(String body) {
        System.out.println(body);
        //接口返回
        Response response = new Response();
        CreateActivityRequestParameter createActivityRequestParameter = null;
        try {
            createActivityRequestParameter = JacksonUtil.json2Bean(body, CreateActivityRequestParameter.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (createActivityRequestParameter == null) {
            response.setCode(Response.ERROR);
        }

        UDActiviti udActiviti = new UDActiviti();
        udActiviti.setCreateUserId(createActivityRequestParameter.getCreateUserId());
        udActiviti.setName(createActivityRequestParameter.getName());
        udActiviti.setIntroduce(createActivityRequestParameter.getIntroduce());
        udActiviti.setDepartLatitude(createActivityRequestParameter.getDepartLatitude());
        udActiviti.setDepartLongitude(createActivityRequestParameter.getDepartLongitude());
        udActiviti.setDestinationLatitude(createActivityRequestParameter.getDestinationLatitude());
        udActiviti.setDestinationLongitude(createActivityRequestParameter.getDestinationLongitude());
        udActiviti.setEstimateCost(createActivityRequestParameter.getEstimateCost());
        udActiviti.setIntroducePicture(createActivityRequestParameter.getIntroducePicture());
        udActiviti.setDepartTimestamp(createActivityRequestParameter.getDepartTimestamp());
        udActiviti.setBackTimestamp(createActivityRequestParameter.getBackTimestamp());
        udActiviti.setWeChatFlockQrCode(createActivityRequestParameter.getWeChatFlockQrCode());
        udActiviti.setStatus(UDActiviti.create);
        activityRepository.save(udActiviti);
        return response;
    }

    /**
     * 查询所有活动
     *
     * @return 活动列表
     */
    @RequestMapping(value = "/queryAllActivity", method = RequestMethod.GET)
    public Response queryAllActivity() {
        //接口返回
        Response response = new Response();
        response.setData(activityRepository.findAll());
        return response;
    }

    /**
     * 查询接受报名的所有活动
     *
     * @return 活动列表
     */
    @RequestMapping(value = "/queryAllCanApplyActivity", method = RequestMethod.GET)
    public Response queryAllCanApplyActivity() {
        //接口返回
        Response response = new Response();
        response.setData(activityRepository.findByStatus(release));
        return response;
    }

    /**
     * 查询已经完成的所有活动
     *
     * @return 活动列表
     */
    @RequestMapping(value = "/queryAllDoneActivity", method = RequestMethod.GET)
    public Response queryAllDoneActivity() {
        //接口返回
        Response response = new Response();
        response.setData(activityRepository.findByStatus(finish));
        return response;
    }

    /**
     * 查询被删除的所有活动
     *
     * @return 活动列表
     */
    @RequestMapping(value = "/queryAllDeleteActivity", method = RequestMethod.GET)
    public Response queryAllDeleteActivity() {
        //接口返回
        Response response = new Response();
        response.setData(activityRepository.findByStatus(delete));
        return response;
    }

    /**
     * 上传活动小群群二维码
     *
     * @return 活动小群二维码信息数据结构
     */
    @RequestMapping(value = "/uploadFlockQrCode", method = RequestMethod.POST)
    public Response uploadFlockQrCode(String body) {
        //接口返回
        Response response = new Response();
        //上传群二维码请求参数
        UploadFlockQrCodeRequestParameter uploadFlockQrCodeRequestParameter = null;
        try {
            uploadFlockQrCodeRequestParameter = JacksonUtil.json2Bean(body, UploadFlockQrCodeRequestParameter.class);
        } catch (IOException e) {
            e.printStackTrace();
            response.setCode(Response.ERROR);
            return response;
        }
        //Base64解码器
        BASE64Decoder decoder = new BASE64Decoder();
        //文件输出流
        FileOutputStream write = null;
        //二维码文件名
        String qrCodeFileName = null;
//        try {
        qrCodeFileName = DigestUtils.md5Hex(String.valueOf(System.currentTimeMillis()));
//            write = new FileOutputStream(new File(flockQrCodeStoragePath + qrCodeFileName));
//            byte[] decoderBytes = decoder.decodeBuffer(uploadFlockQrCodeRequestParameter.getQrCodeBase64Code());
//            write.write(decoderBytes);

//        } catch (IOException e) {
//        } finally {
//            if (write != null) {
//                try {
//                    write.close();
//                } catch (IOException e) {
//                    response.setCode(Response.ERROR);
//                    return response;
//                }
//            }

//        }
        UDActiviti udActiviti = activityRepository.getOne(uploadFlockQrCodeRequestParameter.getAcitivityId());
        udActiviti.setWeChatFlockQrCode(qrCodeFileName);
        activityRepository.save(udActiviti);
        UploadFlockQrCodeResponse uploadFlockQrCodeResponse = new UploadFlockQrCodeResponse();
        uploadFlockQrCodeResponse.setFileName(qrCodeFileName);
        // TODO: 2018/12/22 后续需要重新梳理图片路径的拼接问题
        uploadFlockQrCodeResponse.setFileUrl(systemDomain + flockQrCodeStoragePath + qrCodeFileName);
        response.setData(uploadFlockQrCodeResponse);
        return response;
    }

}
