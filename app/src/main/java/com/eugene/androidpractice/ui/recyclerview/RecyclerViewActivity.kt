package com.eugene.androidpractice.ui.recyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.eugene.androidpractice.AppExecutors
import com.eugene.androidpractice.R
import com.eugene.androidpractice.data.model.SimpleModel
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_recycler_view.*
import timber.log.Timber
import javax.inject.Inject

class RecyclerViewActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var appExecutors: AppExecutors

    private lateinit var items: MutableList<SimpleModel>

    private lateinit var adapter: SimpleModelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = SimpleModelAdapter(appExecutors) {
            Timber.d("Item ${it.id} clicked")
        }
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.END) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                val fromPos = viewHolder.adapterPosition
                val toPos = target.adapterPosition
                val selectedItem = items[fromPos]
                items.removeAt(fromPos)
                items.add(toPos, selectedItem)
                adapter.notifyItemMoved(fromPos, toPos)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                items.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
        })
        rv.adapter = adapter
        itemTouchHelper.attachToRecyclerView(rv)
        items = ArrayList<SimpleModel>().apply {
            for (i in 1..15)
                add(SimpleModel(i, "Item $i"))
        }
        adapter.submitList(items)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector
}
