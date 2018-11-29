package com.github.ludoviccarlu.pokemon.presentation.detail

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.github.ludoviccarlu.pokemon.data.pojo.RestPokemonDetail
import com.github.ludoviccarlu.pokemon.data.repository.PokemonRepository
import com.github.ludoviccarlu.pokemon.di.PokemonApplication
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class DetailViewModel : ViewModel(), LifecycleObserver {

    @Inject
    lateinit var pokemonRepository: PokemonRepository

    private val compositeDisposable = CompositeDisposable()

    //TODO Changer liveDataChampion to liveDataPokemonDetail
    val liveDataPokemonDetail: MutableLiveData<RestPokemonDetail> = MutableLiveData()

    init {
        initializeDagger()
    }

    fun onStart(pokemonId : Int){
        val disposable = pokemonRepository.getPokemonNameById(pokemonId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    pokemonDetail -> liveDataPokemonDetail.value = pokemonDetail
                }
        compositeDisposable.add(disposable)
                }

    private fun initializeDagger() = PokemonApplication.appComponent.inject(this)

}