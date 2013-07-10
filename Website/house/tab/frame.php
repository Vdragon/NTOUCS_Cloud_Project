<?php print( '<?xml version = "1.0" encoding = "utf-8"?>' ) ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html lang="zh-tw">
<head>
<meta name="Generator" content="EditPlus" />

<meta name="Keywords" content="" />
<meta name="Description" content="" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>frame of google service</title>



<style type="text/css">
	ul, li {
		margin: 0;
		padding: 0;
		list-style: none;
	}
	.abgne_tab {
		clear: left;
		width: 863px;
		height: 700px;
		margin: 10px 0;
	}
	ul.tabs {
		width: 100%;
		height: 32px;
		border-bottom: 1px solid #999;
		border-left: 1px solid #999;
	}
	ul.tabs li {
		float: left;
		height: 31px;
		line-height: 31px;
		overflow: hidden;
		position: relative;
		margin-bottom: -1px;	/* 讓 li 往下移來遮住 ul 的部份 border-bottom */
		border: 1px solid #999;
		border-left: none;
		background: #e1e1e1;
	}
	ul.tabs li a {
		display: block;
		padding: 0 20px;
		color: #000;
		border: 1px solid #fff;
		text-decoration: none;
	}
	ul.tabs li a:hover {
		background: #ccc;
	}
	ul.tabs li.active  {
		background: #fff;
		border-bottom: 1px solid #fff;
	}
	ul.tabs li.active a:hover {
		background: #fff;
	}
	div.tab_container {
		clear: left;
		width: 100%;
		border: 1px solid #999;
		border-top: none;
		background: transparent;
	}
	div.tab_container .tab_content {
		padding: 20px;
	}
	div.tab_container .tab_content h2 {
		margin: 0 0 20px;
	}
</style>
<script type="text/javascript">
	$(function(){
		// 預設顯示第一個 Tab
		var _showTab = 0;
		var $defaultLi = $('ul.tabs li').eq(_showTab).addClass('active');
		$('.tab_content').eq($defaultLi.index()).siblings().hide();
		
		// 當 li 頁籤被點擊時...
		// 若要改成滑鼠移到 li 頁籤就切換時, 把 click 改成 mouseover
		$('ul.tabs li').click(function() {
			// 找出 li 中的超連結 href(#id)
			var $this = $(this),
				_index = $this.index();
			// 把目前點擊到的 li 頁籤加上 .active
			// 並把兄弟元素中有 .active 的都移除 class
			$this.addClass('active').siblings('.active').removeClass('active');
			// 淡入相對應的內容並隱藏兄弟元素
			$('.tab_content').eq(_index).stop(false, true).fadeIn().siblings().hide();

			return false;
		}).find('a').focus(function(){
			this.blur();
		});
	});
</script>
</head>
<?php
// 變數宣告區
$lng = $_GET['Olng'];
$lat = $_GET['Olat'];
?>
<body>
	<div class="abgne_tab">
		<ul class="tabs">
			<li><a href="#" >Object Look</a></li>
			<li><a href="#" >Google Earth</a></li>
			<li><a href="#" >Google Street</a></li>
		</ul>

		<div class="tab_container">
			<div id="tab1" class="tab_content">
				<iframe src="../imageveiw/look.php?Onum=<?php echo $_GET['Onum']?>" width=820 height=620 marginwidth="0" marginheight="0" scrolling="no" frameborder="0" align="middle"></iframe>
			</div>

			<div id="tab2" class="tab_content">
				<iframe src="map.php?<?php echo 'lng='.$lng.'&lat='.$lat;?>" width="820" height="620" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" align="center"></iframe>
			</div>
			<div id="tab3" class="tab_content">
				<iframe src="street.php?<?php echo 'lat='.$lat.'&lng='.$lng;?>" width="820" height="620" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" align="center"></iframe>
			</div>	
		</div>
	</div>
</body>
</html>
