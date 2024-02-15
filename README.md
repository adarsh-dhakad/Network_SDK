# Network SDK for Android

Network SDK provides convenient tools for making network calls and caching responses in Android applications.

## Installation

### Step 1: Add the JitPack repository to your build file

Add the following line to your project's root `build.gradle` file:

```groovy
        allprojects {
            repositories {
                // Other repositories...
                maven { url 'https://jitpack.io' }
            }
        }
```        

### Step 2: Add the dependency
Add the following dependency to your app module's build.gradle file:

``` groovy
    dependencies {
        implementation 'com.github.adarsh-dhakad:Network_SDK:1.0.7'
    }
```
#### Features
Paging 3 Integration: Utilize the power of Paging 3 for efficient network data loading and caching.

### Usage
Initialize following modules 
1. databaseModule
2. networkModule
   
 in your application class

 example -  

     startKoin {
            androidContext(this@MovieListApplication)
            modules(listOf(networkModule, databaseModule))
        }

 full code - https://github.com/adarsh-dhakad/Movies_List_App_DevRev

### Contributing
Contributions are welcome! If you encounter any issues or have suggestions for improvement, please feel free to open an issue or submit a pull request.
