package com.udriving.api.execute;

import com.udriving.api.execute.base.BaseExecute;

import java.util.HashMap;
import java.util.Map;

public class ExecuteMap {
    public static Map<String, BaseExecute> apiMap;

    static {
        apiMap = new HashMap<>();
        apiMap.put("shutdown", new ShutdownExecute());
    }
}
