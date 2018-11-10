package porikuch.bottleswitch

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    val myId: Int = intent.getIntExtra("USER_ID", 0)

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                //message.setText(R.string.title_home)
                title = "Created Bottles"
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                //message.setText(R.string.title_dashboard)
                title = "Received Bottles"
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                //message.setText(R.string.title_notifications)
                title = "Message Switch"
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        //setSupportActionBar(toolbar)
        title = "Created Bottles"

        layoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager

        adapter = RecyclerAdapter()
        recycler_view.adapter = adapter


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}