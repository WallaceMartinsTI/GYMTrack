package br.com.wcsm.gymtrack.di

import br.com.wcsm.gymtrack.data.remote.repository.AuthRepositoryImpl
import br.com.wcsm.gymtrack.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

object FirebaseKoinModule : KoinModule {
    override val module = module {
        single { FirebaseAuth.getInstance() }
        single { FirebaseFirestore.getInstance() }
        single { FirebaseStorage.getInstance() }

        singleOf(::AuthRepositoryImpl) bind AuthRepository::class
    }
}