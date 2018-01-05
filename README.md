# Agera-Repositories

In [Hello World By Agera](https://github.com/AnkitDroidGit/Hello-World-By-Agera) project, we learned about `Observable`, `Updatable`, `Supplier` and `Receiver`.
 
 The most important concept in Agera is the `Repository`. Repositories receive, supply, and store data and emit updates. The interfaces `Observable`, `Supplier` and `Receiver` are combined into two types of repositories:

In our [hello world code](https://github.com/AnkitDroidGit/Hello-World-By-Agera), you could replace 

    implements Observable, Supplier<String>, Receiver<String>

with
  
     implements MutableRepository<String>
  
  but Agera provides a Repository factory, so remove the `MyDataSupplier` class altogether and replace
  
      MyDataSupplier myDataSupplier = new MyDataSupplier();
  
  with 
  
     MutableRepository<String> mStringRepo = Repositories.mutableRepository("Initial value by Agera with Repository");
  
  `mutableRepository()` creates a repository similar to our previous implementation, but that is thread-safe and has a more sophisticated update dispatcher, so let's use that from now on.
  
  Also, remove the `updatables` (with `removeUpdatable()`) when you know you're done with them. In our example this is not needed but it is a good practice: it avoids potential leaks and prevents updating destroyed views. The class is now much shorter:
  
     public class JavaMainActivity extends AppCompatActivity {
         //    MyDataSupplier myDataSupplier = new MyDataSupplier();
         //    Updatable updatable;
         private MutableRepository<String> mStringRepo;
         private Updatable mLoggerUpdatable;
  
  
         @Override
         protected void onCreate(@Nullable Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.activity_main);
  
            mStringRepo = Repositories.mutableRepository("Initial value by Agera with Repository");
  
           final TextView textView = findViewById(R.id.textView);
  
              // Create an Updatable
              //        updatable = new Updatable() {
              //            @Override
              //            public void update() {
              //                Log.d("AGERA", myDataSupplier.get());
              //                textView.setText(myDataSupplier.get());
              //            }
              //        };
              //        myDataSupplier.addUpdatable(updatable);
  
              //        myDataSupplier.accept("Hello Agera!");
  
  
              // Create an Updatable by Respository
          mLoggerUpdatable = () -> {
              Log.d("AGERA", mStringRepo.get());
              textView.setText(mStringRepo.get());
          };
         }
    
          @Override
          protected void onStart() {
              super.onStart();
              mStringRepo.addUpdatable(mLoggerUpdatable);
  
              // Change the repository's value
              mStringRepo.accept("Hello World by Agera with Repository");
          }
  
        @Override
         protected void onStop() {
            mStringRepo.removeUpdatable(mLoggerUpdatable);
           super.onStop();
         }
     }
  
  ## In summary:
  * Agera uses very simple interfaces you should be aware of: `Observable`, `Updatable`, `Supplier` and `Receiver`. These are combined in the Repository and MutableRepository interfaces.
  * Agera provides a factory of implementations of these simple Repositories: `Repositories.mutableRepository(T)`and `Repositories.repository(T)` for immutable repositories.
    
    
  ## Next Step
   
  Lets Learn [Complex Repository Here](https://github.com/AnkitDroidGit/Agera-ComplexRepository)
  
  ## let's connect to learn together
  - [Twitter](https://twitter.com/KumarAnkitRKE)
  - [Github](https://github.com/AnkitDroidGit)
  - [LinkedIn](https://www.linkedin.com/in/kumarankitkumar/)
  - [Facebook](https://www.facebook.com/freeankit)
  - [Slack](https://ankitdroid.slack.com)
  - [Stackoverflow](https://stackoverflow.com/users/3282461/android)
- [Android App](https://play.google.com/store/apps/details?id=com.freeankit.ankitprofile)

  
  
  ### License
  
      Copyright 2017 Ankit Kumar
      
      Licensed under the Apache License, Version 2.0 (the "License");
      you may not use this file except in compliance with the License.
      You may obtain a copy of the License at
  
         http://www.apache.org/licenses/LICENSE-2.0
  
      Unless required by applicable law or agreed to in writing, software
      distributed under the License is distributed on an "AS IS" BASIS,
      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
      See the License for the specific language governing permissions and
      limitations under the License.
