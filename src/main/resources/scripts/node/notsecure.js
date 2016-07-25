var request = require('request');
var rp = require('request-promise');
var config = require('./config');

var options1 = {
  method: 'POST',
  uri: config.Porandu_ROOT + '/api/notsecure',
  headers: {
 	'content-type': 'application/json'
  },
  json: true,
  body: {}
};


function notsecure() {
  return rp(options1)
   .then(function(parsedBody) {
      //var secret = parsedBody.secret;
	  //options2.headers['X-JOKO-AUTH'] = secret;
	  //return rp(options2);
	  console.log(parsedBody);
   }).catch(function(err){
      console.log('LOGIN err', err);
   });
   
};   
notsecure();
