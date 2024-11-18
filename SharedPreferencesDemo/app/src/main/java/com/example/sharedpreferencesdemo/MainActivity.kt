package com.example.sharedpreferencesdemo

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView // Ensure TextView is imported
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var greetingTextView: TextView
    private lateinit var nameEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var loadButton: Button
    private lateinit var clearButton: Button
    private lateinit var ageEditText: EditText
    private lateinit var cityEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components inside onCreate
        greetingTextView = findViewById(R.id.tv_greeting)
        nameEditText = findViewById(R.id.et_name) // Corrected ID to match XML
        saveButton = findViewById(R.id.btn_save)
        loadButton = findViewById(R.id.btn_load)
        clearButton = findViewById(R.id.btn_clear)
        ageEditText = findViewById(R.id.et_age)
        cityEditText = findViewById(R.id.et_city)

        // Set up button click listeners
        saveButton.setOnClickListener {
            saveName()
        }

        loadButton.setOnClickListener {
            loadName()
        }

        clearButton.setOnClickListener {
            clearData()
        }
    }

    private fun saveName() {

        // Get the SharedPreferences object
        val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)

        // Open the editor to write to SharedPreferences
        val editor = sharedPreferences.edit()

        // Get the name from EditText and save it with key
        val name = nameEditText.text.toString() // Corrected variable name
        editor.putString("userName", name)

        // Show a confirmation message
        greetingTextView.text = "Name Saved!"

        val age = ageEditText.text.toString()
        editor.putString("userAge", age)

        val city = cityEditText.text.toString()
        editor.putString("userCity", city)

        greetingTextView.text = "Data Saved!"

        // Apply changes to save the data
        editor.apply()

        // Input validation
        if (name.isEmpty() || age.isEmpty() || city.isEmpty()) {
            greetingTextView.text = "Please fill all fields"
            return
        }
    }

    private fun loadName() {
        // Get the SharedPreferences object
        val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)

        // Retrieve the saved name using the key
        val savedName = sharedPreferences.getString("userName", "No name saved")
        val savedAge = sharedPreferences.getString("userAge", "No age saved")
        val savedCity = sharedPreferences.getString("userCity", "No city saved")

        // Display the saved name in the TextView
        greetingTextView.text = "Welcome, $savedName! Age: $savedAge, City: $savedCity"
    }

    private fun clearData() {
        val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Clear all data from SharedPreferences
        editor.clear()
        editor.apply()

        // Reset the greeting message and input field
        greetingTextView.text = "Data Cleared"
        nameEditText.text.clear()
    }
}

