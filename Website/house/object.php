<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>give me a house!</title>
	<link href="ntou.ico" rel="shortcut icon" type="image/x-icon">
	<style type="text/css">
	body 
	{
	background-image:url('hd_wall_11299.jpg');
 	background-color:#cccccc;
	background-repeat:no-repeat;
	background-attachment:fixed;
	background-position:center; 
	}
	table.another
	{
	background-color:rgba(255,255,255,0.3);
	}
	table.above
	{
	text-align:center;
	background-color:rgba(255,255,255,0.3);
	}
	table.above caption
	{
	text-align:left;
	color:#FACC2E;
	}
	table.above td
	{
	height:121;
	background-color:rgba(255,255,255,0.3);
	}
	table.above td.noncolor
	{
	background-color:rgba(255,255,255,0.3);
	align:middle;
	}
	table.above td.morecolor
	{
	background-color:rgb(50,50,50);
	color:White;	
	}
	</style>
<?php
// 向各 node 要圖
function getObjectImg($instance)
{
	// 執行指令
	$array_string = exec($instance,$result,$rc);						
    //exec('ls', $output, $return_var); array_string 似乎沒用，可刪
    //$output: 回傳內容都會存於此變數中(儲存成陣列), 不會直接秀在頁面上.
    //$return_var: 取得系統狀態回傳碼

	//echo $res[0]."<br />\n";
	if($result[0] === "HTTP/1.1 307 TEMPORARY_REDIRECT"){
		// 拆解回傳資訊的位置（Location:....）
		// 找出欲修改位置
		//echo $res[2] . "<br />";
		$before = substr($result[2], 10, 7);
		$target = substr($result[2], 17, 5);
		$affter = substr($result[2], 22);
		// 轉址
		switch (substr($target, -1)) {
				case "1":					// node1	140.121.198.160
			$target = "140.121.198.160";	break;
				case "2":					// node2	140.121.198.146
			$target = "140.121.198.146";	break;
				case "3":					// node3	140.121.198.148
			$target = "140.121.198.148";	break;
				case "4":					// node4	140.121.198.141
			$target = "140.121.198.141";	break;
				case "5":					// node5	140.121.198.142
			$target = "140.121.198.142";	break;
				case "6":					// node6	140.121.198.143
			$target = "140.121.198.143";	break;
				case "7":					// node7	140.121.198.144
			$target = "140.121.198.144";	break;
				case "8":					// node8	140.121.198.150
			$target = "140.121.198.150";	break;
				case "9":					// node9	140.121.198.153
			$target = "140.121.198.153";	break;
			}
		// 重組目標連結
		$src = $before . $target . $affter;
		return $src;
		}//end if
	$$instance ='';
	$result ='';	
}
?>
	</head>
<body>
<?php
$objectNumber = $_GET['Oid'];// 物件編號
$objectTitle = "";// 標題
$objectAddress = "";// 物件地址
$objectMoney = "";// 價格
$objectAllFloor = "";// 全坪
$objectFloor = "";// 地坪
$objectUse = "";// 用途
$objectOld = "";// 仲介商
$objectLatitude = '';// 經度
$objectLongitude = '';// 緯度
$objectURL = '';// 物件URL
$arraystrings = array();

// 抓取物件說明（from ID）.
// 建立向 webhdfs 執行的指令
$ins='curl -i -L "http://node1:50070/webhdfs/v1/House/'.$objectNumber.'/houseinfo.txt?op=OPEN"';
//echo $ins."<br />\n";		//已知curl可抓網頁內容，但不知其結果

// 執行指令
$array_string = exec($ins,$res,$rc);
// 根據其回傳的 HTTP code，執行不同動作
// 正常情況下是  第一次是 307（重新導向）  第二次是 200（確認連線）
if($res[0] === "HTTP/1.1 307 TEMPORARY_REDIRECT" && $res[6] === "HTTP/1.1 200 OK"){ //a === b 代表a與b完全相等
	// 歸類
	$strings = explode("：",$res[16]);												//可縮短，不需一直用explode?
	$objectTitle = $strings[1];														
	$strings = explode("：",$res[13]);
	$objectAddress = $strings[1];
	$strings = explode("：",$res[12]);
	$objectMoney = $strings[1];
	$strings = explode("：",$res[11]);
	$objectUse = $strings[1];
	$strings = explode("：",$res[14]);
	$objectAllFloor = $strings[1];
	$strings = explode("：",$res[15]);
	$objectFloor = $strings[1];
	$strings = explode("：",$res[19]);
	$objectOld = $strings[1];
	$strings = explode("：",$res[18]);
	$objectLatitude = $strings[1];
	$strings = explode("：",$res[17]);
	$objectLongitude = $strings[1];
	$strings = explode(":",$res[20]);
	$objectURL = $strings[2];
	//for($testnumber = 11;$testnumber <= count($res)-1;$testnumber++){
	//	$string = explode("：",$res[$testnumber]);
	//	echo $testnumber.$res[$testnumber] . "<br />\n\t".$string[1]."<br />\n";
	}
// 或是 404 無目標檔案
else if($res[0] === "HTTP/1.1 404 Not Found"){
	echo "沒有檔案，請向謝君偉教授申請深夜不斷網切結書。<br />\n";
	}
// 或是其他狀況 
else{
	echo "抱歉。<br/>\n";
	//echo "result[0] is : ". $res[0] ." <br />\nslove it youself!<br />\n";
	}
// 重置變數
$ins = '';
$res = '';


// 得知物件圖片張數
$picnumber = 0;// 圖片張數
// 建立向 webhdfs 執行的指令
$ins='curl -i "http://node1:50070/webhdfs/v1/House/'.$objectNumber.'/image?op=GETCONTENTSUMMARY"';
// 執行指令
$array_string = exec($ins,$res,$rcc);

// 根據其回傳的 HTTP code，執行不同動作
// 正常情況下 是 200 （確認連線）
if($res[0] === "HTTP/1.1 200 OK"){
	$array = explode(",",$res[5]);
	$picnumber = substr($array[1],-1);
	//echo "has " . $picnumber . " pic.<br />\n";
	}
// 404 無目標資料夾
else if($res[0] === "HTTP/1.1 404 Not Found"){
	echo "要不然就是壓根就是沒有圖，請協助網路管理者向謝君偉教授申請深夜不斷網切結書。<br />\n";
	}
// 其他狀況
else
	echo "result[0] is " . $res[0] . " .<br />\nSolve this by youself!<br />\n";
// 重置變數
$ins = '';
$res = '';

// 抓取物件圖片Url
// 可能不只一張，利用上階段的＄picnumber用迴圈處理
for($number = 1;$number <= $picnumber;$number++){
	// 建立向 webhdfs 執行的指令
	$ins = "curl -i -L \"http://node1:50070/webhdfs/v1/House/".$objectNumber."/image/".$number.".jpg?op=OPEN\"";
	//echo $ins."<br />\n";

	array_push($arraystrings , getObjectImg($ins));

	$ins ='';
	}//end for
//抓取結束

if ($objectNumber > 0)
{
	echo "
<table class='above' border=2  cellspacing='0' cellpadding='0' >
	<caption><h1>$objectTitle</h1></caption>
	<tr >
		<td class='noncolor' rowspan=7 ><iframe src='tab/frame.php?Olng=".$objectLongitude."&Olat=".$objectLatitude."&Onum=$objectNumber' width=880 height=720></iframe></td>
		<td class='morecolor'  width=90 >地址</td>
		<td width=220>".$objectAddress."</td>
	</tr>
	<tr >
		<td class='morecolor'>總價</td>
		<td >".$objectMoney."</td>
	</tr>
	<tr >
		<td class='morecolor'>總坪</td>
		<td >".$objectAllFloor."</td>
	</tr>
	<tr >
		<td class='morecolor'>類型/用途</td>
		<td >".$objectUse."</td>
	</tr>
	<tr >
		<td class='morecolor'>地坪</td>
		<td >".$objectFloor."</td>
	</tr>
	<tr >
		<td class='morecolor'>原仲介商</td>
		<td ><a href='http:".$objectURL."' target='_parent'>".$objectOld."</a></td>
	</tr>
</table>";

	// 感興趣物件部份
	echo "		
<table class='another'>
	<tr>
		<td>你可能感興趣：</td>
	</tr>
	<tr>
		";
	// mySQL連線部份
	$db = "CloudHouse";
	$link = mysqli_init();
	if(!$link->real_connect("140.121.198.146", "lala", "123")){
		die("Database server GG.");
	}
	if(!$link->set_charset("utf8")) {
		die("Charset still in lanti1.");
	}
	if(!$link->select_db($db))
	{
		die('Connect Error (' . $link->connect_errno . ') ' . $link->connect_error)."<br />\n";
	}
	// 取得 ID 和 標題即可
	$query  = "SELECT `ID`,`Title` FROM `House` WHERE `Address` LIKE \"".$_GET['near']."%\" ORDER BY RAND() LIMIT 3";
	if($link->multi_query($query)){
		if($result = $link->store_result()){
			while($row = $result->fetch_row()){
				$anotherObjectID = $row[0];
				$anotherObjectTitle = $row[1];
				$inst = "curl -i -L \"http://node1:50070/webhdfs/v1/House/".$anotherObjectID."/image/1.jpg?op=OPEN\"";

				$anotherObjectImgUrl = getObjectImg($inst);
			
				$inst ='';
				echo "<td><a href='object.php?Oid=".$anotherObjectID."&near=".$_GET['near']."'>". $anotherObjectTitle ."<br><img width=360 height=180 src='$anotherObjectImgUrl'/></a></td>
			" ;
				}
			}
		}
	echo "</tr>
	</table>";
}
else
	echo "賣光了，下次請早。<br />
抑或請向 <b style=\"color:#FF0000\">基隆市政府</b> <b style=\"color:#00FF00\">都市發展處</b> <b style=\"color:#0000FF\">國宅科</b> 辦理\"合宜住宅\"相關手續。<br />
地址：基隆市義一路1號<br />
";
?>
</body>
</html>
