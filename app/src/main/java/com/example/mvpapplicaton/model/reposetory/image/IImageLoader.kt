package com.example.mvpapplicaton.model.reposetory.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}