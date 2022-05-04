
# B4F Android SDK!

[![Jitpack](https://jitpack.io/v/Brand4Fans/B4FSDK-Android.svg)](https://jitpack.io/#Brand4Fans/B4FSDK-Android)

[Technical documentation](https://bitbucket.org/baturamobile/b4f-sdk-android/src/master/)

# Instalation

Add  in build.gradle in rootproyect
```xml
maven { url "https://jitpack.io" }
```

Add in build.gradle in the app (You need to get the lastest version)
```xml
implementation 'com.github.Brand4Fans:B4FSDK-Android:{version}'
```

# Usage

## Rest-Services
Init B4F with the applicationContext and the apiKey we provide you. 
```kotlin
B4F.init(applicationContext,apiKey)
```

B4F is a singleton you can only have one instance at once

```kotlin
B4F.single()
```

### Authentification
[Auth Technical documentation](https://appB4F.github.io/B4FSDK-Android/B4F-library/com.batura.B4Flibrary.rest/-auth/index.html)

Once you have init with the api key you need login with the corresponding user identification. If your wan't the push feature you need to send the FCM ID Push token
```kotlin
//The devicePushToken is not necessary
B4F.single().auth.setUserData("userId","devicePushToken")  
//If you want change the api key
B4F.single().auth.setApiKey("proyectApiKey")  
//When you update the devicePushToken you need to refresh the pushToken
B4F.single().auth.refreshPushToken("newDevicePushToken")
```

### Rest 

[Rest documentation](https://appB4F.github.io/B4FSDK-Android/B4F-library/com.batura.B4Flibrary.rest/index.html
)

You may find the result of the query result has encapsuled in a class with name "Either"
```kotlin
val response = Either<B4FError, NewsList>

//If is true the call returned a error
response.isLeft  
//get the B4FError
result.getLeft()
//If is true the call has a correct response
response.isRight
//You get te response
result.getLeft()
```
[B4F error](https://appB4F.github.io/B4FSDK-Android/B4F-library/com.batura.B4Flibrary.rest.error/-B4F-error/index.html)

## SmartTag
if you want to use the Tag delegate  you will  need to implement the lifecycle methods. 
Example bellow
```kotlin
val B4FDelegate = B4FDelegate()
  
override fun onCreate(savedInstanceState: Bundle?) {  
  super.onCreate(savedInstanceState)  
  setContentView(R.layout.activity_main)  
  
  //In the onCreate delegate you need to pass the activity
  B4FDelegate.onCreate(this){
  //In this function function you will receive the result of the RFID TAG  
  }  
  
  //Change to true when you want activate the reader  
  B4FDelegate.changeStatusReadable(true)
}  
  
override fun onPause() {  
  super.onPause()  
  B4FDelegate.onPause()
}  
  
override fun onResume() {  
  super.onResume()  
  B4FDelegate.onResume()
}  
  
override fun onDestroy() {  
  super.onDestroy()  
  B4FDelegate.onDestroy()
}
```
