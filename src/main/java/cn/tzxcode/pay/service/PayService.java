package cn.tzxcode.pay.service;

import cn.tzxcode.pay.dto.MerchantReqDTO;
import cn.tzxcode.pay.entity.PayConfig;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *支付父类接口
 *@auth tuzongxun
 *@email 1160569243@qq.com
 *@blog https://blog.tzxcode.cn、https://blog.csdn.net/tuzongxun
 */
public interface PayService {
    /**
     * 读取配置文件
     * @param payconfigList
     * @param paName
     * @return
     */
    public default String readConfig(List<PayConfig> payconfigList, String paName) {
        String paValue = null;
        for (int i = 0; i < payconfigList.size(); i++) {
            PayConfig payConfig = payconfigList.get(i);
            String paramName = payConfig.getParamName();
            if (paName.equals(paramName)) {
                payconfigList.remove(i);
                paValue = payConfig.getParamValue();
                break;
            }
        }
        return paValue;
    }

    /**
     * 支付统一处理方法
     * @param response
     * @param merchantReqDTO
     * @return
     */
    public String toPay(HttpServletResponse response, MerchantReqDTO merchantReqDTO);
}
