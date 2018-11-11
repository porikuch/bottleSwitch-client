package porikuch.bottleswitch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val bottleId: Int = intent.getIntExtra("Bottle_ID", 0)
        // TODO request Bottle Data
        val title: String = "This is title"
        val body: String = "This is body.\nHello World."
        titleTextView.text = title
        bodyTextView.text = body
    }
}
