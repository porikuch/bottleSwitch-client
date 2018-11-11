package porikuch.bottleswitch

import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class BottleManager(private val myId: Int) {
    private var bottleList: List<BottleSwitch>? = null
    private val createdBottleList: MutableList<BottleSwitch> = mutableListOf()
    private val receivedBottleList: MutableList<BottleSwitch> = mutableListOf()

    val moshi = Moshi.Builder().build()
    val bottleAdapter = moshi.adapter(BottleSwitch::class.java)
    val type = Types.newParameterizedType(List::class.java, BottleSwitch::class.java)
    val listAdapter: JsonAdapter<List<BottleSwitch>> = moshi.adapter(type)

    public fun requestBottles() {
        //-- TODO:request server
        //val result: String = "{\"bottles\": [{\"id\": 1,\"title\": \"hoge\", \"nickname\": \"ueyuki\"}, {\"id\": 3,\"title\": \"hogehogehoge\",\"nickname\": \"ishiireo\"}]}"
        val resultBottles: String = "[{\"id\":1,\"title\":\"hoge\",\"created_user_id\":1,\"nickname\":\"ueyuki\"},{\"id\":3,\"title\":\"hogehogehoge\",\"created_user_id\":2,\"nickname\":\"ishiireo\"}]"
        bottleList = listAdapter.fromJson(resultBottles)
        Log.d("bottle List", "$bottleList")

        separateBottles(bottleList)
        Log.d("created bottle List", "$createdBottleList")
        Log.d("received bottle List", "$receivedBottleList")
    }

    private fun separateBottles(bottleList: List<BottleSwitch>?){
        if (bottleList != null) {
            for (bottle in bottleList) {
                if (bottle.created_user_id == myId) {
                    this.createdBottleList.add(bottle)
                } else {
                    this.receivedBottleList.add(bottle)
                }
            }
        }
    }

    fun createBottle(id: Int, title: String, createdUserId: Int, nickname: String): BottleSwitch {
        return BottleSwitch(id, title, createdUserId, nickname)
    }

    fun getCreatedBottleListElemet(index: Int): BottleSwitch {
        return this.createdBottleList[index]
    }

    fun getCreatedBottleListSize(): Int {
        return this.createdBottleList.size
    }

    fun getReceivedBottleListElemet(index: Int): BottleSwitch {
        return this.receivedBottleList[index]
    }

    fun getReceivedBottleListSize(): Int {
        return this.receivedBottleList.size
    }
}