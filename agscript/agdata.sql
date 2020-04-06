DROP TABLE IF EXISTS `dw_t_admin`;
 CREATE TABLE `dw_t_admin` (
  `adminid` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL DEFAULT '',
  PRIMARY KEY (`adminid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `dw_t_user`;
CREATE TABLE `dw_t_user` (
  `userid` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL default '',
  PRIMARY KEY  (`userid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `dw_t_pmtype_variety`;
CREATE TABLE `dw_t_pmtype_variety` (
  `provincename` varchar(10),
  `marketName` varchar(30),
  `type` varchar(20),
  `variety` varchar(20),
  PRIMARY KEY(provincename,marketName,type,variety)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `dw_t_real_time_query`;
CREATE TABLE `dw_t_real_time_query` (
  `id` varchar(40) PRIMARY KEY,
  `marketsum` int NOT NULL,
  `typesum` int NOT NULL,
  `varietysum` int NOT NULL,
  `countsum` bigint NOT NULL,
  `dailycount` int NOT NULL,
  `crawlTime` varchar(40) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `dw_t_cloud`;
CREATE TABLE `dw_t_cloud` (
  `type` varchar(20) PRIMARY KEY,
  `count` int NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `dw_t_type_record`;
CREATE TABLE `dw_t_type_record` (
  `id` varchar(40) PRIMARY KEY,
  `provincename` varchar(10) NOT NULL,
  `marketname` varchar(30) NOT NULL,
  `type` varchar(10) NOT NULL,
  `crawltime` varchar(40) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `dw_t_regional_market`;
CREATE TABLE `dw_t_regional_market` (
  `id` varchar(40) PRIMARY KEY,
  `provincename` varchar(10) NOT NULL,
  `type` varchar(20) NOT NULL,
  `variety` varchar(20) NOT NULL,
  `avgprice` double(5,3) NOT NULL,
  `productdate` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `dw_t_type_compare`;
CREATE TABLE `dw_t_type_compare` (
  `id` varchar(40) PRIMARY KEY,
  `provincename` varchar(10) NOT NULL,
  `marketName` varchar(20) NOT NULL,
  `type` varchar(20) NOT NULL,
  `variety` varchar(20) NOT NULL,
  `avgprice` double(5,3) NOT NULL,
  `productdate` varchar(20)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `dw_t_price_trender`;
CREATE TABLE `dw_t_price_trender` (
  `id` varchar(40) PRIMARY KEY,
  `provincename` varchar(10) NOT NULL,
  `marketname` varchar(20) NOT NULL,
  `type` varchar(20) NOT NULL,
  `variety` varchar(20) NOT NULL,
  `avgprice` double(5,3) NOT NULL,
  `maxprice` double(5,3) NOT NULL,
  `minprice` double(5,3) NOT NULL,
  `productdate` varchar(20) NOT NULL,
  `productmonth` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `dw_t_price_prediction`;
CREATE TABLE `dw_t_price_prediction` (
  `id` varchar(40) PRIMARY KEY,
  `provincename` varchar(10) NOT NULL,
  `marketname` varchar(20) NOT NULL,
  `type` varchar(20) NOT NULL,
  `variety` varchar(20) NOT NULL,
  `avgprice` double(5,3) NOT NULL,
  `productdate` varchar(20)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;














