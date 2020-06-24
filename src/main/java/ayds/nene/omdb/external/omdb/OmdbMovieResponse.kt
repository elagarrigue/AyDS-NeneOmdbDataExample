package ayds.nene.omdb.external.omdb

import java.util.*

class OmdbMovieResponse {
    var title = ""
    var year = ""
    var plot = ""
    var director = ""
    var actors = ""
    var posterUrl = "https://i.picsum.photos/id/355/267/179.jpg"
    var ratings: List<RatingResponse> = ArrayList()
}