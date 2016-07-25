# Scripts node

Existen sripts node en `src/main/resources/scripts/node`

## Utilizaci�n

`$ cd src/main/resources/scripts/node`
`$ npm install`
`$ node abrirLote.js`

## Descripci�n de scripts
Cada uno de estos scripts se comunica con el Porandu corriendo el localhost.

abrirLote.js       # Hace un login y abre un lote
cerrarLote.js      # Hace un login y cierra el lote actual
cerrarLoteForce.js # Forza el answer de un lote. Observaci�n: la respuesta a la ejecuci�n es un BAD_REQUEST pero el lote se cierra igual
consultarAnde.js   # Simula una CONSULTA a Ande, con NIS: 1234567
doLogin.js         # Hace un login con user: sodep, pass: s0dep2016
pagarAnde.js	   # Simula un pago a Ande, con NIS: 1234567

## Configuraci�n avanzada

Si se quiere ejecutar los scripts enviando peticiones a un Porandu externo se debe modificar `config.js`
```
Porandu_ROOT: 'http://localhost:9081/joko'
```