package com.dao;

import com.entity.CartEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.CartVO;
import com.entity.view.CartView;


/**
 * 购物车表
 * 
 * @author 
 * @email 
 * @date 2022-04-23 15:10:37
 */
public interface CartDao extends BaseMapper<CartEntity> {
	
	List<CartVO> selectListVO(@Param("ew") Wrapper<CartEntity> wrapper);
	
	CartVO selectVO(@Param("ew") Wrapper<CartEntity> wrapper);
	
	List<CartView> selectListView(@Param("ew") Wrapper<CartEntity> wrapper);

	List<CartView> selectListView(Pagination page,@Param("ew") Wrapper<CartEntity> wrapper);
	
	CartView selectView(@Param("ew") Wrapper<CartEntity> wrapper);
	

}
