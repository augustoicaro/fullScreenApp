fullScreenApp
=============

Full screen android application with Action Bar Compart. Support android 2.2+ including Kit Kat version.

## Dependencies

* Android Support Library v7 - Action Bar Compat
  * If you don't know setup or download, please read this: [https://developer.android.com/tools/support-library/setup.html](https://developer.android.com/tools/support-library/setup.html)

## Inspiration

I was using Eclipse IDE and I created a new default  full screen application.
This example works perfect on android 2.2 - 4.2 version, but had some issues
with kit kat.

So I decided create a example application which fix the issue and use Action
Bar Compat. In my fix when your system is in immersive mode it only show/hide
action bar, if not, it show/hide action bar, status bar and navigation bar.

I also used Action Bar overlay. With this trick the transitions are more smooth
between screen modes, and prevent layout rezise all time( It cause white spaces
before transitions start).

## References

* Eclipse default full screen application
* Codes on [http://developer.android.com/reference/android/view/View.html#setSystemUiVisibility(int)](http://developer.android.com/reference/android/view/View.html#setSystemUiVisibility(int))
* [https://developer.android.com/samples/ImmersiveMode/index.html](http://developer.android.com/reference/android/view/View.html#setSystemUiVisibility(int))
* [https://developer.android.com/samples/AdvancedImmersiveMode/index.html](http://developer.android.com/reference/android/view/View.html#setSystemUiVisibility(int))

