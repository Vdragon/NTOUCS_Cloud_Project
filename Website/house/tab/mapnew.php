<?php print( '<?xml version = "1.0" encoding = "utf-8"?>' ) ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns = "http://www.w3.org/1999/xhtml">
<title>placemark_example.html</title>
<head>



<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<link href="https://developers.google.com/maps/documentation/javascript/examples/default.css" rel="stylesheet">
    <script src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>



<script type="text/javascript">

var ge;
<?php
echo 'var g_lng = '.$_GET['lng'].' ;'.PHP_EOL;
echo 'var g_lat = '.$_GET['lat'].' ;'.PHP_EOL;
?>
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
	

	showName();
	// Create a style map.
	var styleMap = ge.createStyleMap('');

	// Create normal style for style map.
	/*var normalStyle = ge.createStyle('');
	var normalIcon = ge.createIcon('');
	normalIcon.setHref('http://maps.google.com/mapfiles/kml/paddle/red-circle.png');
	normalStyle.getIconStyle().setIcon(normalIcon);
*/
	/* Create highlight style for style map.
	var highlightStyle = ge.createStyle('');
	var highlightIcon = ge.createIcon('');
	highlightIcon.setHref('http://google-maps-icons.googlecode.com/files/girlfriend.png');
	highlightStyle.getIconStyle().setIcon(highlightIcon);
	highlightStyle.getIconStyle().setScale(2.0);
	*/
	
	//   styleMap.setHighlightStyle(highlightStyle);

	

		
		
	// Move the camera.
	var la = ge.createLookAt('');
	la.set( parseFloat(g_lng), parseFloat(g_lat), 1000, ge.ALTITUDE_RELATIVE_TO_GROUND, 0, 0, 200);
	ge.getView().setAbstractView(la);
	
}

function failureCB(errorCode) {}



function createPoint(title,lat,lon,placemark, now_houseAgency)
{
	//CREATE THE PLACEMARK
	placemark= ge.createPlacemark('');
	placemark.setName(title);
	
	//define a custom icon
	var selficon = ge.createIcon('');
	if(now_houseAgency=="cthouse")
		selficon.setHref('http://img404.imageshack.us/img404/2761/48289740.png');
	else if(now_houseAgency=="twhg")
		selficon.setHref('http://imageshack.us/a/img90/9743/19409143.png');
	else if(now_houseAgency=="etwarm")
		selficon.setHref('http://imageshack.us/a/img87/5581/ewt.png');
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
	
	if(g_lng==lat&&g_lat==lon){        
	 var balloon = ge.createHtmlStringBalloon('');
         balloon.setFeature(placemark); // optional
         balloon.setContentString(
            "<font size=5>"+title+"</br>I'm here!</br>Please buy meee~~</font>");
         balloon.setBackgroundColor('#FFF');
         ge.setBalloon(balloon);
	}

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
	
	xmlhttp.open("GET","output.xml",false);
	xmlhttp.send();
	xmlDoc=xmlhttp.responseXML; 

	x = xmlDoc.getElementsByTagName("MARKER");
	var placemark=new Array(x.length+1);
	for (var i = 0, count=0; i < x.length; i++){
		var now_lng=x[i].getElementsByTagName("LNG")[0].childNodes[0].nodeValue;
		var now_lat=x[i].getElementsByTagName("LAT")[0].childNodes[0].nodeValue;
		var now_houseAgency=x[i].getElementsByTagName("TYPE")[0].childNodes[0].nodeValue;
		if((parseFloat(g_lat)+0.005)>parseFloat(now_lat)&&(parseFloat(g_lat)-0.005)<parseFloat(now_lat)){
			if((parseFloat(g_lng)+0.005)>parseFloat(now_lng)&&(parseFloat(g_lng)-0.005)<parseFloat(now_lng)){			
				if (count >= 20 && (g_lng <> now_lng || g_lat <> now_lat))
					coutinue;
				else {
					createPoint(x[i].getElementsByTagName("NAME")[0].childNodes[0].nodeValue,
						parseFloat(x[i].getElementsByTagName("LNG")[0].childNodes[0].nodeValue),
						parseFloat(x[i].getElementsByTagName("LAT")[0].childNodes[0].nodeValue),
						placemark[i], now_houseAgency);	
					if(count >= 20)
						break;
					else
						count++;
					}	
				
				}
		
			}
		}
	}

google.setOnLoadCallback(init);
</script>




</head>
<body >



		
	

 </div>
<table >
<tbody>
<tr>
<td><div id="map3d" style="height:620px; width:820px;"></div></td>
<td></td>
</tr>
</tbody>
</table>
</body>
</html>

