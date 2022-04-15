package com.github.krottv.tmstemp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.krottv.tmstemp.databinding.StudentItemBinding

class StudentListAdapter(
    private val students: MutableList<Student>)
    : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>() {

    class StudentViewHolder(val binding: StudentItemBinding) : RecyclerView.ViewHolder(binding.root)

    fun selectItemInList(index: Int) {
        var oldIndex = 0
        for (i in students.indices) {
            if (students[i].isSelected) {
                oldIndex = i
                break
            }
        }
        if (oldIndex != index) {
            students[oldIndex].isSelected = false
            notifyItemChanged(oldIndex)
        }
        students[index].isSelected = true
        notifyItemChanged(index)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val curStudent = this.students[position]
        with(holder.binding) {
            tvStudentTransformName.setText(curStudent.name)
            if (curStudent.isSelected) {
                tvStudentTransformName.addBorder()
            } else {
                tvStudentTransformName.setBackgroundResource(android.R.color.transparent)
            }
        }
    }

    override fun getItemCount(): Int {
        return students.size
    }
}