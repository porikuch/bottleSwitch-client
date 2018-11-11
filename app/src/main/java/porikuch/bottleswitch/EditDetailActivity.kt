package porikuch.bottleswitch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class EditDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_detail)
        val bottleId: Int = intent.getIntExtra("Bottle_ID", 0)
        Log.d("Bottle_ID", bottleId.toString())
    }
}
