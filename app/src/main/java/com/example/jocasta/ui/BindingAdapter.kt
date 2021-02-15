package com.example.jocasta.ui

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jocasta.R
import com.example.jocasta.db.entity.ResourceType
import com.example.jocasta.ui.adapter.DetailsFilmsAdapter
import com.example.jocasta.ui.adapter.ResourceTypeGridAdapter
import com.example.jocasta.ui.detail.PeopleDetailViewModel
import com.example.jocasta.ui.resources.SWAPIStatus
import timber.log.Timber

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data : List<ResourceType>?){
    val adapter = recyclerView.adapter as ResourceTypeGridAdapter
    adapter.submitList(null)
    data?.let {
        adapter.submitList(it.toMutableList())
    }
}

@BindingAdapter(value = ["listFilmData", "vM"])
fun bindFilmRecyclerView(recyclerView: RecyclerView, data: List<String>?, viewModel : PeopleDetailViewModel){
    viewModel.getFilmDetails(data!!)
}

@BindingAdapter(value = ["listSpecies", "vM"], requireAll = true)
fun bindSpeciesRecyclerView(recyclerView: RecyclerView, data: List<String>?, viewModel: PeopleDetailViewModel){
    viewModel.getSpeciesDetails(data!!)
}

@BindingAdapter(value = ["listVehicles", "vM"], requireAll = true)
fun bindVehiclesRecyclerView(recyclerView: RecyclerView, data: List<String>?, viewModel: PeopleDetailViewModel){
    viewModel.getVehicles(data!!)
}

@BindingAdapter(value = ["listStarShips", "vM"], requireAll = true)
fun bindStarshipsRecyclerView(recyclerView: RecyclerView, data: List<String>?, viewModel: PeopleDetailViewModel){
    viewModel.getStarships(data!!)
}

@BindingAdapter("swApiStatus")
fun bindResourceTypeFetchStatus(statusImageView : ImageView, status : SWAPIStatus?){
    when(status) {
        SWAPIStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        SWAPIStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        SWAPIStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
