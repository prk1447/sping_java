package com.iot1.sql.vendor.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.iot1.sql.vendor.dto.VendorInfo;

@Repository
public class VendorDaoImpl extends SqlSessionDaoSupport implements VendorDao
{	
	@Override
	public VendorInfo getVendorInfo(VendorInfo vi) 
	{
		return this.getSqlSession().selectOne("vendors.VENDOR_SELECT", vi);
	}

	@Override
	public List<VendorInfo> getVendorInfoList(VendorInfo vi) 
	{
		return this.getSqlSession().selectList("vendors.VENDOR_SELECT", vi);
	}

	@Override
	public List<VendorInfo> selectVendorInfoCombo()
	{
		return this.getSqlSession().selectList("vendors.VENDOR_SELECT_COMBO");
	}

	@Override
	public int insertVendorInfo(VendorInfo vi) 
	{
		return this.getSqlSession().insert("vendors.VENDOR_INSERT", vi);
	}

	@Override
	public int updateVendorInfo(VendorInfo vi) 
	{
		return this.getSqlSession().update("vendors.VENDOR_UPDATE", vi);
	}

	@Override
	public int deleteVendorInfo(VendorInfo vi) 
	{
		return this.getSqlSession().delete("vendors.VENDOR_DELETE", vi);
	}

}
