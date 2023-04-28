package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.QiugouhuifuDao;
import com.entity.QiugouhuifuEntity;
import com.service.QiugouhuifuService;
import com.entity.vo.QiugouhuifuVO;
import com.entity.view.QiugouhuifuView;

@Service("qiugouhuifuService")
public class QiugouhuifuServiceImpl extends ServiceImpl<QiugouhuifuDao, QiugouhuifuEntity> implements QiugouhuifuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<QiugouhuifuEntity> page = this.selectPage(
                new Query<QiugouhuifuEntity>(params).getPage(),
                new EntityWrapper<QiugouhuifuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<QiugouhuifuEntity> wrapper) {
		  Page<QiugouhuifuView> page =new Query<QiugouhuifuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<QiugouhuifuVO> selectListVO(Wrapper<QiugouhuifuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public QiugouhuifuVO selectVO(Wrapper<QiugouhuifuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<QiugouhuifuView> selectListView(Wrapper<QiugouhuifuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public QiugouhuifuView selectView(Wrapper<QiugouhuifuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
