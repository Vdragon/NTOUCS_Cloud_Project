-- phpMyAdmin SQL Dump
-- version 2.10.3
-- http://www.phpmyadmin.net
-- 
-- 主機: localhost
-- 建立日期: Dec 04, 2013, 11:42 AM
-- 伺服器版本: 5.0.51
-- PHP 版本: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- 
-- 資料庫: `cloud`
-- 

-- --------------------------------------------------------

-- 
-- 資料表格式： `brand`
-- 

CREATE TABLE `brand` (
  `ID` varchar(20) NOT NULL default '',
  `Name` varchar(50) default NULL,
  `orignURL` varchar(1024) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- 列出以下資料庫的數據： `brand`
-- 

INSERT INTO `brand` VALUES ('0001', 'Audi奧迪', 'http://www.audi.com.tw/tw/brand/zh.html');
INSERT INTO `brand` VALUES ('0002', 'Benz賓士', 'http://www.mercedes-benz.com.tw/content/taiwan/mpc/mpc_taiwan_website/twng/home_mpc/passengercars.flash.html');
INSERT INTO `brand` VALUES ('0003', 'BMW寶馬', 'http://www.bmw.com.tw/');
INSERT INTO `brand` VALUES ('0004', 'Buick別克', 'http://www.e-gm.com.tw/buick/index.asp');
INSERT INTO `brand` VALUES ('0005', 'Cadillac凱迪拉克', 'http://www.e-gm.com.tw/cadillac/index.asp');
INSERT INTO `brand` VALUES ('0006', 'Chrysler克萊斯勒', 'http://www.chrysler.com.tw/');
INSERT INTO `brand` VALUES ('0007', 'Citroen雪鐵龍', 'http://www.citroen.com/');
INSERT INTO `brand` VALUES ('0008', 'Ferrari法拉利', 'http://www.ferrari.com/English/Pages/home.aspx');
INSERT INTO `brand` VALUES ('0009', 'Ford福特', 'http://www.ford.com.tw/');
INSERT INTO `brand` VALUES ('0010', 'Formosa台塑', 'http://www.daf.com.tw/');
INSERT INTO `brand` VALUES ('0011', 'Honda本田', 'http://www.honda-taiwan.com.tw/');
INSERT INTO `brand` VALUES ('0012', 'Hyundai現代', 'http://www.hyundai-motor.com.tw/');
INSERT INTO `brand` VALUES ('0013', 'Infiniti日產', 'http://www.infiniti.com.tw/');
INSERT INTO `brand` VALUES ('0014', 'Jaguar捷豹', 'http://www.jaguarcars.com.tw/');
INSERT INTO `brand` VALUES ('0015', 'Jeep吉普', 'http://www.jeepgo4x4.com.tw/');
INSERT INTO `brand` VALUES ('0016', 'Kia嘉樂寶', 'http://www.kiamotors.com.tw/');
INSERT INTO `brand` VALUES ('0017', 'LandRover', 'http://www.landrover.com.tw/home.aspx');
INSERT INTO `brand` VALUES ('0018', 'Lexus凌志', 'http://www.lexus.com.tw/');
INSERT INTO `brand` VALUES ('0019', 'Mazda馬自達', 'http://www.mazda.com.tw/');
INSERT INTO `brand` VALUES ('0020', 'Mitsubishi三菱', 'http://www.china-motor.com.tw/');
INSERT INTO `brand` VALUES ('0021', 'Mitsubishi中華汽車', 'http://www.china-motor.com.tw/');
INSERT INTO `brand` VALUES ('0022', 'Nissan裕隆', 'http://www.nissan.com.tw/TC/Default.aspx');
INSERT INTO `brand` VALUES ('0023', 'Opel歐普', 'http://www.e-gm.com.tw/');
INSERT INTO `brand` VALUES ('0024', 'Peugeot寶獅', 'http://www.peugeot.com.tw/');
INSERT INTO `brand` VALUES ('0025', 'Porsche保時捷', 'http://www.porsche.com.tw/');
INSERT INTO `brand` VALUES ('0026', 'Renault雷諾', 'http://www.renault.com.tw/index.asp');
INSERT INTO `brand` VALUES ('0027', 'Saab紳寶', 'http://www.saab.com.tw/');
INSERT INTO `brand` VALUES ('0028', 'Ssangyong雙龍', 'http://www.ssangyong.com.tw/');
INSERT INTO `brand` VALUES ('0029', 'Suzuki鈴木', 'http://www.auto21.com.tw/');
INSERT INTO `brand` VALUES ('0030', 'TOYOTA豐田', 'http://www.toyota.com.tw/');
INSERT INTO `brand` VALUES ('0031', 'Subaru速霸陸', 'http://www.subaru-sot.com.tw/');
INSERT INTO `brand` VALUES ('0032', 'Volkwagen福斯', 'http://www.volkswagen.tw/zh.html');
INSERT INTO `brand` VALUES ('0033', 'Volvo富豪', 'http://www.volvocars.com/tw/Pages/default.aspx');
