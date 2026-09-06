# Mood Tracker (MoodMate)

A lightweight Android application designed to help users track their daily emotional well-being. The app allows users to log their mood, write brief notes, and select specific dates for their entries.

## 🚀 Features

- **Mood Logging:** Choose from a variety of moods (Happy, Sad, Excited, Calm, Angry, Okay) using an intuitive dropdown.
- **Custom Notes:** Attach personal notes to each mood entry to provide context.
- **Date Picker:** Backdate or schedule mood entries using a native Android DatePickerDialog.
- **Persistent Storage:** All data is saved locally on the device using a SQLite database.
- **User Specific:** Supports individual logging by username.

## 🛠️ Tech Stack

- **Language:** Java
- **Database:** SQLite (local storage)
- **UI Components:** 
    - `AppCompatActivity`
    - `Material Components`
    - `Spinner` for mood selection
    - `DatePickerDialog` for date management
- **Architecture:** Android Jetpack libraries (AppCompat, ConstraintLayout)

## 📂 Project Structure


## ⚙️ Installation & Setup

1. **Clone the repository:**
   ```bash
   git clone https://github.com/YOUR_USERNAME/Mood.git
   ```
2. **Open in Android Studio:**
   - Go to `File > Open` and select the `Mood` project folder.
3. **Sync Gradle:**
   - Wait for Android Studio to finish indexing and syncing the `build.gradle` dependencies.
4. **Run the app:**
   - Connect an Android device or start an emulator and click the **Run** button.

## 📝 Usage

1. Open the app and navigate to the **Add Mood** screen.
2. Select your current mood from the dropdown menu.
3. (Optional) Enter a note about how you are feeling.
4. Use the **Date** button to change the entry date if needed.
5. Tap **Save** to store your entry in the database.

## 📄 License
This project is for educational purposes. Feel free to modify and use it for your own personal projects.
