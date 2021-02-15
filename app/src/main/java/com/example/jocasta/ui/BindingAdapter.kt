package com.example.jocasta.ui

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.jocasta.R
import com.example.jocasta.db.entity.ResourceType
import com.example.jocasta.ui.adapter.DetailsFilmsAdapter
import com.example.jocasta.ui.adapter.ResourceTypeGridAdapter
import com.example.jocasta.ui.detail.*
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

@BindingAdapter(value = ["listPeopleOnPlanet", "vM"], requireAll = true)
fun bindingPeoplePlanetDetails(recyclerView: RecyclerView, data: List<String>?, viewModel: PlanetDetailViewModel){
    viewModel.getPeopleDetails(data!!)
}

@BindingAdapter(value = ["listFilmsOnPlanet", "vM"], requireAll = true)
fun bindFilmsOnPlanetDetails(recyclerView: RecyclerView, data: List<String>?, viewModel: PlanetDetailViewModel){
    viewModel.getFilmDetails(data!!)
}

@BindingAdapter(value = ["listPeopleOnFilm", "vM"], requireAll = true)
fun bindPeopleOnFilmDetails(recyclerView: RecyclerView, data: List<String>?, viewModel : FilmDetailViewModel){
    viewModel.getPeopleDetails(data!!)
}

@BindingAdapter(value = ["listPlanetsOnFilm", "vM"], requireAll = true)
fun bindPlanetsOnFilmDetails(recyclerView: RecyclerView, data: List<String>?, viewModel : FilmDetailViewModel){
    viewModel.getPlanetDetails(data!!)
}

@BindingAdapter(value = ["listStarshipOnFilm", "vM"], requireAll = true)
fun bindStarshipsOnFilmDetails(recyclerView: RecyclerView, data: List<String>?, viewModel : FilmDetailViewModel){
    viewModel.getStarshipDetails(data!!)
}

@BindingAdapter(value = ["listVehiclesOnFilm", "vM"], requireAll = true)
fun bindVehiclesOnFilmDetails(recyclerView: RecyclerView, data: List<String>?, viewModel : FilmDetailViewModel){
    viewModel.getVehicleDetails(data!!)
}

@BindingAdapter(value = ["listSpeciesOnFilm", "vM"], requireAll = true)
fun bindSpeciesOnFilmDetails(recyclerView: RecyclerView, data: List<String>?, viewModel : FilmDetailViewModel){
    viewModel.getSpeciesDetails(data!!)
}

@BindingAdapter(value = ["listPeopleOnSpecies", "vM"], requireAll = true)
fun bindPeopleOnSpeciesDetails(recyclerView: RecyclerView, data: List<String>?, viewModel : SpeciesDetailViewModel){
    viewModel.getPeopleDetails(data!!)
}

@BindingAdapter(value = ["listFilmsOnSpecies", "vM"], requireAll = true)
fun bindFilmsOnSpeciesDetails(recyclerView: RecyclerView, data: List<String>?, viewModel : SpeciesDetailViewModel){
    viewModel.getFilmDetails(data!!)
}

@BindingAdapter(value = ["listPeopleOnVehicles", "vM"], requireAll = true)
fun bindPeopleOnVehicleDetails(recyclerView: RecyclerView, data: List<String>?, viewModel : VehicleDetailViewModel){
    viewModel.getPeopleDetails(data!!)
}

@BindingAdapter(value = ["listFilmsOnVehicles", "vM"], requireAll = true)
fun bindFilmsOnVehicleDetails(recyclerView: RecyclerView, data: List<String>?, viewModel : VehicleDetailViewModel){
    viewModel.getFilmDetails(data!!)
}

@BindingAdapter(value = ["listPeopleOnStarship", "vM"], requireAll = true)
fun bindPeopleOnStarshipDetails(recyclerView: RecyclerView, data: List<String>?, viewModel : StarshipDetailViewModel){
    viewModel.getPeopleDetails(data!!)
}

@BindingAdapter(value = ["listFilmsOnStarships", "vM"], requireAll = true)
fun bindFilmsOnStarshipDetails(recyclerView: RecyclerView, data: List<String>?, viewModel : StarshipDetailViewModel){
    viewModel.getFilmDetails(data!!)
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
