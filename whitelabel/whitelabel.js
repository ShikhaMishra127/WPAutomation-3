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
  var inFUNC = getURLParameter('FUNC');
  var inTOK = getURLParameter('TOK');
  var inOID = getURLParameter('OID');
  var inCSS = getURLParameter('CSS');
  var inEBO = getURLParameter('EBO');
  var _baseURL = "https://" + getURLParameter('LOC') + ".perfect.com";
  var _csspath = "";
  var _wprocure = _wprocure || [];

  _wprocure.push(['_targetDiv','webprocurediv']);

  if (inCSS == "Y") {
        _csspath = _baseURL + '/html/whitelabel/mockups/test.css';
  }

  _wprocure.push(['_csspath', _csspath]);


  if (inFUNC == "whitelabellogin") {
                _wprocure.push(['_height','500px']);
                _wprocure.push(['_width','700px']);
  }

  if (inOID)  { _wprocure.push(['_oid', inOID]); }

  if (inFUNC == "whitelabelreg")  {
          var _param1 = 'vid'.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
          var regex = new RegExp("[\\?&]" + _param1 + "=([^&#]*)"),

          results = regex.exec(location.search);
          _param1 = results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, null));
          _wprocure.push(['_uid', inTOK]);
          _wprocure.push(['_vid', _param1]);
          _wprocure.push(['_provider', _baseURL]);

          (function() {
                   var im = document.createElement('script'); im.type = 'text/javascript';
                   im.src = _baseURL + "/js/supplierreg/loader.js";
                   var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(im, s);
                   var fm = document.createElement('script'); fm.type = 'text/javascript';
                   fm.src = _baseURL + "/js/supplierreg/FrameManager.js";
                   s.parentNode.insertBefore(fm, s);
           })();
}
else if (inFUNC == "whitelabelcb")  {
    _baseURL += "/wp-web-public";
    var _loginURL = "https://missouribuys.mo.gov/login.html";
    var _wprocure = _wprocure || [];
    _wprocure.push(['_wpcall', 'contractboard']);
    _wprocure.push(['_provider', _baseURL]);
    _wprocure.push(['_loginProvider', _loginURL]);
    _wprocure.push(['_customerid', inEBO]);

        (function() {
        var im = document.createElement('script'); im.type = 'text/javascript';
        im.src = _baseURL + "/assets/WhiteLabel.js";
        var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(im, s);
        var fm = document.createElement('script'); fm.type = 'text/javascript';
        fm.src = _baseURL + "/assets/FrameManager.js";
        s.parentNode.insertBefore(fm,s);
      })();
}
else {
        _wprocure.push(['_guid', inTOK]);
        _wprocure.push(['_wpcall', inFUNC]);
        _wprocure.push(['_provider', _baseURL]);

         (function() {
                   var im = document.createElement('script'); im.type = 'text/javascript';
                   if (inFUNC == "whitelabelcbb") {
                           im.src = _baseURL + "/js/whitlabelutil/whitlabelcbutil.js"; }
                   else {
                           im.src = _baseURL + "/js/whitlabelutil/whitlabelutil.js"; }

                   var s = document.getElementsByTagName('script')[0];
                   s.parentNode.insertBefore(im, s);
                   var fm = document.createElement('script'); fm.type = 'text/javascript';
                   fm.src = _baseURL + "/js/whitlabelutil/FrameManager.js";
                   s.parentNode.insertBefore(fm, s);
           })();
  }