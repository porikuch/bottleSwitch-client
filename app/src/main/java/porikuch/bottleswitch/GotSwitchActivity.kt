package porikuch.bottleswitch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_got_switch.*

class GotSwitchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_got_switch)

        okButton.setOnClickListener { _ ->
            //val intent = Intent(this@DetailActivity, GotSwitchActivity::class.java)
            //intent.putExtra("BOTTLE_ID", bottleId)
            //intent.putExtra("CREATED_ID", createdUserId)
            //startActivity(intent)
            finish()
        }
    }
}
