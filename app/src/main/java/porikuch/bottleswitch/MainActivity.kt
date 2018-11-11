package porikuch.bottleswitch

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var createdBottlesAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    private var receivedBottlesAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    private var bottleManager: BottleManager? = null
    private var myId: Int = 0

    private val mOnNavigationItemSelectedListener = OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                //message.setText(R.string.title_home)
                title = "Created Bottles"
                recycler_view.adapter = this.createdBottlesAdapter
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                //message.setText(R.string.title_dashboard)
                title = "Received Bottles"
                recycler_view.adapter = this.receivedBottlesAdapter
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
        myId = intent.getIntExtra("USER_ID", 0)
        title = "Created Bottles"

        bottleManager = BottleManager(myId)
        bottleManager?.requestBottles()

        layoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager

        createdBottlesAdapter = RecyclerAdapter(object : RecyclerAdapter.ListListener {
            override fun onClickRow(tappedView: View, bottleSwitch: BottleSwitch) {
                this@MainActivity.onClickRow(tappedView, bottleSwitch)
            }
        })
        // set data
        for (i in 0 until bottleManager!!.getCreatedBottleListSize()) {
            (createdBottlesAdapter as RecyclerAdapter).bottleListAdd(bottleManager!!.getCreatedBottleListElemet(i))
        }

        receivedBottlesAdapter = RecyclerAdapter(object : RecyclerAdapter.ListListener {
            override fun onClickRow(tappedView: View, bottleSwitch: BottleSwitch) {
                this@MainActivity.onClickRow(tappedView, bottleSwitch)
            }
        })
        for (i in 0 until bottleManager!!.getReceivedBottleListSize()) {
            (receivedBottlesAdapter as RecyclerAdapter).bottleListAdd(bottleManager!!.getReceivedBottleListElemet(i))
        }

        recycler_view.adapter = createdBottlesAdapter

        fab.setOnClickListener { _ ->
            val intent = Intent(this@MainActivity, EditDetailActivity::class.java)
            intent.putExtra("USER_ID", myId)
            startActivity(intent)
        }

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    fun onClickRow(tappedView: View, bottleSwitch: BottleSwitch) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra("Bottle_ID", bottleSwitch.id)
        intent.putExtra("CREATED_ID", bottleSwitch.created_user_id)
        intent.putExtra("USER_ID", myId)
        startActivity(intent)
    }
}