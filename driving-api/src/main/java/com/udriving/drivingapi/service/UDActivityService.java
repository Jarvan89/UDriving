package com.udriving.drivingapi.service;

import com.udriving.drivingapi.entity.dao.activiti.ActivityForDetail;
import com.udriving.drivingapi.entity.dao.activiti.UDActivity;
import com.udriving.drivingapi.entity.dao.activiti.ActivityRepository;
import com.udriving.drivingapi.http.HttpCode;
import com.udriving.drivingapi.http.pojo.activity.HttpModifyActivity;
import com.udriving.drivingapi.http.pojo.activity.ReqActivity;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static com.udriving.drivingapi.entity.dao.activiti.ActivitiStatusConstant.CREATE;

/**
 * Created by IntelliJ IDEA
 * Coder : haiyang
 * Date:2019/2/24
 */
@Service
public class UDActivityService {
    @Autowired
    private ActivityRepository activityRepository;


    public UDActivity createActivity(ReqActivity activity) throws InvocationTargetException, IllegalAccessException {
        UDActivity udActivity = new UDActivity();
        BeanUtils.copyProperties(udActivity, activity);
        udActivity.setId(generateNewId());
        return activityRepository.save(udActivity);
    }


    public void delActivity() {

    }

    public HttpCode modifyActivity(HttpModifyActivity modifyActivity) throws InvocationTargetException, IllegalAccessException {
        //接口返回
        Optional<UDActivity> optional = activityRepository.findById(modifyActivity.getId());
        if (optional == null || !optional.isPresent()) {
            return HttpCode.ACTIVITY_NOT_FIND;
        }
        UDActivity UDActivity = optional.get();
        BeanUtils.setProperty(UDActivity, modifyActivity.getKey(), modifyActivity.getValue());
        UDActivity = activityRepository.save(UDActivity);
        if (UDActivity == null) {
            return HttpCode.SAVE_FAIL;
        }
        return HttpCode.SUCCEED;

    }


    public ActivityForDetail selectActivityById(long id) throws InvocationTargetException, IllegalAccessException {
        Optional<UDActivity> optional = activityRepository.findById(id);
        if (optional == null || !optional.isPresent()) {
            return null;
        }
        ActivityForDetail activityForDetail = new ActivityForDetail();
        BeanUtils.copyProperties(activityForDetail, activityForDetail);
        return activityForDetail;
    }


    public List<ReqActivity> selectActivityList(int offSet, int count) throws InvocationTargetException, IllegalAccessException {

        //接口返回

        List<UDActivity> lists = activityRepository.queryAllCanApplyActivity(offSet, CREATE, count);
        List<ReqActivity> reqActivityList = new ArrayList<>(lists.size());
        for (int i = 0; i < lists.size(); i++) {
            ReqActivity reqActivity = new ReqActivity();
            UDActivity udActivity = lists.get(i);
            BeanUtils.copyProperties(reqActivity, udActivity);
            reqActivityList.add(reqActivity);
        }

        return reqActivityList;

    }

    public void selectActivityByUser() {


    }


    /**
     * 检查指定id的活动是否不存在
     *
     * @param id 活动id
     * @return true:不存在
     */
    private boolean checkActivityInexistence(long id) {
        Optional<UDActivity> optional = activityRepository.findById(id);
        if (optional != null) {
            return !optional.isPresent();
        }
        return true;
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
        UDActivity idMaxUDActivity = activityRepository.findIdMaxActivity();
        //新活动id
        long newActivityId = 0;
        //活动未空是生成一个新的流水号
        if (idMaxUDActivity == null) {
            newActivityId = Long.valueOf(stringBuilder.append("0001").toString());
            //未找到指定活动时说明新id有效，返回给调用层
            if (checkActivityInexistence(newActivityId)) {
                return newActivityId;
            }
            //未返回说id无效，递归调用，重新生成。
            return generateNewId();
        }
        //id最大的活动的活动id
        long id = idMaxUDActivity.getId();
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

}
