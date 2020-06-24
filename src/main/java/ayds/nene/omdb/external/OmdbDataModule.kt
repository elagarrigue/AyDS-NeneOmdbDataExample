package ayds.nene.omdb.external

import ayds.nene.omdb.external.omdb.OmdbAPI
import ayds.nene.omdb.external.omdb.OmdbResponseToOmdbMovieResolverImpl
import ayds.nene.omdb.external.omdb.OmdbService
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object OmdbDataModule {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://www.omdbapi.com/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    private fun getOmdbAPI(): OmdbAPI = retrofit.create(OmdbAPI::class.java)

    val omdbService: ExternalService = OmdbService(
        getOmdbAPI(),
        OmdbResponseToOmdbMovieResolverImpl()
    )
}