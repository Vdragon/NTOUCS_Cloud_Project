<?php print( '<?xml version = "1.0" encoding = "utf-8"?>' ) ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="ntou.ico" rel="shortcut icon" type="image/x-icon">
	<title>天邊一房屋</title>
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<link href="https://developers.google.com/maps/documentation/javascript/examples/default.css" rel="stylesheet">
	<script src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
<style type="text/css">
	body 
	{
	background-image:url('universe.jpg');
 	background-color:#131313;
	background-size: 100% auto;	
	}
	table
	{
	position: fixed;
	top: 5%;
	left: 5%;
	right: 5%;
	bottom: 5%;
	}
</style>
<script type="text/javascript">
var ge;
var g_lng = 25.044955;
var g_lat = 121.335579;
google.load("earth", "1");
//google.load("maps","2");
function init() 
{
	google.earth.createInstance('map3d', initCB, failureCB);
	
}

function initCB(instance) {

	ge = instance;
	
	ge.getWindow().setVisibility(true);
	ge.getNavigationControl().setVisibility(ge.VISIBILITY_AUTO);
	
	//road!!
	
	//ge.getLayerRoot().enableLayerById(ge.LAYER_BORDERS, true);  
	//ge.getLayerRoot().enableLayerById(ge.LAYER_ROADS, true);
	// add plusshow more  
	//ge.getLayerRoot().enableLayerById(ge.LAYER_BUILDINGS, true); 
	//ge.getLayerRoot().enableLayerById(ge.LAYER_BUILDINGS_LOW_RESOLUTION, true);   
	//ge.getLayerRoot().enableLayerById(ge.LAYER_TERRAIN, true);   
	//ge.getLayerRoot().enableLayerById(ge.LAYER_TREES, true);
	//ge.getSun().setVisibility(false);

	//ge.getOptions().setAtmosphereVisibility(true);

	
	//many i set

	showName();
	// Create a style map.
	var styleMap = ge.createStyleMap('');

	// Move the camera.
	var la = ge.createLookAt('');
	la.set( parseFloat(g_lng), parseFloat(g_lat), 1000, ge.ALTITUDE_RELATIVE_TO_GROUND, 0, 0,  100000);
	ge.getView().setAbstractView(la);
	
}

function failureCB(errorCode) {}



function createPoint(lat,lon,placemark,now_houseAgency)
{
	//CREATE THE PLACEMARK
	placemark= ge.createPlacemark('');
	//placemark.setName(title);
	
	//define a custom icon
	var selficon = ge.createIcon('');
	if(now_houseAgency=="cthouse")
		selficon.setHref('http://i.imgur.com/PsG6o.png');
	else if(now_houseAgency=="twhg")
		selficon.setHref('http://i.imgur.com/L7S1a.png');
	else if(now_houseAgency=="etwarm")
		selficon.setHref('http://i.imgur.com/vkOw6.png');
	else
		selficon.setHref('http://maps.google.com/mapfiles/kml/paddle/red-circle.png');
	var style = ge.createStyle('');
	style.getIconStyle().setIcon(selficon);
	placemark.setStyleSelector(style);
	
	

	// Set the placemark's location.  
	var point = ge.createPoint('');
	point.setLatitude(parseFloat(lat));
	point.setLongitude(parseFloat(lon));

	

	// Apply stylemap to a placemark.
	placemark.setGeometry(point);
	//getDescription(placemark,title);
	ge.getFeatures().appendChild(placemark);
	// show message
	// Show an HTML string balloon, overriding the feature's
			// description when showing a balloon with setBalloon.
	
	

}
function getDescription(placemark,title)
{
	placemark.setDescription();
}

function showName(){

	if (window.XMLHttpRequest)// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	
	else// code for IE6, IE5	
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	
	xmlhttp.open("GET","tab/output.xml",false);
	xmlhttp.send();
	xmlDoc=xmlhttp.responseXML; 

	x=xmlDoc.getElementsByTagName("MARKER");
	var placemark=new Array(1001);
		
	for (var i = 0; i < 1000; i++)
	{
		var now_lng=x[i].getElementsByTagName("LNG")[0].childNodes[0].nodeValue;
		var now_lat=x[i].getElementsByTagName("LAT")[0].childNodes[0].nodeValue;
		var now_houseAgency=x[i].getElementsByTagName("TYPE")[0].childNodes[0].nodeValue;
		
		
	createPoint(parseFloat(x[i].getElementsByTagName("LNG")[0].childNodes[0].nodeValue), parseFloat(x[i].getElementsByTagName("LAT")[0].childNodes[0].nodeValue), placemark[i],now_houseAgency);	
		
	}
}


google.setOnLoadCallback(init);
</script>




</head>
<body bgcolor=#121212>
	<table border=0  cellspacing='20' cellpadding='20' >
		<tr>
			<td rowspan=2><div id="map3d" style="align:center; height:480px; width:640px;"></div></td>
			<td><img align=center src='http://imageshack.us/a/img203/4577/teamlogoc.png' /></td>
		</tr>
		<tr>
			<td align=center><a href='frames.php'><img src='enter.gif' width=200px height=200px /></a></td>
		</tr>
	</table>
</body>
</html>

