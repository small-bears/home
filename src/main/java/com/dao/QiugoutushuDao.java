package com.dao;

import com.entity.QiugoutushuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.QiugoutushuVO;
import com.entity.view.QiugoutushuView;


/**
 * 求购图书
 * 
 * @author 
 * @email 
 * @date 2022-04-23 15:10:37
 */
public interface QiugoutushuDao extends BaseMapper<QiugoutushuEntity> {
	
	List<QiugoutushuVO> selectListVO(@Param("ew") Wrapper<QiugoutushuEntity> wrapper);
	
	QiugoutushuVO selectVO(@Param("ew") Wrapper<QiugoutushuEntity> wrapper);
	
	List<QiugoutushuView> selectListView(@Param("ew") Wrapper<QiugoutushuEntity> wrapper);

	List<QiugoutushuView> selectListView(Pagination page,@Param("ew") Wrapper<QiugoutushuEntity> wrapper);
	
	QiugoutushuView selectView(@Param("ew") Wrapper<QiugoutushuEntity> wrapper);
	

}
