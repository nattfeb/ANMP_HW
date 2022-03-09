package id.ac.ubaya.informatika.advweek4a.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.informatika.advweek4a.R
import id.ac.ubaya.informatika.advweek4a.viewmodel.DetailViewModel
import id.ac.ubaya.informatika.advweek4a.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_student_detail.*


class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch()

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner, Observer {
            txtId.setText(it.id)
            txtName.setText(it.name)
            txtDob.setText(it.dob)
            txtPhone.setText(it.phone)
        })
    }

}