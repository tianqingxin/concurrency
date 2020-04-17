package com.inspur.concurrency.utils;

import com.alibaba.fastjson.JSONObject;

public class ResponseUtils {

    /**
     * 返回成功code
     */
    private  final static String RETURN_SUCCESS_CODE ="200";
    /**
     * 返回失败code
     */
    private  final static String RETURN_FAILURE_CODE ="500";
    /**
     * 返回成功信息
     */
    private final static String RETURN_SUCCESS_MSG ="成功！";
    /**
     * 返回失败信息
     */
    private final static String RETURN_FAILURE_MSG ="失败！";
    /**
     * =rtnCode
     */
    private final static String RTN_CODE="rtnCode";
    /**
     * =rtnMsg
     */
    private final static  String RTN_MSG="rtnMsg";
    /**
     * =data
     */
    private final static String DATA="data";

    /**
     * 返回成功信息
     * @param object
     * @return
     */
    public static JSONObject successResponse(Object object){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(RTN_CODE,RETURN_SUCCESS_CODE);
        jsonObject.put(RTN_MSG,RETURN_SUCCESS_MSG);
        jsonObject.put(DATA,object);
        return jsonObject;
    }

    /**
     * 返回失败信息
     * @param
     * @return
     */
    public static JSONObject successResponse(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(RTN_CODE,RETURN_FAILURE_CODE);
        jsonObject.put(RTN_MSG,RETURN_FAILURE_MSG);
        return jsonObject;
    }
}
