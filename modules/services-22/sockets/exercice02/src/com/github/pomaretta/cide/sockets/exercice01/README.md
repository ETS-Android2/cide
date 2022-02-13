# Exercice 01

En aquesta pràctica crearem un servei de transferència de fitxers simple.

Per aquesta implementació el servidor només tindrà dues instruccions
implementades:

- LLISTA: quan rebi aquesta instrucció enviarà una llista de tots els ids dels fitxers disponibles. (un sol id per línia)
- OBTENIR &lt;id_fitxer&gt;: quan rebi aquesta instrucció enviarà el contingut del fitxer sol·licitat (id) en format de text plà. Si se sol·licita un id inexistent. El servidor retornarà un -1 al client.

S’utilitzarà socket stream per tota la comunicació.

El servei estarà actiu en el port 44001.

La part client, tindrà un petit menú on seleccionar quina operació vol
realitzar.