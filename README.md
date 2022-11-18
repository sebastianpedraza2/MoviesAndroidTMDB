# **MOVIES TMDB**
## Arquitectura utilizada:

Escogí una arquitectura modularizada de manera híbrida, esto quiere decir que primero se modulariza por feature y después por layer, cabe aclarar que para un proyecto real de este tamaño seguramente hubiera escogido una arquitectura monolítica


<img width="237" alt="image" src="https://user-images.githubusercontent.com/46971682/202617540-bdd536fc-a2e7-48cb-bab0-03c1af470de1.png">
Escogí esta arquitectura que se caracteriza por tener una alta granularidad con el fin de poder lograr faster Gradle building time, especialmente a medida que el proyecto escala, mejor separación de concerns y reusabilidad de los módulos (librerías), además se prohíbe la comunicación entre módulos a menos que exista una dependencia explícita, haciendo que cada módulo sea independiente y forzando un bajo acoplamiento con respecto a los demás componentes.

Para este proyecto el domain layer funciona como dependencia principal tanto de las capas de présentation como de data

## Patrón de présentation: 
Escogí MVVM clean, organizado de la siguiente manera: 

* **Presentation**: Compose screens o views, viewmodels y cualquier otro stateholder
* **Domain**: Use cases, domain models y abstracciones para los repos para que la comunicación entre layer se de por medio de abstracciones (siguiendo el dependency inversion principle)

* **Data**: Diferentes data sources (local, remote, etc), entities (DTOs y ORMs), mappers, Retrofit service, Room DB, etc.

## Inyección de dependencias:

Para este proyecto escogí Hilt como framework de DI

El módulo (gradle) de cada feature contiene la lógica de inyección de dependencias de ese feature, sin embargo, hay un módulo (Hilt) principal en “app”

## Navegación: 

Utilice navigation component ya que provee soporte para compose, es decir, usando la función de NavHost para poder crear un “Navigation graph” programáticamente.

## Funcionalidad:
Para la pantalla de lista de películas implemente dos filtros:
* Top rated movies
* Upcoming movies


El botón de “No filter” trae la lista inicial que consume el servicio de “popular movies”

<img width="309" alt="image" src="https://user-images.githubusercontent.com/46971682/202622652-93f4cb9f-8292-442e-88e4-e032cb38ce33.png">    <img width="308" alt="image" src="https://user-images.githubusercontent.com/46971682/202622791-c3452067-d0b2-4fa9-a10d-1428cd4d8be5.png">    <img width="308" alt="image" src="https://user-images.githubusercontent.com/46971682/202622902-c81719da-e880-4270-8ad8-e45148c3b442.png">

Se implementó paginación para la lista de películas con cada filtro

Se puede añadir una película a los “favoritos” y persistirá por medio de una Room DB 

<img width="391" alt="image" src="https://user-images.githubusercontent.com/46971682/202623236-b862a75c-3574-428d-aef6-ba90e3dc9ccf.png">

Pantalla del detalle de cada película:

<img width="303" alt="image" src="https://user-images.githubusercontent.com/46971682/202623710-8fa25241-d006-4d9a-ac64-27d96fefa06f.png">

Animaciones con lottie para las pantallas de carga

<img width="169" alt="image" src="https://user-images.githubusercontent.com/46971682/202625784-bd617eb8-1f77-40b8-a834-65d1726a14ee.png">


