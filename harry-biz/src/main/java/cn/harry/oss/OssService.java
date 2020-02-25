package cn.harry.oss;


import cn.harry.oss.vo.OssCallbackResult;
import cn.harry.oss.vo.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * oss上传管理Service
 * Created by macro on 2018/5/17.
 */

/**
 * ClassName: MybatisPlusConfig
 * Description:
 *
 * @author honghh
 * Date 2019/10/14 11:50
 * Copyright (C) www.honghh.top
 */
public interface OssService {
    /**
     * oss上传策略生成
     *
     * @return
     */
    OssPolicyResult policy();

    /**
     * oss上传成功回调
     *
     * @param request
     * @return
     */
    OssCallbackResult callback(HttpServletRequest request);
}
