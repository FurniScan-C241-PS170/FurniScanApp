# FurniScan Mobile App

**Introduction**
Welcome to FurniScan, the mobile application that allows you to scan furniture and display detailed product information. This readme provides an overview of the FurniScan mobile app, its features, and the technologies used in its development.

**About the Project**
The FurniScan mobile app follows the MVVM (Model-View-ViewModel) architecture pattern. In this pattern, the user interface components, like activities and fragments, observe real-time data updates from the view model. The view model retrieves the observed data from the data source layer, which consists of the model and repository. The data source layer serves as a connection between the API responses and the presentation layer (view and view model), enabling smooth data access for users.

**Mobile Tech Stack**
The FurniScan mobile app is built using the following technologies:

- **Android Studio**: The official IDE for Android app development.
- **Kotlin Programming Language**: A modern programming language for developing Android apps.
- **Viewpager**: A component for implementing swipeable views in the app.
- **Datastore**: A data storage solution for managing app preferences and settings.
- **Camerax**: A camera library for capturing furniture images within the app.
- **Jetpack Library**:
  - Lifecycle: Provides lifecycle-aware components for managing Android app lifecycle.
  - Navigation: Handles navigation and deep linking within the app.
  - UI: Offers UI components and tools for creating a polished app interface.
  - Data: Provides components for working with data, including LiveData and ViewModel.
- **Retrofit2**: A type-safe HTTP client for making API requests.
- **Glide**: A powerful image loading and caching library for displaying furniture images in the app.


**Project Installation Guide**
To install and run the FurniScan mobile app on your local machine, follow the steps below:

1. Clone the project repository from GitHub:
```
git clone https://github.com/FurniScan-C241-PS170/FurniScanApp.git
```
2. Open the project in Android Studio.

3. Build and run the app on an emulator or physical device.

To design the user interface, use Figma, here is the link:
[Design Figma](https://www.figma.com/design/aDrVhXEeVmxsrhjWO8ye3d/FurniScan-C241-PS170?node-id=0-1&t=vrainglLZTxNH4te-1)

For more detailed instructions and troubleshooting tips, please consult the project's GitHub repository:
[GitHub Repository](https://github.com/FurniScan-C241-PS170/FurniScanApp)
