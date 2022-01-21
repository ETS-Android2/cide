# Exercice 02

Escriu un programa que contesti a preguntes. El programa crearà un
socket stream i esperarà connexions. Quan arribi una connexió, llegirà els
missatges rebuts, byte a byte, fins que trobi el caràcter ASCII "?"(signe de
final d'interrogació). Quan això passi, construirà una frase amb tots els
bytes rebuts i contestarà amb un missatge. El contingut del missatge
dependrà de la frase rebuda:
- Si la frase és "Com et dius?", respondrà amb la cadena "Em dic Exercici
2".
- Si la frase és "Quantes línies de codi tens?", Respondrà amb el nombre
de línies de codi que tingui.
- Si la frase és qualsevol altra cosa, respondrà "No he entès la pregunta".