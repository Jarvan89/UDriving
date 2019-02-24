package com.udriving.drivingapi.http.controller;

import com.udriving.drivingapi.http.HttpCode;

import com.udriving.drivingapi.entity.dao.activiti.ActivityForDetail;
import com.udriving.drivingapi.http.pojo.BaseResponse;
import com.udriving.drivingapi.http.pojo.activity.HttpModifyActivity;
import com.udriving.drivingapi.http.pojo.activity.ReqActivity;
import com.udriving.drivingapi.service.UDActivityService;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


/**
 * 活动相关接口
 */
@RestController
@Log4j2
@Api(tags = "活动管理", description = "ActivityController")
public class ActivityController {
    @Autowired
    UDActivityService udActivityService;
    /**
     * 群二维码存储路径
     */
//    @Resource
    private String flockQrCodeStoragePath = "flockQrCode/";
    /**
     * 系统域名
     */
//    @Resource
    private String systemDomain = "https://liuhao.space/";

    /**
     * 创建一个新的活动
     * 异常统一捕获
     *
     * @return 活动创建操作状态
     */
    @RequestMapping(value = "/api/activity/create", method = RequestMethod.POST)
    public synchronized BaseResponse createActivity(@Validated @RequestBody ReqActivity reqActivity) throws InvocationTargetException, IllegalAccessException {
        udActivityService.createActivity(reqActivity);
        return new BaseResponse();
    }

    /**
     * 获取指定id的活动详情
     *
     * @return 接口返回结构
     */
    @RequestMapping(value = "/apt/activiti/get", method = RequestMethod.GET)
    public BaseResponse getActivityDetailById(@Validated @RequestParam("id") long id) throws InvocationTargetException, IllegalAccessException {
        //接口返回
        ActivityForDetail activityForDetail = udActivityService.selectActivityById(id);
        return new BaseResponse(activityForDetail);
    }

    /**
     * 查询接受报名的所有活动
     *
     * @return 活动列表
     */
    @RequestMapping(value = "/api/activity/list", method = RequestMethod.GET)
    public BaseResponse queryAllCanApplyActivity(@RequestParam("offset") int offset, @RequestParam("num") byte num) throws InvocationTargetException, IllegalAccessException {
        List<ReqActivity> lists = udActivityService.selectActivityList(offset, num);
        if (lists == null || lists.size() == 0) {
            return new BaseResponse(HttpCode.ACTIVITY_NOT_FIND);
        }
        return new BaseResponse(lists);
    }


    /**
     * 修改活动
     *
     * @return 接口返回结构
     */
    @RequestMapping(value = "/moidfyActivity", method = RequestMethod.POST)
    public BaseResponse moidfyActivity(@RequestBody HttpModifyActivity httpModifyActivity) throws InvocationTargetException, IllegalAccessException {
        return new BaseResponse(udActivityService.modifyActivity(httpModifyActivity));
    }


//    /**
//     * 上传活动小群群二维码
//     *
//     * @return 活动小群二维码信息数据结构
//     */
//    @RequestMapping(value = "/uploadFlockQrCode", method = RequestMethod.POST)
//    public BaseResponse uploadFlockQrCode(@RequestBody UploadFlockQrCodeRequestParameter uploadFlockQrCodeRequestParameter) {
//        //接口返回
//        BaseResponse baseResponse = new BaseResponse();
//        //Base64解码器
//        BASE64Decoder decoder = new BASE64Decoder();
//        //文件输出流
//        FileOutputStream write = null;
//        //二维码文件名
//        String qrCodeFileName = null;
////        try {
//        qrCodeFileName = DigestUtils.md5Hex(String.valueOf(System.currentTimeMillis()));
////            write = new FileOutputStream(new File(flockQrCodeStoragePath + qrCodeFileName));
////            byte[] decoderBytes = decoder.decodeBuffer(uploadFlockQrCodeRequestParameter.getQrCodeBase64Code());
////            write.write(decoderBytes);
//
////        } catch (IOException e) {
////        } finally {
////            if (write != null) {
////                try {
////                    write.close();
////                } catch (IOException e) {
////                    baseResponse.setCode(BaseResponse.ERROR);
////                    return baseResponse;
////                }
////            }
//
////        }
//        UDActivity UDActivity = activityRepository.getOne(uploadFlockQrCodeRequestParameter.getAcitivityId());
//        UDActivity.setWeChatFlockQrCode(qrCodeFileName);
//        activityRepository.save(UDActivity);
//        // TODO: 2018/12/22 后续需要重新梳理图片路径的拼接问题
//        baseResponse.setData(new UploadImageResponse(systemDomain + flockQrCodeStoragePath + qrCodeFileName));
//        return baseResponse;
//    }
//
//    /**
//     * 上传活动介绍图片
//     *
//     * @return 活动小群二维码信息数据结构
//     */
//    @RequestMapping(value = "/uploadIntroducePicture", method = RequestMethod.POST)
//    public BaseResponse uploadIntroducePicture(@RequestBody UploadFlockQrCodeRequestParameter uploadFlockQrCodeRequestParameter) {
//        //接口返回
//        BaseResponse baseResponse = new BaseResponse();
//        //Base64解码器
//        BASE64Decoder decoder = new BASE64Decoder();
//        //文件输出流
//        FileOutputStream write = null;
//        //二维码文件名
//        String qrCodeFileName = null;
////        try {
//        qrCodeFileName = DigestUtils.md5Hex(String.valueOf(System.currentTimeMillis()));
////            write = new FileOutputStream(new File(flockQrCodeStoragePath + qrCodeFileName));
////            byte[] decoderBytes = decoder.decodeBuffer(uploadFlockQrCodeRequestParameter.getQrCodeBase64Code());
////            write.write(decoderBytes);
//
////        } catch (IOException e) {
////        } finally {
////            if (write != null) {
////                try {
////                    write.close();
////                } catch (IOException e) {
////                    baseResponse.setCode(BaseResponse.ERROR);
////                    return baseResponse;
////                }
////            }
//
////        }
//        UDActivity UDActivity = activityRepository.getOne(uploadFlockQrCodeRequestParameter.getAcitivityId());
//        if (UDActivity == null) {
//            baseResponse.setCode(ACTIVITY_NOT_FIND);
//            return baseResponse;
//        }
//        List<String> introducePicture = UDActivity.getIntroducePicture();
//        if (introducePicture == null) {
//            baseResponse.setCode(SAVE_FAIL);
//            return baseResponse;
//        }
//
//        introducePicture.add(qrCodeFileName);
//        activityRepository.save(UDActivity);
//        // TODO: 2018/12/22 后续需要重新梳理图片路径的拼接问题
//        baseResponse.setData(new UploadImageResponse(systemDomain + flockQrCodeStoragePath + qrCodeFileName));
//        return baseResponse;
//    }
//
//
//    /**
//     * 获取指定用户创建的活动
//     *
//     * @return 接口返回结构
//     */
//    @RequestMapping(value = "/getActivityListByCreateId", method = RequestMethod.GET)
//    public BaseResponse getActivityListByCreateId(@RequestParam("startId") int startId, @RequestParam("createUserId") int createUserId, @RequestParam("dataSize") byte dataSize) {
//        //接口返回
//        BaseResponse baseResponse = new BaseResponse();
//        List<UDActivity> UDActivityList = null;
//        if (startId == 0) {
//            UDActivityList = activityRepository.findActivityByCreateUserId(createUserId, dataSize);
//        } else {
//            UDActivityList = activityRepository.findActivityByCreateUserId(startId, createUserId, dataSize);
//        }
//        if (UDActivityList == null) {
//            baseResponse.setCode(ACTIVITY_NOT_FIND);
//            return baseResponse;
//        }
//        baseResponse.setData(new ActivityListResponse(UDActivityList));
//        return baseResponse;
//    }
//
//
//    /**
//     * 报名指定活动
//     *
//     * @return 操作结果
//     */
//    @RequestMapping(value = "/applyActivityById", method = RequestMethod.GET)
//    public BaseResponse applyActivityById(@RequestParam("id") long id, @RequestParam("currentUserId") long currentUserId) {
//        //接口返回
//        BaseResponse baseResponse = new BaseResponse();
//        Optional<UDActivity> operation = activityRepository.findById(id);
//        if (operation == null) {
//            baseResponse.setCode(ACTIVITY_NOT_FIND);
//            return baseResponse;
//        }
//        UDActivity UDActivity = operation.get();
//        if (UDActivity == null) {
//            baseResponse.setCode(ACTIVITY_NOT_FIND);
//            return baseResponse;
//        }
//        List<String> memberIdList = UDActivity.getMemberIdList();
//        if (memberIdList == null) {
//            memberIdList = new LinkedList<>();
//        }
//        memberIdList.add(String.valueOf(currentUserId));
//        UDActivity.setMemberIdList(memberIdList);
//        UDActivity = activityRepository.save(UDActivity);
//        if (UDActivity == null) {
//            baseResponse.setCode(SAVE_FAIL);
//            return baseResponse;
//        }
//        baseResponse.setData(new MoidfyActivityResponse());
//        return baseResponse;
//    }


}
