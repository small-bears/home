package com.dao;

import com.entity.QiugouhuifuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.QiugouhuifuVO;
import com.entity.view.QiugouhuifuView;


/**
 * 求购回复
 * 
 * @author 
 * @email 
 * @date 2022-04-23 15:10:37
 */
public interface QiugouhuifuDao extends BaseMapper<QiugouhuifuEntity> {
	
	List<QiugouhuifuVO> selectListVO(@Param("ew") Wrapper<QiugouhuifuEntity> wrapper);
	
	QiugouhuifuVO selectVO(@Param("ew") Wrapper<QiugouhuifuEntity> wrapper);
	
	List<QiugouhuifuView> selectListView(@Param("ew") Wrapper<QiugouhuifuEntity> wrapper);

	List<QiugouhuifuView> selectListView(Pagination page,@Param("ew") Wrapper<QiugouhuifuEntity> wrapper);
	
	QiugouhuifuView selectView(@Param("ew") Wrapper<QiugouhuifuEntity> wrapper);
	

}
