package com.github.krottv.tmstemp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.github.krottv.tmstemp.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_second)

        val student: Student = intent.getParcelableExtra<Student>("student")!!
        val newStudent = student.copy(name = "transformed_${student.name}")

        val btnTransform = findViewById<Button>(R.id.btnTransformAct)
        btnTransform.setOnClickListener {
            setResult(RESULT_OK, Intent().putExtra("student", newStudent))
            finish()
        }

        val tvTransformTitle = findViewById<TextView>(R.id.tvTransformStudentTitle)
        tvTransformTitle.setText(student.name)
    }
}