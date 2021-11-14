# GreatMovies

Simple overview of use/purpose.

## Description

Greats Movie es un codigo de ejemplo para prueba tecnica empresa, sus funciones no estan completadas

Requerimientos solicitados


Requerimientos completados (Para Peliculas unicamente)
<ul>
  <Li>Peliculas y/o series categorizadas por Popular y Top Rated )</Li>
  <li>Detalle de pelicula y/o serie </Li>
  <li>Buscador de Pelicula y/o serie por nombre</Li>
  <li>Visualizacion de video en Detalle</Li>
  <li>transiciones /Animaciones entre un listado y detalle y carga inicial de peliculas (Recycler animation)</Li>
  <li>Api https://developers.themoviedb.org/</Li>
  <li>Usar Kotlin (Se me permition realizar prueba con Java)</Li>
  <li>Bibliotecas usadas (ver Dependencias)</Li>
  <li>Subir proyecto a Girhub</Li>
  <li>La app debe ser Escalable : arquitectura usada MVVM (Model - View - View Model)</Li>
  <li>Injectar Dependencias : Dagger 2</Li>
</ul> 
-Requerimientos no Completados
<ul>
 <li>La app debe funcionar offline</li>
 <li>-Pruebas Unitarias</li>
</ul>


## Desarrollado en java android MVVM, RxAndroid, Dagger 2, RxJava , ButterKnife, api desde https://developers.themoviedb.org proceso de consumo de respuestas JSON 

#Consideraciones
Basado en patron de diseño MVVM y programacion reactiva se realizo aplicacion online, la implementacion o sincronizacion de archivos de manera offline se implementaria 
MoviesREpository esta tendria los observadores necesarios para la sincronizacion con persistencia de datos DAO la clase MovieDataBase (Extiende de RoomDatabase)
Sincroniza los datos traidos desde la api y estos estarian disponibles para ser injectados en los viewmodels correspondientes 
interface MovileDao contendria las sentencias SQLite pertinentes para: insercion de datos, consulta y actualizacion

### Dependencies

* Android studio Artic Fox </br>
*def butterversion="10.2.3"
    implementation "com.jakewharton:butterknife-annotations:$butterversion"</br>
    annotationProcessor "com.jakewharton:butterknife-compiler:$butterversion"</br>
    implementation "com.jakewharton:butterknife:$butterversion"</br>
    //Dagger</br>
    def dagger = "2.40"//"2.23.2"</br>
    implementation "com.google.dagger:dagger:$dagger"</br>
    annotationProcessor "com.google.dagger:dagger-compiler:$dagger"</br>


    // ViewModel and LiveData</br>
    def lifecycle_version = "2.2.0"</br>
    implementation "androidx.lifecycle:lifecycle-extensions:$lifecycle_version"</br>

    /*// Room
    implementation "android.arch.persistence.room:runtime:$lifecycle_version"</br>
    annotationProcessor "android.arch.persistence.room:compiler:$lifecycle_version"*/</br>


    //Retrofit</br>
    def retrofit = "2.9.0"</br>
    implementation "com.squareup.retrofit2:retrofit:$retrofit"</br>
    implementation "com.squareup.retrofit2:converter-gson:$retrofit"</br>
    implementation "com.squareup.retrofit2:adapter-rxjava2:$retrofit"</br>

    //Gson Google</br>
    implementation 'com.google.code.gson:gson:2.8.9'</br>

    //Rxjava</br>
    def rxandroid = "2.1.1"</br>
    def rxjava = "2.2.9"</br>
    implementation "io.reactivex.rxjava2:rxandroid:$rxandroid"</br>
    implementation "io.reactivex.rxjava2:rxjava:$rxjava"</br>

    //picasso</br>
    implementation "com.squareup.picasso:picasso:2.71828@aar"</br>

    //wipelayout</br>
    implementation "com.github.omadahealth:swipy:1.2.3@aar"</br>

    //youtube player</br>
    implementation 'com.pierfrancescosoffritti.androidyoutubeplayer:core:10.0.5'</br>

### Installing</br>

* Archivo de configuracion de api_key  y url util.Constants</br>
* Any modifications needed to be made to files/folders</br>

### Descargar Apk de prueba

* https://drive.google.com/file/d/1SPdhAI8dLz1T9aBALXrLr_C6TiEYtOcO/view?usp=sharing






## Joaquin Martinez Marulanda



## Version History

* 0.1
    * Initial Release

## License

Este proyecto fue realizado para prueba practica de programacion en patron de diseño MVVM, RxJava, RXAndroid,Inyeccion de dependencia con Dagger2

## Acknowledgments


