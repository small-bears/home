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


import com.dao.ErshoutushuDao;
import com.entity.ErshoutushuEntity;
import com.service.ErshoutushuService;
import com.entity.vo.ErshoutushuVO;
import com.entity.view.ErshoutushuView;

@Service("ershoutushuService")
public class ErshoutushuServiceImpl extends ServiceImpl<ErshoutushuDao, ErshoutushuEntity> implements ErshoutushuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ErshoutushuEntity> page = this.selectPage(
                new Query<ErshoutushuEntity>(params).getPage(),
                new EntityWrapper<ErshoutushuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ErshoutushuEntity> wrapper) {
		  Page<ErshoutushuView> page =new Query<ErshoutushuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}
    
    @Override
	public List<ErshoutushuVO> selectListVO(Wrapper<ErshoutushuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ErshoutushuVO selectVO(Wrapper<ErshoutushuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ErshoutushuView> selectListView(Wrapper<ErshoutushuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ErshoutushuView selectView(Wrapper<ErshoutushuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
