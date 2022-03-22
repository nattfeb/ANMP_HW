package id.ac.ubaya.informatika.advweek4a.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.ac.ubaya.informatika.advweek4a.model.Student
import id.ac.ubaya.informatika.advweek4a.view.StudentDetailFragment
import id.ac.ubaya.informatika.advweek4a.view.StudentListFragment

class DetailViewModel(application: Application): AndroidViewModel(application){
    val studentLD = MutableLiveData<Student>()

    private val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(studentID : String) {
        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php?id=$studentID"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val result = Gson().fromJson<Student>(response, Student::class.java)
                studentLD.value = result

                Log.d("showvoley", response.toString())
            },
            {
                Log.d("showvoley", it.toString())

            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)

    }

}