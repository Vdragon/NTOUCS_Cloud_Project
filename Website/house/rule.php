<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>天邊一房屋</title>
	<link href="ntou.ico" rel="shortcut icon" type="image/x-icon">
	<style type="text/css">
		body {
		background-image:url('hd_wall_11299.jpg');
	 	background-color:#cccccc;
		}
		b
		{
		color: #ff0000;
		}
		</style>
	</head>
<?php
// mySQL連線部份
$db = "cloud";
$link = mysqli_init();
if(!$link->real_connect("localhost", "root", "ntoucs")){
	die("Database server GG.");
}
if(!$link->set_charset("utf8")) {
	die("Charset still in lanti1.");
}
if(!$link->select_db($db))
{
	die('rule.php : Connect Error (' . $link->connect_errno . ') ' . $link->connect_error)."<br />\n";
}
$house = array();
$sql1  = "SELECT count(*) FROM `house` where `OrginURL` Like '%cthouse%'";  //來源辨識
$sql2  = "SELECT count(*) FROM `house` where `OrginURL` Like '%twhg%'";
$sql3  = "SELECT count(*) FROM `house` where `OrginURL` Like '%etwarm%'";
if($result = $link->query($sql1)){
	if($row = $result->fetch_row()){
		array_push($house,$row[0]);
		$result->free();
	}
}
if($result = $link->query($sql2)){
	if($row = $result->fetch_row()){
		array_push($house,$row[0]);
		$result->free();
	}
}
if($result = $link->query($sql3)){
	if($row = $result->fetch_row()){
		array_push($house,$row[0]);
		$result->free();
	}
}
mysqli_close($link);
?>
<body text="#000000">
	<font size="5">
	<br>
	<p align=center>
		<img align=center src='http://imageshack.us/a/img203/4577/teamlogoc.png'  alt='yo~' />
		</p>
	<br>
	<br>
	歡迎來到 <b>天邊一房屋</b>  ，在使用本網站前，請注意下列規定：
	<ol>
	<li>本網站 (140.121.198.160) 依「<a href='http://www.cs.ntou.edu.tw/files/studiofiles/M0BF8908.pdf'>國立臺灣海洋大學電機資訊學院資訊工程系專題暨實習實施細則</a>」為其產物網站。
	<li>本網站管理員為 fgasge@gmail.com 如您有發現不適當內容在本網站出現，請來信通知，網站管理員將儘速處理。
	<li>本網站中的圖片及文字資訊，網站管理員並無需要保證其內容之正確性。
	<li>徜若有法律紛爭，本網站無需負擔任何責任。
	<li>若您開始使用本網站服務，代表您已經詳細閱讀並完全了解，同意配合上述規定。
	</ol>
	<p align=center>
<?php 

echo "中信房屋共： <b>".$house[0]."</b> 間  / 台灣房屋共： <b>".$house[1]."</b> 間  / 東森房屋共： <b>".$house[2]."</b> 間".PHP_EOL;
?>
	</p>
	<br>
	<br>
	</font>

</body>
</html>
