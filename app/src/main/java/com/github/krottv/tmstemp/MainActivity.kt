package com.github.krottv.tmstemp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var tvCurStudent: TextView
    private lateinit var studentList: RecyclerView
    private lateinit var studentListAdapter: StudentListAdapter
    private var students: MutableList<Student> = mutableListOf(
        Student(1, 18, 1, "Sveta"),
        Student(2, 19, 1, "Sanya"),
        Student(3, 18, 2, "Vasya"),
        Student(4, 20, 3, "Kolya"),
        Student(5, 19, 2, "Hleb"),
        Student(6, 21, 4, "Kostya"),
        Student(7, 21, 1, "Alisa"),
        Student(8, 19, 3, "German"),
        Student(9, 18, 1, "Katya"),
        Student(10, 22, 4, "Darya")
    )
    private var currentStudentIndex: Int = 0
    private lateinit var secondActiviyStarter: ActivityResultLauncher<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCurStudent = findViewById<TextView>(R.id.tvCurStudent)
        studentList = findViewById<RecyclerView>(R.id.rwStudentList)
        studentListAdapter = StudentListAdapter(students)
        studentList.adapter = studentListAdapter
        studentList.layoutManager = LinearLayoutManager(this)

        val selectedStudent = savedInstanceState?.getParcelable<Student>("student")
        currentStudentIndex = getIndexByStudent(selectedStudent)

        showCurrentStudentName()

        val btnNextStudent = findViewById<Button>(R.id.btnNextStudent)
        btnNextStudent.setOnClickListener {
            currentStudentIndex++
            if (currentStudentIndex == students.size) {
                currentStudentIndex = 0
            }
            showCurrentStudentName()
        }

        val btnTranform = findViewById<Button>(R.id.btnTransformStudent)
        btnTranform.setOnClickListener {
            secondActiviyStarter.launch(students[currentStudentIndex])
        }

        secondActiviyStarter = registerForActivityResult(StudentResultContract()) {
            currentStudentIndex = getIndexByStudent(it)
            students[currentStudentIndex] = it
            showCurrentStudentName()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("student", students[currentStudentIndex])
    }

    private fun showCurrentStudentName() {
        tvCurStudent.setText(students[currentStudentIndex].name)
        studentListAdapter.selectItemInList(currentStudentIndex)
        studentList.post {
            studentList.layoutManager?.scrollToPosition(currentStudentIndex)
        }
    }

    private fun getIndexByStudent(selectedStudent: Student?) : Int {
        if (selectedStudent != null) {
            return students.indexOf(selectedStudent)
        }
        return 0
    }
}

class StudentResultContract : ActivityResultContract<Student, Student>() {
    override fun createIntent(context: Context, input: Student?): Intent {
        return Intent(context, SecondActivity::class.java).putExtra("student", input)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): Student? {
        return intent?.getParcelableExtra<Student>("student")
    }
}