package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussershoutushuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussershoutushuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussershoutushuView;


/**
 * 二手图书评论表
 *
 * @author 
 * @email 
 * @date 2022-04-23 15:10:37
 */
public interface DiscussershoutushuService extends IService<DiscussershoutushuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussershoutushuVO> selectListVO(Wrapper<DiscussershoutushuEntity> wrapper);
   	
   	DiscussershoutushuVO selectVO(@Param("ew") Wrapper<DiscussershoutushuEntity> wrapper);
   	
   	List<DiscussershoutushuView> selectListView(Wrapper<DiscussershoutushuEntity> wrapper);
   	
   	DiscussershoutushuView selectView(@Param("ew") Wrapper<DiscussershoutushuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussershoutushuEntity> wrapper);
   	

}

