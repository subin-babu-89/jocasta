package com.example.jocasta.ui

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.jocasta.R
import com.example.jocasta.db.entity.ResourceType
import com.example.jocasta.network.model.*
import com.example.jocasta.ui.adapter.*
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
    val adapter = recyclerView.adapter as DetailsFilmsAdapter
    adapter.submitList(listOf(Film(title = "Loading", name = "Loading")))
    viewModel.getFilmDetails(data!!)
}

@BindingAdapter(value = ["listSpecies", "vM"], requireAll = true)
fun bindSpeciesRecyclerView(recyclerView: RecyclerView, data: List<String>?, viewModel: PeopleDetailViewModel){
    val adapter = recyclerView.adapter as DetailsSpeciesAdapter
    adapter.submitList(listOf(Species(title = "Loading", name = "Loading")))
    viewModel.getSpeciesDetails(data!!)
}

@BindingAdapter(value = ["listVehicles", "vM"], requireAll = true)
fun bindVehiclesRecyclerView(recyclerView: RecyclerView, data: List<String>?, viewModel: PeopleDetailViewModel){
    val adapter = recyclerView.adapter as DetailsVehiclesAdapter
    adapter.submitList(listOf(Vehicle(title = "Loading", name = "Loading")))
    viewModel.getVehicles(data!!)
}

@BindingAdapter(value = ["listStarShips", "vM"], requireAll = true)
fun bindStarshipsRecyclerView(recyclerView: RecyclerView, data: List<String>?, viewModel: PeopleDetailViewModel){
    val adapter = recyclerView.adapter as DetailsStarshipAdapter
    adapter.submitList(listOf(Starship(title = "Loading", name = "Loading")))
    viewModel.getStarships(data!!)
}

@BindingAdapter(value = ["listPeopleOnPlanet", "vM"], requireAll = true)
fun bindingPeoplePlanetDetails(recyclerView: RecyclerView, data: List<String>?, viewModel: PlanetDetailViewModel){
    val adapter = recyclerView.adapter as DetailsPeopleAdapter
    adapter.submitList(listOf(People(title = "Loading", name = "Loading")))
    viewModel.getPeopleDetails(data!!)
}

@BindingAdapter(value = ["listFilmsOnPlanet", "vM"], requireAll = true)
fun bindFilmsOnPlanetDetails(recyclerView: RecyclerView, data: List<String>?, viewModel: PlanetDetailViewModel){
    val adapter = recyclerView.adapter as DetailsFilmsAdapter
    adapter.submitList(listOf(Film(title = "Loading", name = "Loading")))
    viewModel.getFilmDetails(data!!)
}

@BindingAdapter(value = ["listPeopleOnFilm", "vM"], requireAll = true)
fun bindPeopleOnFilmDetails(recyclerView: RecyclerView, data: List<String>?, viewModel : FilmDetailViewModel){
    val adapter = recyclerView.adapter as DetailsPeopleAdapter
    adapter.submitList(listOf(People(title = "Loading", name = "Loading")))
    viewModel.getPeopleDetails(data!!)
}

@BindingAdapter(value = ["listPlanetsOnFilm", "vM"], requireAll = true)
fun bindPlanetsOnFilmDetails(recyclerView: RecyclerView, data: List<String>?, viewModel : FilmDetailViewModel){
    val adapter = recyclerView.adapter as DetailsPlanetAdapter
    adapter.submitList(listOf(Planet(title = "Loading", name = "Loading")))
    viewModel.getPlanetDetails(data!!)
}

@BindingAdapter(value = ["listStarshipOnFilm", "vM"], requireAll = true)
fun bindStarshipsOnFilmDetails(recyclerView: RecyclerView, data: List<String>?, viewModel : FilmDetailViewModel){
    val adapter = recyclerView.adapter as DetailsStarshipAdapter
    adapter.submitList(listOf(Starship(title = "Loading", name = "Loading")))
    viewModel.getStarshipDetails(data!!)
}

@BindingAdapter(value = ["listVehiclesOnFilm", "vM"], requireAll = true)
fun bindVehiclesOnFilmDetails(recyclerView: RecyclerView, data: List<String>?, viewModel : FilmDetailViewModel){
    val adapter = recyclerView.adapter as DetailsVehiclesAdapter
    adapter.submitList(listOf(Vehicle(title = "Loading", name = "Loading")))
    viewModel.getVehicleDetails(data!!)
}

@BindingAdapter(value = ["listSpeciesOnFilm", "vM"], requireAll = true)
fun bindSpeciesOnFilmDetails(recyclerView: RecyclerView, data: List<String>?, viewModel : FilmDetailViewModel){
    val adapter = recyclerView.adapter as DetailsSpeciesAdapter
    adapter.submitList(listOf(Species(title = "Loading", name = "Loading")))
    viewModel.getSpeciesDetails(data!!)
}

@BindingAdapter(value = ["listPeopleOnSpecies", "vM"], requireAll = true)
fun bindPeopleOnSpeciesDetails(recyclerView: RecyclerView, data: List<String>?, viewModel : SpeciesDetailViewModel){
    val adapter = recyclerView.adapter as DetailsPeopleAdapter
    adapter.submitList(listOf(People(title = "Loading", name = "Loading")))
    viewModel.getPeopleDetails(data!!)
}

@BindingAdapter(value = ["listFilmsOnSpecies", "vM"], requireAll = true)
fun bindFilmsOnSpeciesDetails(recyclerView: RecyclerView, data: List<String>?, viewModel : SpeciesDetailViewModel){
    val adapter = recyclerView.adapter as DetailsFilmsAdapter
    adapter.submitList(listOf(Film(title = "Loading", name = "Loading")))
    viewModel.getFilmDetails(data!!)
}

@BindingAdapter(value = ["listPeopleOnVehicles", "vM"], requireAll = true)
fun bindPeopleOnVehicleDetails(recyclerView: RecyclerView, data: List<String>?, viewModel : VehicleDetailViewModel){
    val adapter = recyclerView.adapter as DetailsPeopleAdapter
    adapter.submitList(listOf(People(title = "Loading", name = "Loading")))
    viewModel.getPeopleDetails(data!!)
}

@BindingAdapter(value = ["listFilmsOnVehicles", "vM"], requireAll = true)
fun bindFilmsOnVehicleDetails(recyclerView: RecyclerView, data: List<String>?, viewModel : VehicleDetailViewModel){
    val adapter = recyclerView.adapter as DetailsFilmsAdapter
    adapter.submitList(listOf(Film(title = "Loading", name = "Loading")))
    viewModel.getFilmDetails(data!!)
}

@BindingAdapter(value = ["listPeopleOnStarship", "vM"], requireAll = true)
fun bindPeopleOnStarshipDetails(recyclerView: RecyclerView, data: List<String>?, viewModel : StarshipDetailViewModel){
    val adapter = recyclerView.adapter as DetailsPeopleAdapter
    adapter.submitList(listOf(People(title = "Loading", name = "Loading")))
    viewModel.getPeopleDetails(data!!)
}

@BindingAdapter(value = ["listFilmsOnStarships", "vM"], requireAll = true)
fun bindFilmsOnStarshipDetails(recyclerView: RecyclerView, data: List<String>?, viewModel : StarshipDetailViewModel){
    val adapter = recyclerView.adapter as DetailsFilmsAdapter
    adapter.submitList(listOf(Film(title = "Loading", name = "Loading")))
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

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.loading_img))
            .into(imgView)
    }
}
