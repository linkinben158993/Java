package com.myclass.constants;

public class StringConstants {
	//Hibernate Config
	public static String DATA_SOURCE_DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	public static String DATA_SOURCE_URL = "jdbc:mysql://localhost:3306/elearningdb";
	public static String DATA_SOURCE_USER_NAME = "root";
	public static String DATA_SOURCE_PASSWORD = "Thienanvip@321";
	
	//Session Factory
	//Chỉ ra gói để quét
	public static String SESSION_FACTORY_PACKAGE_TO_SCAN = "com.myclass.entity";
	public static String SESSION_FACTORY_PUT_DIALECT = "hibernate.dialect";
	//Dialect của MySQL
	public static String SESSION_FACTORY_PUT_DIALECT_DATABASE = "org.hibernate.dialect.MySQLDialect";
	public static String SESSION_FACTORY_PUT_SHOW = "hibernate.show_sql";
	public static String SESSION_FACTORY_PUT_FORMAT = "hibernate.formate_sql";
	
	//Character Encoding
	public static String CHARACTER_CODING = "UTF-8";
	
	//Myclass Config
	public static String TEMPLATE_RESOLVER_PREFIX = "/WEB-INF/templates/";
	public static String TEMPLATE_RESOLVER_SUFFIX = ".html";
	public static String TEMPLATE_RESOLVER_TEMPLATE_MODE = "HTML5";
	public static String TEMPLATE_RESOLVER_RESOURCE_LOCATION = "/WEB-INF/assets/";
	
	
}
