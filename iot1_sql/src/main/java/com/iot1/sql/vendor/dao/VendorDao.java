package com.iot1.sql.vendor.dao;

import java.util.List;

import com.iot1.sql.vendor.dto.VendorInfo;

public interface VendorDao 
{
	public VendorInfo getVendorInfo(VendorInfo vi);
	
	public List<VendorInfo> getVendorInfoList(VendorInfo vi);
	
	public List<VendorInfo> selectVendorInfoCombo();
	
	public int insertVendorInfo(VendorInfo vi);
	
	public int updateVendorInfo(VendorInfo vi);
	
	public int deleteVendorInfo(VendorInfo vi);
}
