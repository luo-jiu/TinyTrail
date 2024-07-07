package org.tinytrail.admin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import org.tinytrail.admin.dao.entity.UserDO;
import org.tinytrail.admin.dto.req.UserRegisterReqDTO;
import org.tinytrail.admin.dto.resp.UserRespDTO;


public interface UserService extends IService<UserDO> {
    /**
     * 根据用户姓名查询用户信息
     * @return 用户返回实体
     */
    UserRespDTO getUserByUsername(String username);

    /**
     * 检查用户名是否存在
     */
    Boolean hasUsername(String username);

    /**
     * 注册用户
     * @param requestParam 用户注册请求参数
     */
    void register(UserRegisterReqDTO requestParam);
}
