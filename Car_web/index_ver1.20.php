<!DOCTYPE html>
<html lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <head>
    <meta charset="utf-8">
    <title>Bootstrap, from Twitter</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="./bootstrap/css/bootstrap.css" rel="stylesheet">
    <style>
      body {
        padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
      }
	  div.test {position: fixed;}
	  
    </style>
    <link href="./bootstrap/css/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="../assets/js/html5shiv.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
    <link rel="shortcut icon" href="../assets/ico/favicon.png">
	<script language = "javascript">
	//variable=new XMLHttpRequest();
	$('.navbar-inner').dropdown('toggle');
	function showUser(str)
	{ 
		xmlHttp=GetXmlHttpObject()
		if (xmlHttp==null)
		 {
		 alert ("Browser does not support HTTP Request");
		 return;
		 }
		var url="ajax_server.php?";
		url=url+str;
		url=url+"&sid="+Math.random();//这个是在ajax调用后台页面的时候区别ID用的，因为如果ID相同导致url相同的话，浏览器会直接在缓存中取值，而不回发到服务器端
		xmlHttp.onreadystatechange=stateChanged; //从而导致值的错误加随机数后可以避免
		xmlHttp.open("GET",url,true);
		xmlHttp.send(null);
	}
	function stateChanged() 
	{ 
	if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete")
	 { 
	 document.getElementById("table_test").innerHTML=xmlHttp.responseText 
	 } 
	}
	function GetXmlHttpObject()
	{
	var xmlHttp=null;
	try
	 {
	 // Firefox, Opera 8.0+, Safari
	 xmlHttp=new XMLHttpRequest();
	 }
	catch (e)
	 {
	 //Internet Explorer
	 try
	  {
	  xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
	  }
	 catch (e)
	  {
	  xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	 }
	return xmlHttp;
	}
	
	
	
	
	window.onload = function() 
	{
		initialiseStateFromURL();

		if ("onhashchange" in window) {
			window.onhashchange = initialiseStateFromURL;
		} 
		else {
			setInterval(pollHash, 1000);//每1000毫秒執行一次pollHash()
		}
	}

	var recentHash;
	function pollHash() {
		if (window.location.hash == recentHash) {
			return; //hash沒有變化，不做任何事情
		}
		recentHash = window.location.hash;
	  
		// URL has changed, update the UI accordingly.
		initialiseStateFromURL();
	}

	function initialiseStateFromURL() {
		var hash = window.location.hash;
		// ...判斷state並執行對應(ajax)動作
	}
	/*xmlhttp.open("GET","new_3.php",true);
	xmlhttp.send();*/
	</script>
	<script src="./bootstrap/js/bootstrap-dropdown.js"></script>
	<script src="./bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="http://www.see-source.com/bootstrap/js/jquery.js"></script> 
    <script type="text/javascript" src="./bootstrap/js/bootstrap-button.js"></script>
	<script type="text/javascript" src="./bootstrap/js/bootstrap-collapse.js"></script> 
  </head>

  <body>
	<div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#">Project name</a>
          <div class="nav-collapse collapse" >
            <ul class="nav">
              <li class="active"><a class="bootstro" data-bootstro-step="0" data-bootstro-placement="bottom" href="http://www.yahoo.com.tw">Home</a></li>
              <li><a class="bootstro" data-bootstro-step="1" data-bootstro-placement="bottom" href="#about">車商</a></li>
              <li><a class="bootstro" data-bootstro-step="2" data-bootstro-placement="bottom" href="#contact">車種</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container-fluid">
		<div class="row-fluid">
			<div class="span2">
			<div class="well sidebar-nav test">
            <ul class="nav nav-list">
              <li class="nav-header">地區</li>
              <li><a onclick="showUser('city=台北市&type=1')">台北</a></li>
              <li><a onclick="showUser('city=台中市&type=1')">台中</a></li>
              <li><a onclick="showUser('city=高雄市&type=1')">高雄</a></li>
              <li><a onclick="showUser('city=花蓮市&type=1')">花蓮</a></li>
              <li class="nav-header">金額</li>
              <li><a onclick="showUser('upperMoney=10&lowerMoney=0&type=2')">10萬以下</a></li>
              <li><a onclick="showUser('upperMoney=50&lowerMoney=10&type=2')">10~50萬</a></li>
              <li><a onclick="showUser('upperMoney=100&lowerMoney=50&type=2')">50萬~100萬</a></li>
              <li><a onclick="showUser('upperMoney=200&lowerMoney=100&type=2')">100萬~200萬</a></li>
              <li class="nav-header">品牌</li>
              <li><a href="#">法拉利</a></li>
              <li><a href="#">BMW</a></li>
            </ul>
          </div><!--/.well -->
			</div>
			<div class="span10 page-header">
			<table>
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Brands</th>
						<th>Company</th>
						<th>Year</th>
						<th>Displacement</th>
						<th>Url</th>
						<th>Address</th>
						<th>Price</th>
						<th>Color</th>
					</tr>
				</thead>
				<tbody id = "table_test">
				<?php
					//html5的video標籤學一下，問題:是video.js還是<video>
					if(!($link = mysql_connect('localhost', "root", "j725912")))
					die("cannot link database");//請詢問$link = mysql(....)和$link->multi_query(...)差異?
					mysql_query("SET NAMES 'utf8'");//與學長們的做法不同，這行是設定mysql編碼，刪掉中文字會變亂碼
					//mysql_select_db($d, $link);
					if(!(mysql_select_db("cloud")))
					die("cannot open db");
					$result = mysql_query("select * from car");
					if(!($result))
					{print("can't execute");}
					for ( $counter = 0; $row = mysql_fetch_row( $result ); $counter++)
					{
						print("<tr><td><a onclick='showUser(\"TID=$row[0]&type=3\")'>$row[0]</a></td>");
						print("<td>$row[1]</td>");
						print("<td>$row[2]</td>");
						print("<td>$row[3]</td>");
						print("<td>$row[4]</td>");
						print("<td>$row[5]</td>");
						print("<td><a href = $row[6]>$row[6]</a></td>");
						print("<td>$row[7]</td>");
						print("<td>$row[8]</td>");
						print("<td>$row[9]</td></tr>");
					}
				?>
				</tbody>
			</table>
			</div>
		</div>
    </div>

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
	
    <!--<script src="./bootstrap-dropdown.js"></script>
	<script src="./bootstrap.js"></script>
    <script src="./bootstrap.min.js"></script>-->
    
  </body>
</html>