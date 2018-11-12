package porikuch.bottleswitch

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpPost
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private var mFloatBottleTask: BottleFloatTask? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val bottleId: Int = intent.getIntExtra("BOTTLE_ID", 0)
        val myId: Int = intent.getIntExtra("USER_ID", 0)
        val createdUserId: Int = intent.getIntExtra("CREATED_ID", 0)

        // TODO request Bottle Data
        val title: String = "This is title"
        val body: String = "This is body.\nHello World."
        titleTextView.text = title
        bodyTextView.text = body

        if (myId != 0 && myId == createdUserId) {
            floatButton.text = "Float Bottle"
            getSwitchButton.visibility = View.GONE
        } else {
            floatButton.text = "Float Again"
            getSwitchButton.setOnClickListener { _ ->
                val intent = Intent(applicationContext, GotSwitchActivity::class.java)
                intent.putExtra("BOTTLE_ID", bottleId)
                intent.putExtra("CREATED_ID", createdUserId)
                startActivity(intent)
                finish()
            }
        }

        floatButton.setOnClickListener { _ ->
            // TODO request server
            mFloatBottleTask = BottleFloatTask(bottleId)
            mFloatBottleTask!!.execute(null as Void?)

        }
    }

    inner class BottleFloatTask internal constructor(private val bottleId: Int): AsyncTask<Void, Void, Boolean>() {
        var status = false
        override fun doInBackground(vararg param: Void): Boolean? {
            val (request, response, result) = "http://$SERVER_ADDRESS/release_bottle".httpPost(listOf("id" to bottleId)).responseJson()
            result.fold(success = { json ->
                status = json.obj()["status"].toString().toBoolean()
                if (status) {
                    finish()
                }
            }, failure = { error ->
                Log.e("error", error.toString())
            })

            return status
        }

        override fun onPostExecute(success: Boolean?) {
            mFloatBottleTask = null
            //showProgress(false)

            if (success!!) {
                finish()
            } else {
                //password.error = getString(R.string.error_incorrect_password)
                //password.requestFocus()
            }
        }

        override fun onCancelled() {
            mFloatBottleTask = null
            //showProgress(false)
        }
    }
}
