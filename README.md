# AndroidExplorer
This project contains some simple examples of using Android features

State list drawable matching algorithm:

Going from top to bottom till find satisfying item.
What does 'satisfying' mean? It means that ONLY all declared states need to be satisfied (all undeclared do not matter).

Suppose we have next drawable set to a Button background:
```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@android:color/black" android:state_pressed="true"/>
    <item android:drawable="@android:color/holo_red_dark"/>
</selector>
```
If button is pressed then black color will be chosen. If not then red.

If button has next drawable set to background:
```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@android:color/holo_red_dark"/>
    <item android:drawable="@android:color/black" android:state_pressed="true"/>
</selector>
```
Then does not matter whether button is enabled or disabled red color will be chosen (because it is first satisfied).
