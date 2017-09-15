package com.iot1.sql.vendor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot1.sql.vendor.dao.VendorDao;
import com.iot1.sql.vendor.dto.VendorInfo;

@Service
public class VendorServiceImpl implements VendorService
{
	@Autowired
	VendorDao vendorDao;
	
	@Override
	public VendorInfo getVendorInfo(VendorInfo vi) 
	{
		return vendorDao.getVendorInfo(vi);
	}

	@Override
	public List<VendorInfo> getVendorInfoList(VendorInfo vi) 
	{
		return vendorDao.getVendorInfoList(vi);
	}

	@Override
	public List<VendorInfo> getVendorInfoCombo()
	{
		return vendorDao.selectVendorInfoCombo();
	}

	@Override
	public int insertVendorInfo(VendorInfo vi) 
	{
		return vendorDao.insertVendorInfo(vi);
	}

	@Override
	public int insertVendorInfoList(VendorInfo[] vi) 
	{
		int cnt = 0;
		for(VendorInfo vi2 : vi)
		{
			cnt += vendorDao.insertVendorInfo(vi2);
		}
		return cnt;
	}

	@Override
	public int updateVendorInfoList(VendorInfo vi) 
	{	
		return vendorDao.updateVendorInfo(vi);
	}

	@Override
	public int deleteVendorInfo(VendorInfo vi)
	{
		return vendorDao.deleteVendorInfo(vi);
	}

}
