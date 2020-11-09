#if($file == "exercice")${serieEjercicio}${numeroDeEjercicio}${version}#end
#if($file != "")#parse ("${file}.java")#else#parse("default.java")#end