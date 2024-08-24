package org.tinytrail.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.tinytrail.admin.dao.entity.GroupDO;
import org.tinytrail.admin.dto.req.TinyTrailGroupSaveReqDTO;
import org.tinytrail.admin.dto.req.TinyTrailGroupSortReqDTO;
import org.tinytrail.admin.dto.req.TinyTrailGroupUpdateReqDTO;

import java.util.List;

/**
 * 短链接分组接口层
 */
public interface GroupService extends IService<GroupDO> {

    /**
     * 新增短链接分组
     * @param groupName 短链接分组名
     */
    void saveGroup(String groupName);

    /**
     * 获取短链接分组集合
     * @return 短链接集合
     */
    List<TinyTrailGroupSaveReqDTO> listGroup();

    /**
     * 修改短链接分组
     * @param requestParam 修改短链接分组参数
     */
    void updateGroup(TinyTrailGroupUpdateReqDTO requestParam);

    /**
     * 删除短链接分组
     * @param gid 分组标识
     */
    void deleteGroup(String gid);

    /**
     * 短连接分组排序
     * @param requestParam 短连接分组排序参数
     */
    void sortGroup(List<TinyTrailGroupSortReqDTO> requestParam);
}
