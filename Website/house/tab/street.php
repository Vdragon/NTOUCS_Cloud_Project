<?php print( '<?xml version = "1.0" encoding = "utf-8"?>' );print("\n") ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns = "http://www.w3.org/1999/xhtml">
<title>Street</title>
<head>
<!--google street-->
<link href="https://developers.google.com/maps/documentation/javascript/examples/default.css" rel="stylesheet">
   
<script src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
<script type="text/javascript">
<?php 
echo "var g_lng=".$_GET['lng'].";\n";
echo "var g_lat=".$_GET['lat'].";\n";
?>
function street() 
{
	var oiuy = new google.maps.LatLng(parseFloat(g_lng),parseFloat(g_lat));
	//var oiuy = new google.maps.LatLng(25.05348,121.55062);
	var panoramaOptions = {
	  position:oiuy,
	  pov: {
		heading: 165,
		pitch:0,
		zoom:1
	  }
	};
	var myPano = new google.maps.StreetViewPanorama(document.getElementById('mapstreet'), panoramaOptions);
	myPano.setVisible(true);
 }
</script>
</head>


<body onload="street()">
<div id="mapstreet" style="height:620px; width:820px;"></div>
<!--?php echo $_GET['lng']."<-lng\nlat->".$_GET['lat']."<br />\n";?-->
</body>

</html>
