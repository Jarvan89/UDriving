package com.udriving.drivingapi.controller;

import com.udriving.drivingapi.controller.request.CreateActivityRequestParameter;
import com.udriving.drivingapi.controller.request.MoidfyActivityRequestParameter;
import com.udriving.drivingapi.controller.request.UploadFlockQrCodeRequestParameter;
import com.udriving.drivingapi.controller.response.*;
import com.udriving.drivingapi.entity.activity.Activity;
import com.udriving.drivingapi.entity.activity.ActivityForDetail;
import com.udriving.drivingapi.entity.activity.ActivityRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.util.Optional;

import static com.udriving.drivingapi.controller.response.ResponseConstant.*;
import static com.udriving.drivingapi.entity.activity.ActivitiStatusConstant.*;

/**
 * 活动相关接口
 */
@RestController
@Log4j2
public class ActivityController {
    @Autowired
    private ActivityRepository activityRepository;
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
     *
     * @return 活动创建操作状态
     */
    @RequestMapping(value = "/createActivity", method = RequestMethod.POST)
    public Response createActivity(@RequestBody CreateActivityRequestParameter createActivityRequestParameter) {
        //接口返回
        Response response = new Response();
        if (createActivityRequestParameter == null) {
            response.setCode(ERROR);
            return response;
        }
        Activity activity = new Activity();
        activity.setCreateUserId(createActivityRequestParameter.getCreateUserId());
        activity.setCreateUserName(createActivityRequestParameter.getCreateNikeName());
        activity.setTitle(createActivityRequestParameter.getTitle());
        activity.setIntroduce(createActivityRequestParameter.getIntroduce());
        activity.setDepartAddressInfo(createActivityRequestParameter.getDepartAddressInfo());
        activity.setDestinationAddressInfo(createActivityRequestParameter.getDestinationAddressInfo());
        activity.setEstimateCost(createActivityRequestParameter.getEstimateCost());
        activity.setIntroducePicture(createActivityRequestParameter.getIntroducePicture());
        activity.setDepartTimestamp(createActivityRequestParameter.getDepartTimestamp());
        activity.setBackTimestamp(createActivityRequestParameter.getBackTimestamp());
        activity.setStatus(Activity.CREATE);
        activity.setCreateTimestamp(System.currentTimeMillis());
        activity = activityRepository.save(activity);
        //存储失败
        if (activity == null) {
            response.setCode(SAVE_FAIL);
        } else {
            response.setData(new CreateActivityResponse(activity.getId()));
        }
        return response;
    }

    /**
     * 查询接受报名的所有活动
     *
     * @return 活动列表
     */
    @RequestMapping(value = "/getAllCanApplyActivity", method = RequestMethod.GET)
    public Response queryAllCanApplyActivity(@RequestParam("offset") int offset, @RequestParam("dataSize") byte dataSize) {
        //接口返回
        Response response = new Response();
        if (offset == 0) {
            response.setData(new ActivityListResponse(activityRepository.queryAllCanApplyActivity(CREATE, dataSize)));
            return response;
        } else {
            response.setData(new ActivityListResponse(activityRepository.queryAllCanApplyActivity(offset, CREATE, dataSize)));
            return response;
        }

    }

    /**
     * 获取指定id的活动详情
     *
     * @return 接口返回结构
     */
    @RequestMapping(value = "/getActivityDetailById", method = RequestMethod.GET)
    public Response getActivityDetailById(@RequestParam("id") int id) {
        //接口返回
        Response response = new Response();
        Optional<Activity> optional = activityRepository.findById(id);
        if (optional == null) {
            response.setCode(ACTIVITY_NOT_FIND);
            return response;
        }
        Activity activity = optional.get();
        if (activity == null) {
            response.setCode(ACTIVITY_NOT_FIND);
            return response;
        }
        response.setData(new ActivityDetailResponse(ActivityForDetail.convert(activity)));
        return response;
    }

    /**
     * 获取指定id的活动详情
     *
     * @return 接口返回结构
     */
    @RequestMapping(value = "/moidfyActivity", method = RequestMethod.POST)
    public Response getActivityDetailById(@RequestBody MoidfyActivityRequestParameter moidfyActivityForRequestParameter) {
        //接口返回
        Response response = new Response();
        Optional<Activity> optional = activityRepository.findById(moidfyActivityForRequestParameter.getId());
        if (optional == null) {
            response.setCode(ACTIVITY_NOT_FIND);
            return response;
        }
        Activity activity = optional.get();
        if (activity == null) {
            response.setCode(ACTIVITY_NOT_FIND);
            return response;
        }
        activity.setTitle(moidfyActivityForRequestParameter.getTitle());
        activity.setEstimateCost(moidfyActivityForRequestParameter.getEstimateCost());
        activity.setCreateUserName(moidfyActivityForRequestParameter.getCreateUserName());
        activity.setIntroducePicture(moidfyActivityForRequestParameter.getIntroducePicture());
        activity.setDepartAddressInfo(moidfyActivityForRequestParameter.getDepartAddressInfo());
        activity.setDestinationAddressInfo(moidfyActivityForRequestParameter.getDestinationAddressInfo());
        activity.setDepartTimestamp(moidfyActivityForRequestParameter.getDepartTimestamp());
        activity.setBackTimestamp(moidfyActivityForRequestParameter.getBackTimestamp());
        // TODO: 2019/1/5 后续实现成员id提取和修改逻辑
//        activity.setMemberIdList(moidfyActivityForRequestParameter.getMemberIdList());
        activity.setWeChatFlockQrCode(moidfyActivityForRequestParameter.getWeChatFlockQrCode());
        activity.setCarNumber(moidfyActivityForRequestParameter.getCarNumber());
        activity.setPathImage(moidfyActivityForRequestParameter.getPathImage());
        activity.setNotes(moidfyActivityForRequestParameter.getNotes());
        activity = activityRepository.save(activity);
        if (activity == null) {
            response.setCode(SAVE_FAIL);
            return response;
        }
        response.setData(new MoidfyActivityResponse());
        return response;
    }


    /**
     * 上传活动小群群二维码
     *
     * @return 活动小群二维码信息数据结构
     */
    @RequestMapping(value = "/uploadFlockQrCode", method = RequestMethod.POST)
    public Response uploadFlockQrCode(@RequestBody UploadFlockQrCodeRequestParameter uploadFlockQrCodeRequestParameter) {
        //接口返回
        Response response = new Response();
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
        Activity activity = activityRepository.getOne(uploadFlockQrCodeRequestParameter.getAcitivityId());
        activity.setWeChatFlockQrCode(qrCodeFileName);
        activityRepository.save(activity);
        UploadFlockQrCodeResponse uploadFlockQrCodeResponse = new UploadFlockQrCodeResponse();
        uploadFlockQrCodeResponse.setFileName(qrCodeFileName);
        // TODO: 2018/12/22 后续需要重新梳理图片路径的拼接问题
        uploadFlockQrCodeResponse.setFileUrl(systemDomain + flockQrCodeStoragePath + qrCodeFileName);
        response.setData(uploadFlockQrCodeResponse);
        return response;
    }


    /**
     * 删除指定活动
     *
     * @return 操作结果
     */
    @RequestMapping(value = "/deleteActivityById", method = RequestMethod.POST)
    public Response deleteActivityById(int id) {
        //接口返回
        Response response = new Response();
        short operationCode = modifiActivityStatusById(id, DELETE);
        if (operationCode != SUCCEED) {
            response.setCode(operationCode);
        }
        return response;
    }

    /**
     * 审批指定活动
     *
     * @return 操作结果
     */
    @RequestMapping(value = "/approvalActivityById", method = RequestMethod.POST)
    public Response approvalActivityById(int id) {
        //接口返回
        Response response = new Response();
        short operationCode = modifiActivityStatusById(id, RELEASE);
        if (operationCode != SUCCEED) {
            response.setCode(operationCode);
        }
        return response;
    }


    /**
     * 修改活动状态
     *
     * @param id     活动id
     * @param status 要修改的状态
     * @return 操作结果状态码
     */
    private short modifiActivityStatusById(int id, byte status) {
        Optional<Activity> optional = activityRepository.findById(id);
        Activity activity = optional.get();
        if (activity == null) {
            return ACTIVITY_NOT_FIND;
        }
        activity.setStatus(status);
        activity = activityRepository.save(activity);
        if (activity == null) {
            return SAVE_FAIL;
        }
        return SUCCEED;
    }

}
