package com.rollanddice.comunidice.dao.util;

public class DaoUtils {
	
	public static void anadir(StringBuilder sql, boolean first, String queryWhere) {
		sql.append(first?" WHERE ":" AND ").append(queryWhere);
	}
	public static void update(StringBuilder sql, boolean first, String update) {
		sql.append(first?" SET ":" , ").append(update);
	}
	
}
