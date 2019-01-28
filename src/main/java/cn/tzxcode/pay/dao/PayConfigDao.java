package cn.tzxcode.pay.dao;

import cn.tzxcode.pay.entity.PayConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 配置信息数据操作
 *
 * @auth tuzongxun
 * @email 1160569243@qq.com
 * @blog https://blog.tzxcode.cn、https://blog.csdn.net/tuzongxun
 */
@Repository
public interface PayConfigDao extends JpaRepository<PayConfig, Integer> {

    List<PayConfig> findByThirdMerId(@Param("third_mer_id") String thirdMerId);
}
