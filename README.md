# AGS vs MOA (TSP battle)
Repositorio para el proyecto final de la materia Cómputo Evolutivo (2017-1).
Comparación entre el Algoritmo Genético simple y el Algoritmo de optmización inspirado en Magnetismo.

## Integrantes
- Andrea González Vargas
- Carlos Gerardo Acosta Hernández

###Ejecución
El proyecto deberá ser compilado con **apache-ant**.

Se cuenta con los siguientes TARGETs:
- jar (por defecto)
- build
- run

Si se utiliza `ant` a secas, se generará un JARFILE con los compilados del proyecto y las bibliotecas.
Que podemos ejecutar de la siguiente forma:
```
[user@host /]$ java -jar MOA.jar <ruta del archivo .tsp>
```

Si se utiliza `ant build`, únicamente se compilaran todas las clases del proyecto (tal vez no queremos un jar o queremos especificar la ubicación de las bibliotecas).
Para ejecutar si utilizamos esta opción:
```
[user@host /build/]$ java -cp ../lib/*:. Main
```

Si se utiliza `ant run`, se ejecutará la clase principal del proyecto *Main*, sin embargo, es preciso utilizar el argumento necesario para el archivo de la siguiente forma:
```
[user@host /]$ ant run -Dfile <ruta del archivo .tsp>
```


