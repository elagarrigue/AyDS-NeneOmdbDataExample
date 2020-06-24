package ayds.nene.omdb.external.omdb

import ayds.nene.omdb.external.ExternalService
import retrofit2.Response

internal class OmdbService(
    private val omdbAPI: OmdbAPI,
    private val omdbMovieResolver: OmdbResponseToOmdbMovieResolver
) : ExternalService {

    override fun getMovie(title: String): OmdbMovieResponse {
        val callResponse = getOmdbMovieFromService(title)
        return omdbMovieResolver.getMovieFromExternalData(callResponse.body())
    }

    private fun getOmdbMovieFromService(title: String): Response<String> {
        return omdbAPI.getTerm(title).execute()
    }

}