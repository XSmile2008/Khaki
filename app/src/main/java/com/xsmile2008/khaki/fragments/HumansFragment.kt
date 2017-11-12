package com.xsmile2008.khaki.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xsmile2008.khaki.AppClass
import com.xsmile2008.khaki.R
import com.xsmile2008.khaki.activities.HumanDetailsActivity
import com.xsmile2008.khaki.adapters.HumansAdapter
import com.xsmile2008.khaki.consts.HUMAN_ID
import com.xsmile2008.khaki.entities.Human
import com.xsmile2008.khaki.interfaces.OnItemPositionRemoved
import com.xsmile2008.khaki.interfaces.OnItemPositionSelected
import com.xsmile2008.khaki.view_model.HumansViewModel
import kotlinx.android.synthetic.main.fragment_humans.*

/**
 * Created by vladstarikov on 10/21/17.
 */
class HumansFragment : Fragment() {

    private lateinit var viewModel: HumansViewModel

    private val adapter = HumansAdapter(
            onItemPositionSelected = object : OnItemPositionSelected<Human> {
                override fun onSelected(item: Human, pos: Int) {
                    startActivity(Intent(context, HumanDetailsActivity::class.java).putExtra(HUMAN_ID, item.id))
                }
            },
            onItemPositionRemoved = object : OnItemPositionRemoved<Human> {
                override fun onRemoved(item: Human, pos: Int) {
                    viewModel.deleteHuman(item)
                }
            }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppClass.component.inject(this)
        viewModel = ViewModelProviders.of(this).get(HumansViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater?.inflate(R.layout.fragment_humans, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(context)
        list.addItemDecoration(DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL).apply { setDrawable(context.getDrawable(R.drawable.divider_empty_8dp)) }
        )

        viewModel.getHumans().observe(this, Observer<List<Human>>(adapter::setItems))
    }
}