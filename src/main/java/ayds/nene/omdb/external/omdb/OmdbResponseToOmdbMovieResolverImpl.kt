package ayds.nene.omdb.external.omdb

import com.google.gson.Gson
import com.google.gson.JsonObject
import java.util.*

internal interface OmdbResponseToOmdbMovieResolver {

    fun getMovieFromExternalData(body: String?): OmdbMovieResponse
}

internal class OmdbResponseToOmdbMovieResolverImpl : OmdbResponseToOmdbMovieResolver {

    override fun getMovieFromExternalData(body: String?): OmdbMovieResponse {
        val gson = Gson()
        val jsonObject = gson.fromJson(body, JsonObject::class.java)
        val movie = OmdbMovieResponse()
        val response = jsonObject["Response"].asString
        if (response == "True") {
            movie.title = jsonObject["Title"].asString
            movie.year = jsonObject["Year"].asString
            movie.plot = jsonObject["Plot"].asString
            movie.director = jsonObject["Director"].asString
            movie.actors = jsonObject["Actors"].asString
            val posterUrl = jsonObject["Poster"].asString
            if (posterUrl.isPosterAvailable()) {
                movie.posterUrl = jsonObject["Poster"].asString
            }
            movie.ratings = getRatingsFromJson(jsonObject)
        }
        return movie
    }

    private fun getRatingsFromJson(jsonObject: JsonObject): ArrayList<RatingResponse> {
        val ratings = ArrayList<RatingResponse>()
        if (jsonObject["Ratings"] != null) {
            val ratingsArray = jsonObject["Ratings"].asJsonArray
            ratingsArray.forEach {
                val rating = RatingResponse()
                rating.source = it.asJsonObject["Source"].asString
                rating.value = it.asJsonObject["Value"].asString
                ratings.add(rating)
            }
        }
        return ratings
    }

    private fun String?.isPosterAvailable() = this != null && isNotEmpty() && this != "N/A"
}