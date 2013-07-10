<?php print( '<?xml version = "1.0" encoding = "utf-8"?>' ) ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns = "http://www.w3.org/1999/xhtml">
<head>
<link href="ntou.ico" rel="shortcut icon" type="image/x-icon">
<title>天邊一房屋</title>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<link href="https://developers.google.com/maps/documentation/javascript/examples/default.css" rel="stylesheet">
    <script src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
<script type="text/javascript">
var ge;

var g_lng = 23.583234 ;
var g_lat = 121.2 ;

google.load("earth", "1");
google.load("maps","2");
	
function init() 
{
	google.earth.createInstance('map3d', initCB, failureCB);
	
}

function initCB(instance) {

	ge = instance;

	ge.getWindow().setVisibility(true);
	ge.getNavigationControl().setVisibility(ge.VISIBILITY_AUTO);

	//road!!
	ge.getLayerRoot().enableLayerById(ge.LAYER_BORDERS, true);
	ge.getLayerRoot().enableLayerById(ge.LAYER_ROADS, true);
	// add plusshow more  
	ge.getLayerRoot().enableLayerById(ge.LAYER_BUILDINGS, true); 
	ge.getLayerRoot().enableLayerById(ge.LAYER_BUILDINGS_LOW_RESOLUTION, true);   
	ge.getLayerRoot().enableLayerById(ge.LAYER_TERRAIN, true);   
	ge.getLayerRoot().enableLayerById(ge.LAYER_TREES, true);
	ge.getSun().setVisibility(false);

	ge.getOptions().setAtmosphereVisibility(true);

	
	//many i set
	var placemark=new Array(1000);
	for(var i = 0; i < 1000; i++)
		placemark[i] = ge.createPlacemark('');

	showName(placemark);
	// Create a style map.
	var styleMap = ge.createStyleMap('');

	// Create normal style for style map.
	var normalStyle = ge.createStyle('');
	var normalIcon = ge.createIcon('');
	normalIcon.setHref('http://maps.google.com/mapfiles/kml/paddle/red-circle.png');
	normalStyle.getIconStyle().setIcon(normalIcon);

	/* Create highlight style for style map.
	var highlightStyle = ge.createStyle('');
	var highlightIcon = ge.createIcon('');
	highlightIcon.setHref('http://google-maps-icons.googlecode.com/files/girlfriend.png');
	highlightStyle.getIconStyle().setIcon(highlightIcon);
	highlightStyle.getIconStyle().setScale(2.0);
	*/
	styleMap.setNormalStyle(normalStyle);
	//   styleMap.setHighlightStyle(highlightStyle);

	// Apply stylemap to a placemark.
	for(var i = 0; i < 1000; i++)
	{
		placemark[i].setStyleSelector(styleMap);

		// Add the placemark to Earth.
		ge.getFeatures().appendChild(placemark[i]);
	}
		
	// Move the camera.
	var la = ge.createLookAt('');
	la.set( parseFloat(g_lng), parseFloat(g_lat), 1000, ge.ALTITUDE_RELATIVE_TO_GROUND, 0, 0, 450000);
	ge.getView().setAbstractView(la);
	
}

function failureCB(errorCode) {}



function createPoint(title,lat,lon,placemark)
{
	placemark.setName("");

	// Set the placemark's location.  
	var point = ge.createPoint('');
	point.setLatitude(parseFloat(lat));
	point.setLongitude(parseFloat(lon));
	placemark.setGeometry(point);

}

function showName(placemark){

	if (window.XMLHttpRequest)// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	
	else// code for IE6, IE5	
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	
	xmlhttp.open("GET","tab/output.xml",false);
	xmlhttp.send();
	xmlDoc=xmlhttp.responseXML; 

	x=xmlDoc.getElementsByTagName("MARKER");

	for (var i = 0; i < x.length; i++)
	{
		var now_lng=x[i].getElementsByTagName("LNG")[0].childNodes[0].nodeValue;
		var now_lat=x[i].getElementsByTagName("LAT")[0].childNodes[0].nodeValue;
		//if((parseFloat(g_lat)+0.005)>parseFloat(now_lat)&&(parseFloat(g_lat)-0.005)<parseFloat(now_lat))
		//	if((parseFloat(g_lng)+0.005)>parseFloat(now_lng)&&(parseFloat(g_lng)-0.005)<parseFloat(now_lng))
		//	{
		
		createPoint(x[i].getElementsByTagName("NAME")[0].childNodes[0].nodeValue,
			parseFloat(x[i].getElementsByTagName("LNG")[0].childNodes[0].nodeValue),
			parseFloat(x[i].getElementsByTagName("LAT")[0].childNodes[0].nodeValue),
			placemark[i]);	
		//}	
	}
}
google.setOnLoadCallback(init);
</script>
</head>
<body bgcolor=#121212>
	<p>
		<table align=center>
			<tr>
				<td><div id="map3d" style="align:center; height:600px; width:800px;"></div></td>
			</tr>
		</table>
	</p>
	<br>
	<hr align=middle width=900 color=#212121 />
	<p align=center><a href='frames.php'>Enter</a></p>
</body>
</html>

