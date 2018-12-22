package com.udriving.drivingapi.controller;

import com.udriving.drivingapi.controller.request.CreateActivityRequestParameter;
import com.udriving.drivingapi.controller.response.Response;
import com.udriving.drivingapi.entity.activity.ActivityRepository;
import com.udriving.drivingapi.entity.activity.UDActiviti;
import com.udriving.drivingapi.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 活动相关接口
 */
@RestController
public class ActivityController {
    @Autowired
    private ActivityRepository activityRepository;

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

}
