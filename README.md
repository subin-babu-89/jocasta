#Jocasta

---

A simple app to look up information regarding your favorite star wars resource from swapi.dev

---

##### Instructions (to use the app)

1. Launch app

2. Navigate to the specific category

3. Scroll through the list to browse all the records supported by swapi

4. use the search box to look up a specific item

5. click on a list item to navigate to the detail page

6. Offline caching - records once fetched are cached locally. This means that if the application loses connectivity, it will continue to function with the data it has collected so far.

7. It is possible to navigate from one resource to another by clicking on the item in a resource detail page.


---

##### Instructions (to import the project in Android Studio)

Import the project as any android project in android studio. Since there are no external API keys to configure, you can directly proceed to run the imported project once the importing process is complete

---

##### Things I would have liked to spend more time on :

- [ ] Implement auto-complete for search
   I hope the person using this app can spell correctly. I wanted to implement a global search(and auto-complete) for all resources so that you did not have to go a particular category to search for information. The app does support impartial string queries. Unfortunately, the focus on the paging mechanism resulted in the autocomplete being unimplemented

- [ ] Generify a a lot of classes
  I can see classes like the Mediator classes, the Details recyclerview adapter and the Viewmodel have been generified. I wish I could have refactored them in a way that would have reduced a lot of similar code.

- [ ] Better UI/UX
  I will the first one to tell you that the app looks basic and I would love to jazz up the look a bit more. But I focused too much on functionality and (clearly) not enough on the UX/UI for the app

- [ ] Dedicate tablet & phone UI
