package porikuch.bottleswitch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class TalkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_talk)
        val nickname: String = intent.getStringExtra("FRIEND_NICKNAME")
        title = nickname
    }
}
