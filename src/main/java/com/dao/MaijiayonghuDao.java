package com.dao;

import com.entity.MaijiayonghuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.MaijiayonghuVO;
import com.entity.view.MaijiayonghuView;


/**
 * 卖家用户
 * 
 * @author 
 * @email 
 * @date 2022-04-23 15:10:36
 */
public interface MaijiayonghuDao extends BaseMapper<MaijiayonghuEntity> {
	
	List<MaijiayonghuVO> selectListVO(@Param("ew") Wrapper<MaijiayonghuEntity> wrapper);
	
	MaijiayonghuVO selectVO(@Param("ew") Wrapper<MaijiayonghuEntity> wrapper);
	
	List<MaijiayonghuView> selectListView(@Param("ew") Wrapper<MaijiayonghuEntity> wrapper);

	List<MaijiayonghuView> selectListView(Pagination page,@Param("ew") Wrapper<MaijiayonghuEntity> wrapper);
	
	MaijiayonghuView selectView(@Param("ew") Wrapper<MaijiayonghuEntity> wrapper);
	

}
