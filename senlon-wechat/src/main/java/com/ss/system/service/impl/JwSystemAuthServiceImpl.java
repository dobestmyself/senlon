package com.ss.system.service.impl;

import java.util.List;
import java.util.ArrayList;

import com.ss.common.constant.UserConstants;
import com.ss.common.core.domain.Ztree;
import com.ss.common.utils.RandomUtils;
import com.ss.common.utils.StringUtils;
import com.ss.system.domain.JwSystemRole;
import com.ss.system.mapper.JwSystemRoleAuthRelMapper;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ss.system.mapper.JwSystemAuthMapper;
import com.ss.system.domain.JwSystemAuth;
import com.ss.system.service.IJwSystemAuthService;
import com.ss.common.core.text.Convert;

import javax.jws.soap.SOAPBinding;

/**
 * 系统权限菜单Service业务层处理
 *
 * @author shishuai
 * @date 2020-07-03
 */
@Service
public class JwSystemAuthServiceImpl implements IJwSystemAuthService {
    @Autowired
    private JwSystemAuthMapper jwSystemAuthMapper;

    @Autowired
    private JwSystemRoleAuthRelMapper roleAuthMapper;

    @Override
    public JwSystemAuth selectJwSystemAuthByAuthId(Long authId) {
        return jwSystemAuthMapper.selectJwSystemAuthByAuthId(authId);
    }

    /**
     * 查询系统权限菜单
     *
     * @param id 系统权限菜单ID
     * @return 系统权限菜单
     */
    @Override
    public JwSystemAuth selectJwSystemAuthById(Long id) {
        return jwSystemAuthMapper.selectJwSystemAuthById(id);
    }

    /**
     * 查询系统权限菜单列表
     *
     * @param jwSystemAuth 系统权限菜单
     * @return 系统权限菜单
     */
    @Override
    public List<JwSystemAuth> selectJwSystemAuthList(JwSystemAuth jwSystemAuth) {
        return jwSystemAuthMapper.selectJwSystemAuthList(jwSystemAuth);
    }

    @Override
    public List<JwSystemAuth> findJwSystemAuthList() {
        List<JwSystemAuth> jwSystemAuthList = new ArrayList<>();
        List<JwSystemAuth> auths = jwSystemAuthMapper.selectJwSystemAuthList(new JwSystemAuth());

        for (int i = 0; i < auths.size(); i++) {
            if (auths.get(i).getParentAuthId() == 0L) {
                jwSystemAuthList.add(auths.get(i));
            }
        }
        for (JwSystemAuth jwSystemAuth : jwSystemAuthList) {
            jwSystemAuth.setChildren(getChild(jwSystemAuth.getAuthId(), auths));
        }

        return jwSystemAuthList;
    }

    private List<JwSystemAuth> getChild(Long id, List<JwSystemAuth> auths) {
        List<JwSystemAuth> childList = new ArrayList<>();
        for (JwSystemAuth jwSystemAuth : auths) {
            if (StringUtils.isNotBlank(jwSystemAuth.getParentAuthId().toString())) {
                if (jwSystemAuth.getParentAuthId().equals(id)) {
                    childList.add(jwSystemAuth);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (JwSystemAuth jwSystemAuth : childList) {// 没有url子菜单还有子菜单
            if (StringUtils.isBlank(jwSystemAuth.getAuthContr())) {
                // 递归
                jwSystemAuth.setChildren(getChild(jwSystemAuth.getAuthId(), auths));
            }
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

    /**
     * 新增系统权限菜单
     *
     * @param jwSystemAuth 系统权限菜单
     * @return 结果
     */
    @Override
    public int insertJwSystemAuth(JwSystemAuth jwSystemAuth) {
        jwSystemAuth.setAuthId(RandomUtils.getRandomNum());
        return jwSystemAuthMapper.insertJwSystemAuth(jwSystemAuth);
    }

    /**
     * 修改系统权限菜单
     *
     * @param jwSystemAuth 系统权限菜单
     * @return 结果
     */
    @Override
    public int updateJwSystemAuth(JwSystemAuth jwSystemAuth) {
        return jwSystemAuthMapper.updateJwSystemAuth(jwSystemAuth);
    }

    /**
     * 删除系统权限菜单对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteJwSystemAuthByIds(String ids) {
        return jwSystemAuthMapper.deleteJwSystemAuthByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除系统权限菜单信息
     *
     * @param id 系统权限菜单ID
     * @return 结果
     */
    @Override
    public int deleteJwSystemAuthById(Long id) {
        return jwSystemAuthMapper.deleteJwSystemAuthById(id);
    }

    /**
     * 查询系统权限菜单树列表
     *
     * @return 所有系统权限菜单信息
     */
    @Override
    public List<Ztree> selectJwSystemAuthTree() {
        List<JwSystemAuth> jwSystemAuthList = jwSystemAuthMapper.selectJwSystemAuthList(new JwSystemAuth());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (JwSystemAuth jwSystemAuth : jwSystemAuthList) {
            Ztree ztree = new Ztree();
            ztree.setId(jwSystemAuth.getAuthId());
            ztree.setpId(jwSystemAuth.getParentAuthId());
            ztree.setName(jwSystemAuth.getAuthName());
            ztree.setTitle(jwSystemAuth.getAuthName());
            ztrees.add(ztree);
        }
        return ztrees;
    }

    @Override
    public List<JwSystemAuth> selectAuthAll(Long userId) {
        List<JwSystemAuth> authList = null;
        //TODO
        if(userId == 1L){
            authList = jwSystemAuthMapper.selectJwSystemAuthList(new JwSystemAuth());
        }else{
            authList = jwSystemAuthMapper.selectAuthAllByUserId(userId);
        }
        return authList;
    }

    @Override
    public List<Ztree> roleAuthTreeData(JwSystemRole role, Long userId) {
        String roleId = role.getRoleId();
        List<Ztree> ztrees = new ArrayList<>();
        //TODO
        List<JwSystemAuth> authList = selectAuthAll(userId);
        if(StringUtils.isNotNull(roleId)){
            List<String> roleAuthList = jwSystemAuthMapper.selectAuthTree(roleId);
            ztrees = initZtree(authList,roleAuthList,true);
        }else{
            ztrees = initZtree(authList,null,true);
        }
        return ztrees;
    }

    @Override
    public List<Ztree> initZtree(List<JwSystemAuth> authList, List<String> roleAuthList, boolean permsFlag) {
        List<Ztree> ztrees = new ArrayList<>();
        boolean isCheck = StringUtils.isNotNull(roleAuthList);
        for(JwSystemAuth auth : authList){
            Ztree ztree = new Ztree();
            ztree.setId(auth.getAuthId());
            ztree.setpId(auth.getParentAuthId());
            ztree.setName(transMenuName(auth,permsFlag));
            ztree.setTitle(auth.getAuthName());
            if(isCheck){
                ztree.setChecked(roleAuthList.contains(auth.getAuthId() + auth.getAuthContr()));
            }
            ztrees.add(ztree);
        }
        return ztrees;
    }

    public String transMenuName(JwSystemAuth auth, boolean permsFlag)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(auth.getAuthName());
        if (permsFlag)
        {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + auth.getAuthContr() + "</font>");
        }
        return sb.toString();
    }

    @Override
    public String checkAuthNameUnique(String authName) {
        List<JwSystemAuth> auths = jwSystemAuthMapper.checkAuthNameUnique(authName);
        if(auths.size() > 0){
            return UserConstants.MENU_NAME_NOT_UNIQUE;
        }
        return UserConstants.MENU_NAME_UNIQUE;
    }

    @Override
    public int selectCountChildByParentId(Long parentAuthId) {
        return jwSystemAuthMapper.selectCountChildByParentId(parentAuthId);
    }

    @Override
    public int selectCountRoleAuthByAuthId(Long authId) {
        return roleAuthMapper.selectCountRoleAuthByAuthId(authId);
    }
}
