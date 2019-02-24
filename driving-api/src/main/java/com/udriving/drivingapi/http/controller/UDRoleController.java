package com.udriving.drivingapi.http.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;



/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2019/2/24
 */
@Controller
public class UDRoleController {
//    /**
//     * 获取所有未审核的活动
//     *
//     * @return 接口返回结构
//     */
//    @RequestMapping(value = "/getActivityListByNotApproval", method = RequestMethod.GET)
//    public BaseResponse getActivityListByNotApproval(@RequestParam("startId") int startId, @RequestParam("dataSize") byte dataSize) {
//        //接口返回
//        BaseResponse baseResponse = new BaseResponse();
//        List<UDActivity> UDActivityList = null;
//        if (startId == 0) {
//            UDActivityList = activityRepository.findLessStatusActivityList(RELEASE, dataSize);
//        } else {
//            UDActivityList = activityRepository.findLessStatusActivityList(startId, RELEASE, dataSize);
//        }
//        if (UDActivityList == null) {
//            baseResponse.setCode(ACTIVITY_NOT_FIND);
//            return baseResponse;
//        }
//        baseResponse.setData(new ActivityListResponse(UDActivityList));
//        return baseResponse;
//    }
//
//    /**
//     * 审批指定活动
//     *
//     * @return 操作结果
//     */
//    @RequestMapping(value = "/approvalActivityById", method = RequestMethod.GET)
//    public BaseResponse approvalActivityById(@RequestParam("id") int id) {
//        //接口返回
//        BaseResponse baseResponse = new BaseResponse();
//        short operationCode = modifiActivityStatusById(id, RELEASE);
//        if (operationCode != SUCCEED) {
//            baseResponse.setCode(operationCode);
//            return baseResponse;
//        }
//        baseResponse.setData(new MoidfyActivityResponse());
//        return baseResponse;
//    }
//
//    /**
//     * 拒绝审批指定活动
//     *
//     * @return 操作结果
//     */
//    @RequestMapping(value = "/refuseApprovalActivityById", method = RequestMethod.POST)
//    public BaseResponse refuseApprovalActivityById(@RequestBody RefuseApprovalActivityRequestParameter refuseApprovalActivityRequestParameter) {
//        //接口返回
//        BaseResponse baseResponse = new BaseResponse();
//        Optional<UDActivity> operation = activityRepository.findById(refuseApprovalActivityRequestParameter.getId());
//        if (operation == null) {
//            baseResponse.setCode(ACTIVITY_NOT_FIND);
//            return baseResponse;
//        }
//        UDActivity UDActivity = operation.get();
//        if (UDActivity == null) {
//            baseResponse.setCode(ACTIVITY_NOT_FIND);
//            return baseResponse;
//        }
//        UDActivity.setStatus(REFUSE);
//        UDActivity.setApproveOpinion(refuseApprovalActivityRequestParameter.getApproveOpinion());
//        UDActivity = activityRepository.save(UDActivity);
//        if (UDActivity == null) {
//            baseResponse.setCode(SAVE_FAIL);
//            return baseResponse;
//        }
//        baseResponse.setData(new MoidfyActivityResponse());
//        return baseResponse;
//    }
}
