package com.rishabhsingh.asteroidneo.presenter

import com.rishabhsingh.asteroidneo.contracts.Contracts
import com.rishabhsingh.asteroidneo.model.MainActivityModel
import com.rishabhsingh.asteroidneo.ui.MainActivity

class MainActivityPresenter(v: MainActivity): Contracts.Presenter {

    private var view = v
    private var model: Contracts.Model = MainActivityModel()

    init {
        view.initView()
    }

    override fun getFastestAsteroid(): String {
        return ""
    }

    override fun getClosestAsteroid(): String {
        return ""
    }

    override fun getAverageSizeAsteroid(): String {
        return ""
    }
}