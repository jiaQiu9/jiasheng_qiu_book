package com.example.jiasheng_qiu_book.network

import com.squareup.moshi.Json

data class BookResponse(
    @Json(name = "items") val items: List<BookItem>?
)

data class BookItem(
    @Json(name="id") val id: String?,
    @Json(name = "volumeInfo") val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    @Json(name = "title") val title: String,
    @Json(name = "authors") val authors: List<String>?,
    @Json(name = "imageLinks") val imageLinks: ImageLinks?,
    @Json(name = "description") val description: String?
)



data class ImageLinks(
    @Json(name = "thumbnail") val thumbnail: String?,
    @Json(name = "smallThumbnail") val smallThumbnail: String?,
)