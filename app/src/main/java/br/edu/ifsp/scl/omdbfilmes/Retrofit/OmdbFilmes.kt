package br.edu.ifsp.scl.omdbfilmes.Retrofit

import br.edu.ifsp.scl.omdbfilmes.MainActivity
import br.edu.ifsp.scl.omdbfilmes.Model.Constantes
import br.edu.ifsp.scl.omdbfilmes.Model.Constantes.APP_KEY_FIELD
import br.edu.ifsp.scl.omdbfilmes.Model.Constantes.OMDB_API_KEY
import br.edu.ifsp.scl.omdbfilmes.Model.Constantes.URL_BASE
import br.edu.ifsp.scl.omdbfilmes.Model.Teste
import com.google.gson.Gson
import kotlinx.android.synthetic.main.frame_main.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import org.jetbrains.anko.design.snackbar
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OmdbFilmes(val mainActivity: MainActivity) {

    // Cria um objeto Retrofit usando a URL base.
    //    val retrofit: Retrofit =Retrofit Retrofit.Builder().baseUrl(URL_BASE).build()

    val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

    // Instanciando o cliente HTTP
    init {
        // Adiciona um interceptador que é um objeto de uma classe anônima
        okHttpClientBuilder.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                // Resgatando requisição interceptada
                val reqInterceptada: Request = chain.request()
                // Criando nova requisição a partir da interceptada e adicionando campos de cabeçalho
                val url = reqInterceptada.url().newBuilder().addQueryParameter(APP_KEY_FIELD, OMDB_API_KEY).build()
                val novaReq: Request = reqInterceptada.newBuilder()
                    .url(url)
                    .method(reqInterceptada.method(), reqInterceptada.body())
                    .build()
                // retornando a nova requisição preenchdia
                return chain.proceed(novaReq)
            }
        })
    }

    // Novo objeto Retrofit usando a URL base e o HttpClient com interceptador
    val retrofit: Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(URL_BASE).client(okHttpClientBuilder.build()).build()


    // Cria um objeto, a partir da Interface Retrofit, que contém as funções de requisição
    val omdbFilmesApi: OmdbFilmesApi = retrofit.create(OmdbFilmesApi::class.java)

    fun pesquisarFilme(titulo: String) {
        /*Chama a função de requisição definida na Interface passando os parâmetros escolhidos pelo usuário e
        enfileira a requisição que recebe um objeto de uma implementação anônima de Callback<ResponseBody>*/


        omdbFilmesApi.getPopularFilme(titulo).enqueue(
            object: Callback<Teste> {
                override fun onFailure(call: Call<Teste>, t: Throwable) {
                   mainActivity.mainLl.snackbar("Erro: " + t.message);
                }

                override fun onResponse(call: Call<Teste>, response: Response<Teste>) {
                    print(response.body())
                }

            } // Fim da classe anônima
        ) // Fim dos parâmetros de enqueue
    } // Fim da função traduzir


}

