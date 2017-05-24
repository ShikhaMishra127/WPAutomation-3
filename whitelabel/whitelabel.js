function getURLParameter(sParam)
{
    var sPageURL = window.location.search.substring(1);
    var sURLVariables = sPageURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++)
    {
        var sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] == sParam) {
            return sParameterName[1];
        }
    }
}

var inFNC = getURLParameter('FUNC');
var inTOK = getURLParameter('TOK');
var inOID = getURLParameter('OID');
var inCSS = getURLParameter('CSS');
var inEBO = getURLParameter('EBO');
var _baseURL = "https://" + getURLParameter('LOC') + ".perfect.com";
var _csspath = "";

if (inCSS == "Y") {
	_csspath = _baseURL + '/html/whitelabel/mockups/test.css';
}

var _param1 = 'vid'.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
var regex = new RegExp("[\\?&]" + _param1 + "=([^&#]*)"),

results = regex.exec(location.search);
_param1 = results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, null));

var _wprocure = _wprocure || [];

if (inFNC == "whitelabellogin") {
			_wprocure.push(['_height','300px']);
			_wprocure.push(['_width','700px']);
}

if ((inFNC == "contractboard")||(inFNC == "bidboard")) {
	_baseURL += "/wp-web-public";
}

_wprocure.push(['_targetDiv','webprocurediv']);
_wprocure.push(['_guid', inTOK]);
_wprocure.push(['_uid', inTOK]);
_wprocure.push(['_wpcall', inFNC]);
_wprocure.push(['_provider', _baseURL]);
_wprocure.push(['_oid', inOID]);
_wprocure.push(['_loginProvider', "https://missouribuys.mo.gov/login.html"]);
_wprocure.push(['_customerid', inEBO]);
_wprocure.push(['_csspath', _csspath]);
_wprocure.push(['_vid', _param1]);

		
(function() {
	
	var im = document.createElement('script');	im.type = 'text/javascript';
	var fm = document.createElement('script');	fm.type = 'text/javascript';
	var s = document.getElementsByTagName('script')[0];
	
	if ((inFNC == "contractboard")||(inFNC == "bidboard")) {
		im.src = _baseURL + "/assets/WhiteLabel.js";
		fm.src = _baseURL + "/assets/FrameManager.js";
		
	} else if (inFNC == "whitelabelreg") {
		im.src = _baseURL + "/js/supplierreg/loader.js";		
		fm.src = _baseURL + "/js/supplierreg/FrameManager.js";
		
	} else {
		im.src = _baseURL + "/js/whitlabelutil/whitlabelutil.js";
		fm.src = _baseURL + "/js/whitlabelutil/FrameManager.js";	
	}

	s.parentNode.insertBefore(im, s);
	s.parentNode.insertBefore(fm, s);
})();
