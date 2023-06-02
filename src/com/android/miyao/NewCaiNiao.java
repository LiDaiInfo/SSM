package com.android.miyao;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

/**
 * 生成摘要工具类
 */
public class NewCaiNiao {
    public static final String UTF8 = "UTF-8";
    /**
     * @param args
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String   data = "<request><id>120086003</id><sellerId>89346737</sellerId><sellerNick>商家测试帐号6</sellerNick><cpCode>EYB</cpCode><branchCode>EYB</branchCode><serviceCode>SVC-COD</serviceCode><serviceName>代收货款</serviceName><action>1</action><needAudit>true</needAudit></request>";
        String   data1 ="{\"logisticsNo\":\"9747151780587\",\"subscribeType\":\"2\"}";
        String data2 ="<OrderNormal><created_time>2022-08-09 16:00:00</created_time><logistics_provider>C</logistics_provider><ecommerce_no>WEIPINHUI</ecommerce_no><ecommerce_user_id>65148133</ecommerce_user_id><is_tuomin>1</is_tuomin><sender_type>1</sender_type><sender_no>1100099658469</sender_no><inner_channel>0</inner_channel><delivery_password_flag>0</delivery_password_flag><logistics_order_no>tcx6655</logistics_order_no><one_bill_flag>0</one_bill_flag><submail_no>1</submail_no><base_product_no>2</base_product_no><weight>0</weight><volume>0</volume><length>0</length><width>0</width><height>0</height><pickup_notes></pickup_notes><insurance_flag>1</insurance_flag><pickup_pre_begin_time>2022-08-09 18:00:00</pickup_pre_begin_time><pickup_pre_end_time>2022-08-10 18:00:00</pickup_pre_end_time><sender><name>瑾喜珠宝</name><phone></phone><mobile>19937721056</mobile><prov>北京市</prov><city>北京市</city><county>西城区</county><address>永安路173号</address></sender><pickup><name>瑾喜珠宝</name><phone></phone><mobile>19937721056</mobile><prov>北京市</prov><city>北京市</city><county>西城区</county><address>永安路173号</address></pickup><receiver><name>王海龙</name><phone></phone><mobile>15061247295</mobile><prov>北京市</prov><city>北京市</city><county>西城区</county><address>永安路173号</address></receiver><cargos><Cargo><cargo_name>商品</cargo_name><cargo_category></cargo_category><cargo_quantity>1</cargo_quantity><cargo_weight>0</cargo_weight></Cargo></cargos></OrderNormal>";
        String parentId = "TF5Oj5hnzgXm";
        //中邮保险 秘钥：4Bkrai67Puqg
        //key123xydJDPT  MDASKJ2356D36J
        // FyknJH2kznLp  key123xydJDPT  TF5Oj5hnzgXm
        // 快手 秘钥      fb2aaZH1Cnjf54
        //key123xydJDPT
        //   String parentId = "Xkss38o5orY7650nXgr9xi9n79qqba65";
        //消息签名

        String data_digest = makeSignEMS(data2,parentId);
        System.out.println("data_digest = " + data_digest);

    }
    public static String makeSignEMS(String data, String parentId)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        String Ret=base64en.encode(MessageDigest.getInstance("MD5").digest((data+parentId).getBytes("UTF-8"))	);
        return Ret;
    }


}
