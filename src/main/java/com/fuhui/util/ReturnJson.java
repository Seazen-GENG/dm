package com.fuhui.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReturnJson {
    private static Map<String, Object> map = new HashMap<String, Object>();

    /**
     * 返回正常的List集合
     *
     * @param list
     * @return
     */
    public static Object returnSuccessList(List list) {
        JSONObject jsonList = JSONObject.fromObject(list);
        map.put("errorNum", 0);
        map.put("errorInfo", "");
        map.put("status", true);
        map.put("data", jsonList);
        JSONObject jsonSuccess = JSONObject.fromObject(map);
        return jsonSuccess;
    }

    /**
     * 返会失败结果
     *
     * @return
     */
    public static Object returnFail() {
        JSONObject json = new JSONObject();
        map.put("status", false);
        map.put("errorNum", 1);
        map.put("errorInfo", "操作失败，请重试");
        map.put("data", json);
        JSONObject jsonFail = JSONObject.fromObject(map);
        return jsonFail;
    }

    /**
     * 放入map集合
     *
     * @param paramMap
     * @return
     */
    public static Object returnSuccessMap(Map<String, Object> paramMap) {
        Map<String, Object> successMap = new HashMap<String, Object>();
        JSONObject jsonMap = JSONObject.fromObject(paramMap);
        map.put("errorNum", 0);
        map.put("errorInfo", "");
        map.put("status", true);
        map.put("data", jsonMap);
        JSONObject returnJsonMap = JSONObject.fromObject(map);
        return returnJsonMap;
    }

    /**
     * 无参方法
     *
     * @return
     */
    public static Object returnSuccessMap() {
        Map<String, Object> successMap = new HashMap<String, Object>();
        map.put("errorNum", 0);
        map.put("errorInfo", "");
        map.put("status", true);
        map.put("data", successMap);
        JSONObject returnJsonMap = JSONObject.fromObject(map);
        return returnJsonMap;
    }

    /**
     * 解决参数arrayList的问题
     *
     * @param list
     * @return
     */
    public static Object returnList(List list) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        JSONArray jsonList = JSONArray.fromObject(list, jsonConfig);
        map.put("errorNum", 0);
        map.put("errorInfo", "");
        map.put("status", true);
        map.put("data", jsonList);
        JSONObject jsonSuccess = JSONObject.fromObject(map);
        return jsonSuccess;
    }

    /**
     * 分页map
     *
     * @param pNum
     * @param pLine
     * @param countAll
     * @return
     */
    public static Map<String, Object> returnPage(Integer pNum, Integer pLine, Integer countAll) {
        Map<String, Object> mapPage = new HashMap<String, Object>();
        map.put("pageNum", pNum);
        map.put("pageLine ", pLine);
        map.put("countAll", countAll);
        return mapPage;
    }

    public static Object returnFail(String errorInfo) {
        JSONObject json = new JSONObject();
        map.put("status", false);
        map.put("errorNum", 1);
        if (errorInfo == null || errorInfo.equals("")) {
            map.put("errorInfo", "操作失败，请重试");
        } else {
            map.put("errorInfo", errorInfo);
        }
        map.put("data", json);
        JSONObject jsonFail = JSONObject.fromObject(map);
        return jsonFail;
    }

    public static Object returnFail(Integer errorNum, String errorInfo) {
        JSONObject json = new JSONObject();
        map.put("status", false);
        if (errorInfo == null || errorInfo.equals("")) {
            map.put("errorInfo", "操作失败，请重试");
        } else {
            map.put("errorInfo", errorInfo);
        }
        map.put("errorNum", errorNum);
        map.put("data", json);
        JSONObject jsonFail = JSONObject.fromObject(map);
        return jsonFail;
    }
}
