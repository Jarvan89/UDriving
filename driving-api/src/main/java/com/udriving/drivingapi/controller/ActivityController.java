package com.udriving.drivingapi.controller;

import com.udriving.drivingapi.controller.request.CreateActivityRequestParameter;
import com.udriving.drivingapi.controller.request.MoidfyActivityRequestParameter;
import com.udriving.drivingapi.controller.request.RefuseApprovalActivityRequestParameter;
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
import java.util.*;

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
    public synchronized Response createActivity(@RequestBody CreateActivityRequestParameter createActivityRequestParameter) {
        //接口返回
        Response response = new Response();
        if (createActivityRequestParameter == null) {
            response.setCode(ERROR);
            return response;
        }
        Activity activity = new Activity();
        activity.setId(generateNewId());
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
     * 生成新活动id
     *
     * @return 新活动id
     */
    private long generateNewId() {
        //代表当前日期的日历类
        Calendar calendar = GregorianCalendar.getInstance();
        //用于将当前日期的年月日时分秒数字拼接成字符串的字符串缓冲区
        StringBuilder stringBuilder = new StringBuilder();
        //提取年
        stringBuilder.append(calendar.get(Calendar.YEAR));
        //提取月份值若月份不足2位补0
        if ((calendar.get(Calendar.MONTH) + 1) < 10) {
            stringBuilder.append(0);
        }
        stringBuilder.append((calendar.get(Calendar.MONTH) + 1));
        //提取日期值，若不足两位后面补0
        if ((calendar.get(Calendar.DAY_OF_MONTH)) < 10) {
            stringBuilder.append(0);
        }
        stringBuilder.append((calendar.get(Calendar.DAY_OF_MONTH)));
        //提取小时值，若不足两位后面补0
        if ((calendar.get(Calendar.HOUR_OF_DAY)) < 10) {
            stringBuilder.append(0);
        }
        stringBuilder.append((calendar.get(Calendar.HOUR_OF_DAY)));
        //提取分钟值，若不足两位后面补0
        if ((calendar.get(Calendar.MINUTE)) < 10) {
            stringBuilder.append(0);
        }
        stringBuilder.append((calendar.get(Calendar.MINUTE)));
        //id最大的活动
        Activity idMaxActivity = activityRepository.findIdMaxActivity();
        //新活动id
        long newActivityId = 0;
        //活动未空是生成一个新的流水号
        if (idMaxActivity == null) {
            newActivityId = Long.valueOf(stringBuilder.append("0001").toString());
            //未找到指定活动时说明新id有效，返回给调用层
            if (checkActivityInexistence(newActivityId)) {
                return newActivityId;
            }
            //未返回说id无效，递归调用，重新生成。
            return generateNewId();
        }
        //id最大的活动的活动id
        long id = idMaxActivity.getId();
        //计算出活动创建时间
        long lastActivityCreateTime = id / 10000;
        //当前时间数值表示
        long currentTime = Long.valueOf(stringBuilder.toString());
        //如果当前时间和id最大的活动创建日期不是一天则生成新的流水
        if (currentTime != lastActivityCreateTime) {
            newActivityId = Long.valueOf(stringBuilder.append("0001").toString());
            //未找到指定活动时说明新id有效，返回给调用层
            if (checkActivityInexistence(newActivityId)) {
                return newActivityId;
            }
            //未返回说id无效，递归调用，重新生成。
            return generateNewId();
        }
        //如果创建日期是同一天则取id最大的活动的流水号
        newActivityId = (id % 10000) + 1;
        //用于构造新活动id字符串表示形式的字符串缓冲区
        StringBuilder newActivityIdBuilder = new StringBuilder();
        newActivityIdBuilder.append(currentTime);
        //计算缺多少个0
        int zeroNumber = 4 - String.valueOf(newActivityId).length();
        //循环补0
        for (int i = 0; i < zeroNumber; i++) {
            newActivityIdBuilder.append("0");
        }
        newActivityId = Long.valueOf(newActivityIdBuilder.append(newActivityId).toString());
        //未找到指定活动时说明新id有效，返回给调用层
        if (checkActivityInexistence(newActivityId)) {
            return newActivityId;
        }
        //未返回说id无效，递归调用，重新生成。
        return generateNewId();
    }

    /**
     * 检查指定id的活动是否不存在
     *
     * @param id 活动id
     * @return true:不存在
     */
    private boolean checkActivityInexistence(long id) {
        Optional<Activity> optional = activityRepository.findById(id);
        if (optional != null) {
            return !optional.isPresent();
        }
        return true;
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
    public Response getActivityDetailById(@RequestParam("id") long id) {
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
     * 修改活动
     *
     * @return 接口返回结构
     */
    @RequestMapping(value = "/moidfyActivity", method = RequestMethod.POST)
    public Response moidfyActivity(@RequestBody MoidfyActivityRequestParameter moidfyActivityForRequestParameter) {
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
        activity.setDepartAddressInfo(moidfyActivityForRequestParameter.getDepartAddressInfo());
        activity.setDestinationAddressInfo(moidfyActivityForRequestParameter.getDestinationAddressInfo());
        activity.setDepartTimestamp(moidfyActivityForRequestParameter.getDepartTimestamp());
        activity.setBackTimestamp(moidfyActivityForRequestParameter.getBackTimestamp());
        // TODO: 2019/1/5 后续实现成员id提取和修改逻辑
//        activity.setMemberIdList(moidfyActivityForRequestParameter.getMemberIdList());
        activity.setCarNumber(moidfyActivityForRequestParameter.getCarNumber());
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
        // TODO: 2018/12/22 后续需要重新梳理图片路径的拼接问题
        response.setData(new UploadImageResponse(systemDomain + flockQrCodeStoragePath + qrCodeFileName));
        return response;
    }

    /**
     * 上传活动介绍图片
     *
     * @return 活动小群二维码信息数据结构
     */
    @RequestMapping(value = "/uploadIntroducePicture", method = RequestMethod.POST)
    public Response uploadIntroducePicture(@RequestBody UploadFlockQrCodeRequestParameter uploadFlockQrCodeRequestParameter) {
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
        if (activity == null) {
            response.setCode(ACTIVITY_NOT_FIND);
            return response;
        }
        List<String> introducePicture = activity.getIntroducePicture();
        if (introducePicture == null) {
            response.setCode(SAVE_FAIL);
            return response;
        }

        introducePicture.add(qrCodeFileName);
        activityRepository.save(activity);
        // TODO: 2018/12/22 后续需要重新梳理图片路径的拼接问题
        response.setData(new UploadImageResponse(systemDomain + flockQrCodeStoragePath + qrCodeFileName));
        return response;
    }


    /**
     * 获取指定id的活动详情
     *
     * @return 接口返回结构
     */
    @RequestMapping(value = "/getActivityListByCreateId", method = RequestMethod.GET)
    public Response getActivityListByCreateId(@RequestParam("startId") int startId, @RequestParam("createUserId") int createUserId, @RequestParam("dataSize") byte dataSize) {
        //接口返回
        Response response = new Response();
        List<Activity> activityList = null;
        if (startId == 0) {
            activityList = activityRepository.findActivityByCreateUserId(createUserId, dataSize);
        } else {
            activityList = activityRepository.findActivityByCreateUserId(startId, createUserId, dataSize);
        }
        if (activityList == null) {
            response.setCode(ACTIVITY_NOT_FIND);
            return response;
        }
        response.setData(new ActivityListResponse(activityList));
        return response;
    }

    /**
     * 获取指定id的活动详情
     *
     * @return 接口返回结构
     */
    @RequestMapping(value = "/getActivityListByNotApproval", method = RequestMethod.GET)
    public Response getActivityListByNotApproval(@RequestParam("startId") int startId, @RequestParam("dataSize") byte dataSize) {
        //接口返回
        Response response = new Response();
        List<Activity> activityList = null;
        if (startId == 0) {
            activityList = activityRepository.findLessStatusActivityList(RELEASE, dataSize);
        } else {
            activityList = activityRepository.findLessStatusActivityList(startId, RELEASE, dataSize);
        }
        if (activityList == null) {
            response.setCode(ACTIVITY_NOT_FIND);
            return response;
        }
        response.setData(new ActivityListResponse(activityList));
        return response;
    }

    /**
     * 审批指定活动
     *
     * @return 操作结果
     */
    @RequestMapping(value = "/approvalActivityById", method = RequestMethod.GET)
    public Response approvalActivityById(@RequestParam("id") int id) {
        //接口返回
        Response response = new Response();
        short operationCode = modifiActivityStatusById(id, RELEASE);
        if (operationCode != SUCCEED) {
            response.setCode(operationCode);
        }
        return response;
    }

    /**
     * 拒绝审批指定活动
     *
     * @return 操作结果
     */
    @RequestMapping(value = "/refuseApprovalActivityById", method = RequestMethod.POST)
    public Response refuseApprovalActivityById(@RequestBody RefuseApprovalActivityRequestParameter refuseApprovalActivityRequestParameter) {
        //接口返回
        Response response = new Response();
        Optional<Activity> operation = activityRepository.findById(refuseApprovalActivityRequestParameter.getId());
        if (operation == null) {
            response.setCode(ACTIVITY_NOT_FIND);
            return response;
        }
        Activity activity = operation.get();
        if (activity == null) {
            response.setCode(ACTIVITY_NOT_FIND);
            return response;
        }
        activity.setStatus(REFUSE);
        activity.setApproveOpinion(refuseApprovalActivityRequestParameter.getApproveOpinion());
        activity = activityRepository.save(activity);
        if (activity == null) {
            response.setCode(SAVE_FAIL);
            return response;
        }
        response.setData(new MoidfyActivityResponse());
        return response;
    }


    /**
     * 报名指定活动
     *
     * @return 操作结果
     */
    @RequestMapping(value = "/applyActivityById", method = RequestMethod.GET)
    public Response applyActivityById(@RequestParam("id") long id, @RequestParam("currentUserId") int currentUserId) {
        //接口返回
        Response response = new Response();
        Optional<Activity> operation = activityRepository.findById(id);
        if (operation == null) {
            response.setCode(ACTIVITY_NOT_FIND);
            return response;
        }
        Activity activity = operation.get();
        if (activity == null) {
            response.setCode(ACTIVITY_NOT_FIND);
            return response;
        }
        List<String> memberIdList = activity.getMemberIdList();
        if (memberIdList == null) {
            memberIdList = new LinkedList<>();
        }
        memberIdList.add(String.valueOf(currentUserId));
        activity.setMemberIdList(memberIdList);
        activity = activityRepository.save(activity);
        if (activity == null) {
            response.setCode(SAVE_FAIL);
            return response;
        }
        response.setData(new MoidfyActivityResponse());
        return response;
    }

    /**
     * 修改活动状态
     *
     * @param id     活动id
     * @param status 要修改的状态
     * @return 操作结果状态码
     */
    private short modifiActivityStatusById(long id, byte status) {
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
