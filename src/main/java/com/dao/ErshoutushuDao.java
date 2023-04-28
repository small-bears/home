package com.dao;

import com.entity.ErshoutushuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.ErshoutushuVO;
import com.entity.view.ErshoutushuView;


/**
 * 二手图书
 * 
 * @author 
 * @email 
 * @date 2022-04-23 15:10:37
 */
public interface ErshoutushuDao extends BaseMapper<ErshoutushuEntity> {
	
	List<ErshoutushuVO> selectListVO(@Param("ew") Wrapper<ErshoutushuEntity> wrapper);
	
	ErshoutushuVO selectVO(@Param("ew") Wrapper<ErshoutushuEntity> wrapper);
	
	List<ErshoutushuView> selectListView(@Param("ew") Wrapper<ErshoutushuEntity> wrapper);

	List<ErshoutushuView> selectListView(Pagination page,@Param("ew") Wrapper<ErshoutushuEntity> wrapper);
	
	ErshoutushuView selectView(@Param("ew") Wrapper<ErshoutushuEntity> wrapper);
	

}
