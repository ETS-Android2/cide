# Exercice 6

Crea una versió generalitzada dels programes de l'exercici 4, per fer
possible que el token es passi entre un grup de 2 o més programes, en
forma d'anell. Cadascun dels programes s'ha d'arrencar indicant com a
arguments d'entrada la seva posició en l'anell i la mida de l'anell. El
programa que es trobi en la posició número 1 (el primer), generarà el
missatge "token" i l'hi enviarà al programa 2. Quan aquest el rebi l'hi
passarà al 3, i així successivament. Quan el rebi l'últim programa, el
trametrà de tornada al nombre 1. Quan el número 1 el rebi, la seqüència
s'interromprà (el testimoni haurà donat una volta completa a l'anell).
S'han de complir a més les següents restriccions:
- Tots els programes han de tenir el mateix codi font. Es tracta, per tant, del
mateix programa, però executat amb diferents paràmetres.
- El programa ha de permetre un nombre variable d'elements en l'anell. La
mida de l'anell s'especificarà per endavant.
Es pot programar utilitzant sòcols stream o sòcols Datagram.