package br.edu.ifsp.scl.omdbfilmes.Model

object Constantes {

    /**Send all data requests to:
     * http://www.omdbapi.com/?apikey=[yourkey]&
     */

    val DEBUG = java.lang.Boolean.parseBoolean("true")
    val APPLICATION_ID = "br.edu.ifsp.scl.sdm.wikifilmes"
    val BUILD_TYPE = "debug"
    val FLAVOR = ""
    val VERSION_CODE = 1
    val VERSION_NAME = "1.0"
    const val URL_BASE: String = "http://www.omdbapi.com"
    val OMDB_API_KEY: String = "f976c781"
    val APP_KEY_FIELD = "apikey"
}