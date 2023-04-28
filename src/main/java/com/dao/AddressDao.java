package com.dao;

import com.entity.AddressEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.AddressVO;
import com.entity.view.AddressView;


/**
 * 地址
 * 
 * @author 
 * @email 
 * @date 2022-04-23 15:10:37
 */
public interface AddressDao extends BaseMapper<AddressEntity> {
	
	List<AddressVO> selectListVO(@Param("ew") Wrapper<AddressEntity> wrapper);
	
	AddressVO selectVO(@Param("ew") Wrapper<AddressEntity> wrapper);
	
	List<AddressView> selectListView(@Param("ew") Wrapper<AddressEntity> wrapper);

	List<AddressView> selectListView(Pagination page,@Param("ew") Wrapper<AddressEntity> wrapper);
	
	AddressView selectView(@Param("ew") Wrapper<AddressEntity> wrapper);
	

}
