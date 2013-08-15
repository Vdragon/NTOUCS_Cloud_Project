<?php
	$db = "Cloud";
	$link = mysqli_init();
	if(!$link->real_connect("localhost", "root", "j725912")){
		die("Database server GG.");
		}
	if(!$link->set_charset("utf8")) {
		die("Charset still in latin1.");
		}
	//else	printf("Current character set: %s<br />\n", $link->character_set_name());
	if(!$link->select_db($db)){
		die('Connect Error (' . $link->connect_errno . ') ' . $link->connect_error)."<br />\n";
		}
	else
	;
?>
<?php
	/*$db = "Cloud";
	$link = mysqli_init();
	if(!$link->real_connect("localhost", "root", "j725912")){
		die("Database server GG.");
		}
	if(!$link->set_charset("utf8")) {
		die("Charset still in latin1.");
		}
	//else	printf("Current character set: %s<br />\n", $link->character_set_name());
	if(!$link->select_db($db)){
		die('Connect Error (' . $link->connect_errno . ') ' . $link->connect_error)."<br />\n";
		}
	else
	;*/
	if(!($link = mysql_connect('localhost', "root", "j725912")))
	die("cannot link database");//請詢問$link = mysql(....)和$link->multi_query(...)差異?
	mysql_query("SET NAMES 'utf8'");//與學長們的做法不同，這行是設定mysql編碼，刪掉中文字會變亂碼
	//mysql_select_db($d, $link);
	if(!(mysql_select_db("cloud")))
	die("cannot open db");
	$result = mysql_query("select * from car");
	if(!($result))
	{print("can't execute");}
	if($type == 1)
	{
		
		$sql = "select * from car where Address Like \"%$city%\"";
		$result = mysql_query($sql);
		if(!($result))
		{print("can't execute");}
		for ( $counter = 0; $row = mysql_fetch_row( $result ); $counter++)
		{
			print("<tr><td><a href = 'index3.php?TID=$row[0]'>$row[0]</a></td>");
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
	}
	else if($type == 2)
	{
		
		$lowerMoney = (int)$lowerMoney;
		$upperMoney = (int)$upperMoney;
		$sql = "select * from car where Price > $lowerMoney*10000 AND Price <= $upperMoney*10000";
		$result = mysql_query($sql);
		if(!($result))
		{print("can't execute");}
		for ( $counter = 0; $row = mysql_fetch_row( $result ); $counter++)
		{
			print("<tr><td><a href = 'index3.php?TID=$row[0]'>$row[0]</a></td>");
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
	}
	else if($type == 3)
	{
		$sql = "select * from car where ID = $TID";
		$result = mysql_query($sql);
		if(!($result))
		{print("can't execute");}
		$row = mysql_fetch_row( $result );
	
		print("<tr><td rowspan = 10 align=center><img width = 512 height = 341 src='url.jpg'/></td><td>$row[0]</td></tr>");
		print("<tr><td>$row[1]</td></tr>");
		print("<tr><td>$row[2]</td></tr>");
		print("<tr><td>$row[3]</td></tr>");
		print("<tr><td>$row[4]</td></tr>");
		print("<tr><td>$row[5]</td></tr>");
		print("<tr><td><a href = $row[6]>$row[6]</a></td></tr>");
		print("<tr><td>$row[7]</td></tr>");
		print("<tr><td>$row[8]</td></tr>");
		print("<tr><td>$row[9]</td></tr>");
	}
	else
	{
		for ( $counter = 0; $row = mysql_fetch_row( $result ); $counter++)
		{
			print("<tr><td><a href = 'index3.php?TID=$row[0]'>$row[0]</a></td>");
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
	}
?>