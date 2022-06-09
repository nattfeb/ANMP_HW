package id.ac.ubaya.informatika.advweek4a.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.informatika.advweek4a.R
import id.ac.ubaya.informatika.advweek4a.databinding.FragmentStudentDetailBinding

import id.ac.ubaya.informatika.advweek4a.viewmodel.DetailViewModel
import id.ac.ubaya.informatika.advweek4a.viewmodel.ListViewModel
import id.ac.ubaya.informatika.anmp_uts_160419080.util.loadImage
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.fragment_student_detail.txtName
import kotlinx.android.synthetic.main.student_list_item.*
import kotlinx.android.synthetic.main.student_list_item.view.*


class StudentDetailFragment : Fragment(), ButtonUpdateClickListener {
    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding : FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_student_detail, container, false)
        dataBinding = DataBindingUtil.inflate<FragmentStudentDetailBinding>(
            inflater, R.layout.fragment_student_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null) {
            val studentID = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentID

            viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            viewModel.fetch(studentID)

            observeViewModel()

            dataBinding.listener = this
        }
    }

    fun observeViewModel() {
        //Jika gambar menggunakan dataBinding (seperti pada ppt W11), terjadi error.
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
//            txtId.setText(it.id)
//            txtName.setText(it.name)
//            txtDob.setText(it.dob)
//            txtPhone.setText(it.phone)
//            studentPic.loadImage(it.photoUrl.toString(), progressBarDetail)
            dataBinding.student = it

        })
    }

    override fun onButtonUpdateClick(v: View) {
        Toast.makeText(v.context, "Student Data has been updated", Toast.LENGTH_SHORT).show()
    }

}