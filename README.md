# Technologies:

**using the best practices combining the following techs to achieve the goal of the challenge**

* Clean Architecture with Repository Pattern and Applied UseCase.
* MVVM with Kotlin
* JetPack Compose with material design.
* Kotlin Coroutines.
* Retrofit and OkHttp with interceptor.
* Coil for loading images.
* Navigation compose to navigate between screens using deep linking.
* Hilt for Dependency Injection.
* Pagination.
* WebView.
* Navigation Compose.
* Tap navigation.
* Applying Dark and Light themes.


  
# The following user stories:

**Plant List :**
* As a user, I want to see a list view with the following plant’s data for each row :
* Image
* Common Name
* alid Name Publication Year
* The acceptance status of this species by IPNI
* If the Name, Year, or Status has an empty value, display NA.
* If there’s no received image, display a placeholder.
* When I select a zone filter from the available filters (All, Palestine, Sudan,
  Myanmar, Transcaucasus, Uzbekistan), only plants with the selected filter must
  be shown.
* Make sure that I can view all plants without filtration when the ALL tab is selected
  in the filters bar as presented in the design section.
* Make sure that the filters section is scrolling horizontally so that the last tab
  (Uzbekistan) could be fully displayed when scrolling to the right.
* When I scroll to the bottom of the list, the next page should load if available.
* Make sure that the ALL tab is selected when launching the app.

**Dark Theme**

<img width="350" alt="dark theme" src="https://github.com/user-attachments/assets/7e5417bb-7442-413d-b0f5-26c03133381f">

**Light Theme**

<img width="350" alt="Light theme" src="https://github.com/user-attachments/assets/03c85a3c-5b74-42a0-b9d2-e9f7d9a03939">
<img width="350" alt="Light theme loading" src="https://github.com/user-attachments/assets/d3de7f92-0c42-4eea-abe0-9c8534d0851f">



------------------------------------------------------------------------------------------------------------------------
**Plant Details:**
* When I select one plant (row), a new screen should appear showing the following plant details:
* Image
* Common Name
* Family
* Index -> With the following format: Bibliography (\n) Scientific Name
* Author
* Button with link to the plant’s more details from the Wikipedia website:
  https://en.wikipedia.org/wiki/{scientific_name}

* If the Name, Family, Index, or Author has an empty value, display NA.
* If there’s no received image, display a placeholder.
* The web page should be opened When I click on the plant’s more details button.
* Make sure that I can go back to the main screen.
  
**API to use:**
* The API documentation is found here :
* https://docs.trefle.io/docs/guides/getting-started
* You need to sign up to get your own ACCESS-TOKEN required for using this API :
* https://trefle.io/users/sign_up
* Example for the used endpoint :
* Getting plants in Palestine: https://trefle.io/api/v1/distributions/pal/plants
  - Method: GET
  - Parameters: token: {ACCESS-TOKEN} , page: 1
  - Where ‘pal’ is the zone ID of Palestine on trefle.io

* Please note that zone ids for Palestine, Sudan, Myanmar,Transcaucasus, Uzbekistan
  are ‘pal’, ‘sud’, ‘mya’, ‘tcs’, ‘uzb’ respectively.
  
**Details screen**

<img width="350" alt="details" src="https://github.com/user-attachments/assets/4c93085a-b3c8-4785-89e8-5909dc8f9d8f">
<img width="350" alt="details1" src="https://github.com/user-attachments/assets/ef6d8a6e-6c5e-4b1e-8883-8a7c6cfa96f7">


**Video Recorded**

   [NavigationSafeType.webm](https://github.com/user-attachments/assets/fad0bae0-d127-427d-93fd-ca5c53819eb5)


   [NavigationSafeType.webm](https://github.com/user-attachments/assets/71ac4426-5a7a-4c29-a3d4-00d1b78fb7ce)




