package com.alserdar.gads2020leaderboard.project_submission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import cn.pedant.SweetAlert.SweetAlertDialog
import com.alserdar.gads2020leaderboard.R
import com.alserdar.gads2020leaderboard.project_submission.network_post.RetrofitPostData.GadsApi.retrofitSubmitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectSubmission : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_submission)

        val subbut: Button =  findViewById(R.id.submitButton)
        val pressBackHere: ImageView =  findViewById(R.id.pressBack)



        pressBackHere.setOnClickListener(View.OnClickListener {
            super.onBackPressed()
        })



        val firstName = findViewById<EditText>(R.id.firstNameEditText)
        val lastName = findViewById<EditText>(R.id.lastNameEditText)
        val emailAddress = findViewById<EditText>(R.id.emailEditText)
        val githubProjectLink = findViewById<EditText>(R.id.githubLinkEditText)


        subbut.setOnClickListener(View.OnClickListener {

            if (firstName.text.isNotEmpty() ||
                lastName.text.isNotEmpty() ||
                emailAddress.text.isNotEmpty() ||
                githubProjectLink.text.isNotEmpty()) {

                val sweetAlertDialog = SweetAlertDialog(this@ProjectSubmission, SweetAlertDialog.WARNING_TYPE)
                sweetAlertDialog.titleText = "Are you sure ?"
                sweetAlertDialog.confirmText = "Yes"
                sweetAlertDialog.setConfirmClickListener {
                    it.cancel()
                    val name = firstName.text.toString()
                    val lastname = lastName.text.toString()
                    val emailaddress = emailAddress.text.toString()
                    val githublink = githubProjectLink.text.toString()
                    val submitService = retrofitSubmitService
                    val detailsCall = submitService.submitTheProject(emailaddress, name, lastname, githublink)
                    detailsCall?.enqueue(object : Callback<Void?> {
                        override fun onFailure(call: Call<Void?>, t: Throwable) {

                            val sweetAlertDialog1 = SweetAlertDialog(this@ProjectSubmission,SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                            sweetAlertDialog1.setCustomImage(R.mipmap.failure)
                            sweetAlertDialog1.contentText = "Submission not Successful : ${t.message.toString()}"
                            sweetAlertDialog1.show()
                        }

                        override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                            if (response.isSuccessful) {
                                Log.i("SubmitActivity", "onResponse: $response : $call")
                                val sweetAlertDialog2 = SweetAlertDialog(
                                    this@ProjectSubmission,
                                    SweetAlertDialog.CUSTOM_IMAGE_TYPE
                                )
                                sweetAlertDialog2.setCustomImage(R.mipmap.success)
                                sweetAlertDialog2.contentText = "Submission Successful"
                                sweetAlertDialog2.show()

                            } else {
                                val sweetAlertDialog3 = SweetAlertDialog(
                                    this@ProjectSubmission , SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                                sweetAlertDialog3.setCustomImage(R.mipmap.failure)
                                sweetAlertDialog3.contentText =
                                    "Submission not Successful, Try again "
                                sweetAlertDialog3.show()
                            }
                        }

                    })
                }
                sweetAlertDialog.show()
            }

            else{
                val sweetAlert = SweetAlertDialog(this@ProjectSubmission)
                sweetAlert.titleText = "Input All Fields ! "
                sweetAlert.show()
            }
        })

    }


    fun showToast(message:String){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }
}