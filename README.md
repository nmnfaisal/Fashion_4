# Maleyat Trial Task

This test project contains all functionality that was requested in the document. More details are mentioned below.

### Developed Using
- Kotlin
- Jetpack Compose
- Hilt - DI
- ViewModel
- Coroutines - Flow
- Room
- Retrofit
- Coil (Image Loading)

## Application Screen Shot
![Screenshot (56)](https://github.com/nmnfaisal/Fashion_4/blob/main/dev-ss.jpeg)

## Architecture
Application is built using Clean-MVVM and Modern Android Architecture, consisting of Repository layer, which uses local (Room DB) and remote (Retrofit) data sources, UseCase Layer containing business logic, ViewModel and finally a Composable UI. The ViewModel and UI communicate through state and events. UI send events to ViewModel and UI observes the state from the ViewModel.
Separate Data classes have been made for each layer and data source.
- Entity for Room DB (can be mapped to Domain Model)
- DTO for Retrofit (can be mapped to Entity/Database Model)
- Domain Model (normal data class used for displaying data)
![system_design (1)](https://github.com/nmnfaisal/Fashion_4/blob/main/arch-sysDesign.jpg)


## References:
**Modern Android Architecture**: https://developer.android.com/topic/architecture

**Repository Pattern:** https://developer.android.com/codelabs/basic-android-kotlin-training-repository-pattern#0

**Clean-MVVM:** https://www.youtube.com/watch?v=8YPXv7xKh2w&t=1322s **by Philipp Lackner**

**Testing:**

https://developer.android.com/training/data-storage/room/testing-db

https://developer.android.com/jetpack/compose/testing

https://developer.android.com/codelabs/jetpack-compose-testing

