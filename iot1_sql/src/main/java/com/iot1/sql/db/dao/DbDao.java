package com.iot1.sql.db.dao;

import java.util.List;
import java.util.Map;

import com.iot1.sql.db.dto.Column;
import com.iot1.sql.db.dto.DataBase;
import com.iot1.sql.db.dto.DbInfo;
import com.iot1.sql.db.dto.Table;

public interface DbDao 
{
	public DbInfo getDbInfo(DbInfo di);
	
	public List<DbInfo> getDbInfoList(DbInfo di);
	
	public boolean isConnectDB(DbInfo di) throws Exception;

	public List<DataBase> selectDataBaseList() throws Exception;
	
	public List<Table> selectTableList(DataBase database) throws Exception;
	
	public List<Column> selectColumnList(Table column) throws Exception;
	
	public Map<String, Object> runSql(Map<String, Object> pm) throws Exception;

}
