package com.android.miyao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CaiNiaoMD5 {

    //单纯的报文加密
//    private String checkDataDigest(String logisticsInterface) {
//        MessageDigest md5;
//        try {
//            md5 = MessageDigest.getInstance("MD5");
//
//            FixedParameters fixedParameters = new FixedParameters();
//            fixedParameters.setPartnered("3i5v6941Nm6UH88rP4F1E32352370l2E");// 事先约定好的
//            fixedParameters.setCharset("utf-8");
//
//            String ret = CoralHelper
//                    .base64StringFromByteArray(md5.digest((logisticsInterface + fixedParameters.getPartnered())
//                            .getBytes(fixedParameters.getCharset().toString())));
//            logger.info("生成的消息签名：" + ret);
//            return ret;
//        } catch (NoSuchAlgorithmException e) {
//            logger.logException(e);
//        } catch (UnsupportedEncodingException e) {
//            logger.logException(e);
//        }
//        return null;
//    }
//
//    //菜鸟面单下发  验签
//    private String checkDataDigest(String logisticsInterface, String dataDigest, String ctrcode) {
//// Date checkBeginTime = new Date();
//// logger.info("校验消息签名 ，开始时间：" + checkBeginTime);
//        String result = ""; // 错误码
//        String isOK = ""; // 下单结果：true or false
//// 订单创建接口校验开始
//        MessageDigest md5;
//        try {
//            md5 = MessageDigest.getInstance("MD5");
//
//            FixedParameters fixedParameters = new FixedParameters();
//            fixedParameters.setPartnered(EYBParternID);// 事先约定好的
//            fixedParameters.setCharset("utf-8");
//
//            String ret = CoralHelper.base64StringFromByteArray(
//                    md5.digest((logisticsInterface + fixedParameters.getPartnered())
//                            .getBytes(fixedParameters.getCharset().toString())));
//            logger.info("生成的消息签名：" + ret);
//            if (!ret.equals(dataDigest)) {
//                isOK = "false";
//                result = "S02";
//                logger.error("数据校验失败...dataDigest = " + dataDigest);
//
//                return returnTbMsg(ctrcode, isOK, result, "");
//            }
//        }
//        catch (NoSuchAlgorithmException e) {
//            isOK = "false";
//            result = "B02";
//            logger.logException(e);
//
//            return returnTbMsg(ctrcode, isOK, result, "");
//        }
//        catch (UnsupportedEncodingException e) {
//            isOK = "false";
//            result = "B02";
//            logger.logException(e);
//
//            return returnTbMsg(ctrcode, isOK, result, "");
//        }
//// Date checkEndTime = new Date();
//// logger.info("发mq，结束时间：" + checkEndTime + " 耗时(ms)：" + (checkEndTime.getTime() -
//// checkBeginTime.getTime()));
//
//        return null;
//    }

}
