package ayds.nene.omdb.external

import ayds.nene.omdb.external.omdb.OmdbMovieResponse

interface ExternalService {

    fun getMovie(title: String): OmdbMovieResponse
}