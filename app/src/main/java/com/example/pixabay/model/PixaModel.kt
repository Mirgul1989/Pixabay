package com.example.pixabay.model

data class PixaModel
    (
    var hits : List<ImageModel>?
)
data class ImageModel (
    var largeImageURL:String

)