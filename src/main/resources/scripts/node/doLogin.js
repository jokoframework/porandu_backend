var login  = require('./login');
var rp     = require('request-promise');
var config = require('./config');


login.doLogin().then(function(resp){
  var secret = JSON.parse(resp).secret;
  console.log('secret is:');
  console.log(secret);
})
.catch(function(err){
  console.log(err);
});

