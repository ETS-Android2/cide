# Exercice 03

En aquesta pràctica heu de implementar un servei de control d&#39;estat de processos remots mitjançant heartbeat.

La funció d&#39;aquest servei és controlar que els seus clients estan actius, i detectar quan s&#39;han desactivat. Per això, els clients han de registrar-se al servidor quan arrenquen. Un cop s&#39;han registrat, han d&#39;enviar un missatge especial (batec del cor o heartbeat) al servidor a intervals de temps
regulars (cada 10 segons). Si al cap de 20 segons el servidor no ha rebut cap missatge per part d&#39;un client, el considerarà mort i avisarà per pantalla. Implementa aquest servei utilitzant sòcols Datagram.

HINTS: Se recomana utilitzar processos (unitat 2) i HashMap