<?php print( '<?xml version = "1.0" encoding = "utf-8"?>' ) ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Smart Image Gallery Slider With Transitions Effects</title>

    <script src="http://www.egrappler.com/smart-gallery/jquery-1.4.4.min.js" type="text/javascript"></script>

    <link href="2.css" rel="stylesheet" type="text/css" />

    <script src="http://www.egrappler.com/smart-gallery/smart-gallery.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function() {
        $('#smart-gallery').gallery({ stickthumbnails: true, random: true});
        });
    </script>

    <style type="text/css">
        body
        {
            margin: 0;
            padding: 0;
            background-color: rgba(255,255,255,0.3);
        }
        #contributor
        {
            color: Gray;
            margin: 0;
            padding: 0;
            margin-top: 4px;
            text-align: center;
        }
        #contributor a
        {
            color: Gray;
        }
    </style>
</head>
<?php
$arraystrings=array();
// 得知物件圖片張數
$picnumber = 0;// 圖片張數
// 建立向 webhdfs 執行的指令
$ins='curl -i "http://140.121.198.160:50070/webhdfs/v1/House/'.$_GET['Onum'].'/image?op=GETCONTENTSUMMARY"';
// 執行指令
$array_string = exec($ins,$res,$rcc);

// 根據其回傳的 HTTP code，執行不同動作
// 正常情況下 是 200 （確認連線）
if($res[0] === "HTTP/1.1 200 OK"){
	$array = explode(",",$res[5]);
	$picnumber = substr($array[1],-1);
	//echo $picnumber."<br/>\n";
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


for($number = 1;$number <= $picnumber;$number++){
	// 建立向 webhdfs 執行的指令
	$ins = "curl -i -L \"http://node1:50070/webhdfs/v1/House/".$_GET['Onum']."/image/".$number.".jpg?op=OPEN\"";
	//echo $ins."<br />\n";

	// 執行指令
	$array_string = exec($ins,$res,$rc);
	//echo $res[0]."<br />\n";
	if($res[0] === "HTTP/1.1 307 TEMPORARY_REDIRECT"){
		// 拆解回傳資訊的位置（Location:....）
		// 找出欲修改位置
		//echo $res[2] . "<br />";
		$before = substr($res[2], 10, 7);
		$target = substr($res[2], 17, 5);
		$affter = substr($res[2], 22);
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
		//echo $src."<br/>\n";
		array_push($arraystrings,$src);
		$res='';
		}
	}
?>
<body>
	<div id="smart-gallery">
<?
foreach($arraystrings as $string)
	echo "\t\t<a href=\"".$string."\" >
\t\t<img align=center width=100% height=100% src=\"".$string."\" /></a>\n";
?>
	</div>
	
   <!--
        Images By Cubagallery
	-->
	</body>
</html>
