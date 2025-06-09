package br.com.wcsm.gymtrack.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import br.com.wcsm.gymtrack.data.remote.dto.PostDto
import br.com.wcsm.gymtrack.data.remote.dto.toPost
import br.com.wcsm.gymtrack.domain.model.BaseResponse
import br.com.wcsm.gymtrack.domain.model.Post
import br.com.wcsm.gymtrack.presentation.ui.theme.GYMTrackTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import retrofit2.Response
import retrofit2.http.GET

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val jsonPlaceViewModel: JsonPlaceViewModel = koinViewModel()

            val posts by jsonPlaceViewModel.posts.collectAsStateWithLifecycle()

            GYMTrackTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "GYM Track",
                            modifier = Modifier.padding(innerPadding)
                        )

                        Button(onClick = jsonPlaceViewModel::getPosts) {
                            Text(text = "BUSCAR POSTS")
                        }

                        if(posts.isEmpty()) {
                            Text("Posts vazio.")
                        } else {
                            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                                items(
                                    items = posts,
                                    key = { it.id }
                                ) { post ->
                                    PostCard(post = post)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun PostCard(
        post: Post,
        modifier: Modifier = Modifier
    ) {
        Column(modifier = modifier.width(300.dp)) {
            Text(text = "Usuário: ${post.userId} - Post: ${post.id}")

            Text(text = post.title)

            Text(text = post.body)
        }
    }

    class JsonPlaceViewModel(
        private val jsonPlaceholderRepository: JsonPlaceholderRepository
    ): ViewModel() {
        private val _posts = MutableStateFlow<List<Post>>(emptyList())
        val posts = _posts.asStateFlow()

        fun getPosts() {
            viewModelScope.launch(Dispatchers.IO) {
                jsonPlaceholderRepository.getPosts().collect { result ->
                    when(result) {
                        is BaseResponse.Loading -> { println("+++ LOADING") }
                        is BaseResponse.Error -> { println("+++ ERROR: ${result.errorMessage}") }
                        is BaseResponse.Success -> {
                            _posts.value = result.data
                        }
                    }
                }
            }
        }
    }

    class JsonPlaceholderRepositoryImpl(
        private val jsonPlaceholder: JsonPlaceholder
    ) : JsonPlaceholderRepository {
        override suspend fun getPosts(): Flow<BaseResponse<List<Post>>> = flow {
          try {
              emit(BaseResponse.Loading)

              val response = jsonPlaceholder.getPosts()
              if(response.isSuccessful && response.body() != null) {
                  val responseBody = response.body()!!
                  emit(BaseResponse.Success(responseBody.map { it.toPost() }))
              }
          } catch (e: Exception) {
              e.printStackTrace()
              emit(BaseResponse.Error("Erro desconhecido, tente mais tarde."))
          }
        }
    }

    interface JsonPlaceholderRepository{
        suspend fun getPosts(): Flow<BaseResponse<List<Post>>>
    }

    interface JsonPlaceholder {
        @GET("posts")
        suspend fun getPosts(): Response<List<PostDto>>
    }
}