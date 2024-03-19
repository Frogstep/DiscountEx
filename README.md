# DiscountEx
**This is a demonstration application that displays RSS feeds from the CNN news site.**

**To run the application:**

1. Open the project in the latest version of Android Studio.
2. Build the project.
3. Run the application on an Android emulator or a connected real device.

**Please note:**

* An internet connection is required to download the feeds.
* If the feed download fails, an error message will be displayed.
* The application checks for updates every 5 seconds. 
* If the publication date in a channel (Travel, Sport, or Entertainment) is different from the last downloaded date, all the items in that channel will be replaced with the new ones. 
* If the publication date is the same, the update will be skipped.



This application is built with a clean architecture approach and utilizes the MVVM design pattern.

To improve clarity and consistency:

**Each channel is visually distinguished by a unique background color:**

* **Travel:** Yellow
* **Sports:** Blue
* **Entertainment:** Green


**Two sample UI integrity tests have been added to the first application screen. To run these tests, execute the following command from the command line interface (CLI):**

```
./gradlew connectedAndroidTest
```


