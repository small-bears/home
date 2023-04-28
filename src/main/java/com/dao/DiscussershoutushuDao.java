package com.dao;

import com.entity.DiscussershoutushuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussershoutushuVO;
import com.entity.view.DiscussershoutushuView;


/**
 * 二手图书评论表
 * 
 * @author 
 * @email 
 * @date 2022-04-23 15:10:37
 */
public interface DiscussershoutushuDao extends BaseMapper<DiscussershoutushuEntity> {
	
	List<DiscussershoutushuVO> selectListVO(@Param("ew") Wrapper<DiscussershoutushuEntity> wrapper);
	
	DiscussershoutushuVO selectVO(@Param("ew") Wrapper<DiscussershoutushuEntity> wrapper);
	
	List<DiscussershoutushuView> selectListView(@Param("ew") Wrapper<DiscussershoutushuEntity> wrapper);

	List<DiscussershoutushuView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussershoutushuEntity> wrapper);
	
	DiscussershoutushuView selectView(@Param("ew") Wrapper<DiscussershoutushuEntity> wrapper);
	

}
