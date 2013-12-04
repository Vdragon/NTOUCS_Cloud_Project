-- phpMyAdmin SQL Dump
-- version 2.10.3
-- http://www.phpmyadmin.net
-- 
-- 主機: localhost
-- 建立日期: Dec 04, 2013, 11:41 AM
-- 伺服器版本: 5.0.51
-- PHP 版本: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- 
-- 資料庫: `cloud`
-- 

-- --------------------------------------------------------

-- 
-- 資料表格式： `car`
-- 

CREATE TABLE `car` (
  `ID` varchar(20) NOT NULL default '',
  `name` varchar(40) default NULL,
  `Brands` varchar(20) default NULL,
  `company` varchar(40) default NULL,
  `Year` int(11) default NULL,
  `Displacement` int(11) default NULL,
  `Url` varchar(1024) default NULL,
  `Address` varchar(200) default NULL,
  `Price` int(11) default NULL,
  `color` varchar(20) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- 列出以下資料庫的數據： `car`
-- 

INSERT INTO `car` VALUES ('00001', 'Fiesta', 'Ford', 'XXX二手車', 2010, 1500, 'http://www.ford.com.tw/cars/fiesta/specifications/spec-data', '台北市大安區忠孝東路五段15號', 588000, 'black');
INSERT INTO `car` VALUES ('00002', 'XF 2.2D Luxury', 'Jaguar', 'YY二手車', 2012, 2179, 'http://www.jaguarcars.com.tw/xf/specifications_xf/', '高雄市大寮區OO路19號', 2460000, 'silver');
INSERT INTO `car` VALUES ('00004', 'X5', 'BMW', 'XXX二手車', 2003, 3000, 'http://auto.8891.com.tw/usedauto-infos-310459.html', '彰化縣', 666000, 'silver');
INSERT INTO `car` VALUES ('00003', 'Boxster', 'Porsche', 'YY二手車', 2011, 2706, 'http://pap.porsche.com/taiwan_zh/models/boxster/boxster/featuresandspecs/', '高雄市三民區XX路258號', 2700000, 'red');
INSERT INTO `car` VALUES ('00005', '120i 2.0', 'BMW', 'ZZZ二手車', 2007, 2000, 'http://auto.8891.com.tw/usedauto-infos-322847.html', '台中市', 628000, 'black');
INSERT INTO `car` VALUES ('00006', 'GLK350', 'Benz', 'XXX二手車', 2010, 3500, 'http://auto.8891.com.tw/usedauto-infos-321438.html', '台中市', 1580000, 'black');
