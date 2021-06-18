<!-- logo -->
<p align="center">
  <img width='300' src="img/logo-and-text.svg">
</p>

<!-- tag line -->
<h3 align='center'> StateLayout Android ! </h3>


## ☢️ What's a State-Layout ?

StateLayout can be used to manage the UI state of activity or fragment with very amazing animation using lottie.
e.g showing loading, error, empty screen(no data found), and content.

<strong>Need more state kindly ask me i will do implement it 😊

<br/>



## 🌻 Usage

Please note StateLayout can have only one direct child.

```xml
<com.shazdroid.statelayout.StateLayout
     android:id="@+id/stateLayout"
     android:layout_width="match_parent"
     android:layout_height="match_parent"
     app:state_backgroundColor="@color/white"
     app:state_textColor="@color/black">

     <!--Your main content goes here-->
     <androidx.appcompat.widget.LinearLayoutCompat
         android:id="@+id/contentView"
         android:layout_width="match_parent"
         android:layout_height="match_parent"
         android:orientation="vertical">
            

    </androidx.appcompat.widget.LinearLayoutCompat>
    <!--Content end-->
</com.shazdroid.statelayout.StateLayout>
```
There are some custom options below.

* To set background colour of stateLayout
```xml
app:state_backgroundColor="@color/white"
```
* To set the text colour 
```xml
app:state_textColor="@color/black"
```
  
  
## Kotlin
```kotlin
  val stateLayout = findViewById<StateLayout>(R.id.stateLayout)
  
  // show loading UI //
  stateLayout.showLoadingView()
  
  // show loading UI with params //
  stateLayout.showLoadingView("Your loading message",R.raw.lottie_animation_file)
  
  // show error UI //
  stateLayout.showErrorView()
  
  // show error UI with param //
  stateLayout.showErrorView("Your error message",R.raw.lottie_animation_file)
  
  // error UI also support retry option just pass the retry listener the button will be shown automatically //
  stateLayout.showErrorView("Your error message",R.raw.lottie_animation_file,this)
  
  // need to change retry button text you can do it as well //
  stateLayout.showErrorView("Your error message",R.raw.lottie_animation_file,"Retry button text",this)
  
  // show empty UI basically used in case if there is no search result or recycler view/list is empty // 
  stateLayout.showEmptyView()
  
  // show empty UI with param //
  stateLayout.showEmptyView("Your message",R.raw.lottie_animation_file)
  
  // show your content //
  stateLayout.showContent()
  
```



<br/>


## Installation
```gradle
dependencies {
   implementation 'com.github.shazDroid:stateLayout:0.1.0'
}
```
<br/>


## 💙 Contributing

PR's are welcome !

Found a Bug ? Create an Issue.

<br/>




## 💖 Like this project ?

Leave a ⭐ If you think this project is cool.

[Share with the world](https://github.com/shazDroid/stateLayout) ✨

<br/>




## 👨‍💻 Author

### Shahbaz Ansari

shahbazansari52@gmail.com

<br/>




## 🍁 Licence

**MIT**
